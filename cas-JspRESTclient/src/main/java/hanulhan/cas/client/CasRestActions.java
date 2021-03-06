/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanulhan.cas.client;

import com.acentic.cloudservices.util.JsonStatus;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Level;
import org.apache.struts2.interceptor.SessionAware;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class CasRestActions extends ActionSupport implements SessionAware, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private static final Logger LOGGER = Logger.getLogger(CasRestActions.class);
    private Map<String, Object> session;

    private final static String CAS_SERVER_URL = "https://cas.acentic.com/CasServer";
//    private final static String CAS_SERVER_URL = "https://dev-cas-server:443/CasServer";


    private final static String GET_URL = "https://acs.acentic.com/CloudServices/public/doLoginCasUserManual.action";
//    private final static String GET_URL = "https://acs.acentic.com/CloudServices/public/doLoginCasUser.action";
//    private final static String GET_URL = "https://de-ws-16:18443/CloudServices/public/doLoginCasUserManual.action";
//    private final static String GET_URL = "https://de-ws-16:18443/CloudServices";
    
    
    private final static String CAS_SERVICE = "https://acs.acentic.com/CloudServices/core/doShowMain.action";
//    private final static String CAS_SERVICE = "https://de-ws-16:18443/CloudServices/gpns/doAccessSLATyps";
//    private final static String CAS_SERVICE = "https://de-ws-16:18443/CloudServices/gpns/getCircuitTyps.action";


//    
    private final String USER_AGENT = "Mozilla/5.0";

    private String cookies;
    private HttpClient client = HttpClientBuilder.create().build();
    private JsonStatus jsonStatus = new JsonStatus();

    String redirectUrl = "";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String doLoginCasRest() {

        jsonStatus = new JsonStatus();
        String myUrlParameters = "username=uhansen01&password=Ava030374Lon_";
        CookieHandler.setDefault(new CookieManager());
        StringBuilder myResult;
        String myServiceTicketUrl = null;
        String myTgt = "";
        String myServiceTicket = "";
        List<NameValuePair> postParams = new ArrayList<>();
        HttpResponse myResponse;

        // GET the TGT
        try {
            myResponse = sendPost(CAS_SERVER_URL + "/v1/tickets?" + myUrlParameters, postParams);
            if (myResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_CREATED) {
                myResult = getHttpResponseResult(myResponse);
                if (myResult != null && myResult.length() > 0) {
                    myServiceTicketUrl = getServiceTicketUrl(myResult.toString());
                    if (myServiceTicketUrl != null && myServiceTicketUrl.length() > 0) {
                        LOGGER.log(Level.INFO, "ServiceTicketURL: " + myServiceTicketUrl);
                        myTgt = getTGT(myServiceTicketUrl);
                        if (myTgt != null && myTgt.length() > 0) {
                            LOGGER.log(Level.INFO, "TGT: " + myTgt);
                        }
                    }
                } else {
                    LOGGER.log(Level.ERROR, "Status Code: " + myResponse.getStatusLine().getStatusCode());
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }

        // Get the ServiceTicket
        try {
            if (myTgt != null && myTgt.length() > 0) {
                postParams.add(new BasicNameValuePair("service", CAS_SERVICE));
                myResponse = sendPost(myServiceTicketUrl, postParams);
                if (myResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                    myServiceTicket = getHttpResponseResult(myResponse).toString();
                    LOGGER.log(Level.INFO, "ServiceTicket: " + myServiceTicket);
                    redirectUrl = GET_URL + "?authServiceTicket=" + myServiceTicket + "&authService=" + CAS_SERVICE;
                    LOGGER.log(Level.INFO, "ServiceURL: " + redirectUrl);
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }

        return SUCCESS;

    }

    public String doLogoutCasUser() {

        jsonStatus = new JsonStatus();
        CookieHandler.setDefault(new CookieManager());
        StringBuilder myResult;
        return SUCCESS;
    }

    private HttpResponse sendPost(String url, List<NameValuePair> postParams) {

        HttpPost post = new HttpPost(url);

        // add header
//        post.setHeader("Host", CAS_SERVER_URL);
//        post.setHeader("User-Agent", USER_AGENT);
//        post.setHeader("Accept",
//                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        post.setHeader("Accept-Language", "en-US,en;q=0.5");
//        post.setHeader("Cookie", getCookies());
//        post.setHeader("Connection", "keep-alive");
////        post.setHeader("Referer", "https://accounts.google.com/ServiceLoginAuth");
//        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            post.setEntity(new UrlEncodedFormEntity(postParams));
        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.ERROR, ex);
        }

        HttpResponse myResponse = null;
        try {
            myResponse = client.execute(post);
            int responseCode = myResponse.getStatusLine().getStatusCode();

            LOGGER.log(Level.DEBUG, "Sending 'POST' request to URL : " + url);
            LOGGER.log(Level.DEBUG, "Post parameters : " + postParams);
            LOGGER.log(Level.DEBUG, "Response Code : " + responseCode);

        } catch (IOException ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return myResponse;

    }

    public StringBuilder getHttpResponseResult(HttpResponse response) {
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException | UnsupportedOperationException ex) {
            LOGGER.log(Level.ERROR, ex);
        }

        StringBuilder result = new StringBuilder();
        String line = "";
        try {
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            // System.out.println(result.toString());
        } catch (IOException ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return (result);
    }

    public String getServiceTicketUrl(String aString) {
        Matcher myMatcher;
        Pattern myPattern = Pattern.compile("https[a-zA-Z0-9\\/:\\-\\.]*TGT-[0-9]+-[a-zA-Z0-9]*-[a-z\\.\\-]*");
        myMatcher = myPattern.matcher(aString);

        if (myMatcher.find()) {
            return myMatcher.group();
        } else {
            return null;
        }

    }

    public String getTGT(String aString) {
        Matcher myMatcher;
        Pattern myPattern;
        myPattern = Pattern.compile("TGT-[0-9]*-[a-zA-Z0-9]*[a-z\\,\\.\\-]");
        myMatcher = myPattern.matcher(aString);

        if (myMatcher.find()) {
            return myMatcher.group();
        } else {
            return null;
        }

    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }


    
    
    

    public JsonStatus getJsonStatus() {
        return jsonStatus;
    }

    public void setJsonStatus(JsonStatus jsonStatus) {
        this.jsonStatus = jsonStatus;
    }

}
