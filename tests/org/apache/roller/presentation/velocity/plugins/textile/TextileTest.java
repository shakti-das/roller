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
/* * Created on Oct 31, 2003 */package org.apache.roller.presentation.velocity.plugins.textile;import junit.framework.Test;import junit.framework.TestCase;import junit.framework.TestSuite;import org.apache.roller.presentation.bookmarks.BookmarksActionTest;import org.apache.roller.model.PagePlugin;import org.apache.roller.presentation.velocity.plugins.textile.TextilePlugin;/** * We really need to solve webtesting to adequately test our Presentation classes. *  * @author lance */public class TextileTest extends TestCase{    String textileStr = "*strong* plain _emphasis_ * _emphaticStrong_ * ";    String expected = "<p><strong>strong</strong> plain <em>emphasis</em> <strong> <em>emphaticStrong</em> </strong></p>";      /*      * This fails because Textile4J appears to place a tab (\t)     * at the beginning of the result.  If the result is .trim()'ed     * then it passes.     */    public void testTextile()    {        PagePlugin textile = new TextilePlugin();                String result = textile.render(textileStr);        //System.out.println(expected);        //System.out.println(result);        assertEquals("this will fail until Textile4J is fixed.", expected, result);             }    	/* (non-Javadoc)	 * @see junit.framework.TestCase#setUp()	 */	protected void setUp() throws Exception	{        super.setUp();	       	}	/* (non-Javadoc)	 * @see junit.framework.TestCase#tearDown()	 */	protected void tearDown() throws Exception	{	   super.tearDown();	}    public static Test suite()     {        return new TestSuite(TextileTest.class);    }}