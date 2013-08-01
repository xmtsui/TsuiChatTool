package com.tsui.p2p.serverDGHandle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.tsui.p2p.ThreadPool.ServerSingleton;
import com.tsui.p2p.datagram.CONST;
import com.tsui.p2p.datagram.DataGram;
import com.tsui.p2p.datagram.IDataGramFactory;
import com.tsui.p2p.datagram.ResponseDGFactory;
import com.tsui.p2p.db.PeerList;

public class HandleContext {
	private DatagramHandle cs;
	private DataGram recvdg;
	private DataGram responsedg;
	
	public HandleContext(DataGram dg, Socket so) throws IOException
	{
		recvdg = dg;
		
		IDataGramFactory dgf = new ResponseDGFactory();
		responsedg = dgf.CreateDataGram();
		
		OutputStream out = so.getOutputStream();
		ObjectOutputStream oouts = new ObjectOutputStream(out);
		
		switch(recvdg.getDgType())
		{
		case CONST.REGISTER:
			cs = new RegisterDGHandle();
			if(cs.handle(dg))
			{
				responsedg.setResponseTrue(true);
				responsedg.setAllPeer(ServerSingleton.GetInstance().getPl());
				oouts.writeObject(responsedg);
				oouts.flush();
				System.out.println("Now Server Registered: " + ServerSingleton.GetInstance().getPl().getReglist().size());
			}
			else
			{
				responsedg.setResponseTrue(false);
				responsedg.setAllPeer((PeerList)null);
				oouts.writeObject(responsedg);
				oouts.flush();
			}
			out.close();
			oouts.close();
			so.close();
			break;
		case CONST.LOGIN:
			cs = new LoginDGHandle();
			if(cs.handle(dg))
			{
				responsedg.setResponseTrue(true);
				responsedg.setAllPeer(ServerSingleton.GetInstance().getPl());
				oouts.writeObject(responsedg);
				oouts.flush();
			}
			else
			{
				responsedg.setResponseTrue(false);
				responsedg.setAllPeer((PeerList)null);
				oouts.writeObject(responsedg);
				oouts.flush();
			}
			out.close();
			oouts.close();
			so.close();
			break;
		case CONST.ONLINE:
			cs = new OnlineDGHandle();
			responsedg.setResponseTrue(true);
			responsedg.setAllPeer(ServerSingleton.GetInstance().getPl());
			oouts.writeObject(responsedg);
			oouts.flush();
			
			out.close();
			oouts.close();
			so.close();
			break;
		case CONST.CHAT:
			cs = new ChatDGHandle();
			if(cs.handle(recvdg))
			{
				responsedg.setResponseTrue(true);
				responsedg.setAllPeer(ServerSingleton.GetInstance().getPl());
				oouts.writeObject(responsedg);
				oouts.flush();
			}
			else
			{
				responsedg.setResponseTrue(false);
				responsedg.setAllPeer((PeerList)null);
				oouts.writeObject(responsedg);
				oouts.flush();
			}
			out.close();
			oouts.close();
			so.close();
			break;
		}
	}
}
