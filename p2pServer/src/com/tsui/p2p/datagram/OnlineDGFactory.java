/**
 * @ProjectName P2P Chat Tool
 * @FileName OnlineDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description ͬ�����Ĺ�����
 */
package com.tsui.p2p.datagram;

/*
 * ͬ�����Ĺ�����
 * @author �����F
 * @version version-1.0
 */
public class OnlineDGFactory implements IDataGramFactory {

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new OnlineDG();
		
	}
	
}
