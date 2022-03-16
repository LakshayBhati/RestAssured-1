package RestTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;



public class DiffJunitandTestNG {
	
	@Before
	public void BT()
	{
		String Name = "Lakshay";
		String GName = "Krypto";
		Assert.assertEquals(Name, GName);
	}
	
	@Test
	public void testcase1()
	{
		System.out.println("This is the first method");
	}
	
	@After
	public void AT()
	{
		System.out.println("This is after test");
	}

}
