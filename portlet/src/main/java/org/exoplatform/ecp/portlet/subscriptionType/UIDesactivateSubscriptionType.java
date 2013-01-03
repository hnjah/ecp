package org.exoplatform.ecp.portlet.subscriptionType;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.form.UIForm;

@ComponentConfig(
		lifecycle = UIFormLifecycle.class,
		template = "/groovy/ecp-portlets/portlet/UIManageSubscriptionTypePortlet/UIDesactivateSubscriptionType.gtmpl"
		)
public class UIDesactivateSubscriptionType extends UIForm {

	public UIDesactivateSubscriptionType() throws Exception {
	}
}