<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div id="hasEloam" style="height: 640px;" class="row">
	<OBJECT ID="EloamGlobal_ID" CLASSID="CLSID:52D1E686-D8D7-4DF2-9A74-8B8F4650BF73" style="display:none"></OBJECT>
	<div class="col-md-5">
		<OBJECT ID="EloamView1" CLASSID="CLSID:26BA9E7F-78E9-4FB8-A05C-A4185D80D759" WIDTH="400" HEIGHT="400"></OBJECT>
	</div>
	<div class="col-md-7">
		<OBJECT ID="EloamThumbnail" CLASSID="CLSID:B5535A1B-D25B-4B3C-854F-94B12E284A4E" WIDTH="540" HEIGHT="400"></OBJECT>
	</div>
	<div id="nameList" class="width-full bordered p-10" style="height: auto; overflow:hidden; clear: both;">
	</div>
	<div class="col-md-12">
			<select id="res1" class="form-control input-sm width-150 pull-left m-r-10"></select> 
			<button class="submit_01 btn btn-sm pull-left m-r-10" type="button" onclick="ShowVideo()"><i class="fa fa-video-camera"></i> 打开设备</button>	
			<button class="submit_01 btn btn-sm pull-left m-r-10" type="button" onclick="Scan()"><i class="fa fa-camera"></i> 拍照</button>
			<button class="submit_01 btn btn-sm pull-left m-r-10" type="button" onclick="resetName()"><i class="fa fa-edit"></i> 重命名</button>
			<button class="submit_01 btn btn-sm pull-left" type="button" onclick="Upload()"><i class="fa fa-upload"></i> 上传</button>
	</div>
	<div class="col-md-12 upload-notice" style="display:none; position: relative;">
		<p class="text-danger p-15 m-t-10 m-b-10" style="border: #e2e2e2 1px solid; border-radius:5px;"></p>
		<a href="javascript:;" class="btn btn-white btn-xs btn-circle notice-close" style="position: absolute; top: 20px; right: 20px;"><i class="fa fa-times"></i></a>
	</div>
