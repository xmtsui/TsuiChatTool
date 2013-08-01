/**
 * @ProjectName P2P Chat Tool
 * @FileName RegisterDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description ע�ᱨ�Ĺ����� Factory
 */
package com.tsui.p2p.datagram;

/*
 * ע�ᱨ�Ĺ�����
 * @author �����F
 * @version version-1.0
 */
public class RegisterDGFactory implements IDataGramFactory {

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new RegisterDG();
		
	}
	
}
