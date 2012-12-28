package org.exoplatform.updateCompanyInfoPortlet.portlet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIGrid;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.form.UIFormInputSet;
import org.exoplatform.webui.form.UIFormSelectBox;
import org.exoplatform.webui.form.UIFormStringInput;

@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "/groovy/ecp-portlets/portlet/updateCompanyInfoPortlet/UIUpdateCompanyInfoPortlet.gtmpl")
public class UIUpdateCompanyInfoPortlet extends UIPortletApplication {

	public static final String COMPANY_NAME = "name";
	
	public static final String COUNTRY = "country";

	public static final String TYPE = "type";
	
	private static final String[] COMPANY_BEAN_FIELD = {COMPANY_NAME, COUNTRY, TYPE};

	private static final String[] COMPANY_ACTION = {"EditCompany"};

	@SuppressWarnings("unchecked")
	private final static List<SelectItemOption<String>> OPTIONS_ = Collections.unmodifiableList(Arrays.asList(new SelectItemOption<String>(COMPANY_NAME, COMPANY_NAME), new SelectItemOption<String>(COUNTRY, COUNTRY), new SelectItemOption<String>(TYPE, TYPE)));

	public UIUpdateCompanyInfoPortlet() throws Exception {
		UISearchForm uiForm = addChild(UISearchForm.class, null, null);
	    uiForm.setOptions(OPTIONS_);
	    UIGrid grid_ = addChild(UIGrid.class, null, "UIListUsersGird");
	    grid_.configure(COMPANY_NAME, COMPANY_BEAN_FIELD, COMPANY_ACTION);
	    grid_.getUIPageIterator().setId("UIListUsersIterator");
	    grid_.getUIPageIterator().setParent(this);
	}
	
	public void quickSearch(UIFormInputSet quickSearchInput) throws Exception {
	    UIFormStringInput input = (UIFormStringInput)quickSearchInput.getChild(0);
	    UIFormSelectBox select = (UIFormSelectBox)quickSearchInput.getChild(1);
		ExoContainer exoContainer = ExoContainerContext.getCurrentContainer();
        RepositoryService rs = (RepositoryService) exoContainer.getComponentInstanceOfType(RepositoryService.class);
        Session session = (Session) rs.getCurrentRepository().getSystemSession("ecp");
        QueryManager queryManager = null;
        queryManager = session.getWorkspace().getQueryManager();
        StringBuilder queryString = new StringBuilder("select * from ecp:company where jcr:path like '/companies/%'");
        if (input.getValue() != "") {
        	queryString.append (" and " + "ecp:" + select.getLabel() + " like '" + input.getValue() + "%'");
        }
		Query query = queryManager.createQuery(queryString.toString(), Query.SQL);
			
	      //Query query = new Query();

	      
//	      String name = input.getValue();
//	      if (name == null || name.equals(""))
//	      {
//	         search(new Query());
//	         return;
//	      }
//	      if (name.indexOf("*") < 0)
//	      {
//	         if (name.charAt(0) != '*')
//	            name = "*" + name;
//	         if (name.charAt(name.length() - 1) != '*')
//	            name += "*";
//	      }
//	      name = name.replace('?', '_');
//	      String selectBoxValue = select.getValue();
//	      if (selectBoxValue.equals(USER_NAME))
//	         query.setUserName(name);
//	      if (selectBoxValue.equals(LAST_NAME))
//	         query.setLastName(name);
//	      if (selectBoxValue.equals(FIRST_NAME))
//	         query.setFirstName(name);
//	      if (selectBoxValue.equals(EMAIL))
//	         query.setEmail(name);
//	      search(query);
		System.out.println("buggggggggggggggggla");
	   }

}