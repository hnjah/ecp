package org.exoplatform.ecp.portlet.subscriptionType;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

@ComponentConfig(
		template = "/groovy/ecp-portlets/portlet/UIManageSubscriptionTypePortlet/UILeftSide.gtmpl",
		events = {
			@EventConfig(listeners = UILeftSide.AddActionListener.class),
			@EventConfig(listeners = UILeftSide.DesactivateActionListener.class)
		}
		)
public class UILeftSide extends UIContainer {

	public UILeftSide() throws Exception {

	}
	
	public static class AddActionListener extends EventListener<UILeftSide>{

		public void execute(Event<UILeftSide> event) throws Exception {
			UILeftSide uiLeftSide = event.getSource();
			UIManageSubscriptionTypePortlet uiManageSubscriptionTypePortlet = uiLeftSide.getParent();
			uiManageSubscriptionTypePortlet.removeChild(UIDesactivateSubscriptionType.class);
			uiManageSubscriptionTypePortlet.addChild(UIAddSubscriptionType.class, null, null);
		}
		
	}
	
	public static class DesactivateActionListener extends EventListener<UILeftSide>{
		
		public void execute(Event<UILeftSide> event) throws Exception {
			UILeftSide uiLeftSide = event.getSource();
			UIManageSubscriptionTypePortlet uiManageSubscriptionTypePortlet = uiLeftSide.getParent();
			uiManageSubscriptionTypePortlet.removeChild(UIAddSubscriptionType.class);
			uiManageSubscriptionTypePortlet.addChild(UIDesactivateSubscriptionType.class, null, null);
		}
		
	}
}