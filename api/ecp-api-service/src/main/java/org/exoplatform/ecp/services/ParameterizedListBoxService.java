package org.exoplatform.ecp.services;

import org.exoplatform.container.xml.InitParams;

public class ParameterizedListBoxService {
	
	private String companyTypes;
	
	public ParameterizedListBoxService(InitParams params){
		companyTypes = params.getValueParam("companyTypes").getValue();
	}

	public String getCompanyPaths() {
		return companyTypes;
	}

	public void setCompanyPaths(String companyTypes) {
		this.companyTypes = companyTypes;
	}
	
}
