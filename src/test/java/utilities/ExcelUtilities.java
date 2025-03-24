package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	public   CellStyle style;
     String path;
	
	public ExcelUtilities(String path)
	{
		this.path=path;
	}
	
	public  int getRowCount(String sheetname) throws IOException
	{
		fi=new FileInputStream(path);
		wb =new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetname);
		int rowcount =ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
		
	}
	
	public  int getCellCount(String sheetname,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb =new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		int cellcount =row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
		
	}
	
	public String getCellData(String sheetname,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb =new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell =row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try
		{
			//data=cell.toString();
			
			data= formatter.formatCellValue(cell);//returns the formatted value of a cell as a string regardless of the cell type
			
		}
		catch(Exception e)
		{
			data="";
			
		}
		
		wb.close();
		fi.close();
		return data;
		
	}
	public  void setCellData(String sheetname,int rownum, int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())     //if file not exists then create new file
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		
		fi=new FileInputStream(path);
		 wb =new XSSFWorkbook(fi);
		   
		 if(wb.getSheetIndex(sheetname)==1) //if sheet not exists create new sheet
		 wb.createSheet(sheetname);
		  ws=wb.getSheet(sheetname);
		  
		  if(ws.getRow(rownum)==null) //if row not exists create new row
			  ws.createRow(rownum);
		  row=ws.getRow(rownum);
		  
		cell =row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillgreencolour(String sheetname,int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb =new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell =row.getCell(colnum);
		
		style= wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public  void fillredcolour(String sheetname,int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb =new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell =row.getCell(colnum);
		
		style= wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
}

	
	
	
	
	
