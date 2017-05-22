$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
    	$('#operateinfo').bootstrapTable({
			//url : jypath+"/static/js/basicfun/logrecord/operate_all.json",        //请求后台的URL（*）
			url : jypath+"/backstage/logRecord/findAll",        //请求后台的URL（*）
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
			pageSize : 5, // 每页的记录行数（*）
			pageList : [ 5, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : false, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			uniqueId : "code", // 每一行的唯一标识，一般为主键列
			showToggle : false, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
            responseHandler: function(data){
            	console.log("^^^^^^^^");
            	console.log(data);
                //return data.rows;
            	var list = data.obj;
				var total = list.length;
				var rows = new Array();
				for ( var i in list) {
					var temp = new Object();
					temp.operate_type = getType(list[i].operate_type);
					temp.pre_id = list[i].preproject.pre_id;
					temp.operate_name = list[i].operater_name;
					temp.operate_time = getTime(list[i].operate_time);
					//temp.operate_describe = getDescribe(list[i].operate_describe);
					temp.operate_describe = list[i].operate_describe;
					temp.approval_state = list[i].approval_state == false ? "未审批" : "已审批";
					temp.approval_name = list[i].approval_name;
					temp.approval_result = list[i].approval_result;
					temp.approval_proposal = list[i].approval_proposal;
					temp.approval_time = getTime(list[i].approval_time);
					rows.push(temp);
				}

				var res = new Object();
				res.total = total;
				res.rows = rows;
				return rows;
            },
            rowStyle: function (row, index) {
                //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
                var strclass = "";
                if (row.approval_state == "未审批") {
                	return { classes: 'warning' };
                }
                
                if (row.approval_result == "不通过") {
                    strclass = 'danger';//还有一个active
                }
                else {
                    strclass = 'success';
                }
                return { classes: strclass };
            },
            columns: [{
                checkbox: true
            }, {
                field: 'operate_type',
                title: '操作类型'
            }, {
                field: 'pre_id',
                title: '预案编号'
            }, {
                field: 'operate_name',
                title: '操作者'
            }, {
                field: 'operate_time',
                title: '操作时间',
                width: '7.8%'
            },{
                field: 'operate_describe',
                title: '详细信息',
                width: '25%'
            },{
                field: 'approval_state',
                title: '审批状态'
            },{
                field: 'approval_name',
                title: '审批者'
            },{
                field: 'approval_result',
                title: '审批结果'
            },{
                field: 'approval_proposal',
                title: '审批意见',
                width: '20%'
            },{
                field: 'approval_time',
                title: '审批时间',
                width: '7%'
            }],
            onDblClickRow: function(row, $element) {
                //$element是当前tr的jquery对象
            	console.log(row);
            	
            	$('#describeModal').modal({backdrop: 'static', keyboard: false});
            	$('#describe').text(row.operate_describe);
            },//单击row事件
//			,{
//                field: 'match',
//                title: '匹配度'
//            }
        });
    };
    return oTableInit;
}
var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
    	$('#btn_cancel').click(function () {
    		window.location.href='/elpdemo/webpage/modules/basicfun/preproject/list.jsp';
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

function getDescribe(describe){
	console.log(describe);
	if(describe == null) return null;
	var res = "";
	var start = 0;
	var i = 0;
	for(; i < describe.length; i++){
		if(i > 0 && i % 30 == 0){
			res += describe.substring(start, i);
			res += "<br/>";
			start = i;
		}
	}
	if(describe.length > 0)
		res += describe.substring(start, i);
	
	console.log(res);
	return res;
}

function getType(type){
	if(type == "insert"){
		return "新建预案";
	}
	
	if(type == "update"){
		return "修改预案";
	}
	
	if(type == "delete"){
		return "删除预案";
	}
}
