package com.utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.junit.Assert;
public class FileReadManager {
	private static FileInputStream fileInputStream;
	private static Properties property;
	private static  void setUpProperty() {
		File file = new File
				("C:\\Users\\HP\\eclipse-workspace\\DataProvider\\src\\main\\resources\\TestData.properties");
		try {
				fileInputStream = new FileInputStream(file);
				 property = new Properties();
				property.load(fileInputStream);
			} catch (FileNotFoundException e) {
			Assert.fail("ERROR: OCCUR DURING READ THE FILE ");
			} catch (IOException e) {
			Assert.fail("ERROR: OCCUR DURING LOAD THE DATA");
			}
	}
	public static String getProperty(String keyData) {
		setUpProperty();
		String value = property.getProperty(keyData);
		return value;
	}

	

	}


