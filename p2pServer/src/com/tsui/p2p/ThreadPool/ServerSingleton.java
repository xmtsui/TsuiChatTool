package com.tsui.p2p.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;

import com.tsui.p2p.datagram.CONST;
import com.tsui.p2p.datagram.ChatDG;
import com.tsui.p2p.datagram.DataGram;
import com.tsui.p2p.db.PeerList;

public class ServerSingleton {
	private static ServerSingleton instance;
	private ThreadPoolManager tpm;
	private ServerSocket ss;
	private PeerList pl;
	private DataGram chatdg;
	
	private ServerSingleton()
	{
		try{
		tpm = new ThreadPoolManager();
		ss = new ServerSocket(CONST.SERVER_PORT);
		pl = new PeerList();
		chatdg = new ChatDG();
		}catch (IOException e)
		{
			System.out.println("ServerSocket Listen Error, error is: ");
			e.printStackTrace();
		}
	}
	
	public ThreadPoolManager getTpm() {
		return tpm;
	}

	public void setTpm(ThreadPoolManager tpm) {
		this.tpm = tpm;
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}
	
	public PeerList getPl() {
		return pl;
	}

	public void setPl(PeerList pl) {
		this.pl = pl;
	}
	
	public DataGram getChatdg() {
		return chatdg;
	}

	public void setChatdg(DataGram chatdg) {
		this.chatdg = chatdg;
	}

	public synchronized static ServerSingleton GetInstance()
	{
		if(instance == null)
		{
			instance = new ServerSingleton();
		}
		return instance;
	}
}
