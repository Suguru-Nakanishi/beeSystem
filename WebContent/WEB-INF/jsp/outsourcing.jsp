<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../html/header.html"%>
	<title>納入画面</title>
<style>
button {
    margin: 20px 0px 5px auto;
    width: 180px;
    height: 30px;
    display: block;
    font: normal 900 14px/20px sans-serif;
    border: 1px solid #000;
    border-radius: 3px;
    box-shadow: 2px 2px 3px #dd444488;
}
</style>

<script>
function doExecute2(args){
	var form = document.forms[0];
	form.siji.value = args;
	form.submit();
}
</script>
<script>
document.addEventListener('DOMContentLoaded', messageAlert());

function messageAlert(){

	var recvMSG = "${message}";
	if(recvMSG != null && recvMSG !=""){
		alert(recvMSG);
	}
}
</script>



</head>

<body id="outsourcing">
<header>
	<a href="javascript:scrset('menu')">メニューへ戻る</a>
	<h1>納入画面</h1>
</header>
<main>
	<form action ="Outsourcing.action" method="POST">
	<input type="hidden" name="siji">
		<label>注文番号　：<input type="text" name="order_no" onchange="doExecute2('idSearch')" value="${os.order_no}"></label>
		<label>注文日　　：<input type="text" name="orderdate"  value="${os.orderdate}" readonly></label>
		<label>品番　　　：<input type="text" name="product_no"  value="${os.product_no}" readonly></label>
		<label>品名　　　：<input type="text" name="product_name"  value="${os.product_name}" readonly></label>
		<label>注文数量　：<input type="text" name="order_qty"  value="${os.order_qty}" readonly></label>
		<label>納入数量　：<input type="text" name="due_qty"  onchange="doExecute2('check')" value="${os.due_qty}"   placeholder="納入数量を入力ください。" ></label>
		<button type ="button" onClick="doExecute2('update')">確定</button>
		<button type ="button" onClick="doExecute2('clear')">キャンセル</button>
	<input type="hidden" name="nextscr">
	</form>
</main>
<footer>&#xa9;&#xa0;2021 Free Style Co.,Ltd. All rights reserved.</footer>
</body>
<script type="text/javascript" charset="utf-8" src="../js/javascript.js"></script>

</html>