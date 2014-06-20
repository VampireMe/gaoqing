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
						<td>使用过的数据库连接信息：</td>
						<td>
							<input id = "existDatabaseID" name = "existDatabase" value = "" />
							<input type = "hidden" id = "databaseTypeID" name = "databaseType" value = "" />
							<input type = "hidden" id = "databaseNameID" name = "databaseName" value = "" />
							
							<script type="text/javascript">
								$(document).ready(function(){
									//得到当前访问链接地址的主地址
									var basePath = window.location.protocol + "//" + window.location.host + "/CodeGenerator";
									
									/*
									 * 设置 使用过的数据库连接信息 为下拉框组件
									 * （1）使用 ajax 的方式，当选中后，动态的填充选中后的数据库连接信息
									 */
									$("#existDatabaseID").combotree({
										url: basePath + '/database/getSelected',
										cascadeCheck:false,
										animate: true,
										checkbox: true,
										onlyLeafCheck:true,
										lines:true,
										multiple: false,
										onSelect: function(node){
											//当点击后，将当前的数据库类型和数据库名称，传到后台，查询其值，然后返回数据
											var tree = $(this).tree;
											
											//将当前选中的值，赋到隐藏项中
											$("#databaseNameID").val(node.id);
											
											//判断当前选择的是否是叶子节点
											var parent = tree("getParent", node.target);
											//设置数据库类型
											$("#databaseTypeID").val(parent.id);
											
											/*
											* 选中之后，将选中的数据作为参数，传到后台，根据参数得到存储的数据库连接信息，
											* 并存放到 request 、session 、cookie 中
											*/
											
											//window.location.href = basePath + '/index?databaseType=' + $("#databaseTypeID").val() + "&databaseName=" + $("#databaseNameID").val();
											window.location.href = basePath + '/database/getLocalInfo?databaseType=' + $("#databaseTypeID").val() + "&databaseName=" + $("#databaseNameID").val();
											
											/* $.get({
												url: basePath + '/database/getLocalInfo?databaseType=' + $("#databaseTypeID").val() + "&databaseName=" + $("#databaseNameID").val(),
											}); */
											
										}
									});
									
								});
							</script>
						</td>
						<td align="center"><input type = "button" name = "test" value = "测试连接数据库"/></td>
						<td align="right"><input type = "button" name = "connect" value = "连接数据库"/></td>
					</tr>
				</table>
			</form>
		</fieldset>
		
		<!-- 引入外部脚本 Start -->
		<script type="text/javascript" src="${basePath }Resource/JS/database.js"></script>
		<!-- 引入外部脚本 End -->