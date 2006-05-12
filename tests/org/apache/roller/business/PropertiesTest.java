/*
* Licensed to the Apache Software Foundation (ASF) under one or more
*  contributor license agreements.  The ASF licenses this file to You
* under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.  For additional information regarding
* copyright in this work, please see the NOTICE file in the top level
* directory of this distribution.
*/
/*
 * PropertiesTest.java
 *
 * Created on April 9, 2006, 2:51 PM
 */

package org.apache.roller.business;

import java.util.Map;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.roller.TestUtils;
import org.apache.roller.model.PropertiesManager;
import org.apache.roller.model.RollerFactory;
import org.apache.roller.pojos.RollerPropertyData;

/**
 * Test Properties related business operations.
 *
 * That includes:
 *
 */
public class PropertiesTest extends TestCase {
    
    public static Log log = LogFactory.getLog(PropertiesTest.class);
    
    
    public PropertiesTest(String name) {
        super(name);
    }
    
    
    public static Test suite() {
        return new TestSuite(PropertiesTest.class);
    }
    
    
    public void setUp() throws Exception {
        
    }
    
    public void tearDown() throws Exception {
        
    }
    
    
    public void testProperiesCRUD() throws Exception {
        
        // remember, the properties table is initialized during Roller startup
        PropertiesManager mgr = RollerFactory.getRoller().getPropertiesManager();
        TestUtils.endSession(true);
        
        RollerPropertyData prop = null;
        
        // get a property by name
        prop = mgr.getProperty("site.name");
        assertNotNull(prop);
        
        // update a property
        prop.setValue("testtest");
        mgr.saveProperty(prop);
        TestUtils.endSession(true);
        
        // make sure property was updated
        prop = null;
        prop = mgr.getProperty("site.name");
        assertNotNull(prop);
        assertEquals("testtest", prop.getValue());
        
        // get all properties
        Map props = mgr.getProperties();
        assertNotNull(props);
        assertTrue(props.containsKey("site.name"));
        
        // update multiple properties
        prop = (RollerPropertyData) props.get("site.name");
        prop.setValue("foofoo");
        prop = (RollerPropertyData) props.get("site.description");
        prop.setValue("blahblah");
        mgr.saveProperties(props);
        TestUtils.endSession(true);
        
        // make sure all properties were updated
        props = mgr.getProperties();
        assertNotNull(props);
        assertEquals("foofoo", ((RollerPropertyData)props.get("site.name")).getValue());
        assertEquals("blahblah", ((RollerPropertyData)props.get("site.description")).getValue());
    }
    
}
