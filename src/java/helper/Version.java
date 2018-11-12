/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.iot.misc.Feed;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author bsati
 */
public class Version {
  
    private final Feed feed;
    public List<JSRecord> records;
    List<String> lines;
    String indent = " ";
    
    public static Version checkVersion(Feed f)
    {
        Version result = null;
        String dv = getStatsVersion(f);
        String fv = getCurrentVersion(f);
        if(!dv.equals(fv))
        {
        f.remove("jsversion");
        f.add("jsversion", dv);
        Version version = new Version(f);
        result = version;
        }
    
        return result;
    }
    
    public static String getStatsVersion(Feed f)
    {
        String query = "select value from config.stats where name='jsversion' and project='" + f.get("project") + "'";
        return f.pullString(query);
    }
    
    public static String getPath(Feed f)
    {
        String dir = System.getProperty("user.dir");
        return dir + "\\web\\js\\" + f.get("project") ;
    
    }
    
    public static String getCurrentVersion(Feed f)
    {
        String result = "";
        String dir = System.getProperty("user.dir");
        String path = dir + "\\web\\js\\" + f.get("project") ;
        File folder = new File(path);
        File[] files = folder.listFiles();
        
        String regex = "\\S_(.*)\\.js";
        
        for(File file:files)
        {
            String name = file.getName();
            result = Misc.getValueFromRegex(name, regex);
        }
        
        return result;
    }
    
    public Version(Feed f)
    {
        feed = f;
        populate(f);
    }

    
    private List<JSRecord> getChildren(List<String[]> l,String par,String type)
    {

        List<JSRecord> result = new ArrayList<>();
    
        for(String[] row:l)
        {
            JSRecord rec = new JSRecord(row);
            if(rec.parent.equals(par))
            {
                String id = rec.id;
                
                rec.item = getChildren(l,id,rec.type);
                result.add(rec);
            
            }
        
        }
        
        
        return result;
    }
    
    private void populate(Feed f) {
        lines = new ArrayList<>();
        String version = (String)f.get("jsversion");
        String path = getPath(feed);
        File folder = new File(path);
        if(!folder.exists())
        folder.mkdir();
        
         Arrays.asList(folder.listFiles()).forEach(fl -> fl.delete());
        
         String query = "select * from config.javascript where project = '" + feed.get("project") + "'";
         
         List<String[]> l = feed.pullTable(query);
         
         records = new ArrayList<>();
         
         records = getChildren(l,"root","");
         
         generate(version);

    }
    
    
    void createLines(List<JSRecord> r, String t)
    {
        
        
        for(JSRecord record:r)
        {
            
            if(!record.type.equals("expression"))
            {
                 if(record.type.equals("else"))
                {
//                      if(indent.length()>0)
//                    indent = indent.substring(0,indent.length()-1);
                    lines.add(indent + "}");
                }               
                
                lines.add(indent + record.body);
                indent = indent + " ";
                lines.add(indent + "{");
                

                if(record.item!=null)
                {   

                    createLines(record.item,record.type);
                }
                if(!record.type.equals("else")){
                    if(indent.length()>0)
                indent = indent.substring(0,indent.length()-1);
                lines.add(indent + "}");
                }
            }else
            {
            lines.add(indent + record.body + ";");
            }
        
        }
    
    
    }
    
    
    public void generate(String v)
    {
        String path = getPath(feed);
        
        File file = new File(path + "\\action_" + v + ".js");
        
        createLines(records,"");
        
        try {
            FileUtils.writeLines(file, lines);
        } catch (IOException iOException) {
        }
        
    
    }
    
}
