package cc.dfsoft.project.biz.base.maintain.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
*@ Description: 错误日志
*@ Author: zhangnx
*@ Date: 2019/10/16 11:30
*@ Version:1.0
*/
@Controller
@RequestMapping(value = "/errorLog")
public class ErrorLogController {

    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView model) {
        model.setViewName("maintain/errorLog");
        return model;
    }



    /**
    *@ Description: 读取文件内容
    *@ Author zhangnx
    *@ date 2019/10/17 10:22
    */
    @RequestMapping(value = "/readErrorLog")
    @ResponseBody
    public String readErrorLog(String  charset,String fileName,String  numRead) {

        charset=StringUtils.isNotBlank(charset)?charset:"GBK";
        fileName=StringUtils.isNotBlank(fileName)?fileName:"project-web-error.log";
        long readRow=(StringUtils.isNotBlank(numRead) && isNumber(numRead))?Long.valueOf(numRead):200;

        String OS = System.getProperty("os.name").toLowerCase();
        String path=System.getProperty("catalina.home");

        if (OS.contains("windows")){
            path= path+"\\logs\\"+fileName;
        }else if (OS.contains("linux")){
            path= path+"/logs/"+fileName;
        }

        StringBuilder sbr = new StringBuilder();
        List<String> read = readLastNLine(path,charset,readRow);

        if (read==null && read.size()<1){return sbr.toString(); }

        for (String s : read) {
            if (s.contains("ERROR")){
                sbr.append("<span style='color:red'><b>").append(s).append("</b></span>").append("<br/>");
            }else if (s.contains("NullPointerException")){
                sbr.append("<span style='color:blue'>").append(s).append("</span>").append("<br/>");
            }else {
                sbr.append(s).append("<br/>");
            }
        }
        return sbr.toString();
    }






    /**
    *@ Description: 判断字符串是否全为数字
    *@ Author zhangnx
    *@ date 2019/10/17 10:21
    */
    private boolean isNumber(String string) {
        if (string == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }







    /**
    *@ Description: 读取文件全部内容
    *@ Author zhangnx
    *@ date 2019/10/17 10:21
    */
    private String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String buff = reader.readLine();
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if (tempStr.contains("ERROR")) {
                    sbf.append("<span style='color:red'><b>");
                    sbf.append(tempStr).append("<></span>").append("<br/>");
                }else{
                    sbf.append(tempStr).append("<br/>");
                }
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }









    /**
    *@ Description: 读取文件倒数第N行
    *@ Author zhangnx
    *@ date 2019/10/17 10:22
    */
    private static List<String> readLastNLine(String fileName, String charset,long numRead) {
        File file = new File(fileName);
        List<String> result = new ArrayList<String>();
        long count = 0;
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile fileRead=null;
        try {
            fileRead = new RandomAccessFile(file, "r");
            long length = fileRead.length();
            if (length == 0L) {
                return result;
            } else {
                long pos = length - 1;
                while (pos > 0) {
                    pos--;
                    fileRead.seek(pos);
                    if (fileRead.readByte() == '\n') {
                        String line=fileRead.readLine();
                        result.add(new String(line.getBytes("ISO-8859-1"),charset));
                        count++;
                        if (count == numRead) {
                            break;
                        }
                    }
                }
                if (pos == 0) {
                    fileRead.seek(0);
                    result.add(fileRead.readLine());
                }
            }
            fileRead.close();
            Collections.reverse(result);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileRead != null) {
                try {
                    fileRead.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result;
    }









}




















