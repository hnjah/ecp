package org.exoplatform.updateCompanyInfoPortlet.portlet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.form.UISearchForm;

@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "/groovy/ecp-portlets/portlet/updateCompanyInfoPortlet/UIUpdateCompanyInfoPortlet.gtmpl")
public class UIUpdateCompanyInfoPortlet extends UIPortletApplication {

	public static final String COMPANY_NAME = "name";
	
	public static final String COUNTRY = "country";

	public static final String TYPE = "type";

	@SuppressWarnings("unchecked")
	private final static List<SelectItemOption<String>> OPTIONS_ = Collections.unmodifiableList(Arrays.asList(new SelectItemOption<String>(COMPANY_NAME, COMPANY_NAME), new SelectItemOption<String>(COUNTRY, COUNTRY), new SelectItemOption<String>(TYPE, TYPE)));

	public UIUpdateCompanyInfoPortlet() throws Exception {
		UISearchForm uiForm = addChild(UISearchForm.class, null, null);
	    uiForm.setOptions(OPTIONS_);
	}
}