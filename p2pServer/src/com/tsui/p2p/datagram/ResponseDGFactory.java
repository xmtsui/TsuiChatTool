/**
 * @ProjectName P2P Chat Tool
 * @FileName ResponseDGFactory.java
 * @Author Tsui Xiaomin 
 * @StuN.O. 201122230107
 * @Description ��Ӧ���Ĺ�����
 */
package com.tsui.p2p.datagram;

/*
 * ��Ӧ���Ĺ�����
 * @author �����F
 * @version version-1.0
 */
public class ResponseDGFactory implements IDataGramFactory{

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new ResponseDG();
	}

}
