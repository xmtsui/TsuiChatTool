/**
 * @ProjectName P2P Chat Tool
 * @FileName OnlineDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 登录报文
 */
package com.tsui.p2p.datagram;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 在线同步更新报文
 * @author 崔晓旻
 * @version version-1.0
 */
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
