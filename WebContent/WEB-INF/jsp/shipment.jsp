<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<link rel="stylesheet" href="../css/style.css">	<meta charset="utf-8">
<title>出荷画面</title>
<style>
label span {
    margin: 0 2px;
    padding: 0 6px;
    font: normal 900 12px/12px sans-serif;
    color: #fff;
    background-color: #d48;
    border: 1px solid #f0f;
    border-radius: 5px;
}
label{
margin:10px auto;
width:385px;
}
</style>
</head>

<body id="shipment">
<header>
	<a href="javascript:scrset('menu')">メニューへ戻る</a>
	<h1>出荷画面</h1>
</header>
<main>
<form action ="Shipment.action" method="POST">
<!-- fanctionに指示を送るための隠し項目 -->
<input type="hidden" name="siji">
		<label style="text-align:right">年月日：<input type="date" id="today" style="width:140px; background-color:#fdfde8; border:none" readonly></label>
		<label>受注番号<span>必須</span>：<input type="text" name="po_no" onchange="doExecute('idSearch')" value="${ship.po_no}"></label>
		<label>受注日　　　　：<input type="text" name="order_date" value="${ship.order_date}" readonly></label>
		<label>顧客コード　　：<input type="text" name="customer_no" value="${ship.customer_no}" readonly></label>
		<label>顧客名(会社名)：<input type="text" name="customer_name" value="${ship.customer_name}" readonly></label>
		<label>顧客名(支店名)：<input type="text" name="branch_name" value="${ship.branch_name}" readonly></label>
		<label>品番　　　　　：<input type="text" name="product_no" value="${ship.product_no}" readonly></label>
		<label>品名　　　　　：<input type="text" name="product_name" value="${ship.product_name}" readonly></label>
		<label>数量　　　　　：<input type="text" name="order_qty" value="${ship.order_qty}" readonly></label>
		<label>出荷日　<span>必須</span>：<input type="date" id="current_date" name="ship_date" value="${ship.ship_date}"></label>
		<button type="button" onclick="doExecute('clear')" style="width: 150px">キャンセル</button>
		<button type="button" onclick="doExecute('update')" style="width: 150px">確定</button>

<input type="hidden" name="nextscr">
</form>

<script type="text/javascript">
    //今日の日時を表示
        window.onload = function () {
            //今日の日時を表示
            var date = new Date()
            var year = date.getFullYear()
            var month = date.getMonth() + 1
            var day = date.getDate()
            var toTwoDigits = function (num, digit) {
              num += ''
              if (num.length < digit) {
                num = '0' + num
              }
              return num
            }
            var yyyy = toTwoDigits(year, 4)
            var mm = toTwoDigits(month, 2)
            var dd = toTwoDigits(day, 2)
            var ymd = yyyy + "-" + mm + "-" + dd;
            document.getElementById("today").value = ymd;
            document.getElementById("current_date").value = ymd;
        }
</script>
<script>
function doExecute(args){
	var form = document.forms[0];
	form.servletSiji.value = args;
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
<%@include file="../html/footer1.html"%>