package com.tsui.p2p.clientGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.tsui.p2p.datagram.CONST;
import com.tsui.p2p.datagram.DataGram;

public class ChatGUI extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	
	private JLabel from;
	private JLabel user1;
	private JLabel to;
	private JLabel user2;
	
	private JTextArea echoMsg;
	private JScrollPane echoJsp; 
	private JTextArea inMsg;
	private JScrollPane inJsp;
	private JButton cancel;
	private JButton send;
	
	private DataGram myself;
	private DataGram chat;
	
	private DatagramSocket udpServer;
	private DatagramSocket udpClient;
	private byte[] sendBuffer;
	private byte[] recvBuffer;
	
	private Map<String, Queue> msgMap;
	
	public ChatGUI(Map<String, Queue> msgMap, DatagramSocket udpServer, DataGram myself, DataGram chat) throws SocketException{
		//the whole config
		super("Chat Panel");
		setLayout(new BorderLayout());
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		
		//Initialize of UDP Server
		this.myself = myself;
		this.chat = chat;
		this.udpServer = udpServer;
		this.msgMap = msgMap;
		//this.udpServer = udpServer;
		System.out.println("my port" + myself.getPort());
		udpClient = new DatagramSocket(chat.getPort()+1);
		System.out.println("his port" + chat.getPort()+1);
		sendBuffer = new byte[CONST.MAXBUFF];
		recvBuffer = new byte[CONST.MAXBUFF];
		
		//Initialize of GUI
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		from = new JLabel("From me:");
		user1 = new JLabel(myself.getUsername());
		to = new JLabel("to");
		user2 = new JLabel(chat.getUsername());
		
		echoMsg = new JTextArea(8, 40);
		echoMsg.setEditable(false);
		echoMsg.setLineWrap(true);
		inMsg = new JTextArea(8, 40);
		inMsg.setEditable(true);
		inMsg.setLineWrap(true);
		echoJsp = new JScrollPane(echoMsg);
		inJsp = new JScrollPane(inMsg);
		cancel = new JButton("CANCEL");
		send = new JButton("send");
		
		//add
		add(p1, BorderLayout.NORTH);
		p1.setLayout(new GridLayout(1,4));
		p1.add(from);
		p1.add(user1);
		p1.add(to);
		p1.add(user2);
		
		add(p2, BorderLayout.CENTER);
		p2.setLayout(new BorderLayout());
		p2.add(echoJsp, BorderLayout.NORTH);
		p2.add(inJsp, BorderLayout.CENTER);
		
		add(p3, BorderLayout.SOUTH);
		p3.setLayout(new GridLayout(1,2));
		p3.add(cancel);
		p3.add(send);
		
		String name = chat.getUsername();
		String ip = chat.getIp();
		int port = chat.getPort();
		System.out.println("Name:" + name + "ip" + ip + "port" + port);
		
		//Msg Queue
		if(msgMap!= null && msgMap.containsKey(chat.getUsername()))
		{
			Iterator<String> i = msgMap.get(chat.getUsername()).iterator();
			if(!msgMap.isEmpty())
			{
				while(i.hasNext())
				{
					String ii = i.next();
					echoMsg.append(ii.trim() + "\r\n");
				}
			}
		}
		else
		{
			System.out.println("MSGMAP NULL!!!!");
		}
	
		//add action listener
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				sendAction(e);
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				quitAction(e);
			}
		});
	}
	
	public void sendAction(ActionEvent e)
	{
		sendMsg();
	}
	
	public void quitAction(ActionEvent e)
	{
		udpClient.close();
		this.dispose();
	}
	
	public void sendMsg()
	{
		InetAddress address;
		try {
			SimpleDateFormat bartDateFormat =new SimpleDateFormat("h:mm a");        
			Date date = new Date();
			String dateS = bartDateFormat.format(date).toString();
			String head = new String("From |" + myself.getUsername() + "| to |" + chat.getUsername() + "| " + dateS + ":\r\n" + inMsg.getText().trim());
			sendBuffer = head.getBytes();
			echoMsg.append(head + "\r\n");
			byte[] ipb = new byte[4]; 
			String[] iphostArr = chat.getIp().split("\\/");
			String[] ipArr = iphostArr[1].split("\\.");
			System.out.println(ipArr[0]);
			ipb[0] = (byte)(Integer.parseInt(ipArr[0]) & 0xFF);
			ipb[1] = (byte)(Integer.parseInt(ipArr[1]) & 0xFF);
			ipb[2] = (byte)(Integer.parseInt(ipArr[2]) & 0xFF);
			ipb[3] = (byte)(Integer.parseInt(ipArr[3]) & 0xFF);
			System.out.println(ipArr[1]);
			address = InetAddress.getByAddress(ipb);
			DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, address, chat.getPort());
			udpClient.send(packet);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean recvMsg()
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
		while(true)
		{
			if(recvMsg())
			{
				String inmsg = new String(recvBuffer,0,recvBuffer.length);
				System.out.println("truetruetruetruetrue:" + inmsg);
				echoMsg.append(inmsg.trim() + "\r\n");
			}
		}
	}


}
