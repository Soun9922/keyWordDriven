package testCase_FB;

import org.testng.annotations.Test;

import keyWord_Engine.keyWordEngg;

public class keyWord_Test {
	keyWordEngg engine;
	@Test
	public void startExecutions_Test() throws InterruptedException {
		engine = new keyWordEngg();
		Thread.sleep(5000);
		engine.startExecutions("Login");
		Thread.sleep(5000);
	}
}
