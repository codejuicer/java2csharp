package com.google.devtools.java2csharp.testclasses.admin;

import java.util.List;

import com.google.devtools.java2csharp.testclasses.Header;
import com.google.devtools.java2csharp.testclasses.ResponseContent;

public class LoginResponse extends Message {
	private ResponseContent content;

	private Information information;

	private List<ContentArea> areas;

	protected LoginResponse() {
		super();
	}

	public LoginResponse(Header header,
			ResponseContent content, Information information,
			List<ContentArea> areas) {
		super(header);
		this.content = content;
		this.information = information;
		this.areas = areas;
	}

	public ResponseContent getContent() {
		return content;
	}

	public void setContent(ResponseContent content) {
		this.content = content;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public List<ContentArea> getAreas() {
		return areas;
	}

	public void setAreas(List<ContentArea> areas) {
		this.areas = areas;
	}
}
