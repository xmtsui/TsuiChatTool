package com.tsui.p2p.serverDGHandle;

import com.tsui.p2p.ThreadPool.ServerSingleton;
import com.tsui.p2p.datagram.DataGram;
import com.tsui.p2p.db.DBOperater;

public class ChatDGHandle extends DatagramHandle{
	public boolean handle(DataGram dg) {
		DataGram finddg = DBOperater.findReg(dg, ServerSingleton.GetInstance().getPl());
		if(finddg != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
