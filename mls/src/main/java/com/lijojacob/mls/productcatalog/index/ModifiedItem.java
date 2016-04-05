package com.lijojacob.mls.productcatalog.index;

import java.io.Serializable;

public class ModifiedItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Class entityClass;
	
	private String id;
	
	public ModifiedItem(Class entityClass, String id) {
		this.entityClass = entityClass;
		this.id = id;
	}

	/**
	 * @return the entityClass
	 */
	public Class getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
