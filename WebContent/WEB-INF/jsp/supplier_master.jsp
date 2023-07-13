<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@include file="../html/header.html"%><title>仕入先マスタ</title></head>
<body onload="scrset()" id="suppplier"><!-- body要素にonloadイベントを設定 -->
<header>
	<a href="javascript:scrset1('menu')">メニューに戻る</a>
	<a href="Logout.action">ログアウト</a>
	<h1>仕入先マスタ</h1>
</header>
<main>
<form action="SupplierMaster.action" method="post">
	<label><input type ="radio" id="1" value="1" name="menu" onClick="sinki()">新規</label>
	<label><input type ="radio" id="2" value="2" name="menu" onClick="kousin()">更新</label>
	<label><input type ="radio" id="3" value="3" name="menu" onClick="sakujo()">削除</label>
	<label>仕入先コード　　　　　：<input type="text" name="supplier_no" id="supplier_no" value="${sm.supplier_no}" onchange="doExecute('idSearch')" placeholder="更新、削除の場合 6桁の半角数字を入力"disabled></label>
	<label>会社名　　　　　<span>必須</span>：<input type="text" name="supplier_name" id="supplier_name" value="${sm.supplier_name}" disabled></label>
	<label>支店名　　　　　　　　：<input type="text" name="branch_name" id="branch_name" value="${sm.branch_name}" disabled></label>
	<label>郵便番号　　　　　　　：<input type="number" name="zipno" id="zipno" value="${sm.zipno}" maxlength="7" onchange="doExecute('zipno')" disabled></label>
	<label>住所（都道府県）　　　：<input type="text" name="address_1" id="address_1" value="${sm.address_1 }" disabled></label>
	<label>住所（市区町村）　　　：<input type="text" name="address_2" id="address_2" value="${sm.address_2}" disabled></label>
	<label>住所（番地以降ビル名）：<input type="text" name="address_3" id="address_3" value="${sm.address_3}" disabled></label>
	<label>電話番号　　　　<span>必須</span>：<input type="text" pattern="\d{1,15}" name="tel" id="tel" value="${sm.tel}" maxlength="15" disabled></label>
	<label>ＦＡＸ番号　　　　　　：<input type="text" class="form-control" pattern="\d{1,15}" name="fax" id="fax" value="${sm.fax}" maxlength="15" disabled></label>
	<label>担当者　　　　　　　　：<input type="text" name="manager" id="manager" value="${sm.manager}" disabled></label>
	<label>備　考：<textarea name="etc" id="etc" rows="3" cols="40" maxlength="40" placeholder="コメントはこちらに入力してください。(全角半角30文字以内)" disabled></textarea></label>
	<button type ="button" onClick="doExecute()">確定</button>
	<button type ="button" onClick="doExecute('clear')">キャンセル</button>
	<input type="hidden" name="siji">
	<input type="hidden" name="dousa" value="${dousa}">
	<input type="hidden" name="nextscr">
</form>
</main>
 <footer>&#xa9;&#xa0;2021 Free Style Co.,Ltd. All rights reserved.</footer>

</body>
<script>
	function doExecute(args) {
		var form = document.forms[0];
		if(args==null){
			form.siji.value =form.dousa.value;
		}else{
		form.siji.value = args;
	}
		form.submit();
	}
</script>
<script>

function checkRadio() {
var sel = "${menu}";
var form = document.getElementsByName("menu")
for (i = 0; i < form.length; i++) {
if (form[i].value == sel) {
form[i].checked = true;
				}
		}
}
</script>
<script>
	document.addEventListener('DOMContentLoaded', messageAlert());
	//　DOMContentLoaded→画面を読み終わったらmessageAlert()ファンクションを呼ぶ
	function messageAlert() {
		var recvMSG = "${message}";
		if (recvMSG != null && recvMSG != "") {
			alert(recvMSG);
		}
		scrset();
	}

