package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "data")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir")+"//testdata//Userdata.xlsx";
		XLUtilities xl = new XLUtilities(path);
		int rownum = xl.getRowCount("Sheet1");
		int columnum = xl.getCellCount("Sheet1", 1);
		
		String apiData[][] = new String[rownum][columnum];
		
		for(int i =1; i<=rownum;i++) {
			for(int j=0;j<columnum;j++) {
				apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
	}
	
	@DataProvider(name = "usernames")
	public String[] getUsernames() throws IOException {
		String path = System.getProperty("user.dir")+"//testdata//Userdata.xlsx";
		XLUtilities xl = new XLUtilities(path);
		int rownum = xl.getRowCount("Sheet1");
		String apiData[] = new String[rownum];
		for(int i = 1 ; i<= rownum; i++) {
			apiData[i-1] = xl.getCellData("Sheet1", i, 1);
		}
		return apiData;
	}

}
