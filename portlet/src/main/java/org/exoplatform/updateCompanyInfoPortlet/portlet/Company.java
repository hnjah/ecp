package org.exoplatform.updateCompanyInfoPortlet.portlet;

public class Company {

	private String name;
	
	private String country;
	
	private String type;
	
	public Company(String name, String country, String type) {
		super();
		this.name = name;
		this.country = country;
		this.type = type;
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
