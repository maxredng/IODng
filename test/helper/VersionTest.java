/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.iot.misc.Feed;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bsati
 */
public class VersionTest {
    
    public VersionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStatsVersion method, of class Version.
     */
    @Test
    public void testGetStatsVersion() {
        System.out.println("getStatsVersion");
        Feed f = new Feed();
        f.init();
        f.add("project", "admin");
        String expResult = "1";
        String result = Version.getStatsVersion(f);
        assertEquals(expResult, result);

    }

    /**
     * Test of getCurrentVersion method, of class Version.
     */
    @Test
    public void testGetCurrentVersion() {
        System.out.println("getCurrentVersion");
        Feed f = new Feed();
        f.init();
        f.add("project", "admin");
        
        String ver = Version.getCurrentVersion(f);

        assertEquals(ver,"1");
    }
    
    @Test 
        public void testVersion()
    {
        Feed f = new Feed();
        f.init();
        f.add("project", "admin");
        Version v = Version.checkVersion(f);
//        assertEquals(3,v.records.size());
    
    
    }
    
}
