package com.tsui.p2p.serverDGHandle;

import com.tsui.p2p.datagram.DataGram;

public abstract class DatagramHandle {
	public abstract boolean handle(DataGram dg);
}
