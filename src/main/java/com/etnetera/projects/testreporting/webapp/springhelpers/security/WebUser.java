package com.etnetera.projects.testreporting.webapp.springhelpers.security;

import java.io.Serializable;

public class WebUser implements Serializable {

	private String id;
	
	private String label;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
