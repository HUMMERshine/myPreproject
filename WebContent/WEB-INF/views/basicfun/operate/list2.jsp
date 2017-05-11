<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../system/common/includeBaseSet.jsp" %>
<%@include file="../../system/common/includeSystemSet.jsp" %>

<style type="text/css">

.ttd{
overflow: hidden;
white-space: nowrap;
text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
				<div class="row">
					<div class="widget-main">	
						<input type="text" name="keyWord" placeholder="这里输入关键词" class="input-large">
						&nbsp;&nbsp;<span id="selectisValid"><label></label>：<select  data-placeholder="状态" name="isValid" class="chosen-select isSelect75"></select></span>											
						&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>		
					</div>
				</div>
				<input type='hidden' class='pageNum' name='pageNum' value='1'/>
				<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" style="table-layout: fixed;">
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:10%" class="center">操作类型</th>
							<th style="width:15%" class="center">预案编号</th>
							<th style="width:10%" class="center">操作者</th>
							<th style="width:20%" class="center">操作时间</th>
							<th style="width:30%" class="center">详情信息</th>
							<th style="width:15%" class="center">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-4">
						<div class="dataTables_info customBtn" >
							<c:forEach var="pbtn" items="${permitBtn}">
								<a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3" ><i class='${pbtn.icon} bigger-220'></i></a>
							</c:forEach>
						</div>
					</div>
					<div class="col-sm-8">
						<!--设置分页位置-->
						<div id="pageing" class="dataTables_paginate paging_bootstrap">
							<ul class="pagination"></ul>
						</div>
					</div>
				</div>		
			<!-- #addorUpdateFrom -->
			<%@include file="form.jsp" %>
			<!-- #dialog-confirm -->
			
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
			            </div>
			            <textarea rows="10" cols="70" id="mytext"></textarea>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
			</div>
			
			<div>
			<%@include file="../../system/common/dialog.jsp" %>
			<%@include file="./addplan.jsp" %>	
			</div>
		</div>
	</div>
	<script src="${jypath}/static/js/basicfun/operate/operate.js"></script>
</body>
</html>