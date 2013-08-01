package com.tsui.p2p.datagram;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LoginDG extends DataGram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginDG()
	{
		setDgType(CONST.LOGIN);
		try {
			setIp(InetAddress.getByName("localhost").toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
