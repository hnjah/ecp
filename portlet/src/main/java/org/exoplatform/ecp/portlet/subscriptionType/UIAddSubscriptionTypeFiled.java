package org.exoplatform.ecp.portlet.subscriptionType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.ecp.model.Category;
import org.exoplatform.ecp.model.SubscriptionType;
import org.exoplatform.ecp.services.Constants;
import org.exoplatform.ecp.services.ParameterizedListService;
import org.exoplatform.ecp.services.manager.ManageCategory;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIFormSelectBox;

@ComponentConfig(
		template = "app:/groovy/ecp-portlets/portlet/UIManageSubscriptionTypePortlet/UIAddSubscriptionTypeFiled.gtmpl",
		events = {
				@EventConfig(listeners = UIAddSubscriptionTypeFiled.AddActionListener.class)
		}
		)
public class UIAddSubscriptionTypeFiled extends UIContainer{
	
	public void load(String categoryId){
		PortalContainer portalContainer = PortalContainer.getInstance();
		ManageCategory manageCategory = (ManageCategory)portalContainer.getComponentInstanceOfType(ManageCategory.class);
		
		Category category = manageCategory.getCategory(categoryId);
		
		ParameterizedListService parameterizedListService = (ParameterizedListService)portalContainer.getComponentInstanceOfType(ParameterizedListService.class);
		
		if(category.isProduct()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getProduct().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getProduct().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.PRODUCT, Constants.PRODUCT, options);
			addChild(uiFormSelectBox);
		}
		if(category.isFamily()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getFamily().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getFamily().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.FAMILY, Constants.FAMILY, options);
			addChild(uiFormSelectBox);
		}
		if(category.isSla()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getSla().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getSla().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.SLA, Constants.SLA, options);
			addChild(uiFormSelectBox);
		}
		if(category.isPartnership()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getPartnership().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getPartnership().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.PARTNERSHIP, Constants.PARTNERSHIP, options);
			addChild(uiFormSelectBox);
		}
		if(category.isTraining()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getTraining().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getTraining().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.TRAINING, Constants.TRAINING, options);
			addChild(uiFormSelectBox);
		}
		if(category.isDuration()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getDuration().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getDuration().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.DURATION, Constants.DURATION, options);
			addChild(uiFormSelectBox);
		}
		if(category.isCore()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getCore().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getCore().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.CORE, Constants.CORE, options);
			addChild(uiFormSelectBox);
		}
		if(category.isLocation()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getLocation().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getLocation().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.LOCATION, Constants.LOCATION, options);
			addChild(uiFormSelectBox);
		}
		if(category.isConsultingDuration()){
			List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
			Iterator<String> iterator = parameterizedListService.getConsultingDuration().keySet().iterator();
			while(iterator.hasNext()){
				String value = iterator.next();
				String label = parameterizedListService.getConsultingDuration().getProperty(value);
				options.add(new SelectItemOption<String>(label, value));
			}
			UIFormSelectBox uiFormSelectBox = new UIFormSelectBox(Constants.CONSULTING_DURATION, Constants.CONSULTING_DURATION, options);
			addChild(uiFormSelectBox);
		}
	}
	
	public static class AddActionListener extends EventListener<UIAddSubscriptionTypeFiled> {
		public void execute(Event<UIAddSubscriptionTypeFiled> event) throws Exception {

			PortalContainer portalContainer = PortalContainer.getInstance();
			ParameterizedListService parameterizedListService = (ParameterizedListService)portalContainer.getComponentInstanceOfType(ParameterizedListService.class);
			
			SubscriptionType subscriptionType = new SubscriptionType();
			StringBuffer description = new StringBuffer();
			StringBuffer sku = new StringBuffer();
			UIAddSubscriptionTypeFiled uiAddSubscriptionTypeFiled = event.getSource();
			
			for(UIComponent uiComponent:uiAddSubscriptionTypeFiled.getChildren()){
				UIFormSelectBox uiFormSelectBox = (UIFormSelectBox)uiComponent;
				uiFormSelectBox.setDisabled(true);
				if(Constants.PRODUCT.equals(uiFormSelectBox.getId())){
					subscriptionType.setProduct(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getProduct().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.FAMILY.equals(uiFormSelectBox.getId())){
					subscriptionType.setFamily(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getFamily().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.SLA.equals(uiFormSelectBox.getId())){
					subscriptionType.setSla(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getSla().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.DURATION.equals(uiFormSelectBox.getId())){
					subscriptionType.setDuration(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getDuration().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.CORE.equals(uiFormSelectBox.getId())){
					subscriptionType.setCore(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getCore().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.PARTNERSHIP.equals(uiFormSelectBox.getId())){
					subscriptionType.setPartnership(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getPartnership().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.TRAINING.equals(uiFormSelectBox.getId())){
					subscriptionType.setTraining(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getTraining().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.LOCATION.equals(uiFormSelectBox.getId())){
					subscriptionType.setLocation(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getLocation().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				else if(Constants.CONSULTING_DURATION.equals(uiFormSelectBox.getId())){
					subscriptionType.setConsultingDuration(uiFormSelectBox.getValue());
					String value = uiFormSelectBox.getValue();
					String label = parameterizedListService.getConsultingDuration().get(value);
					sku.append("-").append(value);
					description.append(" - ").append(label);
				}
				
			}
			
			UIAddSubscriptionType uiAddSubscriptionType = uiAddSubscriptionTypeFiled.getParent();
			
			UIFormSelectBox uiFormCategorySelectBox = uiAddSubscriptionType.getChild(UIFormSelectBox.class);
			uiFormCategorySelectBox.setDisabled(true);
			
			UIComponent uiComponent = uiAddSubscriptionType.getChild(UIAddSubscriptionTypeSummary.class);
			if(uiComponent != null){
				uiAddSubscriptionType.removeChild(UIAddSubscriptionTypeSummary.class);
			}
			
			subscriptionType.setParentCategory(uiFormCategorySelectBox.getValue());
			
			uiAddSubscriptionType.addChild(UIAddSubscriptionTypeSummary.class, null, "uiAddSubscriptionTypeSummary");
			UIAddSubscriptionTypeSummary uiAddSubscriptionTypeSummary = uiAddSubscriptionType.getChild(UIAddSubscriptionTypeSummary.class);
			uiAddSubscriptionTypeSummary.load(description.substring(3), sku.substring(1), subscriptionType);
			
			event.getRequestContext().addUIComponentToUpdateByAjax(uiAddSubscriptionType);
		}
	}

}
