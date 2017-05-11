<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="goods_ID" ></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">编号：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" name="code" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">名称：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="goods_name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">单位：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="unit" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">种类：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="goods_kind" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">重量：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="goods_weight" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">重量单位：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="goods_weight_unit" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">体积：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="goods_volume" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">体积单位：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="goods_volume_unit" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">尺寸：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="goods_size" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="hr hr-dotted"></div>
</div>
	
	