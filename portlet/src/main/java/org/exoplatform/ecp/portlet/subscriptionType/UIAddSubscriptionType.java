package org.exoplatform.ecp.portlet.subscriptionType;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.ecp.model.Category;
import org.exoplatform.ecp.services.manager.ManageCategory;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.Event.Phase;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormSelectBox;

@ComponentConfig(
		lifecycle = UIFormLifecycle.class,
		template = "/groovy/ecp-portlets/portlet/UIManageSubscriptionTypePortlet/UIAddSubscriptionType.gtmpl",
		events = {
			@EventConfig(listeners = UIAddSubscriptionType.ChangeCategoryActionListener.class, phase = Phase.DECODE)
		}
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
		UIFormSelectBox uiFormCategorySelectBox = new UIFormSelectBox("category", "category", options);
		uiFormCategorySelectBox.setOnChange("ChangeCategory");
		addUIFormInput(uiFormCategorySelectBox);
		
		addChild(UIAddSubscriptionTypeFiled.class, null, "uiAddSubscriptionTypeFiled");
		UIAddSubscriptionTypeFiled uiAddSubscriptionTypeFiled = getChild(UIAddSubscriptionTypeFiled.class);
		uiAddSubscriptionTypeFiled.load(options.get(0).getValue());
		
		setActions(new String[] {});
		
	}
	
	public static class ChangeCategoryActionListener extends EventListener<UIAddSubscriptionType> {
		public void execute(Event<UIAddSubscriptionType> event) throws Exception {
			String categoryChildId = event.getRequestContext().getRequestParameter(OBJECTID);
			UIAddSubscriptionType uiAddSubscriptionType = event.getSource();
			
			UIFormSelectBox uiFormCategorySelectBox = uiAddSubscriptionType.getChildById(categoryChildId);
			String categoryId = uiFormCategorySelectBox.getValue();
			
			uiAddSubscriptionType.removeChild(UIAddSubscriptionTypeFiled.class);
			UIComponent uiComponent = uiAddSubscriptionType.getChild(UIAddSubscriptionTypeSummary.class);
			if(uiComponent != null){
				uiAddSubscriptionType.removeChild(UIAddSubscriptionTypeSummary.class);
			}
			uiAddSubscriptionType.addChild(UIAddSubscriptionTypeFiled.class, null, "UIAddSubscriptionTypeFiled");
			UIAddSubscriptionTypeFiled uiAddSubscriptionTypeFiled = uiAddSubscriptionType.getChild(UIAddSubscriptionTypeFiled.class);
			uiAddSubscriptionTypeFiled.load(categoryId);
			
			event.getRequestContext().addUIComponentToUpdateByAjax(uiAddSubscriptionType);
		}
	}
	
}