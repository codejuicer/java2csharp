package com.google.devtools.java2csharp.testclasses;

public class Header {
	private int sequence;
	
	private long timestamp;

	protected Header() {
		super();
	}

	public Header(int sequence, long timestamp) {
		super();
		this.sequence = sequence;
		this.timestamp = timestamp;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
