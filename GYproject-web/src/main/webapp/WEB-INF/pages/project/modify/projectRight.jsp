<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
		<button type="button" onclick="saveClick()" class="btn btn-success btn-sm">保存</button>
		<button type="button" onclick="cancelClick()" class="btn btn-danger btn-sm">取消</button>
	</div>
	<input type="hidden" name="unitType" id="unitType" value="${unitType}"/>
	<input type="hidden" name="designerPost" id="designerPost" value="${designerPost}"/>
	<input type="hidden" name="surveyPost" id="surveyPost" value="${surveyPost}"/>
	<input type="hidden" name="surveyBuilderPost" id="surveyBuilderPost" value="${surveyBuilderPost}"/>

	<div class="clearboth form-box">
		<form id="projectForm" class="form-horizontal"  data-parsley-validate="true" action="">

			<input type="hidden" name="projId" id="projId"/>
			<input type="hidden" name="corpId" id="corpId"/>
			<div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label" for="projNo">工程编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
				</div>
			</div>
			<div class="form-group col-md-12  ">
				<label class="control-label" for="projName">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm controlField"  id="projName" name="projName" data-parsley-maxlength="200" value="" data-parsley-required="true"/>
				</div>
			</div>
			<div class="form-group col-md-12  ">
				<label class="control-label" for="projAddr">工程地址</label>
				<div>
					<input type="text" class="form-control input-sm controlField"  id="projAddr" name="projAddr" value="" data-parsley-required="true"/>
				</div>
			</div>
			<!--  客户信息 -->
			<div class="form-group col-md-12 noUser ">
				<label class="control-label" for="custName">客户名称</label>
				<div>
					<input type="hidden" id="custId" name="custId"/>
					<input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200" data-parsley-required="true"/>
					<a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a>
				</div>

			</div>
			<div class="form-group col-md-6 noUser ">
				<label class="control-label" for="custContact">联系人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="20" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 noUser ">
				<label class="control-label" for="custPhone">联系电话</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" value=""/>
				</div>
			</div>

			<div class="form-group col-md-6 hidden">
				<label class="control-label" >受理人员</label>
				<div>
					<input type="hidden"  id="accepterId" name="accepterId">
					<input type="text" class=" form-control input-sm field-not-editable" id="accepter" name="accepter" data-parsley-required="true">
					<a id="accepterIdPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择受理人"><i class="fa fa-search"></i></a>
				</div>
			</div>
			<div class="form-group col-md-6 hidden">
				<label class="control-label" >踏勘人员</label>
				<div>
					<input type="hidden" id="surveyerId" name="surveyerId">
					<input type="text" class=" form-control input-sm field-not-editable" id="surveyer" name="surveyer" data-parsley-required="true">
					<a id="surveyerPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择踏勘人"><i class="fa fa-search"></i></a>
				</div>
			</div>
			<div class="form-group col-md-6 ">
				<label class="control-label" >设计人</label>
				<div>
					<input type="hidden"  id="designerId" name="designerId"/>
					<input type="text" class=" form-control input-sm field-not-editable" id="designer" name="designer" data-parsley-required="true">
					<a id="designerPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择设计人"><i class="fa fa-search"></i></a>
				</div>
			</div>

			<div class="form-group col-md-6 ">
				<label class="control-label" >现场代表</label>
				<div>
					<input type="hidden"  id="builderId" name="builderId"/>
					<input type="text" class=" form-control input-sm field-not-editable" id="budiler" name="budiler" data-parsley-required="true">
					<a id="builderPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择现场代表"><i class="fa fa-search"></i></a>
				</div>
			</div>

			<div class="form-group col-md-12">
				<label class="control-label" for="projScaleDes">工程规模</label>
				<div>
					<textarea class="form-control controlField" name ="projScaleDes" id="projScaleDes" rows="3"></textarea>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="corpName">燃气公司</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >工程类型</label>
				<div>
					<input type="hidden"  id="projectType" name="projectType"/>
					<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >出资方式</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" >业务部门</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="projSourceDes">受理来源</label>
				<div>
					<input type="text" class="form-control field-not-editable"  id="projSourceDes" name="projSourceDes" rows="2" data-parsley-maxlength="200"/>
				</div>
			</div>
		</form>

		<div class="clearboth form-box m-t-5 scaleTableForm">
			<form id="scaleTableForm"  data-parsley-validate="true">
				<table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%">
					<thead>
					<tr>
						<th width="80px">细类</th>
						<th width="50px">吨位</th>
						<th width="50px">座数</th>
						<th width="50px">台数</th>
						<th width="50px">户数</th>
						<th width="80px">预计用量(m³/h)</th>
					</tr>
					</thead>
				</table>

			</form>
		</div>
		<div class="clearboth form-box m-t-5 scaleTableChangeForm hidden">
			<form id="scaleTableChangeForm"  data-parsley-validate="true">
				<table id="scaleChangeTable" class="table table-striped table-bordered nowrap" width="100%">
				<thead>
				<tr>
					<th width="80px">细类</th>
					<th width="50px">管径</th>
					<th width="50px">长度(千米)</th>
				</tr>
				</thead>
				</table>
			</form>
		</div>

	</div>
