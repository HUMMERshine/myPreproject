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
<div class="modal fade" id="addpregoodsModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<!-- <div class="modal-dialog modal-lg"> -->
	<div class="modal-dialog" style="width:400px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="addpregoodstitle">添加物资</h4>
			</div>
			<div class="col-center-block">
				<form class="form-horizontal" role="form">
					<div class="col-center-block">
						<div class="col-md-12">
						    <div class="form-group">
						        <label class="col-sm-3 control-label">物资编码：</label>
						        <div class="col-sm-9" >
						            <input type="text" id="approval_proposal" class="form-control" placeholder="请输入文本">
						        </div>
						    </div>
						    <div class="form-group">
						        <label class="col-sm-3 control-label">物资数量：</label>
						        <div class="col-sm-9" >
						            <input type="text" id="approval_proposal" class="form-control" placeholder="请输入文本"> 
						        </div>
						    </div>
						    <div class="form-group">
						        <label class="col-sm-3 control-label">物资批次：</label>
						        <div class="col-sm-9" >
						            <input type="text" id="approval_proposal" class="form-control" placeholder="请输入文本">
						        </div>
						    </div>
						    <div class="form-group">
						        <label class="col-sm-3 control-label">救援周期：</label>
						        <div class="col-sm-9" >
						            <input type="text" id="approval_proposal" class="form-control" placeholder="请输入文本"> 
						        </div>
						    </div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary modal-close">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


