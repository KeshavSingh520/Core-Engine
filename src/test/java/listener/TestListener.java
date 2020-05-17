package listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.testresults.TestResults;

import lombok.extern.slf4j.Slf4j;
import report.ReportHandler;
import utils.QualityReport;

@Slf4j
public class TestListener implements ITestListener {

	public ConcurrentLinkedQueue<TestResults> resultsCache = new ConcurrentLinkedQueue<>();

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		computeData(result);
	}

	public void onTestFailure(ITestResult result) {
		logFailure(result);
		computeData(result);
	}

	public void onTestSkipped(ITestResult result) {
		logFailure(result);

		computeData(result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		try {
			QualityReport.generateQualityReport(resultsCache);
		} catch (FileNotFoundException e) {
			log.info("File not found: ");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("IO Exeption Occurred: ");
			e.printStackTrace();
		}
	}

	public void logFailure(ITestResult result) {
		ReportHandler.logTestResults(result);

	}

	public ConcurrentLinkedQueue<TestResults> computeData(ITestResult result) {
		TestResults testResult = new TestResults();
		testResult.setStrPackageName(getPackageName(result)).setStrClassName(getClassName(result))
				.setStrMethodName(getMethodName(result)).setStrStatus(getTestStatus(result))
				.setStrException(getException(result));
		this.resultsCache.add(testResult);
		return this.resultsCache;
	}

	private String getPackageName(ITestResult result) {
		return result.getTestClass().getName();

	}

	private String getClassName(ITestResult result) {
		return result.getTestClass().getRealClass().getName()
				.substring(result.getTestClass().getRealClass().getName().lastIndexOf(".") + 1);

	}

	private String getMethodName(ITestResult result) {
		return result.getName();
	}

	private String getException(ITestResult result) {
		if (getTestStatus(result).equals("FAIL"))
			return result.getThrowable().getClass().getSimpleName().toString();
		return "";
	}

	private String getTestStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS)
			return "PASS";
		else if (result.getStatus() == ITestResult.FAILURE)
			return "FAIL";
		else if (result.getStatus() == ITestResult.SKIP)
			return "SKIP";
		return "UNKNOWN";
	}

}
