package com.tsui.p2p.datagram;

public class ChatDGFactory implements IDataGramFactory{
	public DataGram CreateDataGram() {
		return new ChatDG();
	}
}
