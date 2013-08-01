/**
 * @ProjectName P2P Chat Tool
 * @FileName ChatDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description Chat DataGram Factory
 */
package com.tsui.p2p.datagram;

/*
 * 聊天报文工厂类
 * @author 崔晓旻
 * @version version-1.0
 */
public class ChatDGFactory implements IDataGramFactory{
	/*
	 * (non-Javadoc)
	 * @see com.tsui.p2p.datagram.IDataGramFactory#CreateDataGram()
	 */
	public DataGram CreateDataGram() {
		return new ChatDG();
	}
}
