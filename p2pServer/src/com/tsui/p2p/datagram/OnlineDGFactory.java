/**
 * @ProjectName P2P Chat Tool
 * @FileName OnlineDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 同步报文工厂类
 */
package com.tsui.p2p.datagram;

/*
 * 同步报文工厂类
 * @author 崔晓旻
 * @version version-1.0
 */
public class OnlineDGFactory implements IDataGramFactory {

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new OnlineDG();
		
	}
	
}
