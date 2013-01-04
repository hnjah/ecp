package org.exoplatform.activateUsersPortlet.portlet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.exoplatform.portal.config.UserACL;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.organization.Group;
import org.exoplatform.services.organization.Membership;
import org.exoplatform.services.organization.MembershipType;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.Query;
import org.exoplatform.services.organization.User;
import org.exoplatform.web.application.ApplicationMessage;
import org.exoplatform.webui.application.WebuiRequestContext;
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

@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "/groovy/ecp-portlets/portlet/activateUsersPortlet/UIActivateUsersPortlet.gtmpl", events = {
	   @EventConfig(listeners = UIActivateUsersPortlet.ActivateUserActionListener.class, confirm = "UIActivateUsersPortlet.activateUser"),
	   @EventConfig(listeners = UIActivateUsersPortlet.DesactivateUserActionListener.class, confirm = "UIActivateUsersPortlet.desactivateUser")}) 
public class UIActivateUsersPortlet extends UIPortletApplication {

	private static final String USER_NAME = "userName";

	private static final String LAST_NAME = "lastName";

	private static final String FIRST_NAME = "firstName";

	private static final String EMAIL = "email";

	private static final String[] USER_BEAN_FIELD = {USER_NAME, LAST_NAME, FIRST_NAME, EMAIL};

	private static final String[] USER_ACTION = {"ActivateUser","DesactivateUser"};
	
	private static final String USERS_GROUP = "/platform/users";
	
	private static final String MEMBER_MEMBERSHIPTYPE = "member";
	
	public static final String DESACTIVATE = ".desactivate";
	
	private Query lastQuery_;

	private String userSelected_;

	private UIGrid grid_;

	@SuppressWarnings("unchecked")
	public UIActivateUsersPortlet() throws Exception {
		UISearchForm uiForm = addChild(UISearchForm.class, null, null);
		List<SelectItemOption<String>> options = Collections.unmodifiableList(Arrays.asList(new SelectItemOption<String>(USER_NAME, USER_NAME), new SelectItemOption<String>(LAST_NAME, LAST_NAME), new SelectItemOption<String>(FIRST_NAME, FIRST_NAME), new SelectItemOption<String>(EMAIL, EMAIL)));
	    uiForm.setOptions(options);
	    grid_ = addChild(UIGrid.class, null, "UIListUsersGird");
	    grid_.configure(USER_NAME, USER_BEAN_FIELD, USER_ACTION);
	    grid_.getUIPageIterator().setId("UIListUsersIterator");
	    grid_.getUIPageIterator().setParent(this);
	    search(new Query());
	}
	
	@Override
	public void processRender(WebuiRequestContext context) throws Exception {
		int curPage = grid_.getUIPageIterator().getCurrentPage();
	    if (lastQuery_ == null) {
	    	lastQuery_ = new Query();	    	
	    }
	    search(lastQuery_);
	    grid_.getUIPageIterator().setCurrentPage(curPage);
	    grid_.getUIPageIterator().getCurrentPageData();
	    super.processRender(context);
	}

	public void setUserSelected(String userName) {
		userSelected_ = userName;
	}

	public String getUserSelected() {
		return userSelected_;
	}
	   
	public UISearchForm getUISearchForm() {
		return (UISearchForm)getChild(0);
	}

	public void search(Query query) throws Exception {
		lastQuery_ = query;
	    grid_.getUIPageIterator().setPageList(new FindUsersPageList(query, 10));
	    UIPageIterator pageIterator = grid_.getUIPageIterator();
	    if (pageIterator.getAvailable() == 0) {
	    	UIApplication uiApp = Util.getPortalRequestContext().getUIApplication();
	        uiApp.addMessage(new ApplicationMessage("UISearchForm.msg.empty", null));
	    }
	}

