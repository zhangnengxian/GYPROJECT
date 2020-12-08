package cc.dfsoft.project.biz.ifs.httpclien;

import cc.dfsoft.project.biz.base.messagesync.hongju.pojo.ConstructionInfo;
import cc.dfsoft.project.biz.base.messagesync.hongju.pojo.ProjectInfo;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.utils.HttpClientService;
import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {

    public static void main(String[] args) throws Exception {

        // String url="http://192.168.199.104:8080/httpClient/doPostControllerTwo";
       // HttpClientUtil.doGetSend(url);


//        StringBuffer params = new StringBuffer();
//        params.append("name=" + URLEncoder.encode("张能先", "utf-8"));
//        params.append("&");
//        params.append("age=24");
//        String url1="http://localhost:8080/httpClient/doGetControllerTwo" + "?" + params;
       // HttpClientUtil.doGetTestWayOne(url1);


//        URI uri = null;
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("name", "张能先"));
//        params.add(new BasicNameValuePair("age", "18"));
//        uri = new URIBuilder().setScheme("http").setHost("localhost")
//                .setPort(8080).setPath("/doGetControllerTwo")
//                .setParameters(params).build();
//        HttpClientUtil.doGetTestWayTwo(uri);



//         String url="http://localhost:8080/httpClient/doPostTestOne";
//         HttpClientUtil.doPostTestOne(url);




        // 参数
//        StringBuffer params = new StringBuffer();
//            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
//            params.append("name=" + URLEncoder.encode("张能先", "utf-8"));
//            params.append("&");
//            params.append("age=24");
//        String url="http://localhost:8080/httpClient/doPostControllerFour" + "?" + params;
//        HttpClientUtil.doPostTestFour(url);


        ProjectInfo projectInfo=new ProjectInfo();
        projectInfo.setProjNo("52010117100010");
        projectInfo.setProjScaleDes("民用——高层(户)810户");
        projectInfo.setProjectTypeDes("民用工程");
        projectInfo.setContributionModeDes("用户出资");
        projectInfo.setProjName("恒大中央公园C1区5、6、7、8、9栋");
        projectInfo.setProjAddr("观山湖区金珠东路");
        projectInfo.setProjStatusDes("施工中");
        projectInfo.setCustName("贵阳中渝置地房地产开发有限公司");
        projectInfo.setCustContact("按原预算签订合同。");
        projectInfo.setCustPhone("18984025135");
        projectInfo.setCorpId("1101");
        projectInfo.setDuName("张三");
        projectInfo.setDesigner("李佳");

        ConstructionInfo constructionInfo=new ConstructionInfo();
        constructionInfo.setConNo("52010117100010");
        constructionInfo.setProjTimeLimit("12天");
        //constructionInfo.setCpArriveDate(new Date());
        constructionInfo.setBuilder("石全");
        constructionInfo.setBulTel("15678965432");
        constructionInfo.setCpDocumenter("李青");
        constructionInfo.setHousehold("12户");
        constructionInfo.setRemark("无");
        constructionInfo.setDesignDrawingNo("SO-08-12");
        constructionInfo.setCuName("贵州鸿巨燃气热力工程有限公司");
        constructionInfo.setCuPm("谢苗");
        constructionInfo.setManagementQae("王强");
        constructionInfo.setDocumenter("赵谢");
        constructionInfo.setManagementWacf("张三");
        constructionInfo.setTechnician("李月文");
        constructionInfo.setSaftyOfficer("李元石");
        constructionInfo.setTestLeader("黎昱麟");
        constructionInfo.setWelder("李植义");
        constructionInfo.setSuName("贵州化兴建设监理有限公司");
        constructionInfo.setSuCse("罗琳");
        constructionInfo.setSuJgj("胡强");


        Map map=new HashMap<String,Object>();
        map.put("operate_type","SC-1");
        map.put("project",null);
        map.put("constructionPlan",null);
//        map.put("project", JSON.toJSONString(projectInfo));
//        map.put("constructionPlan",JSON.toJSONString(constructionInfo));

        String url="http://192.168.199.104:8080/httpClient/doPostControllerTwo";
        ResultMsg httpResult = HttpClientService.doPost(url,map,false);
        System.out.println(httpResult.getMsg());

//        User user = new User();
//        user.setName("潘晓婷&站更显");
//        user.setAge(18);
//        user.setGender("女");
//        user.setMotto("姿势要优雅~");


        //String url="http://localhost:8080/httpClient/doPostControllerTwo";
        //String url="http://localhost:8080/httpClient/doPostControllerFour";
      //  String url="http://120.77.244.8:8077/intelligent-factory/api/constructionTaskOrder/synchronizedConstructionTaskOrder";
        //String url="http://120.77.244.8:8077/intelligent-factory/api/workReport/synchronizedWorkReport";
        //String url="https://pm.guizhougas.cn/problemDocument/findChildMenuList?menuId="+201901;

//        ResultMsg httpResult = HttpClientService.doPost(url,map,false);
//        System.out.println(httpResult.toString());
//
//        System.out.println(JSON.toJSONString(map));

        // 将参数放入键值对类NameValuePair中,再放入集合中
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("name","张三"));
//        params.add(new BasicNameValuePair("age","18"));
//
//        URI uri = new URIBuilder().setScheme("http")
//                    .setHost("localhost")
//                    .setPort(8080)
//                    .setPath("/httpClient/doPostControllerThree")
//                    .setParameters(params)
//                    .build();
//
//        // 创建user参数
//        User user = new User();
//        user.setName("潘晓婷");
//        user.setAge(18);
//        user.setGender("女");
//        user.setMotto("姿势要优雅~");
//
//        ResultMessage httpResult = HttpClientService.doPost(uri, user,false);
//        System.out.println(httpResult.toString());


    }



}
