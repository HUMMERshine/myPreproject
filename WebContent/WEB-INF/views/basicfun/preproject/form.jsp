<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>预案编号：</td>
						<td class="DataTD">&nbsp;
						<input id="pre_id_add" type="text" maxlength="16" jyValidate="required,ennum" name="pre_id" class="FormElement ui-widget-content ui-corner-all"></td>
						<td class="CaptionTD"><font color="red">*</font>预案名称：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="pre_name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>灾害类型：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="pre_kind" class="FormElement ui-widget-content ui-corner-all"></td>
						<td class="CaptionTD"><font color="red">*</font>灾害等级：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="strength" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>受灾面积：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="area" class="FormElement ui-widget-content ui-corner-all"></td>
						<td class="CaptionTD"><font color="red">*</font>受灾人口：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="people" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>气象信息：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="climate" class="FormElement ui-widget-content ui-corner-all"></td>
						<td class="CaptionTD"><font color="red">*</font>地理信息：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="geography" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>匹配度：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jyValidate="required,ennum" name="match" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		<%@include file="./pregoods.jsp" %>
</div>	
	
	