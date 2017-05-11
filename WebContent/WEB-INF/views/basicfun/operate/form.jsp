<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="dis_ID" ></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">类别：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" name="dis_kind" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">地址：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="place_name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">面积：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="area" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">人数：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="people" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">强度：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="strength" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">时间：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="occurtime" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">经纬度：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="lat_long" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">描述：</td>
						<td class="DataTD">&nbsp;
						<textarea  rows="2" cols="10" maxlength="100" name="dis_info" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="hr hr-dotted"></div>
</div>
	
	