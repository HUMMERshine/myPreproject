var local_pre_id;
$(function () {
	JY.Dict.setSelect("selectisValid","isValid",2,'全部');
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 }
	});
	//新加
	$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();		
		cleanForm();
		JY.Model.edit2("auDiv","新增预案",function(){
			 
			 if(JY.Validate.form("auForm")){
				 var that =$(this);
				 var pre_id=$("#auForm input[name$='pre_id']").val();
				 var pre_name=$("#auForm input[name$='pre_name']").val();
				 var disaster_kind=$("#auForm input[name$='pre_kind']").val();
				 var disaster_strength=$("#auForm input[name$='strength']").val();
				 var disaster_area=$("#auForm input[name$='area']").val();
				 var disaster_people=$("#auForm input[name$='people']").val();
				 var climate=$("#auForm input[name$='climate']").val();
				 var geography=$("#auForm input[name$='geography']").val();
				 var match=$("#auForm input[name$='match']").val();
				 var params ={pre_id:pre_id,pre_name:pre_name,match:match,preCondition:{disaster_kind:disaster_kind,disaster_strength:disaster_strength,disaster_area:disaster_area,disaster_people:disaster_people,climate:climate,geography:geography}};								 	 
				 $.ajax({type:'POST',url:jypath+'/backstage/preproject/add',data:JSON.stringify(params),dataType:'json',contentType:"application/json",success:function(data,textStatus){  			        	 
			        	 if(data.res==1){
			        		 that.dialog("close");      
			        		JY.Model.info(data.resMsg,function(){search();});
			        	 }else{
			        		 JY.Model.error(data.resMsg);
			        	 }     	
			         }
			     });  
			 }		
		});
		local_pre_id = 'none';
		getpregoodstable();
	});
	
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){chks.push($(this).val());});     
		if(chks.length==0) JY.Model.info("您没有选择任何内容!"); 
		JY.Model.confirm("确认要删除选中的数据吗?",function(){	
			JY.Ajax.doRequest(null,jypath +'/backstage/preproject/delBatch',{chks:chks.toString()},function(data){
				JY.Model.info(data.resMsg,function(){search();});
			});
		});		
	});

	$('.modal-close').click(function () {
//		var approval_result = $('#wrap input[name="approval_result"]:checked').val();
//		var approval_proposal = $('#approval_proposal').val();
//		console.log(pre_operate_id_temp);
//		JY.Ajax.doRequest(null,jypath +'/backstage/',{pre_operate_id:pre_operate_id_temp, approval_result:approval_result,approval_proposal:approval_proposal},function(data){
//			
//		});
		
		$('#addpregoodsModal').modal('hide');
	});
	
	$('#pregoodsformAdd').click(function () {
		if($('#pre_id_add').text().length <= 0){
			swal("请先填写预案ID");
			return;
		}
		$('#addpregoodsModal').modal();
	});
});

function cleanForm(){
	JY.Tags.cleanForm("auForm");
	JY.Tags.isValid("auForm","1");
}

function search(){
	$("#searchBtn").trigger("click");
}

