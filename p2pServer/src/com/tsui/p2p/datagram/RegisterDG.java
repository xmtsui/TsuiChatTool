/**
 * @ProjectName P2P Chat Tool
 * @FileName RegisterDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 登录报文
 */
package com.tsui.p2p.datagram;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 注册报文
 * @author 崔晓旻
 * @version version-1.0
 */
public class RegisterDG extends DataGram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterDG()
	{
		setSerial(CONST.SERIAL++);
		setDgType(CONST.REGISTER);
		try {
			setIp(InetAddress.getByName("localhost").toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setPort(8889);
	}
}
