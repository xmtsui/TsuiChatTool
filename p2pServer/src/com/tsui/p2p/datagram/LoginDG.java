/**
 * @ProjectName P2P Chat Tool
 * @FileName LoginDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 登录报文
 */
package com.tsui.p2p.datagram;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 登录报文
 * @author 崔晓旻
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
