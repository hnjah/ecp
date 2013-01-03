package org.exoplatform.ecp.portlet.subscriptionType;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

@ComponentConfig(
		lifecycle = UIApplicationLifecycle.class,
		template = "/groovy/ecp-portlets/portlet/UIManageSubscriptionTypePortlet/UIManageSubscriptionTypePortlet.gtmpl"
		)
public class UIManageSubscriptionTypePortlet extends UIPortletApplication {

	public UIManageSubscriptionTypePortlet() throws Exception {
		addChild(UILeftSide.class, null, null);
		addChild(UIAddSubscriptionType.class, null, null);
	}
}