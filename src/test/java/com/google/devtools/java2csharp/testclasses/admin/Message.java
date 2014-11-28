package com.google.devtools.java2csharp.testclasses.admin;

import com.google.devtools.java2csharp.testclasses.Header;

public abstract class Message {
	private Header header;

	protected Message() {
		super();
	}

	public Message(Header header) {
		super();
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
}