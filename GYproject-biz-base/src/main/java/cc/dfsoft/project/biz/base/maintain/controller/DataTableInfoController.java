package cc.dfsoft.project.biz.base.maintain.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.entity.StatusEnum;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @Description: 表数据操控
* @author zhangnx
* @date 2019/8/29 8:53
*/
@Controller
@RequestMapping(value = "/dataTableInfo")
public class DataTableInfoController {

    @Resource
    AbandonedRecordService abandonedRecordService;
    @Resource
    WorkFlowService workFlowService;
    @Resource
    ProjectService projectService;



    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView model) {
        model.addObject("tables", abandonedRecordService.queryThisDBAllTables(null));
        model.setViewName("maintain/dataTableInfo");
        return model;
    }




    @RequestMapping(value = "/abandonedRecordPopPageDetail")
    public ModelAndView abandonedRecordPopPageDetail(ModelAndView model,AbandonedRecordReq recordReq) {
        model.addObject("abandonedRecord",abandonedRecordService.queryAbandonedRecord(recordReq));
        model.addObject("mapList",abandonedRecordService.queryAbandonedStepIdList(recordReq));
        model.addObject("projId",recordReq.getProjId());
        model.addObject("loginPost", SessionUtil.getLoginInfo().getPost());
        model.addObject("adminPost", PostTypeEnum.ADMIN.getValue());
        model.setViewName("maintain/abandonedRecordPopPageDetail");
        return model;
    }



    @RequestMapping(value = "/reasonPopPage")
    public ModelAndView reasonPopPage(ModelAndView model,AbandonedRecordReq recordReq) {
        SimpleDateFormat ymdhmsSsdf = new SimpleDateFormat("yyyy-MM-dd");

        model.addObject("_businessId",recordReq.getBusinessId());
        model.addObject("_statusEnumList",getStepList(recordReq.getMenuId()));
        model.addObject("_operator", SessionUtil.getLoginInfo().getStaffName());
        model.addObject("_operatingTime",ymdhmsSsdf.format(new Date()));

        model.setViewName("maintain/reasonPopPage");

        return model;
    }




    @RequestMapping(value = "/recoveryPopPage")
    public ModelAndView recoveryPopPage(ModelAndView model,AbandonedRecordReq recordReq) {
        SimpleDateFormat ymdhmsSsdf = new SimpleDateFormat("yyyy-MM-dd");

        model.addObject("_projId",recordReq.getProjId());
        model.addObject("_stepList",getWorkFlowActionEnumList(recordReq.getProjId()));
        model.addObject("_operator", SessionUtil.getLoginInfo().getStaffName());
        model.addObject("_operatingTime",ymdhmsSsdf.format(new Date()));

        model.setViewName("maintain/recoveryPopPage");

        return model;
    }




    private List<WorkFlowActionEnum> getWorkFlowActionEnumList(String projId) {
        Project project = projectService.findById(projId);
        if (project==null){return null;}

        WorkFlow workFlow = workFlowService.queryWorkFlowCode(project.getCorpId(), project.getProjectType(), project.getContributionMode(), WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
        if (workFlow==null || StringUtils.isBlank(workFlow.getWorkFlowCode())) {return null;}

        List<WorkFlowActionEnum> enumList=new ArrayList<>();
        String[] stepIds = workFlow.getWorkFlowCode().split(",");
        for (int i = 0; i <stepIds.length ; i++) {
            for (WorkFlowActionEnum e:WorkFlowActionEnum.values()) {
               if (StringUtils.isNotBlank(stepIds[i]) && stepIds[i].equals(e.getActionCode())){
                   enumList.add(e);
                   break;
               }
            }
        }
        return  enumList;
    }





    public List<StatusEnum> getStepList(String menuId){
      List<StatusEnum> enumList=new ArrayList<>();
      for (StatusEnum e:StatusEnum.values()) {
        if (StringUtils.isNotBlank(menuId) && menuId.equals(e.getMenuId())){
            enumList.add(e);
        }
      }
      return enumList;
    }





    @RequestMapping(value = "/queryAbandonedRecord")
    @ResponseBody
    public AbandonedRecord queryAbandonedRecord(@RequestBody AbandonedRecordReq recordReq) {
        return abandonedRecordService.queryAbandonedRecord(recordReq);
    }




    @RequestMapping(value = "/queryTableByComment")
    @ResponseBody
    public List<Map<String, Object>> queryTableByComment(String tableComment) {
        return abandonedRecordService.queryThisDBAllTables(tableComment);
    }



    @RequestMapping(value = "/queryColumnsByTableName")
    @ResponseBody
    public List<Map<String, Object>> queryColumnsByTableName(String tableName,String comment) {
       return abandonedRecordService.queryColumnsByTableName(tableName,comment);
    }


    @RequestMapping(value = "/queryThisTableDataByCriteriaMap")
    @ResponseBody
    public Map<String,Object> queryThisTableDataByCriteriaMap(@RequestBody Map<String, Object> criteriaMap) {
        if (criteriaMap==null) return null;
        Object tableName = criteriaMap.get("tableName");
        criteriaMap.remove("tableName");

        Map<String,Object> resultMap=new HashMap<>();
        if (tableName!=null && StringUtils.isNotBlank(tableName.toString())){
            try {
                resultMap.put("tableData",abandonedRecordService.getThisTableOrigData(tableName.toString(), criteriaMap));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return resultMap;
    }



    @RequestMapping(value = "/queryThisTableMapListData")
    @ResponseBody
    public Map<String,Object> queryThisTableMapListData(@RequestBody Map<String, Object> criteriaMap) {

        if (criteriaMap==null) return null;
        Object tableName = criteriaMap.get("tableName");
        criteriaMap.remove("tableName");

        Map<String,Object> resultMap=new HashMap<>();
        List<Map<String, Object>> mapList = abandonedRecordService.queryThisTableMapListData(tableName.toString(), criteriaMap);
        resultMap.put("dataList",mapList);
        int totalRecord=abandonedRecordService.querythisTableTotalRecord(tableName.toString(),criteriaMap);
        resultMap.put("total",totalRecord);
        return resultMap;
    }



    @RequestMapping(value = "/loadUpdatePage")
    public ModelAndView loadUpdatePage(String tableName,String primaryKey,String id) {
        ModelAndView model=new ModelAndView();
        List<Map<String, Object>> mapList = abandonedRecordService.queryColumnsByTableName(tableName, null);

        model.addObject("tableName",tableName);
        model.addObject("primaryKey",primaryKey);
        model.addObject("primaryKeyComment",getColumnComment(mapList,primaryKey));
        model.addObject("primaryKeyVal",id);
        model.addObject("columns",abandonedRecordService.queryColumnsByTableName(tableName,null));
        model.setViewName("maintain/updateTableDataPage");
        return model;
    }



    public String getColumnComment( List<Map<String, Object>> mapList,String primaryKey){
        if (mapList!=null && mapList.size()>0){
            for (Map<String, Object> map:mapList) {
                Object columnName = map.get("columnName");
                if (columnName!=null && columnName.toString().equals(primaryKey)){
                    return map.get("columnComment")+"";
                }
            }
        }
        return null;
    }


    @RequestMapping(value = "/queryThisTableDataById")
    @ResponseBody
    public Map<String,String> queryThisTableDataById(@RequestBody Map<String, Object> criteriaMap) {
        if (criteriaMap==null) return null;
        Object tableName = criteriaMap.get("tableName");
        criteriaMap.remove("tableName");
        Map<String, Object> map = abandonedRecordService.queryThisTableDataById(tableName + "", criteriaMap);

        return MapUtils.objTransformStr(map);
    }




    @RequestMapping(value = "/updateThisTableDataById")
    @ResponseBody
    public Map<String,Object> updateThisTableDataById(@RequestBody Map<String, Object> criteriaMap) {
        if (criteriaMap==null) return null;
        Object tableName = criteriaMap.get("tableName");
        criteriaMap.remove("tableName");

        Map<String,Object> whereMap= (Map<String, Object>) criteriaMap.get("where");
        criteriaMap.remove("where");

        Map<String, Object> map = abandonedRecordService.updateThisTableDataById(tableName + "", criteriaMap, whereMap);
        return map;

    }

    @RequestMapping(value = "/saveResetRecord")
    @ResponseBody
    public boolean saveResetRecord(@RequestBody AbandonedRecord abandonedRecord) {
         abandonedRecordService.saveAbandonedRecord(abandonedRecord);
         return true;
    }


}
