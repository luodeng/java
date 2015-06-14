package com.roden.java.util.junit;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*before 与beforeclass
 * 注意二者区别
 * */
public class TestClass {
	@BeforeClass
    public  static void beforeclass(){
    	System.out.println("beforeclass");
    }
	@Before
    public void  before(){
    	System.out.println("before");
    }
	
    @Test
	  public void   audo(){
		Integer i=2;
		int j=i;
		System.out.println(j);
	}
	
	@Test
	 public  void TestRun(){
		 
		Assert.assertEquals("2",new Person().run());
		
		 
	 }
	
	@After
	    public  void After(){
    	System.out.println("after");
    }
	@AfterClass
    public  static void afterclass(){
    	System.out.println("Afterclass");
    }
}