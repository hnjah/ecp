package org.exoplatform.ecp.services;

import java.util.List;

import org.exoplatform.commons.utils.ExoProperties;
import org.exoplatform.container.xml.InitParams;

public class ParameterizedListService {
	
	private List<String> companyTypes;
	private List<String> categoryProperties;
//	private List<LabelValue> product;
//	private List<LabelValue> family;
//	private List<LabelValue> sla;
//	private List<LabelValue> core;
//	private List<LabelValue> duration;
//	private List<LabelValue> partnership;
//	private List<LabelValue> training;
//	private List<LabelValue> location;
//	private List<LabelValue> consultingDuration;
	private ExoProperties product;
	private ExoProperties family;
	private ExoProperties sla;
	private ExoProperties core;
	private ExoProperties duration;
	private ExoProperties partnership;
	private ExoProperties training;
	private ExoProperties location;
	private ExoProperties consultingDuration;
	
	@SuppressWarnings("unchecked")
	public ParameterizedListService(InitParams params){
		companyTypes = params.getValuesParam("companyTypes").getValues();
		categoryProperties = params.getValuesParam("category.properties").getValues();

		product = params.getPropertiesParam("product").getProperties();
		family = params.getPropertiesParam("family").getProperties();
		sla = params.getPropertiesParam("sla").getProperties();
		core = params.getPropertiesParam("core").getProperties();
		duration = params.getPropertiesParam("duration").getProperties();
		partnership = params.getPropertiesParam("partnership").getProperties();
		training = params.getPropertiesParam("training").getProperties();
		location = params.getPropertiesParam("location").getProperties();
		consultingDuration = params.getPropertiesParam("consultingDuration").getProperties();
		
//		CollectionValues products = (CollectionValues)params.getObjectParam("product").getObject();
//		product = products.getLabelValues();
//		CollectionValues families = (CollectionValues)params.getObjectParam("family").getObject();
//		family = families.getLabelValues();
//		CollectionValues slas = (CollectionValues)params.getObjectParam("sla").getObject();
//		sla = slas.getLabelValues();
//		CollectionValues cores = (CollectionValues)params.getObjectParam("core").getObject();
//		core = cores.getLabelValues();
//		CollectionValues durations = (CollectionValues)params.getObjectParam("duration").getObject();
//		duration = durations.getLabelValues();
//		CollectionValues partnerships = (CollectionValues)params.getObjectParam("partnership").getObject();
//		partnership = partnerships.getLabelValues();
//		CollectionValues trainings = (CollectionValues)params.getObjectParam("training").getObject();
//		training = trainings.getLabelValues();
//		CollectionValues locations = (CollectionValues)params.getObjectParam("location").getObject();
//		location = locations.getLabelValues();
//		CollectionValues consultingDurations = (CollectionValues)params.getObjectParam("consultingDuration").getObject();
//		consultingDuration = consultingDurations.getLabelValues();
		
	}

	public List<String> getCompanyTypes() {
		return companyTypes;
	}

	public List<String> getCategoryProperties() {
		return categoryProperties;
	}

	public ExoProperties getProduct() {
		return product;
	}

	public ExoProperties getFamily() {
		return family;
	}

	public ExoProperties getSla() {
		return sla;
	}

	public ExoProperties getCore() {
		return core;
	}

	public ExoProperties getDuration() {
		return duration;
	}

	public ExoProperties getPartnership() {
		return partnership;
	}

	public ExoProperties getTraining() {
		return training;
	}

	public ExoProperties getLocation() {
		return location;
	}

	public ExoProperties getConsultingDuration() {
		return consultingDuration;
	}

}
