package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import report.ReportHandler;

public class TestListener extends ReportHandler implements ITestListener{

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {
		logFailure(result);
	}

	public void onTestSkipped(ITestResult result) {
		logFailure(result);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void logFailure(ITestResult result) {
			ReportHandler.logTestResults(result);

	}

}
