package com.project.dto;

public class ContentMessage 
{
	private String name;

	public ContentMessage(String name)
	{
		this.name=name;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ContentMessage [name=" + name + "]";
	}
	
	
}
