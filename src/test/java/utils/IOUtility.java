package utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IOUtility {
	private static String strPath = System.getProperty("user.dir") + "\\test-output\\" + getCurrentDate();

	/**
	 * @return the strPath
	 */
	public static String getStrPath() {
		return strPath;
	}

	/**
	 * @param strPath the strPath to set
	 */
	public static String setFileDownloadPath(String strPath) {
		if (null != strPath)
			IOUtility.strPath = strPath;
		return strPath;
	}

	public static String createDireactory() {
		File file = new File(strPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				log.info("Exection Results directory created.");
			} else {
				log.info("Exection Results directory not created.");
			}
		}
		return strPath;
	}

	private static String getCurrentDate() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date()).replace("/", "_").replace(":", "_").replace(" ", "_");
	}
}
