package org.exoplatform.ecp.services;

import java.util.List;

import org.exoplatform.container.xml.InitParams;

public class ParameterizedListService {
	
	private List<String> companyTypes;
	private List<String> categoryProperties;
	private List<String> product;
	private List<String> family;
	private List<String> sla;
	private List<String> core;
	private List<String> duration;
	private List<String> partnership;
	private List<String> training;
	private List<String> location;
	private List<String> consultingDuration;
	
	@SuppressWarnings("unchecked")
	public ParameterizedListService(InitParams params){
		companyTypes = params.getValuesParam("companyTypes").getValues();
		categoryProperties = params.getValuesParam("category.properties").getValues();
		product = params.getValuesParam("product").getValues();
		family = params.getValuesParam("family").getValues();
		sla = params.getValuesParam("sla").getValues();
		core = params.getValuesParam("core").getValues();
		duration = params.getValuesParam("duration").getValues();
		partnership = params.getValuesParam("partnership").getValues();
		training = params.getValuesParam("training").getValues();
		location = params.getValuesParam("location").getValues();
		consultingDuration = params.getValuesParam("consultingDuration").getValues();
		
	}

	public List<String> getCompanyTypes() {
		return companyTypes;
	}

	public List<String> getCategoryProperties() {
		return categoryProperties;
	}

	public List<String> getProduct() {
		return product;
	}

	public List<String> getFamily() {
		return family;
	}

	public List<String> getSla() {
		return sla;
	}

	public List<String> getCore() {
		return core;
	}

	public List<String> getDuration() {
		return duration;
	}

	public List<String> getPartnership() {
		return partnership;
	}

	public List<String> getTraining() {
		return training;
	}

	public List<String> getLocation() {
		return location;
	}

	public List<String> getConsultingDuration() {
		return consultingDuration;
	}

}
