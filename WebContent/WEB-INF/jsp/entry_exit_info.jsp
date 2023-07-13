<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入出庫画面</title>
<link rel="stylesheet" href="../css/style2.css">
<style>
.radio {
	width: 20px;
}

.button {
	display: flex;
}

button {
	margin: 10px;
}
.radio_div{
left: 30px;
position: relative;
}
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button{
-webkit-appearance: none;
margin: 0;
display: inline-block;
}
</style>

</head>
<body id="EntryExitInfo">
	<header>
		<a href="javascript:scrset2('menu')">メニューに戻る</a>
		<h1>入出庫画面</h1>
	</header>
	<main>
	<form action="EntryExitInfo.action" method="post">
		<input type="hidden" name="siji"> <label>品番 <span>必須</span> <input type="number"
			id="product_no" name="product_no" onChange="doExecute('pnSearch')"
			value="${ProductMasterForEntry.product_no}" oninput="javascript:if(this.value.length>this.maxLength)this.value=this.value.slice(0,this.maxLength);"
			maxlength="10" placeholder="半角数字10桁で入力してください。"></label> <label>品名
			　　　 ${ProductMasterForEntry.product_name}</label><br>
		<div class="radio_div">
			<input type="radio" class="radio" name="radio" id="instore"
				value="instore" disabled>入庫 <input type="radio"
				class="radio" name="radio" id="shipping" value="shipping" disabled>出庫
		</div>
		<br>
		<label>数量 <span>必須</span> <input
			type="number" min="1" class="no_spin" id="count" name="count"
			value="${count}" disabled placeholder="半角数字のみ入力してください。"></label> <label>理由 <span>必須</span> <input type="text"
			class="form-control" id="reason" name="reason" value="" disabled placeholder="全角20文字以内で入力してください。"></label>
		<div class="button">
			<button type="button" id="confirm" onClick="doExecute('confirm')"
				disabled>確定</button>
			<button type="button" onClick="doExecute('reset')">キャンセル</button>
		</div>
		<input type="hidden" name="nextscr2">
	</form>
	</main>
</body>
<script>
	function doExecute(args) {
		var form = document.forms[0];
		form.siji.value = args;
		form.submit();
	}
</script>
<script>
	function scrset2(args) {
		form = document.forms[0];
		form.nextscr2.value = args;
		form.submit()
	}
</script>
<script>
	document.addEventListener('DOMContentLoaded', messageAlert());
	function messageAlert() {
		var recvMSG = "${message}";
		if (recvMSG != null && recvMSG != "") {
			alert(recvMSG);
		}
		scrset();
	}

	function scrset() {
		var recvProductNo = "${ProductMasterForEntry.product_no}";
		var recvReason = "${info.reason}";
		if (recvProductNo != "" || recvProductNo.length > 0) {
			document.getElementById("instore").removeAttribute("disabled");
			document.getElementById("instore").setAttribute("checked", true);
			document.getElementById("shipping").removeAttribute("disabled");
			document.getElementById("count").removeAttribute("disabled");
			document.getElementById("reason").removeAttribute("disabled");
		}
		if (recvReason != "" || recvReason != null) {
			document.getElementById("confirm").removeAttribute("disabled");
		}
	}
</script>
</html>