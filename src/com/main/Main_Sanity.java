package com.main;

import org.testng.*;
import com.main.flowZSN;
import com.main.FlowEProc;

public class Main_Sanity {

	public static void main(String[] args) {

		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { FlowEProc.class, flowZSN.class});
		ITestNGListener tla = null;
		testng.addListener(tla);
		testng.run();

	}
}
