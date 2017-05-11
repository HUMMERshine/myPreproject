var goodscontainArray = [];
var index = 0;
var infos;
var pre_operate_id_temp;
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
	$('.modal-close').click(function () {
		var approval_result = $('#wrap input[name="approval_result"]:checked').val();
		var approval_proposal = $('#approval_proposal').val();
		console.log(pre_operate_id_temp);
		JY.Ajax.doRequest(null,jypath +'/backstage/preproject/approval',{pre_operate_id:pre_operate_id_temp, approval_result:approval_result,approval_proposal:approval_proposal},function(data){
			
		});
		
		$('#approvalModal').modal('hide');
	});
});
function search(){
	$("#searchBtn").trigger("click");
}

function cleangoodscontainItemForm(){
	JY.Tags.cleanForm("goodscontainitemDiv");
	var d = new Date(),str = '';
	str += d.getFullYear();
	str  += d.getMonth() + 1;
	str  += d.getDate();
	str += d.getHours(); 
	console.log(str);
	$("#goodscontainitemDiv input[name$='code']").val(str + '11');
}

function getbaseList(init){
	if(init==1) $("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/operate/findNoApprovalByPage',null,function(data){
		 $("#baseTable tbody").empty();
    		 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 infos = results;
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.pre_operate_id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.preproject.pre_id)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.operater_name)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.operate_type)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(getTime(l.operate_time))+"</td>";
            		 html+="<td class='ttd'>"+JY.Object.notEmpty(l.operate_describe)+"</td>";
            		 //html+="<td class='center'>"+JY.Object.notEmpty(l.goods_size)+"</td>";
            		 html+=JY.Tags.setFunction(l.pre_operate_id,permitBtn);
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

function getTime(t){
	var newDate = new Date();
	newDate.setTime(t);
	// Wed Jun 18 2014 
	console.log(newDate.toDateString());
	// Wed, 18 Jun 2014 02:33:24 GMT 
	console.log(newDate.toGMTString());
	// 2014-06-18T02:33:24.000Z
	console.log(newDate.toISOString());
	// 2014-06-18T02:33:24.000Z 
	return newDate.toLocaleString();
}

function check(pre_operate_id){
	for(var i in infos){
		if(pre_operate_id == infos[i].pre_operate_id){
			$('#myModal').modal({backdrop: 'static', keyboard: false});
			$('#mytext').val(infos[i].operate_describe);
			return;
		}
	}
}

function approval(pre_operate_id){
	pre_operate_id_temp = pre_operate_id;
	$('#approvalModal').modal();
}

function setForm(data){
	var l=data.obj;
	var lat_long = JY.Object.notEmpty(l.place_lat) + '-' + JY.Object.notEmpty(l.place_long);
	$("#auForm input[name$='dis_ID']").val(l.dis_ID);
	$("#auForm input[name$='dis_kind']").val(JY.Object.notEmpty(l.dis_kind));
	$("#auForm input[name$='place_name']").val(JY.Object.notEmpty(l.place_name));
	$("#auForm input[name$='area']").val(JY.Object.notEmpty(l.area));
	$("#auForm input[name$='people']").val(JY.Object.notEmpty(l.people));
	$("#auForm input[name$='strength']").val(JY.Object.notEmpty(l.strength));
	$("#auForm input[name$='occurtime']").val(JY.Object.notEmpty(l.occurtime));
	$("#auForm input[name$='lat_long']").val(JY.Object.notEmpty(lat_long));
	$("#auForm textarea[name$='dis_info']").val(JY.Object.notEmpty(l.dis_info));
}
function cleanForm(){
	JY.Tags.isValid("auForm","1");
	JY.Tags.cleanForm("auForm");
}

function ignore(){
	JY.Model.info("忽略"); 
}

function handle(dis_ID){
	JY.Model.handle("preDiv","预案选择","返回","下一步",function(){
		$(this).dialog("close");
	},function(){
		var chks =[];    
		$('#preTable input[name="ids"]:checked').each(function(){chks.push($(this).val());});    
		if(chks.length==0) {
			JY.Model.info("请选择一个预案!"); 
		}
		else if(chks.length > 1) {
			JY.Model.info("只能选择一个预案!"); 
		}
		else {
			$(this).dialog("close");
			disnotice(chks.toString(), dis_ID);
		}
	});
	getpretable();
}

