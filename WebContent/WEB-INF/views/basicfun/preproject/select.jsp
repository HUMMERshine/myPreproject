<%@ page contentType="text/html;charset=UTF-8" %>
<div id="preoperateDiv" class="hide">
		<form id="preoperateForm" method="POST" onsubmit="return false;" >
			<input type='hidden' class='pageNum' name='pageNum' value='1'/>
			<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
		</form>
			<table id="preoperateTable" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width:5%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:5%" class="center">序号</th>
							<th style="width:20%" class="center">操作类型</th>
							<th style="width:10%" class="center">操作者</th>
							<th style="width:30%" class="center hidden-480" >操作时间</th>
							<th style="width:30%" class="center hidden-480" >更多操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
		
		<div class="row">
			<div class="col-sm-8">
				<!--设置分页位置-->
				<div id="preoperatepageing" class="dataTables_paginate paging_bootstrap">
					<ul class="pagination"></ul>
				</div>
			</div>
		</div>
		<div class="hr hr-dotted"></div>
</div>
	
	