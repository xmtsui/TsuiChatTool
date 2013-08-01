/**
 * @ProjectName P2P Chat Tool
 * @FileName CONST.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 定义常量
 */
package com.tsui.p2p.datagram;

/*
 * 常量定义
 * @author 崔晓旻
 * @version version-1.0
 */
public class CONST {
	public static int SERIAL = 0;
	public static String SERVER_IP = "127.0.0.1";
	public static int SERVER_PORT = 8888;
	public final static int MAXUSER = 10;
	public final static int MAXBUFF = 1024;
	
	public final static int RESPONSE = 0;
	
	public final static int REGISTER = 1;
	public final static int LOGIN = 2;
	public final static int ONLINE = 3;
	public final static int CHAT = 4;
	
}
