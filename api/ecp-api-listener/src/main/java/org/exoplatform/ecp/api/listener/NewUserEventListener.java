package org.exoplatform.ecp.api.listener;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.mail.MailService;
import org.exoplatform.services.mail.Message;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserEventListener;
import org.exoplatform.web.url.MimeType;

/**
 * Created with IntelliJ IDEA.
 * User: houssem
 * Date: 25/12/12
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class NewUserEventListener extends UserEventListener {

	private static Log LOG = ExoLogger.getLogger(NewUserEventListener.class);

	public void postSave(User user, boolean isNew) throws Exception {
		LOG.info("process NewUserEventListener postSave action " + isNew);
		// user created
		if (isNew) {
				PortalContainer portalContainer = PortalContainer.getInstance();
				MailService mailService = (MailService)portalContainer.getComponentInstanceOfType(MailService.class);
				Message message = new Message();
//				message.setFrom("eXo Customer Portal");
				message.setTo(user.getEmail());
				message.setSubject("New Account created on eXo Customer Portal");
				StringBuffer body = new StringBuffer();
				body.append("Hello, you have a new account to the eXo Customer Portal.<br>your username is " + user.getUserName());
				body.append("<br> your password is " + user.getPassword());
				body.append("<br> Best regards");
				message.setBody(body.toString());
				message.setMimeType(MimeType.XHTML.toString());
				mailService.sendMessage(message);
		}
	}
}

