<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../system/common/includeBaseSet.jsp"%>
<%@include file="../../system/common/includeSystemSet.jsp"%>
<style type="text/css">
.caret {
	display: inline-block;
	width: 0;
	height: 0;
	margin-left: 2px;
	vertical-align: middle;
	border-top: 4px solid #000;
	border-bottom: 0 dotted;
	border-left: 4px;
	border-right: 4px;
	content: "";
}

.table-hover {
	border: 1px solid #ddd;
	border-left: 4px solid #ddd;
	table-layout: fixed;
}

.table {
	border: 1px solid #ddd;
	border-left: 4px solid #ddd;
	border-collapse: collapse;
	border-spacing: 1;
}
</style>
</head>
<body>

	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<div class="wrapper wrapper-content animated fadeInRight">

					<div class="ibox-content">

						<!-- <div id="toolbar" class="btn-group hidden-xs">
					<button id="btn_add" type="button" class="btn btn-primary">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
					</button>
					<button id="btn_delete" type="button" class="btn btn-danger">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
				</div> 
				style="word-break:break-all; word-wrap:break-all;

						-->
						<form class="form-horizontal col-center-block" role="form">
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">灾害类型</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_type"
										placeholder="请输入灾害类型" value="地震">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">灾害等级</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_level"
										placeholder="请输入灾害等级" value="7.5">
								</div>
							</div>
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">发生时间</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_time"
										placeholder="请输入发生时间" value="2016-7-15 12:03:32">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">受灾人口</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_people"
										placeholder="请输入人口密度" value="100000">
								</div>
							</div>
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">受灾面积</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_area"
										placeholder="请输入受灾面积" value="100">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">地理信息</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_geography"
										placeholder="请输入地理信息" value="平原">
								</div>
							</div>
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">天气信息</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_climate"
										placeholder="请输入天气信息" value="小雨">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">附加信息</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_info"
										placeholder="请输入附加信息">
								</div>
							</div>
						</form>
						<div style="text-align: right;">
							<button id="btn_match" type="button" class="btn btn-success">
								<span class="glyphicon" aria-hidden="true"></span>预案匹配
							</button>
						</div>
						<table id=operateinfo class="table-bordered"></table>
						<div style="text-align: right;">
							<button id="btn_select" type="button" class="btn btn-success">
								<span class="glyphicon" aria-hidden="true"></span>选择预案
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="preprojectGoods" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 1000px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">操作详情</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal col-center-block" role="form">
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">灾害类型</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_type"
										placeholder="请输入灾害类型" value="地震">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">灾害等级</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_level"
										placeholder="请输入灾害等级" value="7.5">
								</div>
							</div>
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">发生时间</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_time"
										placeholder="请输入发生时间" value="2016-7-15 12:03:32">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">受灾人口</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_people"
										placeholder="请输入人口密度" value="100000">
								</div>
							</div>
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">受灾面积</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_area"
										placeholder="请输入受灾面积" value="100">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">地理信息</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_geography"
										placeholder="请输入地理信息" value="平原">
								</div>
							</div>
							<div class="form-group">
								<label for="pre_id" class="col-sm-1 control-label">天气信息</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_climate"
										placeholder="请输入天气信息" value="小雨">
								</div>
								<label for="pre_id" class="col-sm-2 control-label">附加信息</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="disaster_info"
										placeholder="请输入附加信息">
								</div>
							</div>
						</form>
						<table id=goodsInfo class="table-bordered"></table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">同意</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">不同意</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="${jypath}/static/js/bootstrap/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${jypath}/static/js/bootstrap/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script
		src="${jypath}/static/js/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${jypath}/static/js/sweetalert/sweetalert.min.js"></script>
	<script src="${jypath}/static/js/basicfun/emergency/emergency.js"></script>
</body>
</html>