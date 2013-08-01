/**
 * @ProjectName P2P Chat Tool
 * @FileName ChatDG.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description Chat DataGram
 */
package com.tsui.p2p.datagram;

/*
 * 聊天报文
 * @author 崔晓旻
 * @version version-1.0
 */
public class ChatDG extends DataGram{
	/**
	 * 变量定义
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ChatDG 构造函数
	 */
	public ChatDG()
	{
		setDgType(CONST.CHAT);
	}
}
