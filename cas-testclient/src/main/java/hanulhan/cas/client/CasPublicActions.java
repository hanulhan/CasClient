/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanulhan.cas.client;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import static com.opensymphony.xwork2.Action.SUCCESS;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class CasPublicActions extends ActionSupport implements SessionAware, ApplicationContextAware {
//public class CasPublicActions extends ActionSupport implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private static final Logger LOGGER = Logger.getLogger(CasPublicActions.class);
    private Map<String, Object> session;
    

    public String doLoginCasUser() {
        LOGGER.log(Level.TRACE, "do login cas user");

//        SystemUserBean systemUser = null;
        try {
            HttpServletRequest request;
            request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
            @SuppressWarnings("rawtypes")
            Map attributes = principal.getAttributes();
            String uid = (String) attributes.get("uid");
            Long lUid = Long.valueOf(uid);
//            systemUser = (SystemUserBean) applicationContext.getBean(SpringUserBeansDef.SystemUserBean, lUid);
//            if (!systemUser.isLoaded()) {
//                LOGGER.log(Level.ERROR, "unable to login cas user with id " + lUid);
//                internalDoLogout();
//                return ERROR;
//            }

            LOGGER.log(Level.DEBUG, "request with local=" + request.getLocale().toString());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "unable to login cas user", e);
//            internalDoLogout();
            return ERROR;
        }



//        if ((locale != null) && (locale.isEmpty())) {
//            LOGGER.log(Level.DEBUG, "locale parameter detected, setting locale to " + locale);
//            Locale newlocale = localefromString(locale);
//            ActionContext.getContext().setLocale(newlocale);
//            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//            HttpSession session = request.getSession(true);
//            session.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, newlocale);
//        }

        return SUCCESS;
    }


    public String doLogout() {
        LOGGER.log(Level.TRACE, "do logout user");
//        logoutHeadline = this.getText("session.logout.headline");
//        logoutReLogin = this.getText("session.logout.login") ;
//        internalDoLogout();
//
//        if (!StringUtils.isEmpty(casServer)) {
//            LOGGER.log(Level.DEBUG, "do logout user from cas server, sending redirect");
//            return LOGOUT_FROM_CAS;
//        }
        return SUCCESS;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session= session;
    }


    
}
