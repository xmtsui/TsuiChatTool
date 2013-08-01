/**
 * @ProjectName P2P Chat Tool
 * @FileName LoginDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description ��¼����
 */
package com.tsui.p2p.datagram;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * ��¼����
 * @author �����F
 * @version version-1.0
 */
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
