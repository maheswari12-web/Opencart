package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

		//DataProvider--1
		
		@DataProvider(name="LoginData")
		public String [][] getData() throws IOException
		{
			String path=".\\testData\\opencart logindata.xlsx"; //taking excel file from testData
			
			ExcelUtilities eu=new ExcelUtilities(path); //creating a object for excel utilities
			
			int totalrows=eu.getRowCount("sheet1");
			int totalcolumns=eu.getCellCount("sheet1", 1);
			
			String logindata[][]=new String[totalrows][totalcolumns]; //creating for two dimensional array which can store
			
			for(int i=1;i<=totalrows;i++) //1  //read the data from excel storing in two dimensional array
			{
				for(int j=0;j<totalcolumns;j++) //0   i is rows j is columns
				{
					logindata[i-1][j]=eu.getCellData("sheet1", i, j); //1,0
				}
			}
			return logindata; //returning two dimensional array
		}
		
		//DataProvider--2
		
		//DataProvider--3
		
		//DataProvider--4
   }


