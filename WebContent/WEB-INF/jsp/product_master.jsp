<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<link rel="stylesheet" href="../css/style2.css">
	<title>品番マスタ</title>
<style>
input::placeholder {
font-size: 10px
}
</style>
</head>
<body id="product_master" onload="scrset()">
<header>
	<a href="javascript:scrset2('menu')">メニューに戻る</a>
	<a href="javascript:logout()">ログアウト</a>
	<h1>品番マスタ</h1>
</header>
<main>
	<form action ="ProductMaster.action" method="POST">

		<label><input type="radio" name="menu" onclick="sinki()">新規</label>
		<label><input type="radio" name="menu" onclick="kousin()">更新</label>
		<label><input type="radio" name="menu" onclick="sakujo()">削除</label>
			<label>品番　　　　　　　<input type="text" id="product_no" name="product_no" placeholder="10桁の半角数字を入力してください。" value="${product.product_no}" onchange="doExecute('idSearch')" disabled></label>
			<label>品名　　　　<span>必須</span><input type="text" id="product_name" name="product_name" placeholder="例) 国産アカシア蜂蜜500g" value="${product.product_name}" disabled></label>
			<label>仕入先コード<span>必須</span><input type="text" id="sm_supplier_no" name="sm_supplier_no" placeholder="6桁の半角数字を入力してください。" value="${product.sm_supplier_no}" onchange="doExecute('snSearch')" disabled></label>
			<label>仕入先名　　　　　<input type="text" id="sm_supplier_name" name="sm_supplier_name" placeholder="仕入先コード入力後自動表示されます。"  value="${product.sm_supplier_name }"disabled ></label>
			<label>仕入単価　　<span>必須</span><input type="text" id="unitprice" name="unitprice" value="${product.unitprice}" disabled></label>
			<label>売価　　　　　　　<input type="text" id="sellingprice" name="sellingprice" value="${product.sellingprice}" disabled></label>
			<label>輸送Ｌ／Ｔ　<span>必須</span><input type="text" id="leadtime" name="leadtime" placeholder="１より大きな数字を入力してください。" value="${product.leadtime}" disabled></label>
			<label>購買ロット　<span>必須</span><input type="text" id="lot" name="lot" placeholder="１より大きな数字を入力してください。" value="${product.lot}" disabled></label>
			<label>在庫ロケ　　　　　<input type="text" id="location" name="location" value="${product.location}" disabled></label>
			<label>基本在庫　　<span>必須</span><input type="text" id="basestock" name="basestock" value="${product.basestock}" disabled></label>
			<label>備　考<textarea id="etc" name="etc" placeholder="コメントはこちらに入力してください。(全角半角30文字以内)" value="${product.etc}" rows="3" cols="40" disabled></textarea></label>

			<!--<div class="button">-->
			<button type="button" onClick="doExecute()">確定</button>
			<button type="button" onClick="doExecute('reset')">キャンセル</button>
			<input type = "hidden" name="siji"  value="${siji}">
	 <input type="hidden" name="dousa" value="${dousa}">
	 <input type = "hidden" name="nextscr">
	</form>
	</main>
	<footer>&#xa9;&#xa0;2021 Free Style Co.,Ltd. All rights reserved.</footer>