function getpretable(){
	JY.Model.loading();
	JY.Ajax.doRequest("preForm",jypath +'/backstage/preproject/findByPage',null,function(data){
		$("#preTable tbody").empty();
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
	           	html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.pre_id+"' class='ace' /> <span class='lbl'></span></label></td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(i + leng + 1)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.pre_id)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.pre_name)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.pre_kind)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(parseFloat(l.strength).toFixed(2))+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(parseFloat(l.area).toFixed(2))+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(parseInt(l.people, 10))+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.climate)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.geography)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.match)+"</td>";
	       		html+="</tr>";		 
	        } 
	       	$("#preTable tbody").append(html);
	        JY.Page.setPage("preForm","prepageing",pageSize,pageNum,totalRecord,"getpretable");
	    }else{
	       html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
	       $("#preTable tbody").append(html);
	       $("#prepageing ul").empty();//清空分页
	     }		
	    JY.Model.loadingClose();
	});
}

function disnotice(preID, dis_ID){
	JY.Model.handle("goodsneedDiv","灾情通知","返回","生成需求",function(){
		$(this).dialog("close");
		handle();
	},function(){
		$(this).dialog("close");
		handlegoodscontain(preID, dis_ID);
	});
	JY.Ajax.doRequest(null,jypath +'/backstage/disaster/find',{dis_ID:dis_ID},function(data1){
		var data = data1.obj;
		$("#disForm input[name$='place']").val(JY.Object.notEmpty(data.place_name));
		$("#disForm input[name$='deadpeople']").val("目前未知");
		$("#disForm input[name$='distype']").val(JY.Object.notEmpty(data.dis_kind));
		$("#disForm input[name$='injurypeople']").val("目前未知");
		$("#disForm input[name$='level']").val(JY.Object.notEmpty(data.strength));
		$("#disForm input[name$='dispeople']").val(JY.Object.notEmpty(data.people));
		$("#disForm input[name$='occurtime']").val(JY.Object.notEmpty(data.occurtime));
		$("#disForm input[name$='placepeaple']").val("目前未知");
		$("#disForm input[name$='timeinfo']").val("星期三");
		$("#disForm input[name$='dayinfo']").val("下午");
		$("#disForm input[name$='climate']").val("多云转小雨，风力三级");
		$("#disForm input[name$='endplace']").val("成都双流机场");
	});
}

function handlegoodscontain(preID, dis_ID){
	JY.Model.handle("goodscontainDiv","物资信息详情","上一步","任务确认",function(){
		$(this).dialog("close");
		disnotice(preID, dis_ID);
	},function(){
		JY.Model.info("任务确认!"); 
	});
	getgoodscontaintable();
}

function getgoodscontaintable(){
	JY.Model.loading();
	JY.Ajax.doRequest("goodscontainForm",jypath +'/backstage/goodscontain/findByPage',null,function(data){
		$("#goodscontainTable tbody").empty();
		console.log(data);
		 var obj=data.obj;
    	 var list=obj.list;
    	 var results=list.results;
    	 var permitBtn=obj.permitBtn;
     	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
		 var html="";
		 if(results!=null&&results.length>0){
	       	var leng=(pageNum-1)*pageSize;//计算序号
	       	var itemNum = 1;
	       	var count = 0;
	       	if(goodscontainArray.length > 0){
	       		for(var i in goodscontainArray){
	       			html+="<tr id='goodscontaintemtr"+ (itemNum) +"' >";
		           	html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+goodscontainArray[i].code+"' class='ace' /> <span class='lbl'></span></label></td>";
		           	html+="<td class='center'>"+JY.Object.notEmpty(count + leng + 1)+"</td>";
		           	html+="<td class='center'>"+JY.Object.notEmpty(goodscontainArray[i].code)+"</td>";
		       		html+="<td class='center'>"+JY.Object.notEmpty(goodscontainArray[i].goods_name)+"</td>";
		       		html+="<td class='center'>"+JY.Object.notEmpty(goodscontainArray[i].goods_kind)+"</td>";
		       		html+="<td class='center'>"+JY.Object.notEmpty(goodscontainArray[i].goods_num)+"</td>";
		       		html+="<td>";			
					html+="<div class='inline position-relative'>";
					html+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
					html+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";	
					html+="<li><a class='aBtnNoTD' onclick='editgoodscontainItem(&apos;"+itemNum+"&apos;)' title='修改' href='#'><i class='icon-edit color-blue bigger-120'></i></a></li>";
					html+="<li><a class='aBtnNoTD' onclick='delgoodscontainItem(&apos;"+itemNum+"&apos;)' title='删除' href='#'><i class='icon-remove-sign color-red bigger-120'></i></a></li>";	
					html+="</ul></div>";		
					html+="<input type='hidden' name='goodscontainitems' id='goodscontainitem"+itemNum+"' value='"+itemNum+"' goodscontainitemCode='"+goodscontainArray[i].code+"' goodscontainitemName='"+goodscontainArray[i].goods_name+"' goodscontainitemKind='"+goodscontainArray[i].goods_kind+"' goodscontainitemNum='"+goodscontainArray[i].goods_num + "'  >";			

					html+="</td>";
		       		html+="</tr>";
		       		itemNum++;
		       		count++;
	       		}
	       	}
	       	for(var i = 0;i<results.length && count < pageSize;i++){
	           	var l=results[i];       
	           	html+="<tr id='goodscontaintemtr"+ (itemNum) +"' >";
	           	html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.code+"' class='ace' /> <span class='lbl'></span></label></td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(count + leng + 1)+"</td>";
	           	html+="<td class='center'>"+JY.Object.notEmpty(l.code)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.goods_name)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.goods_kind)+"</td>";
	       		html+="<td class='center'>"+JY.Object.notEmpty(l.goods_num)+"</td>";
	       		html+="<td>";			
				html+="<div class='inline position-relative'>";
				html+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
				html+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";	
				html+="<li><a class='aBtnNoTD' onclick='editgoodscontainItem(&apos;"+itemNum+"&apos;)' title='修改' href='#'><i class='icon-edit color-blue bigger-120'></i></a></li>";
				html+="<li><a class='aBtnNoTD' onclick='delgoodscontainItem(&apos;"+itemNum+"&apos;)' title='删除' href='#'><i class='icon-remove-sign color-red bigger-120'></i></a></li>";	
				html+="</ul></div>";		
				html+="<input type='hidden' name='goodscontainitems' id='goodscontainitem"+itemNum+"' value='"+itemNum+"' goodscontainitemCode='"+l.code+"' goodscontainitemName='"+l.goods_name+"' goodscontainitemKind='"+l.goods_kind+"' goodscontainitemNum='"+l.goods_num + "'  >";			
				html+="</td>";
	       		html+="</tr>";
	       		itemNum++;
	       		count++;
	        } 
	       	$("#goodscontainTable tbody").append(html);
	        JY.Page.setPage("goodscontainForm","goodscontainpageing",pageSize,pageNum,totalRecord,"getgoodscontaintable");
	    }else{
	       html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
	       $("#goodscontainTable tbody").append(html);
	       $("#goodscontainpageing ul").empty();//清空分页
	     }		
	    JY.Model.loadingClose();
	});
}

