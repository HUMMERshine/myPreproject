$(function () {
	JY.Dict.setSelect("selectisValid","isValid",2,'全部');
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if(keycode==13){
			 search();
		 }
	});
	//新加
	$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanForm();
		JY.Model.edit("auDiv","新增",function(){
			 if(JY.Validate.form("auForm")){
				 var that =$(this);
				 JY.Ajax.doRequest("auForm",jypath +'/backstage/goods/add',null,function(data){
		        	that.dialog("close");      
		        	JY.Model.info(data.resMsg,function(){search();});  	
				 });
			 }	
		});
	});
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){chks.push($(this).val());});    
		if(chks.length==0) {
			 JY.Model.info("您没有选择任何内容!"); 
		}else{
			JY.Model.confirm("确认要删除选中的数据吗?",function(){	
				JY.Ajax.doRequest(null,jypath +'/backstage/goods/delBatch',{chks:chks.toString()},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});
		}	
	});
});
function search(){
	$("#searchBtn").trigger("click");
}

function getbaseList(init){
	if(init==1) $("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/goods/findByPage',null,function(data){
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
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.goods_ID+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.code)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.goods_name)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.goods_kind)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.goods_weight)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.goods_volume)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.goods_size)+"</td>";
            		 html+=JY.Tags.setFunction(l.goods_ID,permitBtn);
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
function check(goods_ID){
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/backstage/goods/find',{goods_ID:goods_ID},function(data){
    	setForm(data);
    	JY.Model.check("auDiv");     	
	});
}
function del(goods_ID){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,jypath +'/backstage/goods/del',{goods_ID:goods_ID},function(data){
	      	JY.Model.info(data.resMsg,function(){search();});
		});
	});	
}
function edit(goods_ID){
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/backstage/goods/find',{goods_ID:goods_ID},function(data){
    		setForm(data);   
    		JY.Model.edit("auDiv","修改",function(){
    			 if(JY.Validate.form("auForm")){
					 var that =$(this);
					 JY.Ajax.doRequest("auForm",jypath +'/backstage/goods/update',null,function(data){
						 if(data.res==1){
			        		 that.dialog("close");
			        		 JY.Model.info(data.resMsg,function(){search();});
			        	 }else{
			        		 JY.Model.error(data.resMsg);
			        	 } 		
					 });
				 }
    		});   
	});
}
function cleanForm(){
	JY.Tags.isValid("auForm","1");
	JY.Tags.cleanForm("auForm");
}
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='goods_ID']").val(l.goods_ID);
	$("#auForm input[name$='code']").val(JY.Object.notEmpty(l.code));
	$("#auForm input[name$='unit']").val(JY.Object.notEmpty(l.unit));
	$("#auForm input[name$='goods_name']").val(JY.Object.notEmpty(l.goods_name));
	$("#auForm input[name$='goods_kind']").val(JY.Object.notEmpty(l.goods_kind));
	$("#auForm input[name$='goods_weight']").val(JY.Object.notEmpty(l.goods_weight));
	$("#auForm input[name$='goods_weight_unit']").val(JY.Object.notEmpty(l.goods_weight_unit));
	$("#auForm input[name$='goods_volume']").val(JY.Object.notEmpty(l.goods_volume));
	$("#auForm input[name$='goods_volume_unit']").val(JY.Object.notEmpty(l.goods_volume_unit));
	$("#auForm input[name$='goods_size']").val(JY.Object.notEmpty(l.goods_size));
}
