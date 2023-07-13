<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../html/header.html"%>
<title>受注一覧</title>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="../css/style.css">
<meta charset="utf-8">

<style>
form{
	width: 600px;
	height:200px;
	padding: 10px 20px 10px;
}
label{
	width: 470px;
}
table{
	width: 640px;
}
input{
	width: 200px;
}
.radio{
	width: 10px;
}
.input_date{
	width: 100px;
}
th:first-child,td:nth-child(7n+1){
	margin:0 auto;
	text-align:center;
	width:100px;
	border-left: 2px solid #44d
}
th:nth-child(2),td:nth-child(7n+2),th:nth-child(3),td:nth-child(7n+3),th:nth-child(4),td:nth-child(7n+4){
	width:100px;
	border-left: 1px solid #44d;
	text-align: center;
}
th:nth-child(6){
	width: 540px;
	border-right: 2px solid #44d;
}
td:nth-child(7n+6){
	width: 540px;
	border-right: 2px solid #44d;
	text-align: left;
	padding-left: 5px;
}
td:nth-child(5){
	text-align: right;
	padding-right: 5px;
}
button{
	width: 100px;
}

</style>
</head>
<body id="purchaselist">
	<header>
		<a href="javascript:scrset('menu')">メニューに戻る</a>
		<h1>受注一覧</h1>
	</header>
	<main>
	<form action="PurchaseList.action" method="POST">
		<!-- fanctionに指示を送るための隠し項目 -->
		<input type="hidden" name="siji">
		<button type="button" onclick="doExecute('clear')">リセット</button><br>
		<label>品番：<input type="text" name="number" onchange="doExecute('product_no')" value="${product_no}"></label>
		<label>品名：${product_name}</label>

		<label>検索条件：  受注日
		<input type="text" class="input_date" name="date1" onchange="doExecute('date')" value="${date1}" placeholder="yyyy/mm/dd"> ～
		<input type="text" class="input_date" name="date2" onchange="doExecute('date')" value="${date2}" placeholder="yyyy/mm/dd"></label>

		<label>絞り込み：
		<input type="radio" class="radio" id="all" name="radio1" onchange="doExecute('all')" value="all" checked>全件表示
		<input type="radio" class="radio" id="fin_flg0" name="radio1" onchange="doExecute('fin_flg0')" value="fin_flg0">未出荷
		<input type="radio" class="radio" id="fin_flg1" name="radio1" onchange="doExecute('fin_flg1')" value="fin_flg1">出荷済</label>
		<label>並び替え：
		<input type="radio" class="radio" id="date" name="radio2" onchange="doExecute('order_date')" value="order_date" checked>受注日
		<input type="radio" class="radio" id="po_no" name="radio2" onchange="doExecute('po_no')" value="po_no">受注番号
		<input type="radio" class="radio" id="delivery_date" name="radio2" onchange="doExecute('delivery_date')" value="delivery_date">納期
		<input type="radio" class="radio" id="ship_date" name="radio2" onchange="doExecute('ship_date')" value="ship_date">出荷日
		<input type="radio" class="radio" id="order_qty" name="radio2" onchange="doExecute('order_qty')" value="order_qty">数量
		<input type="radio" class="radio" id="customer_no" name="radio2" onchange="doExecute('customer_no')" value="customer_no">顧客コード</label>
	<input type="hidden" name="nextscr">
	</form>
	<table>
		<thead>
			<tr>
				<th>受注日</th>
				<th>受注番号</th>
				<th>納期</th>
				<th>出荷日</th>
				<th>数量</th>
				<th>顧客名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
				<td>${item.order_date}</td>
				<td>${item.po_no}</td>
				<td>${item.delivery_date}</td>
				<td>${item.ship_date}</td>
				<td>${item.order_qty}</td>
				<td title="${item.customer_no}">${item.customer_name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<script>
	//もし中身がなかったらdousaをsijiに送る。なければsijiで。
	function doExecute(args) {
		var form = document.forms[0];
			form.siji.value = args;
		form.submit();
	}
</script>
<script>
function checkRadio() {
var sel = "${radio1}";
var form = document.getElementsByName("radio1")
for (i = 0; i < form.length; i++) {
if (form[i].value == sel) {
form[i].checked = true;
		}
	}
var sel = "${radio2}";
var form = document.getElementsByName("radio2")
for (i = 0; i < form.length; i++) {
if (form[i].value == sel) {
form[i].checked = true;
		}
	}
}
</script>
<script>
	document.addEventListener('DOMContentLoaded', messageAlert());
	function messageAlert() {
		var recvMSG = "${message}";
		if (recvMSG != null && recvMSG != "") {
			alert(recvMSG);
		}
		checkRadio();
	}
</script>
<%@include file="../html/footer1.html"%>