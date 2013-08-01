/**
 * @ProjectName P2P Chat Tool
 * @FileName ChatDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description Chat DataGram Factory
 */
package com.tsui.p2p.datagram;

/*
 * ���챨�Ĺ�����
 * @author �����F
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