<script>
function doExecute(args) {
		var form = document.forms[0];

		if(args == null){
			form.siji.value = form.dousa.value;

		}else{
		form.siji.value = args;
		}
		if(args!="reset") {
			if(args != "snSearch"){
		if(document.getElementById("product_name").value !="" && document.getElementById("leadtime").value <1){
			alert("リードタイムは1以上を入力してください。");
			return;
		}else if(document.getElementById("product_name").value !="" && document.getElementById("lot").value <1){
			alert("購買ロットは1以上を入力してください。");
			return;
		}else {
		document.getElementById("sm_supplier_name").removeAttribute("disabled");
		document.getElementById("sm_supplier_name").setAttribute("readonly",true);
		}}}
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
<script>
function scrset() {
	var recvProductName = "${product.product_name}";
	var recvDousa = "${dousa}";

	if (recvDousa == "update" && (recvProductName != null && recvProductName != "")) {
		document.getElementById("product_no").removeAttribute("disabled");
		document.getElementById("product_name").removeAttribute("disabled");
		document.getElementById("sm_supplier_no").removeAttribute("disabled");
		document.getElementById("unitprice").removeAttribute("disabled");
		document.getElementById("sellingprice").removeAttribute("disabled");
		document.getElementById("leadtime").removeAttribute("disabled");
		document.getElementById("lot").removeAttribute("disabled");
		document.getElementById("location").removeAttribute("disabled");
		document.getElementById("basestock").removeAttribute("disabled");
		document.getElementById("etc").removeAttribute("disabled");
	}


	if (recvDousa == "insert" ) {
		document.getElementById("product_no").setAttribute("disabled",true);
		document.getElementById("product_name").removeAttribute("disabled");
		document.getElementById("sm_supplier_no").removeAttribute("disabled");
		document.getElementById("unitprice").removeAttribute("disabled");
		document.getElementById("sellingprice").removeAttribute("disabled");
		document.getElementById("leadtime").removeAttribute("disabled");
		document.getElementById("lot").removeAttribute("disabled");
		document.getElementById("location").removeAttribute("disabled");
		document.getElementById("basestock").removeAttribute("disabled");
		document.getElementById("etc").removeAttribute("disabled");
	}
	if (recvDousa == "delete" || recvDousa == "update") {
		document.getElementById("product_no").removeAttribute("disabled");

	}
}
function sinki() {
	var form = document.forms[0];
	form.dousa.value = "insert";
	document.getElementById("product_no").setAttribute("disabled",true);
	document.getElementById("product_name").removeAttribute("disabled");
	document.getElementById("sm_supplier_no").removeAttribute("disabled");
	document.getElementById("unitprice").removeAttribute("disabled");
	document.getElementById("sellingprice").removeAttribute("disabled");
	document.getElementById("leadtime").removeAttribute("disabled");
	document.getElementById("lot").removeAttribute("disabled");
	document.getElementById("location").removeAttribute("disabled");
	document.getElementById("basestock").removeAttribute("disabled");
	document.getElementById("etc").removeAttribute("disabled");
}
function kousin() {
	var form = document.forms[0];
	form.dousa.value = "update";
	document.getElementById("product_no").removeAttribute("disabled");
	document.getElementById("product_name").setAttribute("disabled",true);
	document.getElementById("sm_supplier_no").setAttribute("disabled",true);
	document.getElementById("unitprice").setAttribute("disabled", true);
	document.getElementById("sellingprice").setAttribute("disabled", true);
	document.getElementById("leadtime").setAttribute("disabled", true);
	document.getElementById("lot").setAttribute("disabled", true);
	document.getElementById("location").setAttribute("disabled", true);
	document.getElementById("basestock").setAttribute("disabled", true);
	document.getElementById("etc").setAttribute("disabled", true);
}
function sakujo(){
	var form = document.forms[0];
	form.dousa.value="delete";
	document.getElementById("product_no").removeAttribute("disabled");
	document.getElementById("product_name").setAttribute("disabled",true);
	document.getElementById("sm_supplier_no").setAttribute("disabled",true);
	document.getElementById("unitprice").setAttribute("disabled",true);
	document.getElementById("sellingprice").setAttribute("disabled",true);
	document.getElementById("leadtime").setAttribute("disabled",true);
	document.getElementById("lot").setAttribute("disabled",true);
	document.getElementById("location").setAttribute("disabled",true);
	document.getElementById("basestock").setAttribute("disabled",true);
	document.getElementById("etc").setAttribute("disabled",true);
}
function scrset2(args){
	form=document.forms[0];
	form.nextscr.value=args;
	form.submit()
}
function doExecute2(args){
	form=document.forms[0];
	form.siji.value=args==null?form.siji.value:args;
	form.submit();
}
</script>
<script>
	function logout(){if(confirm('ログアウトしますか？'))location.href='Logout.action'}
	</script>
</body>
</html>
