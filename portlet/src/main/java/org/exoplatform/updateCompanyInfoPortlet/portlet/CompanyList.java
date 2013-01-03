package org.exoplatform.updateCompanyInfoPortlet.portlet;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.commons.utils.ListAccessImpl;
import org.exoplatform.commons.utils.PageListAccess;

public class CompanyList extends PageListAccess<Company, Query>
{
	private static final long serialVersionUID = 1L;

	public CompanyList(Query state, int pageSize) {
	      super(state, pageSize);
	   }

	   @Override
	   protected ListAccess<Company> create(Query state) throws Exception {
		   QueryResult result = state.execute();
		   NodeIterator nodeIterator = result.getNodes();
		   List<Company> listCompanies = new ArrayList<Company>();
		   while(nodeIterator.hasNext()){
			   Node companyNode = nodeIterator.nextNode();
               Company comapany = new Company(companyNode.getProperty("ecp:name").getString(), companyNode.getProperty("ecp:country").getString(), companyNode.getProperty("ecp:type").getString()); 
               listCompanies.add(comapany);
           }    
		   return new ListAccessImpl<Company>(Company.class, listCompanies);
	   }
	}
