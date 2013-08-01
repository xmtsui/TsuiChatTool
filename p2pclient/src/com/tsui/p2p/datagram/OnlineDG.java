package com.tsui.p2p.datagram;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OnlineDG extends DataGram {
	/**
	 * 
	 */

	public OnlineDG()
	{
		setDgType(CONST.ONLINE);
		try {
			setIp(InetAddress.getByName("localhost").toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
