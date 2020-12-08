package cc.dfsoft.uexpress.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassDesc: Excel导入或导出
 * @Author zhangnx
 * @Date 2019/1/22 17:19
 * Version:1.0
 */
public class BeanExcel<T>{



    public  boolean exportExcel(String headersJsonArratStr ,List<T> dtoList,HttpServletResponse response) {
        SimpleDateFormat ymdhmsSsdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = ymdhmsSsdf.format(new Date());
        return realExport(fileName, "sheet", headersJsonArratStr, dtoList, 0, 0, response,false);
    }

    public  boolean exportExcel(String fileName,String headersJsonArratStr ,List<T> dtoList,HttpServletResponse response) {
        return realExport(fileName,"sheet",headersJsonArratStr,dtoList,0,0,response,false);
    }


    public  boolean exportExcel(String fileName,String headersJsonArratStr ,List<T> dtoList,HttpServletResponse response,boolean isHeadersCode) {
        return realExport(fileName,"sheet",headersJsonArratStr,dtoList,0,0,response,isHeadersCode);
    }


    public  boolean exportExcel(String fileName, String sheetTitle, String headersJsonArratStr , List<T> dtoList, int columnWidth, float rowHeight, HttpServletResponse response,boolean isHeadersCode) {
        return realExport(fileName,sheetTitle,headersJsonArratStr,dtoList,rowHeight,columnWidth,response,isHeadersCode);
    }



/***************************开始******************************************************************************************************
 ******* 导出Excel
 **************************开始***********************************************************************************************************/

