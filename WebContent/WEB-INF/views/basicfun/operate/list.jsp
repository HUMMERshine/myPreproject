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
					<table id=operateinfo class="table-bordered"></table>
					<div style="text-align: right;">
						<button id="btn_cancel" type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>审核
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="describeModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">操作详情</h4>
				</div>
				<div class="modal-body">
					<!-- <textarea id="describe" rows="10" cols="73"></textarea> -->
					<p id="describe"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">同意</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">不同意</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	</div>
	<script
		src="${jypath}/static/js/bootstrap/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${jypath}/static/js/bootstrap/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script
		src="${jypath}/static/js/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${jypath}/static/js/sweetalert/sweetalert.min.js"></script>
	<script src="${jypath}/static/js/basicfun/operate/operate.js"></script>
</body>
</html>