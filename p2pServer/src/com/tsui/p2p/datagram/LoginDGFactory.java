/**
 * @ProjectName P2P Chat Tool
 * @FileName LoginDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description ��¼���Ĺ�����
 */
package com.tsui.p2p.datagram;

/*
 * ��¼���Ĺ�����
 * @author �����F
 * @version version-1.0
 */
public class LoginDGFactory implements IDataGramFactory {

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new LoginDG();
		
	}
	
}
