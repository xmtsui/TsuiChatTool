/**
 * @ProjectName P2P Chat Tool
 * @FileName ResponseDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description ��¼����
 */
package com.tsui.p2p.datagram;

/*
 * ��Ӧ����
 * @author �����F
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
