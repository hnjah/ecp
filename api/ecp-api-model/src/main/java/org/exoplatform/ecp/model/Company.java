package org.exoplatform.ecp.model;

public class Company {
	
	public static String NAME = "ecp:name";
	public static String ADDRESS = "ecp:address";
	public static String WEBSITE = "ecp:website";
	public static String PHONE = "ecp:phone";
	public static String COUNTRY = "ecp:country";
	public static String EMAIL = "ecp:email";
	public static String TYPE = "ecp:type";
	public static String LOGO = "ecp:logo";

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
