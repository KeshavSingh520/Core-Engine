package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import base.BaseTestCase;

public class AssertStep extends Assert {
	public static Logger log=LoggerFactory.getLogger(AssertStep.class);

	public static void assertTrue(boolean bflag, BaseTestCase baseTestCase, String strStepDetails) {
		Assert.assertTrue(bflag, strStepDetails);
		logFailure(bflag,baseTestCase,strStepDetails);

	}

	public static void assertStep(BaseTestCase baseTestCase, String strStepDetails) {
		baseTestCase.getExtentTestObj().log(Status.PASS, strStepDetails);
		log.info("["+baseTestCase.getCurrentPackage()+"]"+"["+baseTestCase.getCurrentMethod()+"]"+" "+"["+"PASS"+"]"+" - "+ strStepDetails);
	}

	public static void logFailure(boolean bflag, BaseTestCase baseTestCase, String strStepDetails) {
		if (bflag) {
			baseTestCase.getExtentTestObj().log(Status.PASS, strStepDetails);
			log.info("["+baseTestCase.getCurrentPackage()+"]"+"["+baseTestCase.getCurrentMethod()+"]"+" "+"["+"PASS"+"]"+" - "+ strStepDetails);
		} else {
			baseTestCase.getExtentTestObj().log(Status.FAIL, strStepDetails);
		}
	}

}
