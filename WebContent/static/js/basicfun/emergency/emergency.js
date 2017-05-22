$(function() {

	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

	// 2.初始化Button的点击事件
	$('#btn_select').hide();
	var oButtonInit = new ButtonInit();
	oButtonInit.Init();

});

var TableInit = function() {
	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {

	};
	return oTableInit;
}
var ButtonInit = function() {
	var oInit = new Object();
	var postdata = {};
	
	oInit.Init = function() {
//		$('#btn_cancel')
//				.click(
//						function() {
//							window.location.href = '/elpdemo/webpage/modules/basicfun/preproject/list.jsp';
//						});
		$('#btn_create')
		.click(
				function() {
					var param = 'level=' + $('#disaster_level').val() + '&time=' + $('#disaster_time').val() + '&people=' + $('#disaster_people').val() + '&area=' + $('#disaster_area').val() + '&climate=' + encodeURI($('#disaster_climate').val()) + '&geography=' + encodeURI($('#disaster_geography').val()) + '&info=' + $('#disaster_info').val()
					window.location.href = jypath + '/backstage/emergency/create?' + param;
				});
		$('#btn_match').click(function() {
			match();
			$('#btn_select').show();
		});
		$('#btn_select').click(function() {
			var selects =
				 $('#operateinfo').bootstrapTable('getSelections');
				 if(selects.length != 1){
					 swal("必须选择一个预案！");
				 }else{
					 //传递预案。
					 swal("预案选择完成，开始进行救援任务！");
				 }
		});
	};

	return oInit;
};

function getTime(t) {
	var newDate = new Date();
	newDate.setTime(t);
	// Wed Jun 18 2014
	// console.log(newDate.toDateString());
	// Wed, 18 Jun 2014 02:33:24 GMT
	// console.log(newDate.toGMTString());
	// 2014-06-18T02:33:24.000Z
	// /console.log(newDate.toISOString());
	// 2014-06-18T02:33:24.000Z
	return newDate.toLocaleString();
}

function match() {
	$('#operateinfo').bootstrapTable({
		// url : jypath+"/static/js/basicfun/preproject/operate_all.json",
		// //请求后台的URL（*）
		url : jypath + '/backstage/emergency/preprojectMatch', // 请求后台的URL（*）
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
				disaster_type : $("#disaster_type").val(),
				disaster_level : $("#disaster_level").val(),
				disaster_time : $("#disaster_time").val(),
				disaster_people : $("#disaster_people").val(),
				disaster_area : $("#disaster_area").val(),
				maxrows : params.limit,
				pageindex : params.pageNumber,
				disaster_geography : $("#disaster_geography").val(),
				disaster_climate : $("#disaster_climate").val(),
				disaster_info : $("#disaster_info").val(),
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
			console.log("++++++++++");
			console.log(list);
			console.log("++++++++++");
			var total = 0;
			var rows = new Array();
			for ( var i in list) {
				total++;
				var temp = new Object();
				temp.pre_id = list[i].pre_id;
				temp.pre_name = list[i].pre_name;
				temp.pre_time = getTime(list[i].pre_time);
				temp.disaster_strength = list[i].preCondition.disaster_strength;
				temp.disaster_area = list[i].preCondition.disaster_area;
				temp.disaster_kind = list[i].preCondition.disaster_kind;
				temp.disaster_people = list[i].preCondition.disaster_people;
				temp.climate = list[i].preCondition.climate;
				temp.geography = list[i].preCondition.geography;
				temp.match = list[i].match;
				rows.push(temp);
			}

			var res = new Object();
			res.total = total;
			res.rows = rows;
			return rows;
		},
		// rowStyle: function (row, index) {
		// //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning',
		// 'danger'];
		// var strclass = "";
		// if (row.approval_result == "不通过") {
		// return { classes: 'danger' };
		// }
		//                
		// if (row.approval_state == "未审批") {
		// strclass = 'warning';//还有一个active
		// }
		// else {
		// strclass = 'success';
		// }
		// return { classes: strclass };
		// },
		columns : [ {
			checkbox : true
		}, {
			field : 'pre_id',
			title : '预案编号',
			width : '15%',
		}, {
			field : 'pre_name',
			title : '预案名称',
			width : '15%',
		}, {
			field : 'disaster_kind',
			title : '灾害类型',
			width : '15%',
		}, {
			field : 'disaster_strength',
			title : '灾害强度',
			width : '15%',
		}, {
			field : 'disaster_area',
			title : '受灾面积',
			width : '15%',
		}, {
			field : 'pre_time',
			title : '发生时间',
			width : '15%',
		}, {
			field : 'disaster_people',
			title : '受灾人口',
			width : '15%',
		}, {
			field : 'geography',
			title : '地理信息',
			width : '15%',
		}, {
			field : 'climate',
			title : '天气信息',
			width : '15%',
		}, {
			field : 'match',
			title : '匹配度',
			width : '15%',
		} ],
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
			//$('#describe').html(row.operate_describe);
		},// 单击row事件
	// ,{
	// field: 'match',
	// title: '匹配度'
	// }
	});
}

function getGoods(curId){
	$('#goodsInfo').bootstrapTable('destroy').bootstrapTable({
		// url : jypath+"/static/js/basicfun/preproject/operate_all.json",
		// //请求后台的URL（*）
		url : jypath + '/backstage/preproject/findPreGoods', // 请求后台的URL（*）
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
				maxrows : params.limit,
				pageindex : params.pageNumber,
				pre_id : curId,
			};
			return temp;
		},// 传递参数（*）
		contentType : "application/x-www-form-urlencoded",
		sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 5, // 每页的记录行数（*）
		pageList : [ 5, 10, 25, 100 ], // 可供选择的每页的行数（*）
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

			var res = new Object();
			res.total = total;
			res.rows = rows;
			return rows;
		},
		// rowStyle: function (row, index) {
		// //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning',
		// 'danger'];
		// var strclass = "";
		// if (row.approval_result == "不通过") {
		// return { classes: 'danger' };
		// }
		//                
		// if (row.approval_state == "未审批") {
		// strclass = 'warning';//还有一个active
		// }
		// else {
		// strclass = 'success';
		// }
		// return { classes: strclass };
		// },
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
		}],// 单击row事件
	// ,{
	// field: 'match',
	// title: '匹配度'
	// }
	});
}
