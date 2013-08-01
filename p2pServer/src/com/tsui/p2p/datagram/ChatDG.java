/**
 * @ProjectName P2P Chat Tool
 * @FileName ChatDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description Chat DataGram
 */
package com.tsui.p2p.datagram;

/*
 * ���챨��
 * @author �����F
 * @version version-1.0
 */
public class ChatDG extends DataGram{
	/**
	 * ��������
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ChatDG ���캯��
	 */
	public ChatDG()
	{
		setDgType(CONST.CHAT);
	}
}
