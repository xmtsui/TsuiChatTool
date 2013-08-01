/**
 * @Projectname P2P ChatTool
 * @FileName RegisterGUI.java
 * @Author Tsui
 * @Description Graphical User Interface for Register
 */
package com.tsui.p2p.clientGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import com.tsui.p2p.datagram.CONST;
import com.tsui.p2p.datagram.ChatDGFactory;
import com.tsui.p2p.datagram.DataGram;
import com.tsui.p2p.datagram.IDataGramFactory;
import com.tsui.p2p.datagram.OnlineDGFactory;
import com.tsui.p2p.db.DBOperater;
import com.tsui.p2p.db.PeerList;

public class OnlineGUI extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p1;
	private JPanel p2;
	
	private JButton logout;
	
	private JLabel[] name;
	private JButton[] chatButton;
	private JLabel[] newmsg;
	
	private DataGram[] dgl;
	private Socket socket;
	private PeerList pl;
	//private int size;
	private DataGram myself;
	
	private DatagramSocket udpServer;
	private byte[] recvBuffer;
	private Map<String, Queue> msgMap;
	private Queue msgQueue;
	
	private ChatGUI chatGUI;
	
	//Constructor 
	public OnlineGUI(DataGram dg) throws UnknownHostException, IOException
	{
		super("Online Panel");
		//socket = new Socket(CONST.SERVER_IP, CONST.SERVER_PORT);
		
		//the Whole config
		setLayout(new BorderLayout());
		setSize(320,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		
		//Initialize
		chatButton = new JButton[CONST.MAXUSER];
		name = new JLabel[CONST.MAXUSER];
		newmsg = new JLabel[CONST.MAXUSER];
		dgl = new DataGram[CONST.MAXUSER];
		p2 = new JPanel();
		myself = dg;
		udpServer = new DatagramSocket(myself.getPort());
		recvBuffer = new byte[CONST.MAXBUFF];
		msgMap = new HashMap<String, Queue>();
		msgQueue = new ConcurrentLinkedQueue<String>();
		chatGUI = null;
		//p1 config
		int size = onOpeningSend();
		update();
		
		add(p2, BorderLayout.SOUTH);
		p2.setLayout(new GridLayout(1, 2));
		logout = new JButton("logout");
		p2.add(logout);
		
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				quitAction(e);
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			public void windowOpened(WindowEvent e)
			{
				//JOptionPane.showMessageDialog(null, "hehe");
				
			}
		}
		);
	}
	
	//Action
	
	/**
	 * quitAction QUIT Button
	 * 
	 * @param e
	 *            ActionEvent
	 * @return void
	 */
	public void quitAction(ActionEvent e)
	{
		udpServer.close();
		System.exit(0);
	}
	
	public int onOpeningSend() throws UnknownHostException, IOException
	{
		socket = new Socket(CONST.SERVER_IP, CONST.SERVER_PORT);
		IDataGramFactory dgfac = new OnlineDGFactory();
		DataGram onlinedg = dgfac.CreateDataGram();
		onlinedg.SendDatagram(socket);
		onlinedg.ReceiveDatagram(socket);
		pl = new PeerList();
		pl = onlinedg.getAllPeer();
		int size = pl.getReglist().size();
		//int size = pl.getReglist().size();
		if(pl!=null)
		{
			int i = 0;
			Iterator<DataGram> dgI = pl.getReglist().iterator();
			while(dgI.hasNext() && i<CONST.MAXUSER)
			{
				dgl[i] = dgI.next();
				i++;
			}
		}
		socket.close();
		return size;
	}
	
	public void update() throws UnknownHostException, IOException
	{
		int size = onOpeningSend();
		//JPanel p1 = new JPanel();
		p1 = new JPanel();
		System.out.println("Jpanel 1 size: " + size + dgl.length);
		p1.setLayout(new GridLayout(size+1,3));
		add(p1, BorderLayout.NORTH);
	
		p1.add(new JLabel("Name"));
		p1.add(new JLabel("            Chat"));
		p1.add(new JLabel("           New Msg"));
		for(int i=0; i<size; i++)
		{
			if(dgl[i].getUsername().equals(myself.getUsername()))
			{
				name[i] = new JLabel(dgl[i].getUsername());
				p1.add(name[i]);
				p1.add(new JLabel(""));
				p1.add(new JLabel(""));
			}
			else
			{
				name[i] = new JLabel(dgl[i].getUsername());
				chatButton[i] = new JButton("to Chat");
				newmsg[i] = new JLabel("");
				p1.add(name[i]);
				p1.add(chatButton[i]);
				p1.add(newmsg[i]);
				final int index=i;
				chatButton[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							chatAction(e, index);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}
		
		if(!this.isQueueEmpty())
		{
			int j=0;
			while(j<CONST.MAXUSER)
			{
				
				if(name[j] == null)
				{
					//System.out.println("name i  NULL@@@");
					j++;
					continue;
				}
				if(msgMap.containsKey(name[j].getText()))
				{
					System.out.println("New MSG yes!!!");
					newmsg[j].setText("New MSG Coming!");
					//this.invalidate();
					//RepaintManager.currentManager(this).addInvalidComponent(p1);  
				}
				else
				{
					System.out.println("New MSG wrong!!!");
				}
				j++;
			}
		}
		this.invalidate();
		RepaintManager.currentManager(this).addInvalidComponent(p1);  
	}
	
	public void chatAction(ActionEvent e, int i) throws IOException
	{
		socket = new Socket(CONST.SERVER_IP, CONST.SERVER_PORT);
		IDataGramFactory dgfac = new ChatDGFactory();
		DataGram dg = dgfac.CreateDataGram();
		
		dg.setUsername(name[i].getText());
		System.out.println("Chat with" + dg.getUsername());
		dg.SendDatagram(socket);
		dg.ReceiveDatagram(socket);
		PeerList chatList = dg.getAllPeer();
		//DBOperater.findReg(dg, chatList);
		
		//ChatGUI gui = new ChatGUI(msgMap, udpServer, myself, DBOperater.findReg(dg, chatList));
		chatGUI = new ChatGUI(msgMap, udpServer, myself, DBOperater.findReg(dg, chatList));
		System.out.println("Myself port:" + myself.getPort());
		socket.close();
	}

	public boolean recvMsg(byte[] recvBuffer)
	{
		DatagramPacket packet = new DatagramPacket(recvBuffer, CONST.MAXBUFF);
		try {
			if(!udpServer.isClosed())
			{
				udpServer.receive(packet);
				return true;
			}
			else
				return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(chatGUI==null)
		{
			System.out.println("No chat GUI now!");
			while(true)
			{
				String[] inmsgArr = null;
				if(recvMsg(recvBuffer))
				{
					String inmsg = new String(recvBuffer,0,recvBuffer.length);
					inmsgArr = inmsg.split("\\|");
					System.out.println("inmsgArr:" + inmsgArr[1]);
					msgQueue.offer(inmsg.trim());
					msgMap.put(inmsgArr[1].trim(), msgQueue);
				}
				if(chatGUI != null)
					break;
			}
			System.out.println("Open chat GUI now!");
			Thread tt = new Thread(chatGUI);
			tt.start();
			chatGUI.setVisible(true);
		}
		else
		{
			System.out.println("Open chat GUI now!");
			Thread tt = new Thread(chatGUI);
			tt.start();
			chatGUI.setVisible(true);
		}
	}
	
	public boolean isQueueEmpty()
	{
		if(msgQueue.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void newmsg()
	{

	}
}