function getbaseList(init){
	if(init==1){
		$("#baseForm .pageNum").val(1);	
	}
	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/preproject/findByPage',null,function(data){
		$("#baseTable tbody").empty();
		 var obj=data.obj;
    	 var list=obj.list;
    	 var results=list.results;
    	 var permitBtn=obj.permitBtn;
     	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
		 var html="";
		 if(results!=null&&results.length>0){
	       	var leng=(pageNum-1)*pageSize;//计算序号
	       	for(var i = 0;i<results.length;i++){
	       		console.log(l);
	           	var l=results[i];       
	           	html+="<tr>";
	           	html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.pre_id+"' class='ace' /> <span class='lbl'></span></label></td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(i + leng + 1)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.pre_id)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.pre_name)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.preCondition.disaster_kind)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(parseFloat(l.preCondition.disaster_strength).toFixed(2))+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(parseFloat(l.preCondition.disaster_area).toFixed(2))+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(parseInt(l.preCondition.disaster_people, 10))+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.preCondition.climate)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.preCondition.geography)+"</td>";
	       		//html+="<td class='center'>"+JY.Object.notEmpty(l.match)+"</td>";
	       		html+=JY.Tags.setFunction(l.pre_id,permitBtn);
	       		html+="</tr>";		 
	        } 
	       	$("#baseTable tbody").append(html);
	        JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
	    }else{
	       html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
	       $("#baseTable tbody").append(html);
	       $("#pageing ul").empty();//清空分页
	     }		
	    JY.Model.loadingClose();
	});
}
function del(id){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,jypath +'/backstage/preproject/del',{pre_id:id},function(data){
		 	 JY.Model.info(data.resMsg,function(){search();});
		});
	});
}
function edit(id){
	local_pre_id = id;
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/backstage/preproject/find',{pre_id:id},function(data){
		setForm(data);
		JY.Model.edit2("auDiv","修改预案",function(){
			if(JY.Validate.form("auForm")){
				 var that =$(this);
				 var pre_id=$("#auForm input[name$='pre_id']").val();
				 var pre_name=$("#auForm input[name$='pre_name']").val();
				 var disaster_kind=$("#auForm input[name$='pre_kind']").val();
				 var disaster_strength=$("#auForm input[name$='strength']").val();
				 var disaster_area=$("#auForm input[name$='area']").val();
				 var disaster_people=$("#auForm input[name$='people']").val();
				 var climate=$("#auForm input[name$='climate']").val();
				 var geography=$("#auForm input[name$='geography']").val();
				 var match=$("#auForm input[name$='match']").val();
				 var params ={pre_id:pre_id,pre_name:pre_name,match:match,preCondition:{disaster_kind:disaster_kind,disaster_strength:disaster_strength,disaster_area:disaster_area,disaster_people:disaster_people,climate:climate,geography:geography}};								 	 
				 $.ajax({type:'POST',url:jypath+'/backstage/preproject/update',data:JSON.stringify(params),dataType:'json',contentType:"application/json",success:function(data,textStatus){  			        	 
					   if(data.res==1){
					      that.dialog("close");
					      JY.Model.info(data.resMsg,function(){search();});
					   }else{
					      JY.Model.error(data.resMsg);
					   } 				        	 
					  }});
			}
	});	
	});
	$("#pregoodsformAdd").show();
	getpregoodstable();
}
function check(id){
	local_pre_id = id;
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/backstage/preproject/find',{pre_id:id},function(data){
		setForm(data);
		JY.Model.check2("auDiv","查看预案"); 	
	});
	$("#pregoodsformAdd").hide();
	getpregoodstable();
}

function setForm(data){
	var l=data.obj;
	console.log(l);
	var pre_id=$("#auForm input[name$='pre_id']").val(l.pre_id);
	var pre_name=$("#auForm input[name$='pre_name']").val(JY.Object.notEmpty(l.pre_name));
	var pre_kind=$("#auForm input[name$='pre_kind']").val(JY.Object.notEmpty(l.preCondition.disaster_kind));
	var strength=$("#auForm input[name$='strength']").val(JY.Object.notEmpty(l.preCondition.disaster_strength));
	var area=$("#auForm input[name$='area']").val(JY.Object.notEmpty(l.preCondition.disaster_area));
	var people=$("#auForm input[name$='people']").val(JY.Object.notEmpty(l.preCondition.disaster_people));
	var climate=$("#auForm input[name$='climate']").val(JY.Object.notEmpty(l.preCondition.climate));
	var geography=$("#auForm input[name$='geography']").val(JY.Object.notEmpty(l.preCondition.geography));
	var match=$("#auForm input[name$='match']").val(JY.Object.notEmpty(l.match));
	
}

