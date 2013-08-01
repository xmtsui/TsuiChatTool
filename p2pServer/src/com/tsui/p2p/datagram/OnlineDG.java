/**
 * @ProjectName P2P Chat Tool
 * @FileName OnlineDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description ��¼����
 */
package com.tsui.p2p.datagram;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * ����ͬ�����±���
 * @author �����F
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
