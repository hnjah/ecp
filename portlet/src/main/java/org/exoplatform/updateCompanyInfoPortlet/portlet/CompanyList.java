package org.exoplatform.updateCompanyInfoPortlet.portlet;

import java.util.List;

import javax.jcr.query.Query;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.commons.utils.ListAccessImpl;
import org.exoplatform.commons.utils.PageListAccess;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.ecp.model.Company;
import org.exoplatform.ecp.services.manager.ManageCompany;

public class CompanyList extends PageListAccess<Company, Query>
{
	private static final long serialVersionUID = 1L;

	public CompanyList(Query state, int pageSize) {
	      super(state, pageSize);
	   }

	   @Override
	   protected ListAccess<Company> create(Query state) throws Exception {
		   PortalContainer portalContainer = PortalContainer.getInstance();
		   ManageCompany manageCompany = (ManageCompany)portalContainer.getComponentInstanceOfType(ManageCompany.class);
		   List<Company> companies = manageCompany.getAllCompanies(state);
		   return new ListAccessImpl<Company>(Company.class, companies);
	   }
	}