function operate(pre_id){
	JY.Model.handle("preoperateDiv","预案操作信息","返回","下一步",function(){
		$(this).dialog("close");
	},function(){
//		var chks =[];    
//		$('#preTable input[name="ids"]:checked').each(function(){chks.push($(this).val());});    
//		if(chks.length==0) {
//			JY.Model.info("请选择一个预案!"); 
//		}
//		else if(chks.length > 1) {
//			JY.Model.info("只能选择一个预案!"); 
//		}
//		else {
//			$(this).dialog("close");
//			disnotice(chks.toString(), dis_ID);
//		}
	});
	//disnotice(1,1);
	getoperate(pre_id);
}

function getoperate(pre_id){
	JY.Model.loading();
	JY.Ajax.doRequest(null,jypath +'/backstage/preproject/findOperateByPage',{pre_id:pre_id},function(data){
		 $("#preoperateTable tbody").empty();
    		 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.pre_operate_id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(i + leng + 1)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.operate_type)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.operater_name)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.operate_time)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.goods_size)+"</td>";
            		 html+=JY.Tags.setFunction(l.goods_ID,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#preoperateTable tbody").append(html);
        		 JY.Page.setPage("preoperateForm","preoperatepageing",pageSize,pageNum,totalRecord,"getoperate");
        	 }else{
        		html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
        		$("#preoperateTable tbody").append(html);
        		$("#preoperatepageing ul").empty();//清空分页
        	 }	
        	 JY.Model.loadingClose();
	});
}

function exportOne(pre_id){
	JY.Ajax.doRequest(null,jypath +'/backstage/preproject/exportOne',{pre_id:pre_id},function(data){
	});
	JY.Model.info("导入成功");
}

function getpregoodstable(){
	JY.Model.loading();
	console.log("(*******" + local_pre_id);
	$('#pregoodsform_preid').val(local_pre_id);
	JY.Ajax.doRequest("pregoodsForm",jypath +'/backstage/preproject/findPreGoodsByPage',null,function(data){
		$("#pregoodsTable tbody").empty();
		console.log(data);
		 var obj=data.obj;
    	 var list=obj.list;
    	 var results=list.results;
    	 var permitBtn=obj.permitBtn;
     	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
		 var html="";
		 if(results!=null&&results.length>0){
	       	var leng=(pageNum-1)*pageSize;//计算序号
	       	for(var i = 0;i<results.length;i++){
	           	var l=results[i];      
	           	html+="<tr>";
	           	//html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.pre_goods_id+"' class='ace' /> <span class='lbl'></span></label></td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(i + leng + 1)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.goods.code)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.goods.goods_name)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.goods.goods_kind)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(parseFloat(l.amount).toFixed(2))+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.goods.unit)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.goods.goods_size)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.priority)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.save_cycle)+"</td>";
	       		html+="<td>";
	       		if($("#pregoodsformAdd").is(':visible')){
					html+="<div class='inline position-relative'>";
					html+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
					html+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";	
					html+="<li><a class='aBtnNoTD' onclick='editpregoods(&apos;"+l.pre_goods_id+"&apos;)' title='修改' href='#'><i class='icon-edit color-blue bigger-120'></i></a></li>";
					html+="<li><a class='aBtnNoTD' onclick='delpregoods(&apos;"+l.pre_goods_id+"&apos;)' title='删除' href='#'><i class='icon-remove-sign color-red bigger-120'></i></a></li>";	
					html+="</ul></div>";
	       		}
				html+="</td>";
	       		html+="</tr>";
	        } 
	       	$("#pregoodsTable tbody").append(html);
	        JY.Page.setPage("pregoodsForm","pregoodspageing",pageSize,pageNum,totalRecord,"getpregoodstable");
	    }else{
	       html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
	       $("#pregoodsTable tbody").append(html);
	       $("#pregoodspageing ul").empty();//清空分页
	     }		
	    JY.Model.loadingClose();
	});
}

function editpregoods(pre_goods_id){
	$('#addpregoodstitle').text("修改物资");
	$('#addpregoodsModal').modal();
}

function delpregoods(pre_goods_id){
	swal("Here's a message!");
}
