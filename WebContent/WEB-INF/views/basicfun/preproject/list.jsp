<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../system/common/includeBaseSet.jsp"%>
<%@include file="../../system/common/includeSystemSet.jsp"%>
</head>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<table width="100%">
					<tr>
						<td>
							<form id="baseForm" class="form-inline" method="POST"
								onsubmit="return false;">
								<div class="row">
									<div class="widget-main">
										<input type="text" name="keyWord" placeholder="这里输入关键词"
											class="input-large">
										<!-- &nbsp;&nbsp;<span id="selectisValid"><label></label>：<select  data-placeholder="状态" name="isValid" class="chosen-select isSelect75"></select></span>		 -->
										&nbsp;&nbsp;
										<button id='searchBtn' class="btn btn-warning  btn-xs"
											title="过滤" type="button" onclick="getbaseList(1)">
											<i class="icon-search bigger-110 icon-only"></i>
										</button>
									</div>
								</div>
								<input type='hidden' class='pageNum' name='pageNum' value='1' />
								<input type='hidden' class='pageSize' name='pageSize' value='5' />
							</form>
						</td>
						<td style="text-align: right;">
							<div style="display: inline;">
								<form name="Form2"
									action="${jypath}/backstage/preproject/fileUpload"
									method="post" enctype="multipart/form-data">
									<input type="submit" value="导入" style="float: right" /> <input
										type="file" name="files" multiple="multiple"
										style="float: right">
								</form>
							</div>
						</td>
					</tr>
				</table>
				<table id="baseTable"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 3%" class="center"><label><input
									type="checkbox" class="ace"><span class="lbl"></span></label>
							</th>
							<th style="width: 5%" class="center">序号</th>
							<th style="width: 7%" class="center">预案编号</th>
							<th style="width: 8%" class="center">预案名称</th>
							<th style="width: 8%" class="center hidden-480">灾害类型</th>
							<th style="width: 10%" class="center hidden-480">灾害等级</th>
							<th style="width: 10%" class="center hidden-480">受灾面积</th>
							<th style="width: 10%" class="center hidden-480">受灾人口</th>
							<th style="width: 10%" class="center">气象信息</th>
							<th style="width: 10%" class="center hidden-480">地理信息</th>
							<!-- <th style="width:7%" class="center hidden-480" >匹配度</th> -->
							<th style="width: 15%" class="center hidden-480">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-4">
						<div class="dataTables_info customBtn">
							<c:forEach var="pbtn" items="${permitBtn}">
								<a href="#" title="${pbtn.name}" id="${pbtn.btnId}"
									class="lrspace3"><i class='${pbtn.icon} bigger-220'></i></a>
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
				<%@include file="form.jsp"%>
				<%-- <%@include file="form2.jsp" %> --%>
				<%@include file="select.jsp"%>
				<!-- #dialog-confirm -->

				<%@include file="../../system/common/dialog.jsp"%>
			</div>
		</div>
	</div>
	<script src="${jypath}/static/js/basicfun/preproject/preproject.js"></script>
</body>
</html>