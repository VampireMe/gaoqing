<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 根据 SQL 语句生成部分 -->

<div id = "sqlData">
	<form action="">
		<section id = "writeSQLSection">
			<div id = "innderWriteSQL">
				<textarea style="margin: 10px; width: 95%;" rows="4" cols="100" placeholder = "请输入 SQL 语句"></textarea>
			</div>
		</section>
		
		<hr align="center" width="95%" color="#9ff52e" size="1"/>
		
		<section id = "writeCodeInfoSection">
			<table>
				<tr>
					<td>包名：</td>
					<td>
						<input type = "text" name = "packageName" value = "" placeholder = "完整包名"/>
					</td>
					<td>类名：</td>
					<td>
						<input type = "text" name = "packageName" value = "" placeholder = ""/>
					</td>
					<td>作者：</td>
					<td>
						<input type = "text" name = "packageName" value = "" placeholder = ""/>
					</td>
				</tr>
				<tr align="right">
					<td colspan="6">
						<input type = "button" name = "generateCode" 
								value = "&nbsp;生&nbsp;成&nbsp;代&nbsp;码&nbsp;" />
					</td>
				</tr>
			</table>			
		</section>
		
		<hr align="center" width="95%" color="#9ff52e" size="1"/>
		
		<section id = "showSQLGenerateInfo">
			<div id = "showInfo" align="center">
				生成代码信息
			</div>			
		</section>	
	</form>
</div>