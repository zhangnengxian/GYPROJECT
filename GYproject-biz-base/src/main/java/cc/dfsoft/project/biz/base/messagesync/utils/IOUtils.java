package cc.dfsoft.project.biz.base.messagesync.utils;

import java.io.*;
/**
 *@Description: 读写文件
 *@author: zhangnx
 *@Date: 2019/12/6 13:56
 *@Version:1.0
 * */
public class IOUtils {

    /**
    * @Description: 功能描述
    * @author zhangnx
    * @param data 写入的数据
    * @param file 写文件
    * @date 2019/12/5 14:10
    **/
    public static void writeFile(String data, File file) throws IOException {
        FileOutputStream out =null;
        try {
            out=new FileOutputStream(file);
            out.write(data.getBytes());
            out.flush();
        }finally {
            close(out);
        }
    }

    /**
    * @Description: 读文件
    * @author zhangnx
    * @param file 文件
    * @date 2019/12/5 14:17
    **/
    public static String readFile(File file)throws IOException{
        InputStream in=null;
        ByteArrayOutputStream out =null;
        try {
            in=new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len=-1;
            while ((len=in.read(buf))!=-1){
                out.write(buf,0,len);
            }
            out.flush();
            byte[] data = out.toByteArray();
            return new String(data);
        }finally {
            close(in);
            close(out);
        }
    }


    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }



}
