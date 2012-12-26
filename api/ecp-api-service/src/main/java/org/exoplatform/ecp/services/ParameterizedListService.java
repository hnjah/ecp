package org.exoplatform.ecp.services;

import java.util.List;

import org.exoplatform.container.xml.InitParams;

public class ParameterizedListService {
	
	private List<String> companyTypes;
	
	public ParameterizedListService(InitParams params){
		companyTypes = params.getValuesParam("companyTypes").getValues();
	}

	public List<String> getCompanyTypes() {
		return companyTypes;
	}

	public void setCompanyTypes(List<String> companyTypes) {
		this.companyTypes = companyTypes;
	}
	
}
