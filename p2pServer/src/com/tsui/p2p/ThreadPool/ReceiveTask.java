package com.tsui.p2p.ThreadPool;

import java.io.ObjectInputStream;
import java.net.Socket;

import com.tsui.p2p.datagram.DataGram;
import com.tsui.p2p.serverDGHandle.HandleContext;

public class ReceiveTask implements Task{
	private Socket socket;
	
	public ReceiveTask()
	{
		socket = new Socket();
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try{
			while(true){
					socket = ServerSingleton.GetInstance().getSs().accept();
					while(!socket.isClosed())
					{
						ObjectInputStream oins = new ObjectInputStream(socket.getInputStream());
						DataGram temp = (DataGram)oins.readObject();
						System.out.println("Server: Receive Datagram : " + temp.getDgType());
						HandleContext handle = new HandleContext(temp, socket);
						oins.close();
						socket.close();
					}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
