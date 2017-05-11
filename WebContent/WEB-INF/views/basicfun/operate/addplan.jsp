<%@ page contentType="text/html;charset=UTF-8"%>
<style>
.col-center-block {
	float: none;
	display: block;
	padding-left: auto;
	padding-right: auto;
}

.modal-backdrop {
  opacity: 0 !important;
  filter: alpha(opacity=0) !important;
}
</style>
<div class="modal fade" id="approvalModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<!-- <div class="modal-dialog modal-lg"> -->
	<div class="modal-dialog" style="width:400px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myAddPlanModalLabel">添加预案</h4>
			</div>
			<div class="col-center-block">
				<form class="form-horizontal" role="form">
					<div class="col-center-block">
						<div class="col-md-12">
						    	<div class="form-group">
						        <label class="col-sm-3 control-label">单选框：</label>
						        <div class="col-sm-9" id="wrap">
						            <label class="radio-inline">
						                <input type="radio" checked="" value="通过" name="approval_result">通过</label>
						            <label class="radio-inline">
						                <input type="radio" value="不通过"  name="approval_result">不通过</label>
						        </div>
						    	</div>
						    <div class="form-group">
						        <label class="col-sm-3 control-label">文本框：</label>
						        <div class="col-sm-9" >
						            <input type="text" id="approval_proposal" class="form-control" placeholder="请输入文本"> <span class="help-block m-b-none">说明文字</span>
						
						        </div>
						    </div>
				</form>
			</div>
			<table id="inputPreTable" ></table>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary modal-close">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


