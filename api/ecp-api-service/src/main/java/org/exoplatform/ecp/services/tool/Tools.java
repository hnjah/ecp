package org.exoplatform.ecp.services.tool;

import javax.jcr.Session;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;

public class Tools {
	
	public static Session getSession(String workspace) throws Exception {
		PortalContainer portalContainer = PortalContainer.getInstance();
		RepositoryService repositoryService = (RepositoryService) portalContainer.getComponentInstanceOfType(RepositoryService.class);
		ManageableRepository manageableRepository = repositoryService.getCurrentRepository();
		SessionProviderService sessionProviderService = (SessionProviderService) portalContainer.getComponentInstanceOfType(SessionProviderService.class);
	    SessionProvider sessionProvider = sessionProviderService.getSessionProvider(null);
		Session session = null;
		try{
			session = sessionProvider.getSession(workspace, manageableRepository);
		}catch (Exception e) {
			throw new Exception();
		}
		return session;
	}
	
}
