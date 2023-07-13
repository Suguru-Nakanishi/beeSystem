<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../html/header.html"%>
<title>入出庫一覧</title>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="../css/style.css">
<meta charset="utf-8">

<style>
form {
	width: 500px;
}

input {
	width: 200px;
}

.radio {
	width: 20px;
}

.input_date {
	width: 100px;
}

button:nth-child(odd) {
	width: 100px;
	float: right;
}

th:first-child, td:first-child {
	width: 65px;
}
th:nth-child(5), td:nth-child(7n+5) {
	width: 540px;
	border-right: 2px solid #44d;
}
th:nth-child(2), td:nth-child(7n+2) {
	width: 80px;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button{
-webkit-appearance: none;
margin: 0;
display: inline-block;
</style>
</head>
<body id="entry_exit_info_list">
	<header>
		<a href="javascript:scrset2('menu')">メニューに戻る</a>
		<h1>入出庫一覧</h1>
	</header>
	<main>
	<form action="EntryExitInfoList.action" method="POST">
		<!-- fanctionに指示を送るための隠し項目 -->
		<input type="hidden" name="siji"> <input type="hidden"
			name="siji2">
		<button style="width: 150px; margin: 10px; " type="button"
			id="clear" onclick="doExecute('clear')">リセット</button>
		<br> <label>品番：<input type="number" class="no_spin" name="product_no"
			onchange="doExecute('idSearch')" value="${product_no}" oninput="javascript:if(this.value.length>this.maxLength)this.value=this.value.slice(0,this.maxLength);" maxlength="10"></label> <label>品名：${list[0].product_name}
	</label> <label>検索条件： 日付<input type="text"
			class="input_date" id="start_date" name="start_date"
			value="${start_date}" placeholder="例:yyyy/MM/dd" disabled onchange="doExecute('start_end')"> ～ <input type="text"
			class="input_date" id="end_date" name="end_date" value="${end_date}" placeholder="例:yyyy/MM/dd"
			disabled onchange="doExecute('start_end')"></label> <label>絞り込み：<input type="radio"
			class="radio" id="all" name="radio2" onchange="doExecute('all')"
			value="all" disabled checked>全件表示 <input type="radio"
			class="radio" id="instore" name="radio2"
			onchange="doExecute('instore')" value="instore" disabled>入庫のみ
			<input type="radio" class="radio" id="shipping" name="radio2"
			onchange="doExecute('shipping')" value="shipping" disabled>出庫のみ
		</label> <label>並び替え： <input type="radio" class="radio" id="date"
			name="radio" onchange="doExecute('date')" value="date" disabled
			checked>年月日 <input type="radio" class="radio" id="qty"
			name="radio" onchange="doExecute('qty')" value="qty" disabled>数量
		</label>
		<input type="hidden" name="nextscr2">
	</form>
	<table>
		<thead>
			<tr>
				<th>入出庫日</th>
				<th>入出庫番号</th>
				<th>入庫/出庫</th>
				<th>数量</th>
				<th>理由</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.en_ex_date}</td>
					<td>${item.eb_ex_id}</td>
					<td>${item.situation}</td>
					<td>${item.count}</td>
					<td>${item.reason}</td>
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
function scrset2(args){
	form=document.forms[0];
	form.nextscr2.value=args;
	form.submit()
}
</script>
<script>
		function scrset() {
			var recvProductNo = "${product_no}";
			if (recvProductNo != "" || recvProductNo.length > 0) {
				document.getElementById("start_date").removeAttribute(
						"disabled")
				document.getElementById("end_date").removeAttribute("disabled")
				document.getElementById("all").removeAttribute("disabled")
				document.getElementById("instore").removeAttribute("disabled")
				document.getElementById("shipping").removeAttribute("disabled")
				document.getElementById("date").removeAttribute("disabled")
				document.getElementById("qty").removeAttribute("disabled")
			}
			var recvDousa = "${siji}";
			if (recvDousa == "qty") {
				document.getElementById("date").removeAttribute("checked");
				document.getElementById("qty").setAttribute("checked", true);
				document.getElementById("instore").removeAttribute("checked");
				document.getElementById("shipping").removeAttribute("checked");
			}
			if (recvDousa == "instore") {
				document.getElementById("date").removeAttribute("checked");
				document.getElementById("qty").removeAttribute("checked");
				document.getElementById("instore")
						.setAttribute("checked", true);
				document.getElementById("shipping").removeAttribute("checked");
			}
			if (recvDousa == "shipping") {
				document.getElementById("date").removeAttribute("checked");
				document.getElementById("qty").removeAttribute("checked");
				document.getElementById("instore").removeAttribute("checked");
				document.getElementById("shipping").setAttribute("checked",
						true);
			}
		}
		document.addEventListener("load", checkRadio());
		function checkRadio() {
			var sel = "${radio}";
			var form = document.getElementsByName("radio");
			for (i = 0; i < form.length; i++) {
				if (form[i].value == sel) {
					form[i].checked = true;
				}
			}
			var sel = "${radio2}";
			var form = document.getElementsByName("radio2");
			for (i = 0; i < form.length; i++) {
				if (form[i].value == sel) {
					form[i].checked = true;
				}
			}
		}
	</script>
		 <script>
		 document.addEventListener('DOMContentLoaded', messageAlert());

		 function messageAlert(){
		 	var recvMSG = "${message}";
		 	if(recvMSG != null && recvMSG !=""){
		 		alert(recvMSG);
		 	}
		 	scrset()
		 }
</script>
</body>
</html>