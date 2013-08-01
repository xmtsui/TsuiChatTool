package com.tsui.p2p.db;

import java.util.Iterator;

import com.tsui.p2p.datagram.DataGram;

public class DBOperater {
	public static boolean save(DataGram dg, PeerList pl)
	{
		return false;
	}
	
	public static boolean isRegistered(DataGram dg, PeerList pl)
	{
		return false;

	}

	public static DataGram findReg(DataGram dg, PeerList pl)
	{
		Iterator<DataGram> tt = pl.getReglist().iterator();
		if(!pl.getReglist().isEmpty())
		{
			while(tt.hasNext())
			{
				DataGram d = tt.next();
				if(d.getUsername().equals(dg.getUsername()))
				{
					return d;
				}
				else
					continue;
			}
		}
		return null;
	}
}
