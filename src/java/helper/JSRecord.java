/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.iot.misc.Feed;
import com.iot.misc.data.Column;
import com.iot.misc.data.Schema;
import com.iot.misc.data.Table;
import java.util.List;
 

/**
 *
 * @author bsati
 */
public class JSRecord {
    
    private String[] line;
    private Feed feed;
    
    public String id;
    public String parent;
    public String project;
    public String body;
    public String type;
    public int order;
    
    public List<JSRecord> item;
    
    
    public JSRecord(String[] l)
    {   
        line = l;
        feed = new Feed();
        feed.init();
        populate();
    }

    private void populate() {

        Schema sc = (Schema)feed.getObject("schema_config");
        Table js = sc.getTable("javascript");
         
        js.populateColumns(line);
 
        id = (String)js.getValue("bid");
        parent = (String)js.getValue("parent");
        project = (String)js.getValue("project");
        body = (String)js.getValue("body");
        type = (String)js.getValue("type");
        order = Integer.parseInt((String)js.getValue("ord"));
        
    }
    
}
