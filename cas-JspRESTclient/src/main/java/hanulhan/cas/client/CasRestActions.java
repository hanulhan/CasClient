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
public class CasRestActions extends ActionSupport implements SessionAware, ApplicationContextAware {
//public class CasPublicActions extends ActionSupport implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private static final Logger LOGGER = Logger.getLogger(CasRestActions.class);
    private Map<String, Object> session;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
