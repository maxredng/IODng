/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.iot.misc.Feed;
import com.redjsparser.JSFile;
import java.io.File;

/**
 *
 * @author bsati
 */
public class JS extends JSFile{
    private String project;
    
    public JS(String s)
    {
        super(s);
    }
    
    public JS(File f,String pro)
    {
        super(f);
        project = pro;
        Persist();
    }
    
    final void Persist()
    {
          Feed feed = new Feed();
          feed.init();
          feed.add("project", project);
          feed.add("parent", "root");
          feed.add("order", 0);
          feed.add("type", "none");
          
          RedRecord root = new RedRecord(feed,"");
    }
    
    
}
