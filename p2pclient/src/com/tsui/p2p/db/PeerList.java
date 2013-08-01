package com.tsui.p2p.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tsui.p2p.datagram.DataGram;

public class PeerList implements Serializable {
	private List<DataGram> reglist;

	public PeerList()
	{
		reglist = new ArrayList<DataGram>();
	}
	public List<DataGram> getReglist() {
		return reglist;
	}

	public void setReglist(List<DataGram> reglist) {
		this.reglist = reglist;
	}
	
}
