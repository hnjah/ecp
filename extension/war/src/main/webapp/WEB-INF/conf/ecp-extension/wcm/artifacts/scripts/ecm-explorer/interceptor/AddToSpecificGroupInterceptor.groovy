import java.util.logging.Logger;
import org.exoplatform.services.cms.scripts.CmsScript;
import javax.jcr.Node;
import javax.jcr.Session;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.Group;

public class AddToSpecificGroupInterceptor implements CmsScript {
	private static Logger logger = Logger.getLogger("AddToSpecificGroupInterceptor");
	private RepositoryService repositoryService_;
	private OrganizationService organizationService_;

	public AddToSpecificGroupInterceptor(RepositoryService repositoryService, OrganizationService organizationService) {
		repositoryService_ = repositoryService;
		organizationService_ = organizationService;
	}

	public void execute(Object context) {

		// get parameters from dialog ... when creating the action
		String path = (String) context;
		String[] splittedContent = path.split("&workspaceName=");
		String nodePath = splittedContent[0];
		String[] splittedContentAgain = splittedContent[1].split("&repository=");
		String workspace = splittedContentAgain[0];
		Session session = null;
		try {
			session = repositoryService_.getCurrentRepository().getSystemSession(workspace);
			Node srcNode = (Node) session.getItem(nodePath);
			String companyId = srcNode.getProperty("exo:name").getString();
			String companyName = srcNode.getProperty("ecp:name").getString();
			if (srcNode.hasProperty("ecp:type")) {
				String companyType = srcNode.getProperty("ecp:type").getString();
				Group companyTypeGroup = organizationService_.getGroupHandler().findGroupById(companyType)
				if (companyTypeGroup == null){
				    companyTypeGroup = organizationService_.getGroupHandler().createGroupInstance();
				    companyTypeGroup.setGroupName(companyType);
					companyTypeGroup.setLabel(companyType);
					companyTypeGroup.setDescription("the " + companyType + " group");
					organizationService_.getGroupHandler().addChild(null, companyTypeGroup, true);
				}
				Group companyGroup = organizationService_.getGroupHandler().createGroupInstance();
				companyGroup.setGroupName(companyId);
			    companyGroup.setLabel(companyName);
			    companyGroup.setDescription("the " + companyId + " group");
				organizationService_.getGroupHandler().addChild(companyTypeGroup, companyGroup, true);
			}
			
		} catch (Exception e) {
			logger.warning("Error in AddToSpecificGroupInterceptor script : " + e.printStackTrace());
		} finally {
			if (session != null) {
				session.logout();
			}
		}

	}

	public void setParams(String[] params) {
	}
}