</div>


<script>

    var detailSearchData = {};
    App.restartGlobalFunction();
    $(".infodetails").hideMask();//隐藏loading
    $("#projectForm").toggleEditState(false).styleFit();
    $('.datepicker-default').datepicker({ todayHighlight: true});//日期控件

	trSData.t && trSData.t.cgetDetail('projectForm','projectModify/viewProjectDetail','',detailCallback);//第一次加载显示详细



	function saveClick() {
		$(".infodetails").hideMask("正在保存...");

		var formData = $("#projectForm").serializeJson();
		formData.scaleDetails = getScaleData(formData.projectType);

		Base.subimt('projectModify/updateProjectRelationInfo','POST',JSON.stringify(formData),function (data) {
			var content =data?"保存成功！":"保存失败！";
			tips(content);
			$("#projectTable").cgetData(true);//列表重新加载
		})
	}


    //根据工程类型取得不同的工程规模
	function getScaleData(projectType) {
        var tableform=(projectType==13 || projectType==14)?$("#scaleTableChangeForm"):$("#scaleTableForm");   //干线工程规模或者改管规模取相应的表格
		var mdata = tableform.getDTFormData();
		var resultData = [];
		for(var i=0;i<mdata.length;i++){
			var data = mdata[i];
			if(data.scaleId !== undefined){
				data.projNo = $("#projNo").val();
				data.projId = $("#projId").val();
				if((data.tonnage!==null&&data.tonnage!==""&& data.tonnage!==undefined) || (data.searNum!==null&&data.searNum!==""&& data.searNum!==undefined) || (data.num!==null&&data.num!==""&& data.num!==undefined) || (data.houseNum!==null&&data.houseNum!==""&& data.houseNum!==undefined) || (data.gasConsumption!==null&&data.gasConsumption!==""&& data.gasConsumption!==undefined)||(data.pipeDiameter!==null&&data.pipeDiameter!==""&& data.pipeDiameter!==undefined)||(data.pipeLength!==null&&data.pipeLength!==""&& data.pipeLength!==undefined) ){
					resultData.push(data);
				}
			}
		}
		return resultData;
	}








	function tips(content) {
		var myoptions = {
			title: "提示信息",
			content: content,
			accept: popClose,
			chide: true,
			icon: "fa-check-circle"
		};
		$("body").cgetPopup(myoptions);
	}

	//选择现场代表
	$("#builderPop").off().on("click",function(){
		var postBul=$("#surveyBuilderPost").val();
		var corpId=$("#corpId").val();
		var unitType=$("#unitType").val();
		var deptId = '';
		staffPopup({
			'budiler':'staffName',
			'builderId':'staffId'
		},postBul,unitType,deptId,corpId)
	});

	//选择设计
	$("#designerPop").off().on("click",function(){
		var postBul=$("#designerPost").val();
		var corpId=$("#corpId").val();
		var unitType=$("#unitType").val();
		var deptId = '';
		staffPopup({
			'designer':'staffName',
			'designerId':'staffId'
		},postBul,unitType,deptId,corpId)
	});

	//选择踏勘人
	$("#surveyerPop").off().on("click",function(){
		var postBul=$("#surveyPost").val();
		var corpId=$("#corpId").val();
		var	unitType=$("#unitType").val();
		var deptId = '';
		staffPopup({
			'surveyer':'staffName',
			'surveyerId':'staffId'
		},postBul,unitType,deptId,corpId)
	});

    //选择申报单位
    $("#custPop").off().on("click",function(){
        $("body").cgetPopup({
            title: '申报单位选择',
            content: '#popup/custListPop',
            size: 'large',
            callback:function(){
                customer.init();
            },
            accept: function(){
                if($("#customerTable tr.selected").length < 1){
                    $("body").cgetPopup({title:'提示',content:"请选择申报单位！",newpop:'second',accept:innerClose});
                    return false;
                }else{
                    var data = trSData.customerTable.json;
                    $("#custName").val(data.custName);
                    $("#custId").val(data.custId);
                    //联系人、联系电话、单位地址
                    $("#custPhone").val(data.custPhone);
                    $("#custContact").val(data.custContcat);
                }
            }
        });
    });

	var detailCallback=function (data) {
		detailSearchData.projId=data.projId;
		detailSearchData.projLtypeId=data.projLtypeId;
		$('.controlField').attr('readonly',true);
		if (data.projectType==13 || data.projectType==14){
			$(".scaleTableForm").addClass("hidden");
			$(".scaleTableChangeForm").removeClass("hidden");

			if($.fn.DataTable.isDataTable('#scaleChangeTable')){ //初始化过
				$("#scaleChangeTable").cgetData(false,scaleTableCallBack);//列表重新加载
			}else{
				sacletableinit2();
			}
		}else {
			if($.fn.DataTable.isDataTable('#scaleTable')){//初始化过
				$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
			}else{
				sacletableinit();
				}
		}



	};








    var scaleTableCallBack = function(){
		$('#scaleTableForm').toggleEditState(false);
		$('#scaleChangeTable').toggleEditState(false);
    };

    /**
     * 初始化规模列表
     */
    var sacletableinit= function() {
           $('#scaleTable').on( 'init.dt',function(){
            }).DataTable({
                language: language_CN,
                lengthMenu: [18],
                dom: 'Brt',
                buttons: [],
                serverSide:false,
                ajax: {
                    url: 'projectModify/queryScaleDetail',
                    type:'post',
                    data: function(d){
                        $.each(detailSearchData,function(i,k){
                            d[i] = k;
                        });
                    },
                    dataSrc: 'data'
                },
                responsive: {
                    details: {
                        renderer: function ( api, rowIdx, columns ){
                            return renderChild(api, rowIdx, columns);
                        }
                    }
                },//自适应
                columns: [
                    {"data":"projStypeDes", responsivePriority: 1},
                    {"data":"tonnage",className:"text-right", responsivePriority: 3},
                    {"data":"searNum",className:"text-right", responsivePriority: 6},
                    {"data":"num",className:"text-right", responsivePriority: 5},
                    {"data":"houseNum",className:"text-right", responsivePriority: 4},
                    {"data":"gasConsumption",className:"text-right", responsivePriority: 7}
                ],
                columnDefs: [{
                    "targets":0,
                    //长字符串截取方法
                    render: $.fn.dataTable.render.ellipsis({
                        length: 6, 	//截取多少字符（或汉字）
                        end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                    })
                },{
                    targets: 1,
                    render: function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data === null){
                                data = "";
                            }
                            var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_tonnage' id='" + row.projStypeId + "_tonnage'  data-parsley-maxlength='16' data-parsley-type='number' value="+data+"></div>"
                            return tdcon;
                        }else{
                            return data;
                        }}
                },{
                    targets: 2,
                    render: function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data === null){
                                data = "";
                            }
                            var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_searNum' id='" + row.projStypeId + "_searNum' data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
                            return tdcon;
                        }else{
                            return data;
                        }}
                },{
                    targets: 3,
                    render: function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data === null){
                                data = "";
                            }
                            var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_num' id='" + row.projStypeId + "_num'  data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
                            return tdcon;
                        }else{
                            return data;
                        }	}
                },{
                    targets: 4,
                    render: function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data === null){
                                data = "";
                            }
                            var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_houseNum' id='" + row.projStypeId + "_houseNum' data-parsley-maxlength='9' data-parsley-type='integer' value="+data+"></div>"
                            return tdcon;
                        }else{
                            return data;
                        }}
                },{
                    targets: 5,
                    render: function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data === null){
                                data = "";
                            }else if(data !==null && data!==""){
                                data = data.toFixed(2);
                            }
                            var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
                                "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
                                "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
                                "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
                                "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_gasConsumption' id='" + row.projStypeId + "_gasConsumption'  data-parsley-type='number' value="+data+"></div>"
                            return tdcon;
                        }else{
                            return data;
                        }}
                }],
                ordering:false
            });
    };
    /**
     * 初始化规模列表
     */
    var sacletableinit2= function() {
            $('#scaleChangeTable').on( 'init.dt',function(){
            }).DataTable({
                language: language_CN,
                lengthMenu: [18],
                dom: 'Brt',
                buttons: [],
                serverSide:false,
                ajax: {
                    url: 'projectModify/queryScaleDetail',
                    type:'post',
                    data: function(d){
                        $.each(detailSearchData,function(i,k){
                            d[i] = k;
                        });

                    },
                    dataSrc: 'data'
                },
				responsive: {
                    details: {
                        renderer: function ( api, rowIdx, columns ){
                            return renderChild(api, rowIdx, columns);
                        }
                    }
                },//自适应
                columns: [
                    {"data":"projStypeDes", responsivePriority: 3},
                    {"data":"pipeDiameter",className:"text-right", responsivePriority: 2},
                    {"data":"pipeLength",className:"text-right", responsivePriority: 1}
                ],
                columnDefs: [{
                    "targets":0,
                    //长字符串截取方法
                    render: $.fn.dataTable.render.ellipsis({
                        length: 6, 	//截取多少字符（或汉字）
                        end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
                    })
                },{
                    targets: 1,
                    render: function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data === null){
                                data = "";
                            }
                            var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeDiameter' id='" + row.projStypeId + "_pipeDiameter'  data-parsley-maxlength='50' value="+data+"></div>"
                            return tdcon;
                        }else{
                            return data;
                        }}
                },{
                    targets: 2,
                    render: function ( data, type, row, meta ) {
                        if(type === "display"){
                            if(data === null){
                                data = "";
                            }else if(data !==null && data!==""){
                                data = data;
                            }
                            console.info("row.scaleId---"+row.scaleId);

                            var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
                                "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
                                "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
                                "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
                                "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_pipeLength' id='" + row.projStypeId + "_pipeLength'  data-parsley-type='number' value="+data+"></div>"
                            return tdcon;
                        }else{
                            return data;
                        }}
                }],
                ordering:false
            });
    };







    function cancelClick() {
        $("#projectForm").toggleEditState(false);
        $('#projectTable').cgetData(true);
    }
</script>
