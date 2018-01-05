package com.zp.dubbo.configBean;

import java.io.Serializable;

public class BaseConfigBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -97054282132374548L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
