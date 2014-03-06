<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- *** 球员场均数据统计（分别是对前7场、前15场、前30场、总场数的场均统计） *** -->
<div id = "playerAvgStatOuter">
	<div id = "playerAvgStatInner">
		<div id = "theSeventh">
			<h3 style = "text-shadow: 2px 2px 2px #C4C4C4;color: #71C671;">前7场数据统计：</h3>
			<table id = "theSevenTable" cellspacing="4" cellpadding="3">
				<tbody></tbody>
			</table>
		</div>
		<div id = "theFifteen">
			<h3 style = "text-shadow: 2px 2px 2px #C4C4C4;color: #71C671;">前15场数据统计：</h3>
			<table id = "theFifteenTable" cellspacing="4" cellpadding="3">
				<tbody></tbody>
			</table>
		</div>
		<div id = "theThirty">
			<h3 style = "text-shadow: 2px 2px 2px #C4C4C4;color: #71C671;">前30场数据统计：</h3>
			<table id = "theThirtyTable" cellspacing="4" cellpadding="3">
				<tbody></tbody>
			</table>
		</div>
		<div id = "theAll">
			<h3 style = "text-shadow: 2px 2px 2px #C4C4C4;color: #71C671;">所有场数据统计：</h3>
			<table id = "theAllTable" cellspacing="4" cellpadding="3">
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>
