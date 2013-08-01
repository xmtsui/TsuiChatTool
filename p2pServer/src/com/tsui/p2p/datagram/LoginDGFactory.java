/**
 * @ProjectName P2P Chat Tool
 * @FileName LoginDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description 登录报文工厂类
 */
package com.tsui.p2p.datagram;

/*
 * 登录报文工厂类
 * @author 崔晓旻
 * @version version-1.0
 */
public class LoginDGFactory implements IDataGramFactory {

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new LoginDG();
		
	}
	
}