function scrset(){
	var recvSupplier_no = "${sm.supplier_no}";
	var recvSupplier_name = "${sm.supplier_name}";
	var recvBranch_name = "${sm.branch_name}";
	var recvZipno = "${sm.zipno}";
	var recvaddress_1 = "${sm.address_1}";
	var recvaddress_2 ="${sm.address_2}";
	var recvaddress_3 ="${sm.address_3}";
	var recvTel = "${sm.tel}";
	var recvFax = "${sm.fax}";
	var recvManager = "${sm.manager}";
	var recvEtc = "${sm.etc}";
	var recvDousa = "${dousa}";
 //id検索

 //更新（ボタン）
	if (recvDousa == "update" && recvSupplier_no != null
			&& recvSupplier_name != null) {
		document.getElementById("supplier_no").removeAttribute("disabled");
		//removeAttribute属性の変更　disabled　不活性 送信不可
		document.getElementById("supplier_no").setAttribute("readOnly", true);
		//setAttribute属性の追加　readOnly書き換え不可送信可能　
		document.getElementById("supplier_name").removeAttribute("disabled");
		document.getElementById("branch_name").removeAttribute("disabled");
		document.getElementById("zipno").removeAttribute("disabled");
		document.getElementById("address_1").removeAttribute("disabled");
		document.getElementById("address_2").removeAttribute("disabled");
		document.getElementById("address_3").removeAttribute("disabled");
		document.getElementById("tel").removeAttribute("disabled");
		document.getElementById("fax").removeAttribute("disabled");
		document.getElementById("manager").removeAttribute("disabled");
		document.getElementById("etc").removeAttribute("disabled");

	}
	//新規（ボタン）
	if (recvDousa == "regist"
			&& recvSupplier_name != null) {
		//setAttribute属性の追加　readOnly書き換え不可送信可能　
		document.getElementById("supplier_name").removeAttribute("disabled");
		document.getElementById("branch_name").removeAttribute("disabled");
		document.getElementById("zipno").removeAttribute("disabled");
		document.getElementById("address_1").removeAttribute("disabled");
		document.getElementById("address_2").removeAttribute("disabled");
		document.getElementById("address_3").removeAttribute("disabled");
		document.getElementById("tel").removeAttribute("disabled");
		document.getElementById("fax").removeAttribute("disabled");
		document.getElementById("manager").removeAttribute("disabled");
		document.getElementById("etc").removeAttribute("disabled");

	}
	//削除 (ボタン)
	if (recvDousa == "delete" && recvSupplier_no != null
			&& recvSupplier_name != null) {
		document.getElementById("supplier_no").removeAttribute("disabled");
		//removeAttribute属性の変更　disabled　不活性 送信不可
		document.getElementById("supplier_no").setAttribute("readOnly", true);
		//setAttribute属性の追加　readOnly書き換え不可送信可能　
		document.getElementById("supplier_name").removeAttribute("readOnly");
		document.getElementById("branch_name").removeAttribute("readOnly");
		document.getElementById("zipno").removeAttribute("readOnly");
		document.getElementById("address_1").removeAttribute("readOnly");
		document.getElementById("address_2").removeAttribute("readOnly");
		document.getElementById("address_3").removeAttribute("readOnly");
		document.getElementById("tel").removeAttribute("readOnly");
		document.getElementById("fax").removeAttribute("readOnly");
		document.getElementById("manager").removeAttribute("readOnly");
		document.getElementById("etc").removeAttribute("readOnly");

			}
	checkRadio() ;
}
</script>
<script>
	//新規登録
	function sinki() {
		var form = document.forms[0];
		form.dousa.value = "regist";
		document.getElementById("supplier_no").removeAttribute("readOnly");
		//removeAttribute属性の変更　readOnly書き換え不可送信可能
		document.getElementById("supplier_no").setAttribute("disabled",true);
		//setAttribute属性の追加　　disabled　不活性 送信不可
		document.getElementById("supplier_name").removeAttribute("disabled");
		document.getElementById("branch_name").removeAttribute("disabled");
		document.getElementById("zipno").removeAttribute("disabled");
		document.getElementById("address_1").removeAttribute("disabled");
		document.getElementById("address_2").removeAttribute("disabled");
		document.getElementById("address_3").removeAttribute("disabled");
		document.getElementById("tel").removeAttribute("disabled");
		document.getElementById("fax").removeAttribute("disabled");
		document.getElementById("manager").removeAttribute("disabled");
		document.getElementById("etc").removeAttribute("disabled");
 }

	//更新
	function kousin() {
		var form = document.forms[0];
		form.dousa.value = "update";
		document.getElementById("supplier_no").removeAttribute("disabled");
		//removeAttribute属性の変更　readOnly書き換え不可送信可能
		//setAttribute属性の追加　　disabled　不活性 送信不可
		document.getElementById("supplier_name").setAttribute("disabled",true);
		document.getElementById("branch_name").setAttribute("disabled",true);
		document.getElementById("zipno").setAttribute("disabled",true);
		document.getElementById("address_1").setAttribute("disabled",true);
		document.getElementById("address_2").setAttribute("disabled",true);
		document.getElementById("address_3").setAttribute("disabled",true);
		document.getElementById("tel").setAttribute("disabled",true);
		document.getElementById("fax").setAttribute("disabled",true);
		document.getElementById("manager").setAttribute("disabled",true);
		document.getElementById("etc").setAttribute("disabled",true);
 }
 //削除
	function sakujo() {
		var form = document.forms[0];
		form.dousa.value = "delete";
		document.getElementById("supplier_no").removeAttribute("disabled");
		//removeAttribute属性の変更　readOnly書き換え不可送信可能
		//setAttribute属性の追加　　disabled　不活性 送信不可
		document.getElementById("supplier_name").setAttribute("disabled",true);
		document.getElementById("branch_name").setAttribute("disabled",true);
		document.getElementById("zipno").setAttribute("disabled",true);
		document.getElementById("address_1").setAttribute("disabled",true);
		document.getElementById("address_2").setAttribute("disabled",true);
		document.getElementById("address_3").setAttribute("disabled",true);
		document.getElementById("tel").setAttribute("disabled",true);
		document.getElementById("fax").setAttribute("disabled",true);
		document.getElementById("manager").setAttribute("disabled",true);
		document.getElementById("etc").setAttribute("disabled",true);
 }
</script>



<!-- 	<script type="text/javascript" charset="utf-8" src="../js/javascript.js"></script>--->
	<script>
		//document.addEventListener('DOMContentLoaded',messageAlert('${message}'));
		function scrset1(args){
			form=document.forms[0];
			form.nextscr.value=args;
			form.submit()
		}
	</script>
</html>