    /**
     * @MethodDesc: List<T>导出Excel
     * @Author zhangnx
     * @Date 2019/1/24 14:37
     * fileName                   导出文件名
     * sheetTitle                sheet工作簿名称
     * headersJsonArratStr      导出字段json数组：[{id:'编号'},{name:'姓名'},{sex:'性别'}]
     * dtoList                  对象List
     * rowHeight                行高
     * columnWidth              列宽
     * response                 响应浏览器导出
     * isHeadersCode              是否需要标题Code
     * */
    public  boolean realExport(String fileName, String sheetTitle, String headersJsonArrayStr ,List<T> dtoList, float rowHeight,int columnWidth,HttpServletResponse response,Boolean isHeadersCode) {

        try {
            List<String> headersIdList = null;
            List<String> headersNameList = null;
            if (StringUtil.isBlank(headersJsonArrayStr)){//表头标题未配置默认取属性作为表头
                headersIdList = getHeadersList(dtoList);
                headersNameList = getHeadersList(dtoList);
            }else {
                headersIdList = getKeyOrValueList(headersJsonArrayStr, "keyList");//表格标题字段List
                headersNameList = getKeyOrValueList(headersJsonArrayStr, "valueList");  //表格标题List
            }


            if (headersIdList==null || headersIdList.size()<1) return false;
            if (headersNameList==null || headersNameList.size()<1) return false;

            HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
            HSSFSheet sheet = wb.createSheet(sheetTitle);

            HSSFRow row=null;
            sheet.setDefaultRowHeightInPoints(rowHeight == 0 ? 25 : rowHeight);//设置行高
            for (int i = 0; i < headersIdList.size(); i++) {//设置列宽
                sheet.setColumnWidth(i, columnWidth == 0 ? 20 * 256 : columnWidth);
            }

            HSSFCell cell=null;
            if (isHeadersCode) {//表头--标题ID
                int idSize = 0;
                row = sheet.createRow(0);
                for (String s : headersIdList) {
                    cell = row.createCell(idSize++);
                    cell.setCellValue(s);
                    cell.setCellStyle(getStyle(wb, 2, 9, "宋体", 1, 10, 2));
                }
            }

            int nameSize = 0;
            row = isHeadersCode ? sheet.createRow(1) : sheet.createRow(0);
            for (String s : headersNameList) { //表头--标题名称
                cell = row.createCell(nameSize++);
                cell.setCellValue(s);
                cell.setCellStyle(getStyle(wb, 2, 9, "微软雅黑", 1, 12, 2));

            }


            int dataRow = isHeadersCode ? 2 : 1;//数据行
            for (T t : dtoList) {
                int zdCell = 0;
                row = sheet.createRow(dataRow++);
                for (String s : headersIdList) {
                    if (t instanceof Map){//map类型
                        Map map=(Map) t;
                        for (Object key: map.keySet()) {
                            if (key.equals(s)){
                                cell = row.createCell(zdCell++, 0);

                                if (map.get(key)!= null && map.get(key).toString().length()>=30000){//单元格最大能存32767个字符，超出了就会报错
                                    String value = map.get(key).toString();
                                    cell.setCellValue(s.substring(0,30000));//写进excel对象

                                    //System.out.println(value);
                                   // System.out.println("字符长"+value.length()+"大于30000个字符，单元格最大能存32767个字符，超出了就会报错，故截取30000个字符写入excel文档");

                                }else {
                                    cell.setCellValue(map.get(key) != null?map.get(key).toString():"");//写进excel对象
                                }
                            }
                        }
                    }else {
                        Field field = t.getClass().getDeclaredField(s);
                        if (s.equals(field.getName())) { //比对JavaBean的属性名，一致就写入，不一致就丢弃
                            String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);//拿到属性的get方法
                            Class tCls = t.getClass();//拿到JavaBean对象
                            Method getMethod = tCls.getMethod(getMethodName, new Class[]{});//通过JavaBean对象拿到该属性的get方法，从而进行操控
                            Object value = getMethod.invoke(t, new Object[]{});//操控该对象属性的get方法，从而拿到属性值
                            cell = row.createCell(zdCell++, 0);
                            distinguishTypeSetValue(field, cell, value);
                        }
                    }
                }
            }

            response.reset();
            response.setContentType("application/msexcel;charset=UTF-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");//中文乱码：将文件名称转换为ASCII码
            response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");//导出文件名称
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.close();
            //System.out.println("导出成功!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("导出失败!");
            return false;
        }
    }



    /**
     * @MethodDesc: 功能描述
     * @Author zhangnx
     * @Date 2019/4/4 15:51
     */
    public boolean distinguishTypeSetValue(Field field,HSSFCell cell,Object value){
        Type genericType = field.getGenericType();

        if (genericType==Date.class && value!=null){//日期格式化
            String[] strArr = value.toString().split(" ");
            if (strArr.length==6){
                SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH) ;
                try {
                   value=sfEnd.format(sfStart.parse(value.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();return false;
                }
            }
        }

        if (value != null && value.toString().length()>=30000){//单元格最大能存32767个字符，超出了就会报错
            String s = value.toString();
            cell.setCellValue(s.substring(0,30000));//写进excel对象

            //System.out.println(s);
            //System.out.println("字符长"+s.length()+"大于30000个字符，单元格最大能存32767个字符，超出了就会报错，故截取30000个字符写入excel文档");

        }else {
            cell.setCellValue(value != null?value.toString():"");//写进excel对象
        }

        return true;

    }




    /**
     * @MethodDesc: 获取导出标题列
     * @Author zhangnx
     * @Date 2019/1/24 17:42
     */
    public List<String> getKeyOrValueList(String jsonArratStr, String key ){

        List<String> resultKeyValue=new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(jsonArratStr);
        List<Map<String,Object>> mapListJson = (List)jsonArray;
        for (Object o:mapListJson){
            Map mapTypes = JSON.parseObject(o.toString());
            for (Object obj : mapTypes.keySet()){
                if(key.equals("keyList")){
                    resultKeyValue.add(obj.toString());
                }else if(key.equals("valueList")){
                    resultKeyValue.add(mapTypes.get(obj).toString());
                }
            }
        }
        return resultKeyValue;
    }



    private List<String> getHeadersList(List<T> dtoList) {
        if (dtoList == null || dtoList.size()<1) return null;
        List<String> resultList=new ArrayList<>();
            if (dtoList.get(0) instanceof Map){//map类型
                Map map=(Map) dtoList.get(0);
                for (Object key:map.keySet()) {
                    resultList.add(key.toString());
                }
            }else {
                Field[] fields = dtoList.get(0).getClass().getDeclaredFields();
                for (Field f:fields) {
                    resultList.add(f.getName());
                }
            }
        return resultList;
    }



    /**
     * @MethodDesc: 设置标题样式
     * @Author zhangnx
     * @Date 2019/1/24 17:43
     */
    private CellStyle getStyle(HSSFWorkbook wb,int bgStyle,int bgColor,String fontStyle,int fontColor,int fontSize,int align) {
        Font cellStyleFont = wb.createFont();
        CellStyle cellStyle = wb.createCellStyle();// 生成一个样式

        cellStyleFont.setFontHeightInPoints((short)fontSize);
        //cellStyleFont.setColor(IndexedColors.RED.getIndex());//字体颜色
        cellStyleFont.setColor((short)fontColor);//字体颜色
        cellStyleFont.setFontName(fontStyle);
        //cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//对齐
        cellStyle.setAlignment((short)align);//对齐
        cellStyle.setRightBorderColor(IndexedColors.RED.getIndex());
        cellStyle.setLeftBorderColor(IndexedColors.RED.getIndex());
        cellStyle.setTopBorderColor(IndexedColors.RED.getIndex());
        cellStyle.setBottomBorderColor(IndexedColors.RED.getIndex());
        cellStyle.setFont(cellStyleFont);
        HSSFPalette palette = wb.getCustomPalette();  //wb HSSFWorkbook对象
        palette.setColorAtIndex((short)9, (byte)(0x3e), (byte)(0x94), (byte)(0xe1));
        //cellStyle.setFillPattern(HSSFCellStyle.ALT_BARS);//背景色
        cellStyle.setFillPattern((short)bgStyle);//背景色
        cellStyle.setFillForegroundColor((short)bgColor);

        return cellStyle;
    }

/***************************结束******************************************************************************************************
 ******* 导出Excel
 ***************************结束**********************************************************************************************************/









/*******************************开始******************************************************************************************************
******* 获取Excel表格数据(批量导入)
********************************开始*****************************************************************************************************/
    public  List<T> getExcelDataList(MultipartFile file, T t){

        List<T> resultList = new ArrayList<>();

        /**********************判空********************/
        if (file == null) return null;
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName) && file.getSize() == 0) return null;


        /**** 判断文件的类型，是2003还是2007 ***/
        boolean isExcel2003 = true;
        if (isExcel2007(fileName)) isExcel2003 = false;


        Workbook workbook = null;
        try {
            workbook = isExcel2003 ? new HSSFWorkbook(file.getInputStream()) : new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {e.printStackTrace();}


        Sheet sheet = workbook.getSheetAt(0);


        /**********************获取数据行********************/
        List<Row> dataRowList = new ArrayList<>();
        int count = 0;
        for (Row r : sheet) {
            if (count>1)dataRowList.add(r);
            count++;
        }



        /*************获取表头属性字段；与对象属性对应*****************/
        List<String> headersList = new ArrayList<>();
        Row row = sheet.getRow(0);
        if (row==null)return null;

        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            headersList.add(getCellValue(row.getCell(i)));
        }

        if (headersList==null||headersList.size()<1)return null;


        /*************循环读取行的数据*****************/
        for (Row dRow : dataRowList) {
            T newT = null;
            try {
                newT = (T) t.getClass().newInstance();//创建了一个T对象,默认调用该类的空参数构造方法
                int cellNumber = 0;
                for (String s : headersList) {//循环获取每列值
                    Field field = t.getClass().getDeclaredField(s);
                    field.setAccessible(true);// 取消语言访问检查
                    setValue(field, newT, dRow, cellNumber);
                    cellNumber++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isNewTFiledAllBank(newT) > 0) resultList.add(newT);
        }

        return resultList;
    }



    /**
     * @MethodDesc: 判断对象的所有属性，如果所有属性没有值，则不添加到List中
     * @Author zhangnx
     * @Date 2019/1/26 11:24
     */
    public int isNewTFiledAllBank(T newT){
        int allFiledNotbankCount=0;
        Field[] newFields = newT.getClass().getDeclaredFields();//获得JavaBean全部属性
        for (Field field : newFields) {
            String getMethodName = "get"+field.getName().substring(0, 1).toUpperCase()+ field.getName().substring(1);
            Class tCls = newT.getClass();
            Method getMethod;
            try {
                getMethod = tCls.getMethod(getMethodName, new Class[] {});
                Object value = getMethod.invoke(newT, new Object[] {});
                if (value!=null) allFiledNotbankCount++;

            } catch (Exception e) {e.printStackTrace();}
        }
        return allFiledNotbankCount;
    }




    /**
     * @MethodDesc: 设置值时类型判断
     * @Author zhangnx
     * @Date 2019/1/25 19:01
     */
    public void setValue(Field field,T t,Row row,int cellNumber){

        String cellValue = getCellValue(row.getCell(cellNumber));
        boolean notBlank = StringUtils.isNotBlank(cellValue);
        Type type = field.getGenericType();

        try {
            if (type.equals((type==int.class||type==Integer.class)&& notBlank)){
                field.set(t,Integer.parseInt(cellValue));
            }else if ((type==short.class||type==Short.class) && notBlank){
                field.set(t,Short.valueOf(cellValue));
            }else if ((type==double.class||type==Double.class) && notBlank){
                field.set(t,Double.parseDouble(cellValue));
            }else if ((type==boolean.class||type==Boolean.class) && notBlank){
                field.set(t,Boolean.valueOf(cellValue));
            }else if (type==Date.class && notBlank){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                field.set(t,sdf.parse(cellValue));
            }else {
                field.set(t,cellValue);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * @MethodDesc: 获取每列值
     * @Author zhangnx
     * @Date 2019/1/25 18:23
     */
    public String getCellValue(Cell cell) {
        if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            double value = cell.getNumericCellValue();
            return new BigDecimal(value).toString();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            return String.valueOf(cell.getStringCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }



    public boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * @MethodDesc: 验证是否合格
     * @Author zhangnx
     * @Date 2019/1/25 18:23
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) return false;
        return true;
    }
/***************************结束******************************************************************************************************
******* 获取Excel表格数据(批量导入)
****************************结束*********************************************************************************************************/
}
