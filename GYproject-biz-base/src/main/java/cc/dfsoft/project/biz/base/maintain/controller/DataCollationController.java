package cc.dfsoft.project.biz.base.maintain.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.maintain.entity.MenuDocument;
import cc.dfsoft.project.biz.base.maintain.service.DataCollationService;
import cc.dfsoft.uexpress.biz.sys.auth.service.MenuService;

import com.alibaba.fastjson.JSONObject;

/**
* @Description: 表数据操控
* @author zhangnx
* @date 2019/8/29 8:53
*/
@Controller
@RequestMapping(value = "/dataCollation")
public class DataCollationController {

@Resource
DataCollationService dataCollationService;

@Resource
MenuService menuService;

    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView model) {
        model.setViewName("maintain/dataCollation");
        return model;
    }




    @RequestMapping(value = "/getMenuTreeData")
    @ResponseBody
    public List<Map<String, String>> getMenuTreeData(){
        try{
            List<Map<String, String>> maplist = menuService.getMenurelationDocTreeData();
            return maplist;
        }catch(Exception e){
            return null;
        }
    }






    @RequestMapping(value = "/saveUpdateThisTableData")
    @ResponseBody
    public boolean saveUpdateThisTableData(MenuDocument menuDocument) {
        try {
            dataCollationService.saveUpdateThisTableData(menuDocument);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    @RequestMapping(value = "/queryThisTableData")
    @ResponseBody
    public MenuDocument queryThisTableData(MenuDocument menuDocument) {
        MenuDocument md = dataCollationService.queryThisTableData(menuDocument);
        return md;
    }

    @RequestMapping(value ="/uploadImg")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        // 文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath("\\") + "upload\\image";

        String saveUrl = request.getContextPath()+ "upload\\image";


        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        // 最大文件大小
        long maxSize = 1000000;
        response.setContentType("text/html; charset=UTF-8");

        if (!ServletFileUpload.isMultipartContent(request)) {
            writer.println(getError("请选择文件。"));
            return;
        }

        String os = System.getProperties().getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1){
//            savePath.replaceAll("\\","/");
//            saveUrl.replaceAll("\\\","/");
        }else {
            savePath.replaceAll("/","\\");
            saveUrl.replaceAll("/","\\");
        }
        File uploadDir = new File(savePath);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            writer.println(getError("上传目录没有写权限。"));
            return;
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            writer.println(getError("目录名不正确。"));
            return;
        }

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        String fileName = null;
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            fileName = mFile.getOriginalFilename();
            // 检查文件大小
            if (mFile.getSize() > maxSize) {
                writer.println(getError("上传文件大小超过限制。"));
                return;
            }
            String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
            if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                writer.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }
            UUID uuid = UUID.randomUUID();
            String path = savePath + uuid.toString() +"."+ fileExt;
            saveUrl = saveUrl + uuid.toString() +"."+ fileExt;
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
            FileCopyUtils.copy(mFile.getInputStream(), outputStream);

            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", "/upload/image/7d13f7b5-c9c5-419b-abe0-ef0e11c1ff08.jpg");
            writer.println(obj.toString());

        }
    }


    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
    }

    @RequestMapping(value = "/fileManager")
    public void fileManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //根目录路径，可以指定绝对路径，比如 /var/www/attached/
        //String rootPath = request.getSession().getServletContext().getRealPath("/")+ "images/";
        String rootPath ="D:\\wallpapers";

        //根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
        String rootUrl = request.getContextPath() + "/images/";
        //图片扩展名
        String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

        //根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        //排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

        //不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            System.out.println("Access is not allowed.");
            return;
        }
        //最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            System.out.println("Parameter is not valid.");
            return;
        }
        //目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if(!currentPathFile.isDirectory()){
            System.out.println("Directory does not exist.");
            return;
        }

        //遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if(currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if(file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if(file.isFile()){
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);
        response.setContentType("application/json; charset=UTF-8");
        //System.out.println(result.toJSONString());
        PrintWriter writer=response.getWriter();
        writer.println(result);
    }

    public class NameComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable)a;
            Hashtable hashB = (Hashtable)b;
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
            }
        }
    }
    public class SizeComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable)a;
            Hashtable hashB = (Hashtable)b;
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
                    return 1;
                } else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
    public class TypeComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable)a;
            Hashtable hashB = (Hashtable)b;
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
            }
        }
    }

}




















