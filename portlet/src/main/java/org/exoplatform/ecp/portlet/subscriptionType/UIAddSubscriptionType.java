package org.exoplatform.ecp.portlet.subscriptionType;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.ecp.model.Category;
import org.exoplatform.ecp.services.manager.ManageCategory;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormSelectBox;

@ComponentConfig(
		lifecycle = UIFormLifecycle.class,
		template = "/groovy/ecp-portlets/portlet/UIManageSubscriptionTypePortlet/UIAddSubscriptionType.gtmpl"
		)
public class UIAddSubscriptionType extends UIForm {

	public UIAddSubscriptionType() throws Exception {
		PortalContainer portalContainer = PortalContainer.getInstance();
		ManageCategory manageCategory = (ManageCategory)portalContainer.getComponentInstanceOfType(ManageCategory.class);
		List<Category> categories = manageCategory.getAllCategories();
		List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
		for(Category category:categories){
			options.add(new SelectItemOption<String>(category.getName()));
		}
		UIFormSelectBox uiFormSelectBox = new UIFormSelectBox("category", null, options);  
		addUIFormInput(uiFormSelectBox);
	}
}