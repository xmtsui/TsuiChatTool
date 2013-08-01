package com.tsui.p2p.serverGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.tsui.p2p.ThreadPool.ReceiveTask;
import com.tsui.p2p.ThreadPool.ServerSingleton;
import com.tsui.p2p.ThreadPool.ThreadPoolManager;

public class ServerMain extends JFrame{
	private JPanel p1;

	private JButton start;
	private JButton pause;
	private JButton quit;
	
	//Constructor 
	public ServerMain()
	{
		super("Server Panel");
		
		//the Whole config
		setLayout(new BorderLayout());
		setSize(400,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		
		p1 = new JPanel();
		add(p1, BorderLayout.NORTH);
		
		p1.setLayout(new GridLayout(1,3));
		start = new JButton("Start");
		pause = new JButton("Pause");
		quit = new JButton("Quit");
		p1.add(start);
		p1.add(pause);
		p1.add(quit);
		
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				startAction(e);
			}
		}
		);
		
		pause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				pauseAction(e);
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
		 * StarAction Start Button
		 * 
		 * @param e
		 *            ActionEvent
		 * @return void
		 */
		private void startAction(ActionEvent e) {
				ServerSingleton.GetInstance().getTpm().addTask(new ReceiveTask());

		}
		/**
		 * PauseAction Pause Button
		 * 
		 * @param e
		 *            ActionEvent
		 * @return void
		 */
		private void pauseAction(ActionEvent e) {
			if(ServerSingleton.GetInstance().getTpm() != null)	
				ServerSingleton.GetInstance().getTpm().shutdown();
			else
				System.exit(0);
		}
		
		/**
		 * QuitAction quit Button
		 * 
		 * @param e
		 *            ActionEvent
		 * @return void
		 */
		private void quitAction(ActionEvent e) {
			if(ServerSingleton.GetInstance().getTpm() != null)		
			{
				ServerSingleton.GetInstance().getTpm().shutdown();
				System.exit(0);
			}
			else
				System.exit(0);
		}
}
