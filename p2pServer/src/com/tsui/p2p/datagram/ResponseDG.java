/**
 * @ProjectName P2P Chat Tool
 * @FileName ResponseDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 登录报文
 */
package com.tsui.p2p.datagram;

/*
 * 响应报文
 * @author 崔晓旻
 * @version version-1.0
 */
public class ResponseDG extends DataGram{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseDG()
	{
		setDgType(CONST.RESPONSE);
	}
}
