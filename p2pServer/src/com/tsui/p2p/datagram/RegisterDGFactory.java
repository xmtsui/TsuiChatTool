/**
 * @ProjectName P2P Chat Tool
 * @FileName RegisterDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 注册报文工厂类 Factory
 */
package com.tsui.p2p.datagram;

/*
 * 注册报文工厂类
 * @author 崔晓旻
 * @version version-1.0
 */
public class RegisterDGFactory implements IDataGramFactory {

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new RegisterDG();
		
	}
	
}
