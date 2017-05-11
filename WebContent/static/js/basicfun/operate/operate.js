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
		$('#operateinfo').bootstrapTable({
			// url : jypath+"/static/js/basicfun/preproject/operate_all.json",
			// //请求后台的URL（*）
			url : jypath + '/backstage/operate/findNoApprovalByPage', // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			// toolbar: '#toolbar3', //工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : false, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : null,// 传递参数（*）
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : false, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			uniqueId : "code", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			responseHandler : function(data) {
				var list = data.obj.list.results;
				var total = 7;
				var rows = new Array();
				for ( var i in list) {
					var temp = new Object();
					temp.operate_type = list[i].operate_type;
					temp.pre_id = list[i].preproject.pre_id;
					temp.operater_name = list[i].operater_name;
					temp.operate_time = getTime(list[i].operate_time);
					temp.operate_describe = list[i].operate_describe;
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
				field : 'operate_type',
				title : '操作类型'
			}, {
				field : 'pre_id',
				title : '预案编号'
			}, {
				field : 'operater_name',
				title : '操作者'
			}, {
				field : 'operate_time',
				title : '操作时间',
					width : '20%',
			}, {
				field : 'operate_describe',
				title : '详细信息',
				width : '50%',
				cellStyle : function cellStyle(value, row, index) {
					return {
						css : {
							"overflow" : "hidden",
							"white-space" : "nowrap",
							"text-overflow" : "ellipsis"
						}
					}
				}
			} ],
			onDblClickRow : function(row, $element) {
				// $element是当前tr的jquery对象
				console.log(row);

				$('#describeModal').modal({
					backdrop : 'static',
					keyboard : false
				});
				$('#describe').html(row.operate_describe);
			},// 单击row事件
			// ,{
			// field: 'match',
			// title: '匹配度'
			// }
		});
	};
	return oTableInit;
}
var ButtonInit = function() {
	var oInit = new Object();
	var postdata = {};

	oInit.Init = function() {
		$('#btn_cancel')
				.click(
						function() {
							window.location.href = '/elpdemo/webpage/modules/basicfun/preproject/list.jsp';
						});
		// $('#btn_submit').click(function () {
		// window.location.href='/elpdemo/webpage/modules/basicfun/preproject/list.jsp';
		// });
		// //初始化页面上面的按钮事件
		// $('#btn_add').click(function () {
		// $('#addgoodsModal').modal({backdrop: 'static', keyboard: false});
		// });
		// $('#btn_edit').click(function () {
		// var selects =
		// $('#exampleTableToolbar').bootstrapTable('getSelections');
		// if(selects.length != 1){
		// swal("必须选择一个预案！")
		// }else{
		// $('#pre_id').val(selects[0].pre_id);
		// $('#pre_name').val(selects[0].pre_name);
		// $('#disaster_kind').val(selects[0].disaster_kind);
		// $('#disaster_strength').val(selects[0].disaster_strength);
		// $('#disaster_area').val(selects[0].disaster_area);
		// $('#disaster_people').val(selects[0].disaster_people);
		// $('#climate').val(selects[0].climate);
		// $('#geography').val(selects[0].geography);
		// $('#editModal').modal({backdrop: 'static', keyboard: false});
		// }
		// });
		// $('#btn_delete').click(function () {
		// var selects = $('#goodsinfo').bootstrapTable('getSelections');
		// if(selects.length == 0){
		// swal("请选择想要删除的预案！");
		// }else{
		// for(var i in selects){
		// $('#goodsinfo').bootstrapTable('removeByUniqueId', selects[i].code);
		// }
		// }
		// });
		// $('.modal-close').click(function () {
		// $('#goodsinfo').bootstrapTable('refresh',{'url':'/elpdemo/static/basicfun/preproject/js/goods.json'});
		// $('#addgoodsModal').modal('hide');
		// });
		// $('.addFile').click(function () {
		// $('#addModal').modal({backdrop: 'static', keyboard: false});
		// $('#pre_id1').val('111');
		// $('#pre_name').val('222');
		// $('#disaster_kind1').val('333');
		// $('#disaster_strength1').val('444');
		// $('#disaster_area1').val('555');
		// $('#disaster_people1').val('666');
		// $('#climate1').val('777');
		// $('#geography1').val('888');
		// $('#inputPreTable').bootstrapTable({
		// url : "/elpdemo/static/basicfun/preproject/js/goods.json",
		// //请求后台的URL（*）
		// method: 'get', //请求方式（*）
		// toolbar: '#toolbar2', //工具按钮用哪个容器
		// striped: true, //是否显示行间隔色
		// cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		// pagination: true, //是否显示分页（*）
		// sortable: false, //是否启用排序
		// sortOrder: "asc", //排序方式
		// queryParams: null,//传递参数（*）
		// sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
		// pageNumber:1, //初始化加载第一页，默认第一页
		// pageSize: 10, //每页的记录行数（*）
		// pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
		// search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		// strictSearch: true,
		// showColumns: true, //是否显示所有的列
		// showRefresh: true, //是否显示刷新按钮
		// minimumCountColumns: 2, //最少允许的列数
		// clickToSelect: true, //是否启用点击选中行
		// uniqueId: "code", //每一行的唯一标识，一般为主键列
		// showToggle:true, //是否显示详细视图和列表视图的切换按钮
		// cardView: false, //是否显示详细视图
		// detailView: false, //是否显示父子表
		// columns: [{
		// checkbox: true
		// }, {
		// field: 'code',
		// title: '物资编号'
		// },{
		// field: 'goods_name',
		// title: '物资名称'
		// }, {
		// field: 'goods_kind',
		// title: '物资类型'
		// }, {
		// field: 'unit',
		// title: '物资单位'
		// },{
		// field: 'goods_size',
		// title: '物资尺寸'
		// },{
		// field: 'amount',
		// title: '物资数量'
		// },{
		// field: 'priority',
		// title: '物资批次'
		// },{
		// field: 'save_cycle',
		// title: '救援周期'
		// } ]
		// });
		// });
	};

	return oInit;
};

function getTime(t){
	var newDate = new Date();
	newDate.setTime(t);
	// Wed Jun 18 2014 
	//console.log(newDate.toDateString());
	// Wed, 18 Jun 2014 02:33:24 GMT 
	//console.log(newDate.toGMTString());
	// 2014-06-18T02:33:24.000Z
	///console.log(newDate.toISOString());
	// 2014-06-18T02:33:24.000Z 
	return newDate.toLocaleString();
}
