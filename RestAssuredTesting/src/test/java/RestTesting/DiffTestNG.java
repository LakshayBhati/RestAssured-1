package RestTesting;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DiffTestNG {
	
	@BeforeTest
	public void BT()
	{
		String Name = "Lakshay";
		String GName = "Krypto";
		Assert.assertEquals("Lakshay", "Krypto");
	}
	
	@Test
	public void testcase1()
	{
		System.out.println("This the first method");
	}
	
	@AfterTest
	public void AT()
	{
		System.out.println("This is after test");
	}

}
