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
		if(LOG.isInfoEnabled()){
			LOG.info("process NewUserEventListener postSave action " + isNew);
		}
		// user created
		if (isNew) {
			try{
				PortalContainer portalContainer = PortalContainer.getInstance();
				MailService mailService = (MailService)portalContainer.getComponentInstanceOfType(MailService.class);
				Message message = new Message();
//				message.setFrom("eXo Customer Portal");
				message.setTo(user.getEmail());
				message.setSubject("New Account created on eXo Customer Portal");
				StringBuffer body = new StringBuffer();
				body.append("<html>");
				body.append("<body>");
				body.append("<div id=':1yf' class='ii gt adP adO'><div id=':1ye'><u></u>");
				body.append("<div>");
				body.append("<table border='0' cellpadding='0' cellspacing='0' width='600' bgcolor='#fff' align='center' style='font-size:12px;color:#666666;line-height:20px;font-family:verdana,arial,tahoma'>");
				body.append("<tbody><tr>");
				body.append("<td align='center' valign='middle' style='border:1px solid #e1e1e1;border-bottom:none'>");
				body.append("<table border='0' cellpadding='0' cellspacing='0' width='92%' bgcolor='#fff' align='center' style='font-size:12px;color:#666666;line-height:20px;font-family:verdana,arial,tahoma'>");
				body.append("<tbody><tr>");
				body.append("<td>");
				body.append("<h1 style='margin:10px 0 0'><span style='font-family:arial,tahoma,serif;color:#417cab;font-size:22px;text-decoration:none'><span class='il'>Welcome</span>!</span></h1>");
				body.append("</td>");
				body.append("</tr>");
				body.append("</tbody></table>");
				body.append("</td>");
				body.append("</tr>");
				body.append("<tr>");
				body.append("<td align='center' style='border:1px solid #e1e1e1;border-top:none;border-bottom:none'>");
				body.append("<table border='0' cellpadding='0' cellspacing='0' width='92%' bgcolor='#fff' align='center' style='font-size:12px;color:#666666;line-height:20px;font-family:verdana,arial,tahoma'>");
				body.append("<tbody><tr>");
				body.append("<td>");
				body.append("<p style='font-family:verdana,arial,tahoma;font-size:12px;margin-top:20px'>Hi ");
				body.append(user.getFullName());
				body.append(",</p>");
				body.append("<p style='font-family:verdana,arial,tahoma;font-size:12px'><span class='il'>Welcome</span> to <strong>eXoplatform</strong> Customer Portal ! <br> Your username is <strong>");
				body.append(user.getUserName());
				body.append("</strong>, your passeword is <strong>");
				body.append(user.getPassword());
				body.append("</strong> and you can access the <strong>eXoplatform</strong> Customer Portal here:</p>");
				body.append(""); 
				body.append("<p style='font-family:verdana,arial,tahoma;font-size:12px'></p><p><a href='http://access.exoplatform.com' target='_blank'>http://access.<span class='il'>eXoplatform</span><wbr>.com</a></p>");
				body.append("<p style='font-family:verdana,arial,tahoma;font-size:12px;margin:15px 0px 20px 0'><strong>The <span class='il'>eXo</span> <span class='il'>Support</span> Team</strong></p>");
				body.append("<p></p></td>");
				body.append("</tr>");
				body.append("</tbody></table>");
				body.append("</td>");
				body.append("</tr>");
				body.append("<tr>");
				body.append("<td bgcolor='#0D86B1' height='160' align='center' style='border:1px solid #0d86b1'>");
				body.append("<table border='0' cellpadding='0' cellspacing='0' width='92%' style='font-size:12px;color:#666666;line-height:20px;font-family:verdana,arial,tahoma'>");
				body.append("<tbody><tr>");
				body.append("<td align='left' valign='top' style='font-family:Verdana,arial,tahoma,serif;color:#fff;font-size:12px'>");
				body.append("<h3 style='margin:20px 0 5px'><a href='http://access.exoplatform.com/' style='font-family:arial,tahoma,serif;color:#fff;font-size:14px;text-decoration:none' title='What is eXo Cloud Workspaces?' target='_blank'>What is <span class='il'>eXo</span> <span class='il'>Customer Portal</span>?</a></h3>");
				body.append("<div style='font-family:verdana,arial,tahoma;font-size:12px'><span class='il'>eXo</span> <span class='il'>Customer Portal</span> enables subscription customers, Partners or leads to access everything provided with their subscription from one convenient location. Anyone can access the eXo Customer Portal. However, some content is available exclusively to eXo customers with active subscriptions.<br> <a href='http://vimeo.com/33936181?autoplay=1' style='font-family:Verdana,arial,tahoma,serif;color:#fff;font-size:12px;text-decoration:underline' title='Watch Video' target='_blank'> Watch Video</a></div>");						
				body.append("</td>");
				body.append("<td align='right' width='270'>");
				body.append("<iframe src='http://player.vimeo.com/video/33936181' width='250' height='125' frameborder='0' webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe> <p><a href='http://vimeo.com/33936181'>Cloud-Workspaces Overview</a> from <a href='http://vimeo.com/user3456521'>eXo</a> on <a href='http://vimeo.com'>Vimeo</a>.</p>");
				body.append("</td>");
				body.append("</tr>");
				body.append("</tbody></table>");
				body.append("</td>");
				body.append("</tr>");		
				body.append("</tbody></table>");
				body.append("<table align='center' border='0' cellpadding='0' cellspacing='0' width='600'>");
				body.append("<tbody><tr>");
				body.append("<td align='center' height='30'>");
				body.append("<a href='http://www.exoplatform.com/company/en/home' style='font-family:arial,tahoma,serif;color:#636363;font-size:11px;text-decoration:none' title='About eXo' target='_blank'>About eXo </a><font color='#636363' size='1'>|</font>");
				body.append("<a href='http://twitter.com/#!/exoplatform' style='font-family:arial,tahoma,serif;color:#407baf;font-size:11px;text-decoration:none' title='@exoplatform on Twitter' target='_blank'>@exoplatform on Twitter  </a><font color='#636363' size='1'>|</font>");
				body.append("<a href='https://www.facebook.com/eXoPlatform' style='font-family:arial,tahoma,serif;color:#407baf;font-size:11px;text-decoration:none' title='@exoplatform on Twitter' target='_blank'> exoplatform on Facebook</a>");
				body.append("");				
				body.append("</td>");
				body.append("</tr>");
				body.append("</tbody></table><div class='yj6qo'></div><div class='adL'>");		
				body.append("</div></div><div class='adL'>");
				body.append("");
				body.append("</div></div></div>");
				body.append("</body>");
				body.append("</html>");
					
				message.setBody(body.toString());
				message.setMimeType(MimeType.XHTML.toString());
				mailService.sendMessage(message);
				if(LOG.isDebugEnabled()){
					LOG.debug("mail sended to " + user.getEmail());
				}
			}catch(Exception e){
				if(LOG.isErrorEnabled()){
					LOG.error("Can't send mail to user " + user.getUserName());
				}
			}
		}
	}
}

