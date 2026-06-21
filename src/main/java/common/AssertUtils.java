package common;

import org.testng.Assert;

public class AssertUtils {

	public static void assertTextPartial(String expected, String actual) {
		if (expected.contains(actual)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "Validation failed, expected::"+expected+" but not found::" + actual.trim());
		}
	}
	
	public static void assertText(String expected, String actual) {
		if (expected.trim().equals(actual)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "Validation failed, expected::"+expected+" but not found::" + actual.trim());
		}
	}

	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			System.out.println("error at wait");
		}
		
	}
}
