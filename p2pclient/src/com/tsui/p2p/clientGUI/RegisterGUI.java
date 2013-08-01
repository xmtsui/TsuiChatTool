/**
 * @Projectname P2P ChatTool
 * @FileName RegisterGUI.java
 * @Author Tsui
 * @Description Graphical User Interface for Register
 */
package com.tsui.p2p.clientGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tsui.p2p.datagram.CONST;
import com.tsui.p2p.datagram.DataGram;
import com.tsui.p2p.datagram.IDataGramFactory;
import com.tsui.p2p.datagram.RegisterDGFactory;

public class RegisterGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p1;
	private JPanel p2;
	
	private JLabel lusername;
	private JTextField username;
	private JLabel lpassword;
	private JPasswordField password;
	private JLabel linfo;
	private JTextArea info;
	
	private JButton confirm;
	private JButton reset;
	private JButton quit;
	
	private LoginGUI logingui;
	
	private Socket socket;
	
	//Constructor 
	public RegisterGUI(LoginGUI gui)
	{
		super("Register Panel");
		
		this.logingui = gui;
		//the Whole config
		setLayout(new BorderLayout());
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		
		p1 = new JPanel();
		add(p1, BorderLayout.NORTH);
		
		p1.setLayout(new GridLayout(3,1));
		lusername = new JLabel("User Name: ");
		lpassword = new JLabel("Password(6 chars or digits):");
		username = new JTextField(10);
		password = new JPasswordField(10);
		linfo = new JLabel("Basic Info: ");
		info = new JTextArea(2,2);
		p1.add(lusername);
		p1.add(username);
		p1.add(lpassword);
		p1.add(password);
		p1.add(linfo);
		p1.add(info);
		
		p2 = new JPanel();
		add(p2, BorderLayout.SOUTH);
		p2.setLayout(new GridLayout(1,3));
		confirm = new JButton("Confirm");
		reset = new JButton("Reset");
		quit = new JButton("Quit");
		p2.add(confirm);
		p2.add(reset);
		p2.add(quit);
		
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				confirmAction(e);
			}
		}
		);
		
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				resetAction(e);
			}
		}
		);
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				quitAction(e);
			}
		}
		);
	}
	
	//Action
	
	/**
	 * confirmAction Confirm Button
	 * 
	 * @param e
	 *            ActionEvent
	 * @return void
	 */
	private void confirmAction(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			socket = new Socket(CONST.SERVER_IP, CONST.SERVER_PORT);
			IDataGramFactory dgfac = new RegisterDGFactory();
			DataGram dg = dgfac.CreateDataGram();
			dg.setUsername(username.getText());
			dg.setPassword(password.getPassword());
			dg.SendDatagram(socket);
			if(dg.ReceiveDatagram(socket))
			{
				JOptionPane.showMessageDialog((Component) null, "×¢²á³É¹¦");
				logingui.setVisible(true);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog((Component) null, "×¢²áÊ§°Ü");
			}
			socket.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally
		{
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * resetAction Reset Button
	 * 
	 * @param e
	 *            ActionEvent
	 * @return void
	 */
	private void resetAction(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("reset REGISTER");
		username.setText("");
		password.setText("");
		info.setText("");
	}
	
	/**
	 * quitAction Quit Button
	 * 
	 * @param e
	 *            ActionEvent
	 * @return void
	 */
	private void quitAction(ActionEvent e) {
		// TODO Auto-generated method stub
		logingui.setVisible(true);
		this.dispose();
	}
}
