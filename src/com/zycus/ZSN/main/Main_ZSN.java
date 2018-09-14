package com.zycus.ZSN.main;

import org.testng.*;
import com.main.flowZSN;

public class Main_ZSN {

	public static void main(String[] args) {

		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { flowZSN.class });
		ITestNGListener tla = null;
		testng.addListener(tla);
		testng.run();

	}
}
