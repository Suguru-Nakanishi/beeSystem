<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客マスタ</title>

<link rel="stylesheet" href="../css/style2.css">
	<title>顧客マスタ</title>
<style>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button{
-webkit-appearance: none;
margin: 0;
display: inline-block;
}
body#customer_master input{
width: 180px;
}
.button{display: flex;}
</style>
</head>
<body id="customer_master">
	<header>
		<a href="javascript:scrset2('menu')">メニューに戻る</a>
		<a href="javascript:logout()">ログアウト</a>
		<h1>顧客マスタ画面</h1>
	</header>
	<main>
		<form action ="CustomerMaster.action" method="post">
			<label><input type="radio" name="menu" onclick="sinki()">新規</label>
		<label><input type="radio" name="menu" onclick="kousin()">更新</label>
		<label><input type="radio" name="menu" onclick="sakuzyo()">削除</label>
			<label>顧客コード　　　<span>必須</span>：<input type="text" name="customer_no" id="customer_no"
				onChange="doExecute('noSearch')" value="${CustomerMaster.customer_no}" disabled></label>
			<label>会社名　　　　　<span>必須</span>：<input type="text" id="customer_name"
				name="customer_name" value="${CustomerMaster.customer_name}"
				disabled></label>
			<label>支店名　　　　　　　　：<input type="text" id="branch_name" name="branch_name"
				value="${CustomerMaster.branch_name}" disabled></label>
			<label>郵便番号　　　　　　　：<input type="number" id="zip_no" class="no_spin" name="zip_no" value="${CustomerMaster.zip_no}" onchange="doExecute('zipno')"  disabled oninput="javascript:if(this.value.length>this.maxLength)this.value=this.value.slice(0,this.maxLength);" maxlength="7"></label>
			<label>住所（都道府県）　　　：<input type="text" id="address_1" name="address_1" value="${CustomerMaster.address_1}" disabled></label>
			<label>住所（市区町村）　　　：<input type="text" id="address_2" name="address_2" value="${CustomerMaster.address_2}" disabled></label>
			<label>住所（番地以降ビル名）：<input type="text" id="address_3" name="address_3" value="${CustomerMaster.address_3}" disabled></label>
			<label>電話番号　　　　<span>必須</span>：<input type="number" name="tel" value="${CustomerMaster.tel}" placeholder="ハイフンなしで入力してください。" id="tel" disabled oninput="javascript:if(this.value.length>this.maxLength)this.value=this.value.slice(0,this.maxLength);" maxlength="13"></label>
			<label>ＦＡＸ番号　　　　　　：<input type="number" name="fax" value="${CustomerMaster.fax}" name="fax" id="fax" disabled oninput="javascript:if(this.value.length>this.maxLength)this.value=this.value.slice(0,this.maxLength);" maxlength="13"></label>
			<label>担当者　　　　　　　　：<input type="text" name="manager" id="manager" name="manager" value="${CustomerMaster.manager}" disabled></label>
			<label>輸送Ｌ／Ｔ　　　<span>必須</span>：<input type="text" name="delivery_leadtime" id="delivery_leadtime"
				name="delivery_leadtime" value="${CustomerMaster.delivery_leadtime}" disabled></label>
			<label>備　考：<textarea id="etc" name="etc" maxlength="40" rows="3" cols="40" value="${CustomerMaster.etc}" disabled></textarea></label>
			<div class="button">
			<button type="button" onClick="doExecute()">確定</button>
			<button type="button" onClick="doExecute('reset')">キャンセル</button>
			</div>
			<input type="hidden" name="siji" value="${siji}">
			<input type="hidden" name="dousa" value="${dousa}">
			<input type="hidden" name="nextscr2">
		</form>
	</main>
	<footer>&#xa9;&#xa0;2021 Free Style Co.,Ltd. All rights reserved.</footer>
	<script>
	function doExecute(args) {
		var form = document.forms[0];
		if (args == null) {
			form.siji.value = form.dousa.value;
		} else {
			form.siji.value = args;
		}
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
		var recvCustomerNo = "${CustomerMaster.customer_no}";
		var recvDousa = "${dousa}";

		if (recvDousa == "regist" && recvCustomerNo != null) {
			document.getElementById("customer_no").setAttribute("readOnly",
					true);
			document.getElementById("customer_name").removeAttribute(
					"disabled");
			document.getElementById("branch_name").removeAttribute(
					"disabled");
			document.getElementById("zip_no").removeAttribute("disabled");
			document.getElementById("address_1")
					.removeAttribute("disabled");
			document.getElementById("address_2")
					.removeAttribute("disabled");
			document.getElementById("address_3")
					.removeAttribute("disabled");
			document.getElementById("tel").removeAttribute("disabled");
			document.getElementById("fax").removeAttribute("disabled");
			document.getElementById("manager").removeAttribute("disabled");
			document.getElementById("delivery_leadtime").removeAttribute(
					"disabled");
			document.getElementById("etc").removeAttribute("disabled");
		}

		if (recvDousa == "update" && recvCustomerNo != null) {
			document.getElementById("customer_no").removeAttribute(
					"disabled");
			document.getElementById("customer_no").setAttribute("readOnly",
					true);
			document.getElementById("customer_name").removeAttribute(
					"disabled");
			document.getElementById("branch_name").removeAttribute(
					"disabled");
			document.getElementById("zip_no").removeAttribute("disabled");
			document.getElementById("address_1")
					.removeAttribute("disabled");
			document.getElementById("address_2")
					.removeAttribute("disabled");
			document.getElementById("address_3")
					.removeAttribute("disabled");
			document.getElementById("tel").removeAttribute("disabled");
			document.getElementById("fax").removeAttribute("disabled");
			document.getElementById("manager").removeAttribute("disabled");
			document.getElementById("delivery_leadtime").removeAttribute(
					"disabled");
			document.getElementById("etc").removeAttribute("disabled");
		}
		if (recvDousa == "delete" && recvCustomerNo != null) {
			document.getElementById("customer_no").removeAttribute(
					"disabled");
			document.getElementById("customer_name").setAttribute(
					"disabled", true);
			document.getElementById("branch_name").setAttribute("disabled",
					true);
			document.getElementById("zip_no")
					.setAttribute("disabled", true);
			document.getElementById("address_1").setAttribute("disabled",
					true);
			document.getElementById("address_2").setAttribute("disabled",
					true);
			document.getElementById("address_3").setAttribute("disabled",
					true);
			document.getElementById("tel").setAttribute("disabled", true);
			document.getElementById("fax").setAttribute("disabled", true);
			document.getElementById("manager").setAttribute("disabled",
					true);
			document.getElementById("delivery_leadtime").setAttribute(
					"disabled", true);
			document.getElementById("etc").setAttribute("disabled", true);
		}
	}
	function sinki() {
		var form = document.forms[0];
		form.dousa.value = "regist";
		document.getElementById("customer_no").setAttribute("disabled",
				true);
		document.getElementById("customer_name")
				.removeAttribute("disabled");
		document.getElementById("branch_name").removeAttribute("disabled");
		document.getElementById("zip_no").removeAttribute("disabled");
		document.getElementById("address_1").removeAttribute("disabled");
		document.getElementById("address_2").removeAttribute("disabled");
		document.getElementById("address_3").removeAttribute("disabled");
		document.getElementById("tel").removeAttribute("disabled");
		document.getElementById("fax").removeAttribute("disabled");
		document.getElementById("manager").removeAttribute("disabled");
		document.getElementById("delivery_leadtime").removeAttribute(
				"disabled");
		document.getElementById("etc").removeAttribute("disabled");
	}
	function kousin() {
		var form = document.forms[0];
		form.dousa.value = "update";
		document.getElementById("customer_no").removeAttribute("disabled");
		document.getElementById("customer_name").setAttribute("disabled",
				true);
		document.getElementById("branch_name").setAttribute("disabled",
				true);
		document.getElementById("zip_no").setAttribute("disabled", true);
		document.getElementById("address_1").setAttribute("disabled", true);
		document.getElementById("address_2").setAttribute("disabled", true);
		document.getElementById("address_3").setAttribute("disabled", true);
		document.getElementById("tel").setAttribute("disabled", true);
		document.getElementById("fax").setAttribute("disabled", true);
		document.getElementById("manager").setAttribute("disabled", true);
		document.getElementById("delivery_leadtime").setAttribute(
				"disabled", true);
		document.getElementById("etc").setAttribute("disabled", true);
	}
	function sakuzyo() {
		var form = document.forms[0];
		form.dousa.value = "delete";
		document.getElementById("customer_no").removeAttribute("disabled");
		document.getElementById("customer_name").setAttribute("disabled",
				true);
		document.getElementById("branch_name").setAttribute("disabled",
				true);
		document.getElementById("zip_no").setAttribute("disabled", true);
		document.getElementById("address_1").setAttribute("disabled", true);
		document.getElementById("address_2").setAttribute("disabled", true);
		document.getElementById("address_3").setAttribute("disabled", true);
		document.getElementById("tel").setAttribute("disabled", true);
		document.getElementById("fax").setAttribute("disabled", true);
		document.getElementById("manager").setAttribute("disabled", true);
		document.getElementById("delivery_leadtime").setAttribute(
				"disabled", true);
		document.getElementById("etc").setAttribute("disabled", true);
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
	</script>
	<script>
	function logout(){if(confirm('ログアウトしますか？'))location.href='Logout.action'}
	</script>
</body>
</html>