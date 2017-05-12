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

    /* .table>tbody>tr.warning>td{
    background-color: #F7C709;
    border-color: #F7C709;
    }
    .table-hover tbody tr.warning:hover td{
    background-color: #F7C709;
    border-color: #F7C709;
    } */

.table-hover {
	border: 1px solid #ddd;
	border-left: 4px solid #ddd;
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
				</div>  style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"-->

							<table id=operateinfo style="word-break:break-all; word-wrap:break-all;"></table>
							<div style="text-align: right;">
								<button id="btn_cancel" type="button" class="btn btn-warning">
									<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>返回
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
	<script src="${jypath}/static/js/basicfun/preproject/preproject.js"></script>
	<script src="${jypath}/static/js/sweetalert/sweetalert.min.js"></script>
	<script src="${jypath}/static/js/basicfun/logrecord/list3.js"></script>
</body>
</html>