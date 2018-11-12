/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bsati
 */
public class Misc {
    
    
    public static String getValueFromRegex(String body,String regex)
    {
        String result = null;
            
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        
        while(matcher.find())
        {
            result = matcher.group(1);
        }
    
        return result;
    }
    
    public static String getSignature(HttpServletRequest request)
    {
        String result = request.getRequestURI().replace("/", "sLash").replace("?", "QueStion").replace("=", "EquaLs");
    
        Map<String,String[]> par = request.getParameterMap();
        
        Iterator it = par.entrySet().iterator();
        
        while(it.hasNext())
        {
            Map.Entry<String,String[]> map = (Map.Entry)it.next();
            String key = map.getKey();
            String val = (String)map.getValue()[0];
            result = result + key + "EqualS" + val + "~";
        
        }
        HttpSession session = request.getSession();
        Enumeration sespar = session.getAttributeNames();
        
        while(sespar.hasMoreElements())
        {
            String key = (String)sespar.nextElement();
            String val = (String)session.getAttribute(key);
            
            result = result + key + "EqualS" + val + "~";
        }
        
        return result;
    }
    
        public static String getSignatureFromMap(RedRequest request)
    {
//        String result = request.host.replace("/", "sLash").replace("?", "QueStion").replace("=", "EquaLs");
//    
//        Map<String,String[]> par = request.parameters;
//        
//        Iterator it = par.entrySet().iterator();
//        
//        while(it.hasNext())
//        {
//            Map.Entry<String,String[]> map = (Map.Entry)it.next();
//            String key = map.getKey();
//            String val = (String)map.getValue()[0];
//            result = result + key + "EqualS" + val + "~";
//        
//        }
//        HttpSession session = request.getSession();
//        Enumeration sespar = session.getAttributeNames();
//        
//        while(sespar.hasMoreElements())
//        {
//            String key = (String)sespar.nextElement();
//            String val = (String)session.getAttribute(key);
//            
//            result = result + key + "EqualS" + val + "~";
//        }
//        
//        return result;
        return "";
    }
    
    
}
