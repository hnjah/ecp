package org.exoplatform.ecp.portlet.subscriptionType;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.ecp.model.SubscriptionType;
import org.exoplatform.ecp.services.manager.ManageCategory;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIFormSelectBox;

@ComponentConfig(
		template = "/groovy/ecp-portlets/portlet/UIManageSubscriptionTypePortlet/UIAddSubscriptionTypeSummary.gtmpl",
		events = {
				@EventConfig(listeners = UIAddSubscriptionTypeSummary.ConfirmActionListener.class),
				@EventConfig(listeners = UIAddSubscriptionTypeSummary.CancelActionListener.class)
		}
		)
public class UIAddSubscriptionTypeSummary extends UIContainer{
	
	private SubscriptionType subscriptionType;
	private String description;
	private String sku;

	public void load(String description, String sku, SubscriptionType subscriptionType) throws Exception{
		this.subscriptionType = subscriptionType;
		subscriptionType.setName(sku);
		this.description = description;
		this.sku = sku;
	}
	
	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public static class ConfirmActionListener extends EventListener<UIAddSubscriptionTypeSummary> {
		public void execute(Event<UIAddSubscriptionTypeSummary> event) throws Exception {
			
			UIAddSubscriptionTypeSummary uiAddSubscriptionTypeSummary = event.getSource();
			
			SubscriptionType subscriptionType = uiAddSubscriptionTypeSummary.getSubscriptionType();
			
			PortalContainer portalContainer = PortalContainer.getInstance();
			ManageCategory manageCategory = (ManageCategory)portalContainer.getComponentInstanceOfType(ManageCategory.class);
			
			
		}
	}
	public static class CancelActionListener extends EventListener<UIAddSubscriptionTypeSummary> {
		public void execute(Event<UIAddSubscriptionTypeSummary> event) throws Exception {
			
			UIAddSubscriptionTypeSummary uiAddSubscriptionTypeSummary = event.getSource();
			UIAddSubscriptionType uiAddSubscriptionType = uiAddSubscriptionTypeSummary.getParent();
			UIAddSubscriptionTypeFiled uiAddSubscriptionTypeFiled = uiAddSubscriptionType.getChild(UIAddSubscriptionTypeFiled.class); 
			
			for(UIComponent uiComponent:uiAddSubscriptionTypeFiled.getChildren()){
				UIFormSelectBox uiFormSelectBox = (UIFormSelectBox)uiComponent;
				uiFormSelectBox.setDisabled(false);
			}
			
			UIComponent uiComponent = uiAddSubscriptionType.getChild(UIAddSubscriptionTypeSummary.class);
			if(uiComponent != null){
				uiAddSubscriptionType.removeChild(UIAddSubscriptionTypeSummary.class);
			}
			
			UIFormSelectBox uiFormCategorySelectBox = uiAddSubscriptionType.getChild(UIFormSelectBox.class);
			uiFormCategorySelectBox.setDisabled(false);
			
			event.getRequestContext().addUIComponentToUpdateByAjax(uiAddSubscriptionType);
		}
	}

}