function delgoodscontainItem(id){
	JY.Model.confirm("确认要删除字段吗?",function(){
		$("#goodscontaintemtr"+id).remove();
		var code=$("#goodscontainitem"+id).attr("goodscontainitemCode");
		for(var i in goodscontainArray){
			if(code = goodscontainArray[i].code){
				goodscontainArray.splice(i, 1);
				index--;
				break;
			}
		}
	});	
}
function editgoodscontainItem(id){
	cleangoodscontainItemForm();
	var code=$("#goodscontainitem"+id).attr("goodscontainitemCode");
	var goods_name=$("#goodscontainitem"+id).attr("goodscontainitemName");
	var goods_kind=$("#goodscontainitem"+id).attr("goodscontainitemKind");
	var goods_num=$("#goodscontainitem"+id).attr("goodscontainitemNum");
	$("#goodscontainitemDiv input[name$='code']").val(code);
	$("#goodscontainitemDiv input[name$='goods_name']").val(goods_name);
	$("#goodscontainitemDiv input[name$='goods_kind']").val(goods_kind);
	$("#goodscontainitemDiv input[name$='goods_num']").val(goods_num);
	JY.Model.edit("goodscontainitemDiv","修改字典字段",function(){
		var new_code = $("#goodscontainitemDiv input[name$='code']").val();
		var new_goods_name = $("#goodscontainitemDiv input[name$='goods_name']").val();
		var new_goods_kind = $("#goodscontainitemDiv input[name$='goods_kind']").val();
		var new_goods_num = $("#goodscontainitemDiv input[name$='goods_num']").val();
		 if(JY.Validate.form("goodscontainitemDiv")){
			 console.log("new_code" + new_code);
			 console.log("new_code" + new_goods_name);
			 $("#goodscontainitem"+id).attr("goodscontainitemCode",new_code);
			 $("#goodscontainitem"+id).attr("goodscontainitemName",new_goods_name);
			 $("#goodscontainitem"+id).attr("goodscontainitemKind",new_goods_kind);
			 $("#goodscontainitem"+id).attr("goodscontainitemNum",new_goods_num);
			 $("#goodscontaintemtr"+id).find("td").eq(2).html(new_code);
			 $("#goodscontaintemtr"+id).find("td").eq(3).html(new_goods_name);
			 $("#goodscontaintemtr"+id).find("td").eq(4).html(new_goods_kind);
			 $("#goodscontaintemtr"+id).find("td").eq(5).html(new_goods_num);
			 $(this).dialog("close");
		 }
	});
}