	public void quickSearch(UIFormInputSet quickSearchInput) throws Exception {
		Query query = new Query();
		UIFormStringInput input = (UIFormStringInput)quickSearchInput.getChild(0);
	    UIFormSelectBox select = (UIFormSelectBox)quickSearchInput.getChild(1);
	    String name = input.getValue();
	    if (name == null || name.equals("")) {
	    	search(new Query());
	        return;
	    }
	    if (name.indexOf("*") < 0) {
	    	if (name.charAt(0) != '*') {
	    		name = "*" + name;
	        }
	        if (name.charAt(name.length() - 1) != '*') {
	        	name += "*";
	        }
	    }
	    name = name.replace('?', '_');
	    String selectBoxValue = select.getValue();
	    if (selectBoxValue.equals(USER_NAME)) {
	    	query.setUserName(name);
	    }
	    if (selectBoxValue.equals(LAST_NAME)) {
	    	query.setLastName(name);
	    }
	    if (selectBoxValue.equals(FIRST_NAME)) {
	    	query.setFirstName(name);
	    }
	    if (selectBoxValue.equals(EMAIL)) {
	    	 query.setEmail(name);
	    }
	    search(query);
	}

	static public class ActivateUserActionListener extends EventListener<UIActivateUsersPortlet> {
		public void execute(Event<UIActivateUsersPortlet> event) throws Exception {
			UIActivateUsersPortlet uiActivateUsersPortlet = event.getSource();
	        String userName = event.getRequestContext().getRequestParameter(OBJECTID);
	        OrganizationService service = uiActivateUsersPortlet.getApplicationComponent(OrganizationService.class);
	        UIPageIterator pageIterator = uiActivateUsersPortlet.getChild(UIGrid.class).getUIPageIterator();
	        int currentPage = pageIterator.getCurrentPage();
	        uiActivateUsersPortlet.search(uiActivateUsersPortlet.lastQuery_);
	        pageIterator.setCurrentPage(currentPage);
	        User currentUser = service.getUserHandler().findUserByName(userName);
	        currentUser.setEmail(currentUser.getEmail().split(DESACTIVATE)[0]);
	        Group usersGroup = service.getGroupHandler().findGroupById(USERS_GROUP);
	        MembershipType memberMembershipType = service.getMembershipTypeHandler().findMembershipType(MEMBER_MEMBERSHIPTYPE);
	        service.getMembershipHandler().linkMembership(currentUser, usersGroup, memberMembershipType, true);
	        service.getUserHandler().saveUser(currentUser, true);
	        event.getRequestContext().addUIComponentToUpdateByAjax(uiActivateUsersPortlet);
	    }
	}
	
	static public class DesactivateUserActionListener extends EventListener<UIActivateUsersPortlet> {
		public void execute(Event<UIActivateUsersPortlet> event) throws Exception {
			UIActivateUsersPortlet uiActivateUsersPortlet = event.getSource();
	        String userName = event.getRequestContext().getRequestParameter(OBJECTID);
	        OrganizationService service = uiActivateUsersPortlet.getApplicationComponent(OrganizationService.class);
	        UserACL userACL = uiActivateUsersPortlet.getApplicationComponent(UserACL.class);
	        if (userACL.getSuperUser().equals(userName)) {
	        	UIApplication uiApp = event.getRequestContext().getUIApplication();
	            uiApp.addMessage(new ApplicationMessage("UIActivateUsersPortlet.msg.DesactivateSuperUser", new String[]{userName}, ApplicationMessage.WARNING));
	            return;
	         }
	        UIPageIterator pageIterator = uiActivateUsersPortlet.getChild(UIGrid.class).getUIPageIterator();
	        int currentPage = pageIterator.getCurrentPage();
	        uiActivateUsersPortlet.search(uiActivateUsersPortlet.lastQuery_);
	        pageIterator.setCurrentPage(currentPage);
	        User currentUser = service.getUserHandler().findUserByName(userName);
	        currentUser.setEmail(currentUser.getEmail() + DESACTIVATE);
	        Membership membership = service.getMembershipHandler().findMembershipByUserGroupAndType(userName, USERS_GROUP, MEMBER_MEMBERSHIPTYPE);
	        service.getMembershipHandler().removeMembership(membership.getId(), true);
	        service.getUserHandler().saveUser(currentUser, true);
	        event.getRequestContext().addUIComponentToUpdateByAjax(uiActivateUsersPortlet);
	    }
	}
}