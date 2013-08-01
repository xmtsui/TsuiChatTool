package com.tsui.p2p.serverDGHandle;

import com.tsui.p2p.ThreadPool.ServerSingleton;
import com.tsui.p2p.datagram.DataGram;
import com.tsui.p2p.db.DBOperater;

public class RegisterDGHandle extends DatagramHandle{

	@Override
	public boolean handle(DataGram dg) {
		// TODO Auto-generated method stub
		if(DBOperater.save(dg, ServerSingleton.GetInstance().getPl()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
