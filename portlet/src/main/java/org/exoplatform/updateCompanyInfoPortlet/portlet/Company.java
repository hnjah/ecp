package org.exoplatform.updateCompanyInfoPortlet.portlet;

public class Company {

	private String id;
	
	private String name;
	
	private String country;
	
	private String type;
	
	public Company(String id, String name, String country, String type) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
