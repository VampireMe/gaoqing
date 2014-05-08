<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 数据库信息页面部分 -->

		<fieldset id = "database">
			<form id = "databaseForm">
				<table>
					<tr>
						<td>数据库类型:</td>
						<td>
							<select name = "databaseType" id = "databaseType" required = "required" width = "100" value = "${database.databaseType }">
								<option value = "mysql">mysql</option>
								<option value = "oracle">oracle</option>
								<option value = "sqlserver">sqlserver</option>
							</select>
						</td>
						<td>数据库地址:</td>
						<td><input name = "url" value = "${database.url }"  placeholder = "数据库所在主机 IP"  required = "required"/></td>
						<td>端口:</td>
						<td><input name = "port" value = "${database.port }" required = "required" pattern = "[0-9]*" maxlength = "5"/></td>
					</tr>
					
					<tr>
						<td align="right">数据库名称:</td>
						<td><input name = "databaseName" value = "${database.databaseName }" required = "required"/></td>
						<td>用户名:</td>
						<td><input name = "user" value = "${database.user }" required = "required"/></td>
						<td>密码:</td>
						<td><input type = "password" name = "password" value = "${database.password }" required = "required"/></td>
					</tr>
					
					<tr>
						<td>表名:</td>
						<td><input name = "table" value = "${database.table }" placeholder = "数据库中存在的表"/></td>
						<td></td>
						<td align="center" colspan="2"><input type = "button" name = "test" value = "测试连接数据库"/></td>
						<td align="right"><input type = "button" name = "connect" value = "连接数据库"/></td>
					</tr>
				</table>
			</form>
		</fieldset>
		
		<!-- 引入外部脚本 Start -->
		<script type="text/javascript" src="${basePath }Resource/JS/database.js"></script>
		<!-- 引入外部脚本 End -->