package com.testresults;

public class TestResults {

	private String strPackageName;
	private String strClassName;
	private String strMethodName;
	private String strStatus;
	private String strException;

	/**
	 * @return the strPackageName
	 */
	public String getStrPackageName() {
		return strPackageName;
	}

	/**
	 * @param strPackageName the strPackageName to set
	 */
	public TestResults setStrPackageName(String strPackageName) {
		this.strPackageName = strPackageName;
		return this;
	}

	/**
	 * @return the strClassName
	 */
	public String getStrClassName() {
		return strClassName;
	}

	/**
	 * @param strClassName the strClassName to set
	 */
	public TestResults setStrClassName(String strClassName) {
		this.strClassName = strClassName;
		return this;
	}

	/**
	 * @return the strMethodName
	 */
	public String getStrMethodName() {
		return strMethodName;
	}

	/**
	 * @param strMethodName the strMethodName to set
	 */
	public TestResults setStrMethodName(String strMethodName) {
		this.strMethodName = strMethodName;
		return this;
	}

	/**
	 * @return the strStatus
	 */
	public String getStrStatus() {
		return strStatus;
	}

	/**
	 * @param strStatus the strStatus to set
	 */
	public TestResults setStrStatus(String strStatus) {
		this.strStatus = strStatus;
		return this;
	}

	/**
	 * @return the strException
	 */
	public String getStrException() {
		return strException;
	}

	/**
	 * @param strException the strException to set
	 */
	public TestResults setStrException(String strException) {
		this.strException = strException;
		return this;
	}

	@Override
	public String toString() {
		return "TestResults [strPackageName=" + strPackageName + ", strClassName=" + strClassName + ", strMethodName="
				+ strMethodName + ", strStatus=" + strStatus + ", strException=" + strException + "]";
	}

}
