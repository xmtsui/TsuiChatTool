package com.tsui.p2p.datagram;

public class OnlineDGFactory implements IDataGramFactory {

	@Override
	public DataGram CreateDataGram() {
		// TODO Auto-generated method stub
		return new OnlineDG();
		
	}
	
}
