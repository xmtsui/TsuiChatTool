package com.tsui.p2p.clientGUI;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.SwingUtilities;

import com.tsui.p2p.datagram.DataGram;

public class OnlineThread extends Thread{
	Runnable runx;
	DataGram dg;
	OnlineGUI onlineGUI;
	public OnlineThread(OnlineGUI onlineGUI, DataGram dg) throws UnknownHostException, IOException
	{
		this.dg = dg;
		this.onlineGUI = onlineGUI;
		Thread recvMSGThread = new Thread(onlineGUI);
		recvMSGThread.start();
		runx = new Runnable(){
			public void run()
			{
			
			}
		};
	}
	
	public void run(){
		while(true)
		{
			try {
				onlineGUI.update();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try{
				Thread.sleep(15000);
				SwingUtilities.invokeLater(runx);
			}catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
