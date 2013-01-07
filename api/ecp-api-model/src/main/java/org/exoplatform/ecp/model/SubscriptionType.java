package org.exoplatform.ecp.model;

public class SubscriptionType {
	
	public static String PRODUCT = "ecp:product";
	public static String FAMILY = "ecp:family";
	public static String SLA = "ecp:sla";
	public static String DURATION = "ecp:duration";
	public static String CORE = "ecp:core";
	public static String PARTNERSHIP = "ecp:partnership";
	public static String TRAINING = "ecp:training";
	public static String LOCATION = "ecp:location";
	public static String CONSULTING_DURATION = "ecp:consultingDuration";
	
	private String parentCategory;

	private String name;
	
	private String product;
	private String family;
	private String sla;
	private String duration;
	private String core;
	private String partnership;
	private String training;
	private String location;
	
	private String consultingDuration;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getSla() {
		return sla;
	}
	public void setSla(String sla) {
		this.sla = sla;
	}
	public String getCore() {
		return core;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPartnership() {
		return partnership;
	}
	public void setPartnership(String partnership) {
		this.partnership = partnership;
	}
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getConsultingDuration() {
		return consultingDuration;
	}
	public void setConsultingDuration(String consultingDuration) {
		this.consultingDuration = consultingDuration;
	}
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
