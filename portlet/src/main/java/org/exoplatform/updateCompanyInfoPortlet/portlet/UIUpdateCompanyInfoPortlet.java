package org.exoplatform.updateCompanyInfoPortlet.portlet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.web.application.ApplicationMessage;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIApplication;
import org.exoplatform.webui.core.UIPageIterator;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIFormInputSet;
import org.exoplatform.webui.form.UIFormSelectBox;
import org.exoplatform.webui.form.UIFormStringInput;

//@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "/groovy/ecp-portlets/portlet/updateCompanyInfoPortlet/UIUpdateCompanyInfoPortlet.gtmpl", events = @EventConfig(listeners = UIUpdateCompanyInfoPortlet.EditCompanyActionListener.class))
@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "/groovy/ecp-portlets/portlet/updateCompanyInfoPortlet/UIUpdateCompanyInfoPortlet.gtmpl")
public class UIUpdateCompanyInfoPortlet extends UIPortletApplication {

	private static final String COMPANY_ID = "id";
	
	private static final String COMPANY_NAME= "name";
	
	private static final String COUNTRY = "country";

	private static final String TYPE = "type";
	
	private static final String[] COMPANY_BEAN_FIELD = {COMPANY_ID, COMPANY_NAME, COUNTRY, TYPE};

	private static final String[] COMPANY_ACTION = {"EditCompany"};
	
	private UIGrid grid_;

	@SuppressWarnings("unchecked")
	private final static List<SelectItemOption<String>> OPTIONS_ = Collections.unmodifiableList(Arrays.asList(new SelectItemOption<String>(COMPANY_NAME, COMPANY_NAME), new SelectItemOption<String>(COUNTRY, COUNTRY), new SelectItemOption<String>(TYPE, TYPE)));
	
	private RepositoryService rs;
	
	private StringBuilder queryString;

	public UIUpdateCompanyInfoPortlet() throws Exception {
		UISearchForm uiForm = addChild(UISearchForm.class, null, null);
	    uiForm.setOptions(OPTIONS_);
	    grid_ = addChild(UIGrid.class, null, "UIListCompaniesGrid");
	    grid_.configure(COMPANY_ID, COMPANY_BEAN_FIELD, COMPANY_ACTION);
	    grid_.getUIPageIterator().setId("UIListCompaniesIterator");
	    grid_.getUIPageIterator().setParent(this);
		ExoContainer exoContainer = ExoContainerContext.getCurrentContainer();
        rs = (RepositoryService) exoContainer.getComponentInstanceOfType(RepositoryService.class);
        Session session = (Session) rs.getCurrentRepository().getSystemSession("ecp");
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        queryString = new StringBuilder("select * from ecp:company where jcr:path like '/companies/%'");
		Query query = queryManager.createQuery(queryString.toString(), Query.SQL);
	    search(query);
	    session.logout();
	}
	
	public void quickSearch(UIFormInputSet quickSearchInput) throws Exception {
		UIFormStringInput input = (UIFormStringInput)quickSearchInput.getChild(0);
	    UIFormSelectBox select = (UIFormSelectBox)quickSearchInput.getChild(1);
        if (input.getValue() != null) {
        	queryString.append (" and " + "ecp:" + select.getValue() + " like '" + input.getValue() + "%'");
        }
        Session session = (Session) rs.getCurrentRepository().getSystemSession("ecp");
        QueryManager queryManager = session.getWorkspace().getQueryManager();
		Query query = queryManager.createQuery(queryString.toString(), Query.SQL);
	    search(query);
	    queryString = new StringBuilder("select * from ecp:company where jcr:path like '/companies/%'");
	    session.logout();
	}
	
	private void search(Query query) throws Exception {
		grid_.getUIPageIterator().setPageList(new CompanyList(query, 10));
	    UIPageIterator pageIterator = grid_.getUIPageIterator();
	    if (pageIterator.getAvailable() == 0) {
	    	UIApplication uiApp = Util.getPortalRequestContext().getUIApplication();
	        uiApp.addMessage(new ApplicationMessage("UISearchForm.msg.empty", null));
	    }
	}
	
//	static public class EditCompanyActionListener extends EventListener<UIUpdateCompanyInfoPortlet>
//	   {
//	      public void execute(Event<UIUpdateCompanyInfoPortlet> event) throws Exception
//	      {
//	    	  String companyname = event.getRequestContext().getRequestParameter(OBJECTID);
//	    	  System.out.println("Company to be edited: " + companyname);
//	      }
//	   }
}