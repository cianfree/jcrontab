package org.jcrontab.avalon.tests;
/**
 *  This file is part of the jcrontab package
 *  Copyright (C) 2001-2002 Eric Pugh and Israel Olalla
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free
 *  Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 *  MA 02111-1307, USA
 *
 *  For questions, suggestions:
 *
 *  epugh@upstate.com
 *
 */
import org.apache.fulcrum.testcontainer.BaseUnitTest;
import org.jcrontab.avalon.JcrontabScheduler;
import org.jcrontab.data.CrontabEntryBean;
import org.jcrontab.tests.TaskTest;

/**
 * Demonstration of how to use the JcrontabScheduler as an Avalon component.
 * 
 *
 * @author <a href="mailto:epugh@upstate.com">Eric Pugh</a>
 * @version $Id: JcrontabSchedulerTest.java,v 1.2 2003-12-06 15:59:07 dep4b Exp $
 */
public class JcrontabSchedulerTest extends BaseUnitTest
{
    private JcrontabScheduler jcrontabScheduler = null;
    /**
      * Defines the testcase name for JUnit.
      *
      * @param name the testcase's name.
      */
    public JcrontabSchedulerTest(String name)
    {
        super(name);
    }
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(JcrontabSchedulerTest.class);
    }
    public void setUp() throws Exception
    {
        super.setUp();
        this.setRoleFileName(
            "src/org/jcrontab/avalon/tests/TestRoleConfig.xml");
        this.setConfigurationFileName(
            "src/org/jcrontab/avalon/tests/TestComponentConfig.xml");

        jcrontabScheduler =
            (JcrontabScheduler) this.lookup(JcrontabScheduler.ROLE);
        assertNotNull(jcrontabScheduler);

    }

    public void testDAOFindAll() throws Exception
    {
        CrontabEntryBean[] listOfBeans =
            jcrontabScheduler.getContrabEntryDAO().findAll();
        assertEquals(listOfBeans.length, 1);
    }


    public void testJobsRun() throws Exception
    {
        jcrontabScheduler.getContrabEntryDAO().findAll();
        TaskTest taskTest = new TaskTest();
        int counter = TaskTest.getCounter();
        //	Sleep 50000 Millis (50 seconds)
        Thread.sleep(50000);
        assertTrue(
            "Make sure the counter incremented.",
            counter < TaskTest.getCounter());
    }

}
