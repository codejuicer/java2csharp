package com.google.devtools.java2csharp.testclasses.admin;

public class ContentArea {
	private int id;

	private String name;

	private boolean managed;

	protected ContentArea() {
		super();
	}

	public ContentArea(int id, String name, boolean managed) {
		super();
		this.id = id;
		this.name = name;
		this.managed = managed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isManaged() {
		return managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}
}