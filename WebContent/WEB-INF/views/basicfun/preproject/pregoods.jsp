<%@ page contentType="text/html;charset=UTF-8" %>
<div id="pregoodsDiv">
		<form id="pregoodsForm" method="POST" onsubmit="return false;" >
			<input type='hidden' class='pageNum' name='pageNum' value='1'/>
			<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
			<input type='hidden' id = 'pregoodsform_preid' name='pre_id' value=''/>
		</form>
		<a id="pregoodsformAdd" class="lrspace3 aBtnNoTD" title="增加" href="#">
			<i class="icon-plus-sign color-green bigger-200"></i>
		</a>
			<table id="pregoodsTable" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<!-- <th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th> -->
							<th style="width:5%" class="center">序号</th>
							<th style="width:10%" class="center hidden-480">物资编码</th>
							<th style="width:10%" class="center hidden-480">物资名称</th>
							<th style="width:10%" class="center hidden-480">物资种类</th>
							<th style="width:10%" class="center hidden-480">物资数量</th>
							<th style="width:10%" class="center hidden-480" >物资单位</th>
							<th style="width:10%" class="center hidden-480" >物资尺寸</th>
							<th style="width:10%" class="center hidden-480" >物资批次</th>
							<th style="width:10%" class="center hidden-480" >救援周期</th>
							<th style="width:5%" class="center hidden-480" >操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
		
		<div class="row">
			<div class="col-sm-8">
				<!--设置分页位置-->
				<div id="pregoodspageing" class="dataTables_paginate paging_bootstrap">
					<ul class="pagination"></ul>
				</div>
			</div>
		</div>
		<div class="hr hr-dotted"></div>
</div>
<%@include file="./addpregoods.jsp" %>
<!-- <div id="pregoodsitemDiv" class="hide">
	<table cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>物资编码：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="32" jyValidate="required" name="code" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>物资名称：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="16" jyValidate="required" name="goods_name" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>物资种类：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="16" jyValidate="required" name="goods_kind" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>物资数量：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="32" jyValidate="required" name="goods_num" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			
					
		</tbody>
	</table>
</div> -->
	
	