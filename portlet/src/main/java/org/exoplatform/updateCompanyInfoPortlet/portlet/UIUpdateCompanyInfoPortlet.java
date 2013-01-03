package org.exoplatform.updateCompanyInfoPortlet.portlet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.jcr.query.Query;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.ecp.services.manager.ManageCompany;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.web.application.ApplicationMessage;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIApplication;
import org.exoplatform.webui.core.UIPageIterator;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.form.UIFormInputSet;
import org.exoplatform.webui.form.UIFormSelectBox;
import org.exoplatform.webui.form.UIFormStringInput;

@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "/groovy/ecp-portlets/portlet/updateCompanyInfoPortlet/UIUpdateCompanyInfoPortlet.gtmpl")
public class UIUpdateCompanyInfoPortlet extends UIPortletApplication {

	private static final String ID = "id";
	
	private static final String NAME= "name";
	
	private static final String COUNTRY = "country";

	private static final String TYPE = "type";
	
	private static final String[] COMPANY_BEAN_FIELD = {ID, NAME, COUNTRY, TYPE};

	private static final String[] COMPANY_ACTION = {"EditCompany"};
	
	public static String COMPANY_PATH = "/companies";
	
	public static String COMPANY_NODETYPE = "ecp:company";
	
	private UIGrid grid_;

	private ManageCompany manageCompany;
	
	private StringBuilder queryString;

	@SuppressWarnings("unchecked")
	public UIUpdateCompanyInfoPortlet() throws Exception {
		UISearchForm uiForm = addChild(UISearchForm.class, null, null);
		List<SelectItemOption<String>> options = Collections.unmodifiableList(Arrays.asList(new SelectItemOption<String>(NAME, NAME), new SelectItemOption<String>(COUNTRY, COUNTRY), new SelectItemOption<String>(TYPE, TYPE)));
	    uiForm.setOptions(options);
	    grid_ = addChild(UIGrid.class, null, "UIListCompaniesGrid");
	    grid_.configure(ID, COMPANY_BEAN_FIELD, COMPANY_ACTION);
	    grid_.getUIPageIterator().setId("UIListCompaniesIterator");
	    grid_.getUIPageIterator().setParent(this);
		manageCompany = (ManageCompany) PortalContainer.getInstance().getComponentInstanceOfType(ManageCompany.class);
		queryString = new StringBuilder("select * from " + COMPANY_NODETYPE + " where jcr:path like '" + COMPANY_PATH + "/%'");
		Query query = manageCompany.getAllCompaniesQuery(queryString);
	    search(query);
	    manageCompany.getSession().logout();
	}
	
	public void quickSearch(UIFormInputSet quickSearchInput) throws Exception {
		UIFormStringInput input = (UIFormStringInput)quickSearchInput.getChild(0);
	    UIFormSelectBox select = (UIFormSelectBox)quickSearchInput.getChild(1);
        if (input.getValue() != null) {
        	queryString.append (" and " + "ecp:" + select.getValue() + " like '" + input.getValue() + "%'");
        }
		manageCompany = (ManageCompany) PortalContainer.getInstance().getComponentInstanceOfType(ManageCompany.class);
		Query query = manageCompany.getAllCompaniesQuery(queryString);
	    search(query);
	    manageCompany.getSession().logout();
	    queryString = new StringBuilder("select * from " + COMPANY_NODETYPE + " where jcr:path like '" + COMPANY_PATH + "/%'");
	}
	
	private void search(Query query) throws Exception {
		grid_.getUIPageIterator().setPageList(new CompanyList(query, 10));
	    UIPageIterator pageIterator = grid_.getUIPageIterator();
	    if (pageIterator.getAvailable() == 0) {
	    	UIApplication uiApp = Util.getPortalRequestContext().getUIApplication();
	        uiApp.addMessage(new ApplicationMessage("UISearchForm.msg.empty", null));
	    }
	}
}