</div>
<div id="noEloam" class="hidden"><i class="fa fa-warning"></i> 请连接高拍仪</div>
<div id="eloamDrive" class="hidden"><i class="fa fa-warning"></i> 缺少高拍仪驱动</div>
<div id="ieEloam" class="hidden"><i class="fa fa-warning"></i> 请使用ie浏览器访问高拍仪</div>
<script>
	App.restartGlobalFunction();
	
	var EloamGlobal;
	var dev1;
	var video1;
	var type;
	var imageList = [],
	    //文件名称列表
	    nameList=[];
	
	function hideNotice(){
		setTimeout(function(){
			$(".upload-notice").fadeOut(function(){
				$(".upload-notice p").text('');
			});
		}, 6000);
	}
	
	function closeNotice(){
		$(".upload-notice").fadeOut(function(){
			$(".upload-notice p").text('');
		});
	}
	
	function showNotice(text, func, callback){
		$(".upload-notice p").text(text);
		if(func && 'function' === typeof func){
			$(".upload-notice p").append('<a href="javascript:;" class="btn btn-xs btn-primary confirm-btn p-l-10">确定</a>');
			$(".upload-notice .confirm-btn").click(function(){
				func();
			});
		}
		$(".upload-notice").fadeIn(function(){
			callback && callback();
			hideNotice();
		});
	}
	
	$(".notice-close").click(function(){
		closeNotice();
	});
	
	function Load()
	{   if(!_isIE){
		   $("#hasEloam").addClass("hidden");
		   $("#ieEloam").removeClass("hidden");
		   return false;
	     }
		EloamGlobal = document.getElementById("EloamGlobal_ID");
		if(!EloamGlobal){
			 $("#hasEloam").addClass("hidden");
			 $("#eloamDrive").removeClass("hidden");
			 return false;
		}
		EloamGlobal.InitDevs();
		dev1 = EloamGlobal.CreateDevice(1, 0);
		if(dev1)
		{
			 video1 = dev1.CreateVideo(0, 0);
			if (video1)
			{
				EloamView1.SelectVideo(video1);
				EloamView1.SetText("打开视频中，请等待...", 0);
				
			} 
		}else{
			$("#hasEloam").addClass("hidden");
			$("#noEloam").removeClass("hidden");
			return false;
		} 
		//GetEloamType() == 1表示是主头
// 		document.getElementById("lab1").innerHTML = dev1.GetFriendlyName() + "@" + dev1.GetEloamType().toString();
		var select = document.getElementById('res1'); 						
		var nResolution = dev1.GetResolutionCount();
		document.getElementById('res1').options.length = 0; 
		for(var i = 0; i < nResolution; i++)
		{
			var width = dev1.GetResolutionWidth(i);
			var heigth = dev1.GetResolutionHeight(i);
			select.add(new Option(width.toString() + "*" + heigth.toString())); 
		}
		$("#res1 option:eq(4)").attr("selected","selected");
		$("#res1").attr("disabled","disabled");
		return true;
		 
	}
	Load();
	function Unload()
	{
		if (video1)
		{
			EloamView1.SetText("", 0);
			video1.Destroy();
			video1 = null;
		}
		
		if(dev1)
		{
			dev1.Destroy();
			dev1 = null;
		}
		
		EloamGlobal.DeinitDevs();
		EloamGlobal = null;
	}

	
	function Scan()
	{
		if (video1)
		{
			var date = new Date();
			var yy = date.getFullYear().toString();
			var mm = (date.getMonth() + 1).toString();
			var dd = date.getDate().toString();
			var hh = date.getHours().toString();
			var nn = date.getMinutes().toString();
			var ss = date.getSeconds().toString();
			var mi = date.getMilliseconds().toString();
			EloamGlobal.CreateDir("D:\\uploadphoto");
			var namePart=yy + mm + dd + hh + nn + ss + mi;
			var Name = "D:\\uploadphoto\\" + namePart+ ".jpg";					
			var image = video1.CreateImage(0, EloamView1.GetView());
			if (image)
			{
				EloamView1.PlayCaptureEffect();
				image.Save(Name, 0);
				EloamThumbnail.Add(Name);
				imageList.push(image);
				nameList.push(namePart);
				
			}
		}
	}
	
	function ShowVideo()
	{
		CloseVideo();
		var select1 = document.getElementById('res1'); 
		var resolution1 = select1.selectedIndex;
		if(dev1)
		{
			video1 = dev1.CreateVideo(resolution1, 0);
			if (video1)
			{
				EloamView1.SelectVideo(video1);
				EloamView1.SetText("打开视频中，请等待...", 0);
			}
		}
	}
	
	function CloseVideo()
	{
		if (video1)
		{
			EloamView1.SetText("", 0);
			video1.Destroy();
			video1 = null;
		}	
	}
	function resetName(){
		var flag=false;
		$("#nameList").html("");
		var count=EloamThumbnail.GetCount();
		 for(var i=0;i<count;i++){
		    if(EloamThumbnail.GetCheck(i)){
		    	flag=true;
		    	$("#nameList").append('<div class="m-b-5 pull-left" style="overflow:hidden; height:auto; width: 33.3333%;"><label class="pull-left m-t-4 m-b-0">第' + (i + 1) + '张：</label><input type="text" class="form-control m-r-25 input-sm pull-left" style="width: 220px;" id="nameList' + i + '" value='+nameList[i]+'></div>');
		    }	
		 }
		 if(!flag){
// 			 $("body").cgetPopup({
//              	title: "提示信息",
//              	content: "请选择要上传的文件！",
//              	accept: innerClose,
//              	chide: true,
//              	newpop:"second",
//              	icon: "fa-check-circle"
//          	}); 
			 showNotice("请选择要上传的文件！");
		 }
	}
	function Upload()
	{    
		if (dev1)
		{
			if (video1)
			{
				var count=EloamThumbnail.GetCount(); 				 
				var projNo=$("#projNo").val();
				var menuId=getStepId();
				var projId=$("#projId").val();
				var flag=false;
				 for(var i=0;i<count;i++){
					    if(EloamThumbnail.GetCheck(i)){	
					    	flag=true;
						 var image = imageList[i];
						 if (image)
							{	
							    console.info(location.host);
							    var name=$("#nameList"+i).val();
								if(!name){
									name=nameList[i];
								}
							    var http = EloamGlobal.CreateHttp(location.protocol + "//" + location.host + "/work?name="+ encodeURI(encodeURI(name))+"&&projNo="+projNo+"&&menuId="+menuId+"&&projId="+projId);//java服务器demo地址
								if(http){
									
									if(http.UploadImage(image, 2, 0, nameList[i]+".jpg")){
										
									}else{
// 										  $("body").cgetPopup({
// 						                       	title: "提示信息",
// 						                       	content: "上传失败！",
// 						                       	accept: innerClose,
// 						                    	newpop:"second",
// 						                       	chide: true,
// 						                       	icon: "fa-check-circle"
// 						                   });
											showNotice("上传失败！");
										  if(http){
												http.Destroy();
											}
											
											http = null;
											imageList=null;
											return false;
										  
										}
									
								}
									
								var path = EloamGlobal.GetTempName("jpg");
								image.Save(path, 0);									
								image.Destroy();
								image = null;
							}	
					}
					if(http){
						http.Destroy();
					}
					
					http = null;
					
				 }
				 if(flag){
// 				 $("body").cgetPopup({
//                     	title: "提示信息",
//                     	content: "上传成功！",
//                     	accept: function(){
//                     		for(var j=0;j<count;j++){
//            					 var name1=EloamThumbnail.GetFileName(j);
//            						EloamGlobal.DelFile(name1);
//            				     }
//            				     imageList=[];
//                     	},
//                     	chide: true,
//                     	newpop:"second",
//                     	icon: "fa-check-circle"
//                 });
					showNotice("上传成功！", "", function(){
                		for(var j=0;j<count;j++){
         					var name1=EloamThumbnail.GetFileName(j);
         					EloamGlobal.DelFile(name1);
         				}
         				imageList=[];
					});
				 
				 }else{
// 					 $("body").cgetPopup({
// 	                    	title: "提示信息",
// 	                    	content: "请选择要上传的文件！",
// 	                    	accept: innerClose,
// 	                    	chide: true,
// 	                    	newpop:"second",
// 	                    	icon: "fa-check-circle"
// 	                }); 
					 showNotice("请选择要上传的文件！");
				 }
				
			}		
		}
		
		
	}
	
</script>


	

<!-- ================== END PAGE LEVEL JS ================== -->