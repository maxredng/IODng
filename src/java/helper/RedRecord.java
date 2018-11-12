/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.iot.misc.Feed;
import com.iot.misc.data.Schema;
import com.iot.misc.data.Table;
import com.redjsparser.Record;

/**
 *
 * @author bsati
 */
public class RedRecord extends Record{
    
    private Feed feed;
   
        public RedRecord(Feed f,String bo)
    {
        feed = f;
        body = bo;
        populate();
    }

    private void populate() 
    {
        project = (String)feed.get("project");
        parent = (String)feed.get("parent");
        order = (int)feed.get("order");
        type = (String)feed.get("type");
        bid = getBid();
        if(!type.equals("none")){
        Object[] row = {bid,parent,project,body,type,order};
        String query = "insert into config.javascript values ('" + bid + "','" + parent + "','" + project + "','"
                + body + "','" + type + "'," + order + ")";
        Schema sc = (Schema)feed.get("schema_config");
        Table t = sc.getTable("javascript");
        t.populateColumns(row);
        t.insert();
    }
        int i = 0;
        if(records!=null)
        for(Record rec:records)
        {
            feed.remove("parent");
            feed.remove("type");
            feed.remove("order");
            
            feed.add("parent", bid);
            feed.add("type", rec.type);
            feed.add("order", i);
            
            RedRecord record = new RedRecord(feed,rec.body);
            i++;
        }

    }
  
}
