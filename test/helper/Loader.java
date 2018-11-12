/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.iot.misc.Feed;
import com.iot.web.HTML;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class Loader {
    
    public void loadScreen(){
        String s = System.getProperty("user.dir") + "\\web\\constructor.html";
        HTML html = null;
        try {
            html = new HTML(FileUtils.readFileToString(new File(s), "UTF-8"));
                Feed feed = new Feed();
    feed.init();
    feed.add("boss", "redone");
    feed.add("id", "redoneid");
    feed.add("project", "admin");
    feed.add("scope", "redconstructor");
    feed.add("parent","main");
    html.set(feed);
    html.item.forEach(e -> e.save());
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    @Test
    public void test(){
        loadScreen();
    }
    
}
