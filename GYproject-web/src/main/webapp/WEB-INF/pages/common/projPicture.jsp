<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="assets/plugins/cropper/css/cropper.css">

       <div class="width-full upload-notice" style="display:none; position: relative; height: auto; overflow: hidden">
		<p class="text-danger p-15 m-t-10 m-b-10" style="border: #e2e2e2 1px solid; border-radius:5px;"></p>
		<a href="javascript:;" class="btn btn-white btn-xs btn-circle notice-close" style="position: absolute; top: 20px; right: 20px;"><i class="fa fa-times"></i></a>
	  </div>
      <div class="width-full docs-buttons " style="height: auto; overflow: hidden">
		<p class="text-danger pull-left p-t-10 al-has hidden"><i class="fa fa-warning"></i> 该项目已设置工程图片，再次上传将被覆盖!</p>
        <div class="btn-group">
        
          <label class="btn btn-primary btn-upload" for="inputImage" title="Upload image file">
            <input type="file" class="sr-only" id="inputImage" name="file" accept="image/*">
            <span class="docs-tooltip" data-toggle="tooltip" title="Import image with Blob URLs">
              <span class="fa fa-picture-o"></span>
            </span>
          </label>
         <div class="btn-group">
         <button type="button" id="uploadFile" class="btn btn-primary"  title="upload File">
            <span class="docs-tooltip" data-toggle="tooltip" title="">
              <span class="fa fa-upload"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-method="rotate" data-option="-45" title="Rotate Left">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;rotate&quot;, -90)">
              <span class="fa fa-rotate-left"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-method="rotate" data-option="45" title="Rotate Right">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;rotate&quot;, 90)">
              <span class="fa fa-rotate-right"></span>
            </span>
          </button>
        </div>
        </div>

       

        <!-- Show the cropped image in modal -->
        <div class="modal fade docs-cropped" id="getCroppedCanvasModal" aria-hidden="true" aria-labelledby="getCroppedCanvasTitle" role="dialog" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="getCroppedCanvasTitle">Cropped</h4>
              </div>
              <div class="modal-body"></div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a class="btn btn-primary" id="download" href="javascript:void(0);" download="cropped.jpg">Download</a>
              </div>
            </div>
          </div>
        </div><!-- /.modal -->
      </div><!-- /.docs-buttons -->
      <div class="width-full" style="height: auto; overflow: hidden">
        <!-- <h3 class="page-header">Demo:</h3> -->
        <div class="img-container">
          <img id="image" src="">
        </div>
      </div>



  <!-- Scripts -->
 <script src="assets/plugins/cropper/js/cropper.js"></script>
 <script src="assets/plugins/cropper/js/main.js"></script>
 <script>
 var checkAcc=function(){
	    var data={};
	    data.projId=$("#projId").val();
	    data.step="-1";
	    data.sourceType=0;
	    data.encryption="0";
		$.ajax({
	        type: 'POST',
	        url: "accessoryCollect/queryAccListPhoto",
			data:data,
			dataType: 'json',
	        success: function (data) {       	
	        	if(data.srcs&&data.srcs.length){       		
	        		$(".al-has").removeClass("hidden");
	        	}
	           }
	        });
	        
	};
 checkAcc();
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


 $("#uploadFile").on("click",function(){
	    var imgurl=pictureDone1();
	    if(!imgurl){
	    	showNotice("请选择照片！");
	    	return false;
	    }
	    var data={};
		data.projId=$("#projId").val();	    
		data.projNo=$("#projNo").val();
		data.sourceType="0";
		data.step="-1";
		data.stepId="工程图片";
		data.pictureStr=imgurl;
		data.alName="工程图片";
		data.stepDesTemp=$(".has-sub.active > a span").text() || $('[data-mid="' + getStepId() + '"] > span').text();
        //data.stepId=data.stepDesTemp;
        console.info("fulwi----");
        console.info(data);
		$.ajax({
			 url:'accessoryCollect/uploadPicture',
			type:'post',
			contentType: "application/json;charset=UTF-8",
			data:JSON.stringify(data),
			success:function(data){
				if(data=="true"){			
					showNotice("上传成功！");
				}else{
					showNotice("上传失败！");
				}				   
			}
		});
	});
 function pictureDone1(){
	 var $image = $('.img-container > img');
	 var dataURL = $image.cropper("getCroppedCanvas", {
		  width: 500,
		  height: 400
		});
	 var imgurl="";
	 if(dataURL){
	  imgurl=dataURL.toDataURL("image/png",1.0);//这里转成base64 image，img的src可直接   	 
	 }
	 return imgurl;
}
 </script>
 
