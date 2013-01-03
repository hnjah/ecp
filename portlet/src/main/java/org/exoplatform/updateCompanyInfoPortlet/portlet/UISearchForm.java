package org.exoplatform.updateCompanyInfoPortlet.portlet;

import java.util.List;

import org.exoplatform.commons.serialization.api.annotations.Serialized;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormInputSet;
import org.exoplatform.webui.form.UIFormSelectBox;
import org.exoplatform.webui.form.UIFormStringInput;

@ComponentConfig(lifecycle = UIFormLifecycle.class, template = "/groovy/ecp-portlets/portlet/updateCompanyInfoPortlet/UISearchForm.gtmpl", events = @EventConfig(listeners = UISearchForm.QuickSearchActionListener.class))
@Serialized
public class UISearchForm extends UIForm
{
   /**
    * The name of the quick search set
    */
   final static public String QUICK_SEARCH_SET = "QuickSearchSet";

   public UISearchForm() throws Exception
   {
      UIFormInputSet uiQuickSearchSet = new UIFormInputSet(QUICK_SEARCH_SET);
      uiQuickSearchSet.addUIFormInput(new UIFormStringInput("searchTerm", null, null));
      uiQuickSearchSet.addUIFormInput(new UIFormSelectBox("searchOption", null, null));
      addChild(uiQuickSearchSet);
   }

   public void setOptions(List<SelectItemOption<String>> options)
   {
      UIFormSelectBox uiSelect = (UIFormSelectBox)getQuickSearchInputSet().getChild(1);
      uiSelect.setOptions(options);
   }

   public UIFormInputSet getQuickSearchInputSet()
   {
      return (UIFormInputSet)getChild(0);
   }

   static public class QuickSearchActionListener extends EventListener<UISearchForm>
   {
      public void execute(Event<UISearchForm> event) throws Exception
      {
         UISearchForm uiForm = event.getSource();
         UIUpdateCompanyInfoPortlet uiUpdateCompanyInfoPortlet = uiForm.getParent();
         uiUpdateCompanyInfoPortlet.quickSearch(uiForm.getQuickSearchInputSet());
      }
   }
}