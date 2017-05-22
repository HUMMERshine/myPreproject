<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../system/common/includeBaseSet.jsp"%>
<%@include file="../../system/common/includeSystemSet.jsp"%>
<style>
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

						<form class="form-horizontal" role="form">
							<fieldset>
								<div class="form-group">
									<label class="col-sm-1 control-label" for="ds_host">灾害类型</label>
									<div class="col-sm-4">
										<input class="form-control" id="type" type="text"
											placeholder="192.168.1.161" />
									</div>
									<label class="col-sm-2 control-label" for="ds_name">灾害等级</label>
									<div class="col-sm-4">
										<input class="form-control" id="level" type="text"
											placeholder="msh" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label" for="ds_host">发生时间</label>
									<div class="col-sm-4">
										<input class="form-control" id="time" type="text"
											placeholder="192.168.1.161" />
									</div>
									<label class="col-sm-2 control-label" for="ds_name">受灾人口</label>
									<div class="col-sm-4">
										<input class="form-control" id="people" type="text"
											placeholder="msh" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label" for="ds_host">受灾面积</label>
									<div class="col-sm-4">
										<input class="form-control" id="area" type="text"
											placeholder="192.168.1.161" />
									</div>
									<label class="col-sm-2 control-label" for="ds_name">天气信息</label>
									<div class="col-sm-4">
										<input class="form-control" id="climate" type="text"
											placeholder="msh" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label" for="ds_host">地理信息</label>
									<div class="col-sm-4">
										<input class="form-control" id="geography" type="text"
											placeholder="192.168.1.161" />
									</div>
									<label class="col-sm-2 control-label" for="ds_name">附加信息</label>
									<div class="col-sm-4">
										<input class="form-control" id="ds_name" type="text"
											placeholder="..." />
									</div>
								</div>
							</fieldset>
						</form>
						<table id=goodsInfo class="table-bordered"></table>
						<div style="text-align: right;">
							<button id="btn_select" type="button" class="btn btn-success">
								<span class="glyphicon" aria-hidden="true"></span>选择预案
							</button>
							<button id="btn_cancel" type="button" class="btn btn-danger">
								<span class="glyphicon" aria-hidden="true"></span>放弃预案
							</button>
						</div>
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
	<script src="${jypath}/static/js/basicfun/emergency/create.js"></script>
</body>
</html>