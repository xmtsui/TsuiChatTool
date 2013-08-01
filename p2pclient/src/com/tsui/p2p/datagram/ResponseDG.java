package com.tsui.p2p.datagram;

public class ResponseDG extends DataGram{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseDG()
	{
		setDgType(CONST.RESPONSE);
	}
}
