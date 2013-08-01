package com.tsui.p2p.datagram;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import com.tsui.p2p.db.PeerList;

public abstract class DataGram implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long serial;
	protected int dgType;
	protected String username;
	protected char[] password;
	protected String ip;
	protected int port;
	protected boolean responseTrue;
	protected PeerList allPeer;
	//protected PeerList mypeer;
	
	public DataGram()
	{
		this.setPort(10000);//本机测试注意端口
	}
	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
	public int getDgType() {
		return dgType;
	}

	public void setDgType(int dgType) {
		this.dgType = dgType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public boolean isResponseTrue() {
		return responseTrue;
	}
	public void setResponseTrue(boolean responseTrue) {
		this.responseTrue = responseTrue;
	}
	public PeerList getAllPeer() {
		return allPeer;
	}
	public void setAllPeer(PeerList allPeer) {
		this.allPeer = allPeer;
	}

	public void SendDatagram(Socket socket)
	{
		ObjectOutputStream oouts;
		try {
			oouts = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Client: Send: \'" + this.getDgType() + "\' type DataGram");
			oouts.writeObject(this);
			oouts.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean ReceiveDatagram(Socket socket)
	{
		ObjectInputStream oins;
		try{
			oins = new ObjectInputStream(socket.getInputStream());
			ResponseDG resdg = (ResponseDG)oins.readObject();
			boolean flag = resdg.isResponseTrue();
			if(flag)
			{
				System.out.println("Client: Result \' " + this.getDgType() + " \' type SUCCESS");
				allPeer = resdg.getAllPeer();
				System.out.println("---------Total Registered NOW: " + allPeer.getReglist().size() + "---------");
				return true;
			}
			else
			{
				System.out.println("Client: Result \' " + this.getDgType() + " \' type FAIL");
				//System.out.println("---------Total Registered NOW: " + allPeer.getReglist().size() + "---------");
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
