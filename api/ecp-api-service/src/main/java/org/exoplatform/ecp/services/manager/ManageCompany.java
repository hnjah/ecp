package org.exoplatform.ecp.services.manager;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.exoplatform.ecp.model.Company;
import org.exoplatform.ecp.services.Constants;
import org.exoplatform.ecp.services.tool.Tools;

public class ManageCompany {
	
	private Session session = null;
	
	public ManageCompany(){
	}
	
	public Session getSession() {
		return session;
	}

	public List<Company> getAllCompanies(Query state){
		List<Company> listCompanies = new ArrayList<Company>();
		try {
			QueryResult result = state.execute();
			NodeIterator nodeIterator = result.getNodes();
			while(nodeIterator.hasNext()){
				Node companyNode = nodeIterator.nextNode();
				Company comapany = new Company(companyNode.getName(), companyNode.getProperty(Company.NAME).getString(), companyNode.getProperty(Company.COUNTRY).getString(), companyNode.getProperty(Company.TYPE).getString()); 
				listCompanies.add(comapany);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return listCompanies;
	}
	
	public Query getAllCompaniesQuery(StringBuilder queryString){
		Query query =  null;
		try{
			session = Tools.getSession(Constants.ECP_WORKSPACE);
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			query = queryManager.createQuery(queryString.toString(), Query.SQL);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return query;
	}
}
