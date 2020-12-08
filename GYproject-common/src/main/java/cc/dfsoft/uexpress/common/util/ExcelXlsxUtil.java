package cc.dfsoft.uexpress.common.util;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ExcelXlsxUtil {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ExcelXlsxUtil.class);
	private XSSFWorkbook wb = null;
	private XSSFSheet sheet = null;

	public ExcelXlsxUtil(XSSFWorkbook wb, XSSFSheet sheet) {
		this.wb = wb;
		this.sheet = sheet;
	}

	public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs) {
		int toprowNum = region.getFirstRow();
		for (int i = toprowNum; i <= region.getLastRow(); i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				XSSFCell cell = row.getCell(j);// HSSFCellUtil.getCell(row,
				// (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	public XSSFCellStyle getHeadBodyStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		// 设置单元格内容水平对其方式
		// XSSFCellStyle.ALIGN_CENTER 居中对齐
		// XSSFCellStyle.ALIGN_LEFT 左对齐
		// XSSFCellStyle.ALIGN_RIGHT 右对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		// 设置单元格内容垂直对其方式
		// XSSFCellStyle.VERTICAL_TOP 上对齐
		// XSSFCellStyle.VERTICAL_CENTER 中对齐
		// XSSFCellStyle.VERTICAL_BOTTOM 下对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		// 设置单元格边框样式
		// CellStyle.BORDER_THIN 细边线
		// CellStyle.BORDER_MEDIUM 中等边线
		// CellStyle.BORDER_DASHED 虚线边线
		// CellStyle.BORDER_THICK 粗边线
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		// cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	public XSSFCellStyle getHeadStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		// cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		// 设置单元格内容水平对其方式
		// XSSFCellStyle.ALIGN_CENTER 居中对齐
		// XSSFCellStyle.ALIGN_LEFT 左对齐
		// XSSFCellStyle.ALIGN_RIGHT 右对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		// 设置单元格垂直居中对齐
		// 设置单元格内容垂直对其方式
		// XSSFCellStyle.VERTICAL_TOP 上对齐
		// XSSFCellStyle.VERTICAL_CENTER 中对齐
		// XSSFCellStyle.VERTICAL_BOTTOM 下对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		// 设置单元格边框样式
		// CellStyle.BORDER_THIN 细边线
		// CellStyle.BORDER_MEDIUM 中等边线
		// CellStyle.BORDER_DASHED 虚线边线
		// CellStyle.BORDER_THICK 粗边线
		// cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		// cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		// cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		// cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	public XSSFCellStyle getBodyStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		// 设置单元格居中对齐
		// 设置单元格内容水平对其方式
		// XSSFCellStyle.ALIGN_CENTER 居中对齐
		// XSSFCellStyle.ALIGN_LEFT 左对齐
		// XSSFCellStyle.ALIGN_RIGHT 右对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		// 设置单元格内容垂直对其方式
		// XSSFCellStyle.VERTICAL_TOP 上对齐
		// XSSFCellStyle.VERTICAL_CENTER 中对齐
		// XSSFCellStyle.VERTICAL_BOTTOM 下对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		// 设置单元格边框样式
		// CellStyle.BORDER_DOUBLE 双边线
		// CellStyle.BORDER_THIN 细边线
		// CellStyle.BORDER_MEDIUM 中等边线
		// CellStyle.BORDER_DASHED 虚线边线
		// CellStyle.BORDER_HAIR 小圆点虚线边线
		// CellStyle.BORDER_THICK 粗边线
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	public XSSFCellStyle getBigHeadStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为蓝色
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		// 设置单元格内容水平对其方式
		// XSSFCellStyle.ALIGN_CENTER 居中对齐
		// XSSFCellStyle.ALIGN_LEFT 左对齐
		// XSSFCellStyle.ALIGN_RIGHT 右对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		// 设置单元格内容垂直对其方式
		// XSSFCellStyle.VERTICAL_TOP 上对齐
		// XSSFCellStyle.VERTICAL_CENTER 中对齐
		// XSSFCellStyle.VERTICAL_BOTTOM 下对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 400);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		// 设置单元格边框样式
		// CellStyle.BORDER_DOUBLE 双边线
		// CellStyle.BORDER_THIN 细边线
		// CellStyle.BORDER_MEDIUM 中等边线
		// CellStyle.BORDER_DASHED 虚线边线
		// CellStyle.BORDER_HAIR 小圆点虚线边线
		// CellStyle.BORDER_THICK 粗边线
		// cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		// cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		// cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		// cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	/**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValue(Cell cell) {
    	if (cell == null) {
    		return "";
    	}
    	 String strCell = "";
	     switch (cell.getCellType()) {
	         case Cell.CELL_TYPE_STRING:
	             strCell = cell.getStringCellValue().trim();
	             break;
	         case Cell.CELL_TYPE_NUMERIC:
	             if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
	            	 if (DateUtil.isCellDateFormatted(cell)) {
		            	 Date d = cell.getDateCellValue();
		            	 DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		            	 strCell=formater.format(d).trim();
		            	 break;
	            	 }else{
	            		 DecimalFormat df = new DecimalFormat("########.##");
	            		 strCell=df.format(cell.getNumericCellValue()).trim();  
	            		/* strCell =cell.getNumericCellValue()+"";
	            		 if(strCell.indexOf(".")>0){
	            			 strCell = strCell.substring(0,strCell.indexOf("."));
	            		 }*/
	            	 }
	             }
	             break;
	         case Cell.CELL_TYPE_BOOLEAN:
	             strCell = String.valueOf(cell.getBooleanCellValue()).trim();
	             break;
	         case Cell.CELL_TYPE_BLANK:
	             strCell = "";
	             break;
	         default:
	             strCell = "";
	             break;
	      }
         if (strCell.equals("") || strCell == null) {
             return "";
         }
         return strCell;
    }
    /**
     * 得到Excel表中的值
     * 
     * @param xssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValue(XSSFCell cell) {
    	if (cell == null) {
    		return "";
    	}
    	 String strCell = "";
	     switch (cell.getCellType()) {
	         case XSSFCell.CELL_TYPE_STRING:
	             strCell = cell.getStringCellValue().trim();	             
	             break;
	         case XSSFCell.CELL_TYPE_NUMERIC:
	             if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
	            	 if (HSSFDateUtil.isCellDateFormatted(cell)) {
		            	 Date d = cell.getDateCellValue();
		            	 DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		            	 strCell=formater.format(d).trim();
		            	 break;
	            	 }else{
	            		 strCell =cell.getNumericCellValue()+"";
	            		 if(strCell.indexOf(".")>0){
	            			 strCell = strCell.substring(0,strCell.indexOf("."));
	            		 }
	            	 }
	             }
	             break;
	         case XSSFCell.CELL_TYPE_BOOLEAN:
	             strCell = String.valueOf(cell.getBooleanCellValue()).trim();
	             break;
	         case HSSFCell.CELL_TYPE_BLANK:
	             strCell = "";
	             break;
	         default:
	             strCell = "";
	             break;
	      }
         if (strCell.equals("") || strCell == null) {
             return "";
         }
         return strCell;
    }
    /**
     * 得到Excel表中的值
     *  只用于处理长串数字符串
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private String getValueNg(HSSFCell cell) {
    	if (cell == null) {
    		return "";
    	}
    	 String strCell = "";
	     switch (cell.getCellType()) {
	         case HSSFCell.CELL_TYPE_STRING:
	             strCell = cell.getStringCellValue().trim();
	             break;
	         case HSSFCell.CELL_TYPE_NUMERIC:
        		 DecimalFormat df = new DecimalFormat("########");
        		 strCell=df.format(cell.getNumericCellValue()).trim();  
        		 break;
	         case HSSFCell.CELL_TYPE_BOOLEAN:
	             strCell = String.valueOf(cell.getBooleanCellValue()).trim();
	             break;
	         case HSSFCell.CELL_TYPE_BLANK:
	             strCell = "";
	             break;
	         default:
	             strCell = "";
	             break;
	      }
         if (strCell.equals("") || strCell == null) {
             return "";
         }
         return strCell;
    }

	/**
	 * excel 导出方法
	 * 
	 * @author pengtt
	 * @param response
	 * @param title
	 *            excel表格的标题
	 * @param excelHeader
	 *            excel每列的列名
	 * @param headerWidth
	 *            excel每列的宽度
	 * @param propertyNames
	 *            对应每列的属性名称
	 * @param map
	 *            { fileName ：excel文件名称 remarkInfo：备注信息 remarkInfoHeight：备注信息的行高
	 *            }
	 * @param json
	 *            表格中需要的数据值
	 * @throws Exception
	 */
	public static void export(HttpServletResponse response, String title, String[] excelHeader, Integer[] headerWidth,
			String[] propertyNames, Map<String, String> map, JSONArray json) throws Exception {
		OutputStream ouputStream = response.getOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");
		ExcelXlsxUtil exportUtil = new ExcelXlsxUtil(workbook, sheet);
		XSSFCellStyle bigHeadStyle = exportUtil.getBigHeadStyle();
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle headbodyStyle = exportUtil.getHeadBodyStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		XSSFCell cell = null;

		// 注释文字的行高默认设置500
		int remarkHeight = 500;
		if (StringUtils.isNotBlank(map.get("remarkInfoHeight"))) {
			// 获取注释文字的行高
			remarkHeight = Integer.valueOf(map.get("remarkInfoHeight"));
		}
		// excel表格列宽设置
		for (int i = 0; i < excelHeader.length; i++) {
			int width = 3000;
			if (headerWidth.length > i) {
				width = headerWidth[i];
			}
			sheet.setColumnWidth(i, width);
		}
		// 列头信息合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelHeader.length - 1));

		XSSFRow regRow = sheet.createRow(0);
		// 标题行设置800高度
		regRow.setHeight((short) 800);
		cell = regRow.createCell(0);
		cell.setCellStyle(bigHeadStyle);
		// 设置表格名称
		cell.setCellValue(title);

		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, excelHeader.length - 1));
		XSSFRow dataRow = sheet.createRow(1);
		dataRow.setHeight((short) remarkHeight);
		cell = dataRow.createCell(0);
		cell.setCellStyle(headStyle);
		// 设置标题注释文字
		cell.setCellValue(new XSSFRichTextString(map.get("remarkInfo")));

		XSSFRow headRow = sheet.createRow(2);
		headbodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headbodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headbodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headbodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headbodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 放入表格头信息（相当于表格列名）
		for (int i = 0; i < excelHeader.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(headbodyStyle);
		}
		// 遍历json串，放到表格中
		if (null != json && json.size() > 0) {
			for (int i = 2; i < json.size() + 2; i++) {
				headRow = sheet.createRow(i + 1);
				JSONObject ob = (JSONObject) json.get(i - 2);
				for (int j = 0; j < propertyNames.length; j++) {
					cell = headRow.createCell(j);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(ob.getString(propertyNames[j]));
				}
			}
		}
		response.setContentType("application/binary;charset=utf-8");
		String fileName = map.get("fileName");
		if (StringUtils.isBlank(fileName)) {
			fileName = "exportInfo";
		}
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}
	
	
	/**
	 * excel 导出方法
	 * 
	 * @author pengtt
	 * @param response
	 * @param title
	 *            excel表格的标题
	 * @param excelHeader
	 *            excel每列的列名
	 * @param headerWidth
	 *            excel每列的宽度
	 * @param propertyNames
	 *            对应每列的属性名称
	 * @param map
	 *            { fileName ：excel文件名称 remarkInfo：备注信息 remarkInfoHeight：备注信息的行高
	 *            }
	 * @param json
	 *            表格中需要的数据值
	 * @throws Exception
	 */
	public static void exportExcel(HttpServletResponse response, String title,String sheetName, String[] excelHeader, Integer[] headerWidth,
			String[] propertyNames, Map<String, String> map, JSONArray json) throws Exception {
		OutputStream ouputStream = response.getOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);
		ExcelXlsxUtil exportUtil = new ExcelXlsxUtil(workbook, sheet);
		XSSFCellStyle bigHeadStyle = exportUtil.getBigHeadStyle();
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle headbodyStyle = exportUtil.getHeadBodyStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		XSSFCell cell = null;

		// 注释文字的行高默认设置500
		int remarkHeight = 500;
		if (StringUtils.isNotBlank(map.get("remarkInfoHeight"))) {
			// 获取注释文字的行高
			remarkHeight = Integer.valueOf(map.get("remarkInfoHeight"));
		}
		// excel表格列宽设置
		for (int i = 0; i < excelHeader.length; i++) {
			int width = 3000;
			if (headerWidth.length > i) {
				width = headerWidth[i];
			}
			sheet.setColumnWidth(i, width);
		}
		// 列头信息合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelHeader.length - 1));

		XSSFRow regRow = sheet.createRow(0);
		// 标题行设置800高度
		regRow.setHeight((short) 800);
		cell = regRow.createCell(0);
		cell.setCellStyle(bigHeadStyle);
		// 设置表格名称
		cell.setCellValue(title);

		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, excelHeader.length - 1));
		XSSFRow dataRow = sheet.createRow(1);
		dataRow.setHeight((short) remarkHeight);
		cell = dataRow.createCell(0);
		cell.setCellStyle(headStyle);
		// 设置标题注释文字
		cell.setCellValue(new XSSFRichTextString(map.get("remarkInfo")));

		XSSFRow headRow = sheet.createRow(2);
		headbodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headbodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headbodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headbodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 放入表格头信息（相当于表格列名）
		for (int i = 0; i < excelHeader.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(headbodyStyle);
		}
		// 遍历json串，放到表格中
		if (null != json && json.size() > 0) {
			for (int i = 2; i < json.size() + 2; i++) {
				headRow = sheet.createRow(i + 1);
				JSONObject ob = (JSONObject) json.get(i - 2);
				for (int j = 0; j < propertyNames.length; j++) {
					cell = headRow.createCell(j);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(ob.getString(propertyNames[j]));
				}
			}
		}
		response.setContentType("application/binary;charset=utf-8");
		String fileName = map.get("fileName");
		if (StringUtils.isBlank(fileName)) {
			fileName = "exportInfo";
		}
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}
	
    /**
     * excel导入
     * @author pengtt
     * @createTime 2016-06-03
     * @param fileName  文件名称
     * @param params    行的每列所对应的属性名称（用于返回结果集，通过key，获取value）
     * @return list<Map<String,String>>
     * @throws Exception
     */
	public List<Map<String, String>> importExcel(String fileName,String[] params)throws Exception {
		InputStream is = new FileInputStream(fileName);
		Workbook wb = null;
		//返回结果的list
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		//判断导入文档的格式
		if (StringUtils.isBlank(fileName)) {
			throw new RuntimeException("导入文档为空！");
		} else if (fileName.toLowerCase().endsWith("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (fileName.toLowerCase().endsWith("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new RuntimeException("导入文档必须为excel表格！");
		}

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
			Sheet sheet = wb.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			//循环遍历表格的每一行的内容
			for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Map<String, String> map = new HashMap<String, String>();
				int i = 0;
				Row row = sheet.getRow(rowNum);
				if (row != null) {
					Iterator<Cell> it = row.cellIterator();
					while (it.hasNext()) {
						Cell cell = it.next();
						if (i < params.length) {
							map.put(params[i], this.getValue(cell));
							i++;
						}
					}
					//表格每一行的内容，为一个map，放入到list中
					result.add(map);
				}
			}
		}
		//返回结果集
		return result;
	}
	 /**
     * excel导入
     * @author zhangjj
     * @createTime 2016-07-06
     * @param file  文件
     * @param sheetName sheet名称 不传默认读取第一个sheet
     * @param meregNum  合并单元格数 ，没有时传0
     * @param startRow 开始行数
     * @param delLastRow 去除最后行数
     * @param params  行的每列所对应的属性名称（用于返回结果集，通过key，获取value）
     * @return JSONArray
     * @throws Exception
     */
	public static JSONArray importExcelJson(MultipartFile file,String SheetName,int meregNum,int startRow,int delLastRow,String[] params)throws Exception {
		InputStream is = file.getInputStream();
		Workbook wb = null;
		String fileName=file.getOriginalFilename();
		
		JSONArray jsonarr=new JSONArray();
		//判断导入文档的格式
		if (StringUtils.isBlank(fileName)) {
			throw new RuntimeException("导入文档为空！");
		} else if (fileName.toLowerCase().endsWith("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (fileName.toLowerCase().endsWith("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new RuntimeException("导入文档必须为excel表格！");
		}

	    // 循环工作表Sheet
		 Sheet sheet =null;
	    if (StringUtils.isNotBlank(fileName)){ 
	    	sheet= wb.getSheet(SheetName);
	    }else{
	    	sheet=wb.getSheetAt(0);
	    }	
	     int m=0,n=0;
	     if(meregNum!=0){
		   CellRangeAddress ca=sheet.getMergedRegion(meregNum);
		    m=ca.getFirstColumn();
		    n=ca.getLastColumn();
	     }
		   for (int rowNum = startRow; rowNum <= sheet.getLastRowNum()-delLastRow; rowNum++) {
				JSONObject jsonobj=new JSONObject();
				int i=0;
				int j=0;
				Row row = sheet.getRow(rowNum);
//				int lastColumn = row.getLastCellNum();
				if (row != null) {
					for (;i < params.length;) {
						 Cell cell = row.getCell(i, Row.RETURN_BLANK_AS_NULL);
//						 System.err.println(cell);
						 if (i < params.length) {
								if(j>m&&j<=n){
									j++;
								 continue;	
								}else{
									j++;
								}
								jsonobj.put(params[i], getValue(cell));
								i++;
							}
					}
					//表格每一行的内容，为一个map，放入到list中
					jsonarr.add(jsonobj);
				}
			}
		
		   logger.info(jsonarr.toJSONString());
		//返回结果集
		return jsonarr;
	}
	
}
