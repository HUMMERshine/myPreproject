<%@ page contentType="text/html;charset=UTF-8"%>
<style>
.col-center-block {
	float: none;
	display: block;
	padding-left: auto;
	padding-right: auto;
}
</style>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<!-- <div class="modal-dialog modal-lg"> -->
	<div class="modal-dialog" style="width:400px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myEditPlanModalLabel">添加预案</h4>
			</div>
			<!-- <div class="modal-body">
			</div> 
			<table id="test" ></table>-->
			<div class="col-center-block">
				<form class="form-horizontal" role="form">
					<div class="col-center-block">
						<div class="form-group">
							<label for="pre_id" class="col-sm-4 control-label">预案编号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="pre_id"
									placeholder="请输入预案编号">
							</div>
						</div>
						<div class="form-group">
							<label for="pre_name" class="col-sm-4 control-label">预案名字</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="pre_name"
									placeholder="请输入预案名称">
							</div>
						</div>
						<div class="form-group">
							<label for="disaster_kind" class="col-sm-4 control-label">灾害类型</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="disaster_kind"
									placeholder="请输入灾害类型">
							</div>
						</div>
						<div class="form-group">
							<label for="disaster_strength" class="col-sm-4 control-label">灾害强度</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="disaster_strength"
									placeholder="请输入灾害强度">
							</div>
						</div>
						<div class="form-group">
							<label for="disaster_area" class="col-sm-4 control-label">灾害范围</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="disaster_area"
									placeholder="请输入灾害范围">
							</div>
						</div>
						<div class="form-group">
							<label for="disaster_people" class="col-sm-4 control-label">受灾人口</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="disaster_people"
									placeholder="请输入受灾人口">
							</div>
						</div>
						<div class="form-group">
							<label for="climate" class="col-sm-4 control-label">天气信息</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="climate"
									placeholder="请输入天气信息">
							</div>
						</div>
						<div class="form-group">
							<label for="geography" class="col-sm-4 control-label">地理信息</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="geography"
									placeholder="请输入地理信息">
							</div>
						</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary modal-close">提交更改</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


