<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 根据表格生成代码的部分 -->

<div id = "tableData">
	<section id = "tablesSection">
		<div id = "tables">
			<ul>
				
			</ul>
		</div>
	</section>
	
	<section id = "generateInfoSection">
		<div id = "generateInfo">
			<div id = "writeInfo">
				<form action="">
					<table>
						<tr>
							<td>包名：</td>
							<td>
								<input type = "text" id = "packageNameID" name = "packageName" value = "" placeholder = "完整包名"/>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>类名：</td>
							<td>
								<input type = "text" id = "classNameID" name = "className" value = "" placeholder = ""/>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						
						<tr>
							<td>作者：</td>
							<td>
								<input type = "text" id = "authorNameID" name = "authorName" value = "" placeholder = ""/>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr align="right">
							<td colspan="3">
								<input type = "button" name = "generateCode" id = "generateCodeID"
										value = "&nbsp;生&nbsp;成&nbsp;代&nbsp;码&nbsp;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			<hr align="center" width="90%" color="#9ff52e" size="1"/>
			
			<div id = "showInfo" align="center">
				生成代码信息
			</div>
		</div>
	</section>
	
	<!-- 自定义脚本文件 Start -->
	<script type="text/javascript" src = "${basePath }Resource/JS/tableData.js"></script>
	<!-- 自定义脚本文件 End -->
	
</div>