$(function() {

	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

	// 2.初始化Button的点击事件
	var oButtonInit = new ButtonInit();
	oButtonInit.Init();

});

var TableInit = function() {
	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {
		$('#goodsInfo').bootstrapTable({
			// url : jypath+"/static/js/basicfun/preproject/operate_all.json",
			// //请求后台的URL（*）
			url : jypath + '/backstage/preproject/findPreGoodsByPage3', // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			// toolbar: '#toolbar3', //工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : false, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : function queryParams(params) {
				var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					limit : params.limit, // 页面大小
					offset : params.offset, // 页码
					pre_id : "1000000001",
					maxrows : params.limit,
					pageindex : params.pageNumber,
				};
				return temp;
			},// 传递参数（*）
			contentType : "application/x-www-form-urlencoded",
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : false, // 是否显示所有的列
			showRefresh : false, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			uniqueId : "code", // 每一行的唯一标识，一般为主键列
			showToggle : false, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			responseHandler : function(data) {
				var list = data.obj.list.results;
				console.log("==========");
				console.log(list);
				console.log("==========");
				var total = 0;
				var rows = new Array();
				for ( var i in list) {
					total++;
					var temp = new Object();
					temp.code = list[i].goods.code;
					temp.goods_name = list[i].goods.goods_name;
					temp.goods_kind = list[i].goods.goods_kind;
					temp.amount = list[i].amount;
					temp.unit = list[i].goods.unit;
					temp.goods_size = list[i].goods.goods_size;
					temp.priority = list[i].priority;
					temp.save_cycle = list[i].save_cycle;
					rows.push(temp);
				}
				
				$('#type').val("地震");
				$('#level').val(list[0].preproject.preCondition.disaster_strength);
				$('#time').val(getTime(list[0].preproject.pre_time));
				$('#people').val(list[0].preproject.preCondition.disaster_people);
				$('#climate').val(list[0].preproject.preCondition.climate);
				$('#area').val(list[0].preproject.preCondition.disaster_area);
				$('#geography').val(list[0].preproject.preCondition.geography);
				

				var res = new Object();
				res.total = total;
				res.rows = rows;
				return rows;
			},
			columns : [ {
				checkbox : true
			}, {
				field : 'code',
				title : '物资编号',
				width : '15%',
			}, {
				field : 'goods_name',
				title : '物资名称',
				width : '15%',
			}, {
				field : 'goods_kind',
				title : '物资类型',
				width : '15%',
			}, {
				field : 'amount',
				title : '物资数量',
				width : '15%',
			}, {
				field : 'unit',
				title : '物资单位',
				width : '15%',
			}, {
				field : 'goods_size',
				title : '物资尺寸',
				width : '15%',
			}, {
				field : 'priority',
				title : '物资批次',
				width : '15%',
			}, {
				field : 'save_cycle',
				title : '救援周期',
				width : '15%',
			} ],// 单击row事件
			onDblClickRow : function(row, $element) {
				// $element是当前tr的jquery对象
				console.log("***********");
				console.log(row.pre_id);
				console.log(row);

				$('#preprojectGoods').modal({
					backdrop : 'static',
					keyboard : false
				});
				getGoods(row.pre_id);
			},// 单击row事件
		});
	};
	return oTableInit;
}
var ButtonInit = function() {
	var oInit = new Object();
	var postdata = {};

	oInit.Init = function() {
		// $('#btn_cancel')
		// .click(
		// function() {
		// window.location.href =
		// '/elpdemo/webpage/modules/basicfun/preproject/list.jsp';
		// });
		$('#btn_cancel').click(function() {
			console.log("ttttt");
			JY.Ajax.doRequest(null,jypath +'/backstage/emergency/cleanPreproject',{},function(data){
				console.log(data);
				
			});
			console.log("ttttt");
			window.location.href = jypath +'/backstage/emergency/index';
		});
		$('#btn_select').click(function() {
//			var selects = $('#operateinfo').bootstrapTable('getSelections');
//			if (selects.length != 1) {
//				swal("必须选择一个预案！");
//			} else {
//				// 传递预案。
//				swal("预案选择完成，开始进行救援任务！");
//			}
			swal("预案选择完成，开始进行救援任务！");
			 //window.location.href="http://192.168.11.30:8080/mypaper";
			 JY.Ajax.doRequest(null,jypath +'/backstage/preproject/getFinalData',{pre_id:"1000000001"},function(data){
				 console.log(data);
				 console.log(resolveData(data));
				 JY.Ajax.doRequest(null,'http://192.168.11.30:8080/mypaper/reqgood/receivereq',{gid:resolveData(data)},function(data){
					 console.log(data);
					 JY.Ajax.doRequest(null,jypath +'/backstage/emergency/cleanPreproject',{},function(data){
							console.log(data);
						});
					});
				});
			
			 setTimeout(function () { 
				 window.location.href="http://192.168.11.30:8080/mypaper";
			    }, 500);
		});
	};

	return oInit;
};

function getTime(t) {
	var newDate = new Date();
	newDate.setTime(t);
	return newDate.toLocaleString();
}

function match() {

}

function resolveData(data){
	list = data.obj.list;
	var res = "";
	for(var  i in list){
		res += list[i].goods.code + "_" + list[i].amount + "_" +list[i].priority + "_" + list[i].save_cycle+ "*";
	}
	if(res.length > 0) res = res.substring(0, res.length-1);
	return res;
}
