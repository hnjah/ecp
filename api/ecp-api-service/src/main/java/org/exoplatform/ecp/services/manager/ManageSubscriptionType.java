package org.exoplatform.ecp.services.manager;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.exoplatform.ecp.model.Category;
import org.exoplatform.ecp.model.SubscriptionType;
import org.exoplatform.ecp.services.Constants;
import org.exoplatform.ecp.services.tool.Tools;

public class ManageSubscriptionType {
	
	public ManageSubscriptionType(){
	}
	
	public void addSubscriptionType(SubscriptionType subscriptionType) throws Exception{
		Session session = null;
		try{
			session = Tools.getSession(Constants.ECP_WORKSPACE);
			Node node = (Node)session.getItem(Constants.CATEGORY_PATH + "/" + subscriptionType.getParentCategory());
			node.addNode(subscriptionType.getName(), Constants.SUBSCRIPTION_TYPE_NODETYPE);
			
			node.setProperty(name, value)
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.save();
			}
		}
		
	}
	
	public Category getCategory(String categoryName){
		
		Session session = null;
		Category category = null;
		try{
			session = Tools.getSession(Constants.ECP_WORKSPACE);
			Node node = (Node)session.getItem(Constants.CATEGORY_PATH + "/" + categoryName);
			category = nodeToCategory(node);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.logout();
			}
		}
		return category;
		
	}
	
	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<Category>();
		Session session = null;
		try{
			session = Tools.getSession(Constants.ECP_WORKSPACE);
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			String requete = "SELECT * FROM " + Constants.CATEGORY_NODETYPE + " where jcr:path = '" + Constants.CATEGORY_PATH + "/%' order by exo:dateCreated DESC";
			Query query = queryManager.createQuery(requete, Query.SQL);
			QueryResult queryResult = query.execute();
			if (queryResult.getNodes().getSize() > 0) {
				for (NodeIterator iter = queryResult.getNodes(); iter.hasNext();) {
					Node node = iter.nextNode();
			
					Category category = nodeToCategory(node);
					categories.add(category);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.logout();
			}
		}
		return categories;
	}
	
	public static Category nodeToCategory(Node node)throws Exception{
		Category category = new Category();
		category.setName(node.getName());
		category.setProduct(node.getProperty(Category.PRODUCT).getBoolean());
		category.setFamily(node.getProperty(Category.FAMILY).getBoolean());
		category.setSla(node.getProperty(Category.SLA).getBoolean());
		category.setCore(node.getProperty(Category.CORE).getBoolean());
		category.setDuration(node.getProperty(Category.DURATION).getBoolean());
		category.setPartnership(node.getProperty(Category.PARTNERSHIP).getBoolean());
		category.setTraining(node.getProperty(Category.TRAINING).getBoolean());
		category.setLocation(node.getProperty(Category.LOCATION).getBoolean());
		category.setConsultingDuration(node.getProperty(Category.CONSULTING_DURATION).getBoolean());
		
		return category;
		
	}
	
	

}
