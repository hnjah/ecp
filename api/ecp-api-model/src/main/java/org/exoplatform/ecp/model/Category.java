package org.exoplatform.ecp.model;

public class Category {
	
	public static String PRODUCT = "ecp:product";
	public static String FAMILY = "ecp:family";
	public static String SLA = "ecp:sla";
	public static String CORE = "ecp:core";
	public static String DURATION = "ecp:duration";
	public static String PARTNERSHIP = "ecp:partnership";
	public static String TRAINING = "ecp:training";
	public static String LOCATION = "ecp:location";
	public static String CONSULTING_DURATION = "ecp:consultingDuration";
	
	private String name;
	private boolean product;
	private boolean family;
	private boolean sla;
	private boolean core;
	private boolean duration;
	private boolean partnership;
	private boolean training;
	private boolean location;
	private boolean consultingDuration;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isProduct() {
		return product;
	}
	public void setProduct(boolean product) {
		this.product = product;
	}
	public boolean isFamily() {
		return family;
	}
	public void setFamily(boolean family) {
		this.family = family;
	}
	public boolean isSla() {
		return sla;
	}
	public void setSla(boolean sla) {
		this.sla = sla;
	}
	public boolean isCore() {
		return core;
	}
	public void setCore(boolean core) {
		this.core = core;
	}
	public boolean isDuration() {
		return duration;
	}
	public void setDuration(boolean duration) {
		this.duration = duration;
	}
	public boolean isPartnership() {
		return partnership;
	}
	public void setPartnership(boolean partnership) {
		this.partnership = partnership;
	}
	public boolean isTraining() {
		return training;
	}
	public void setTraining(boolean training) {
		this.training = training;
	}
	public boolean isLocation() {
		return location;
	}
	public void setLocation(boolean location) {
		this.location = location;
	}
	public boolean isConsultingDuration() {
		return consultingDuration;
	}
	public void setConsultingDuration(boolean consultingDuration) {
		this.consultingDuration = consultingDuration;
	}
}
