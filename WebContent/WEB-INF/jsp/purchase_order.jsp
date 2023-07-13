<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@include file="../html/header.html"%><title>受注画面</title></head>
<body id="purchase_order" onload="dateset()">
<header>
	<a href="javascript:scrset2('menu')">メニューに戻る</a>
	<h1>受注画面</h1>
</header>
<main>
	<form action ="PurchaseOrder.action" method="POST">
		<input type = "hidden" name="siji">
		<input type="hidden" name="dousa" value="${dousa}">
		<input type = "hidden" name="nextscr">
		<label>顧客コード：<input type="text" id="cm_customer_no" name="cm_customer_no" placeholder="記入例) A0100" value="${purchase.cm_customer_no }" onchange="doExecute('idSearch')" ></label>
		<label>顧客名　　：<input type="text" id="cm_customer_name" name="cm_customer_name" placeholder="顧客コード入力後自動表示されます。"  value="${purchase.cm_customer_name }"disabled></label>
		<label>品番　　　：<input type="text" id="pm_product_no" name="pm_product_no" placeholder="10桁の半角数字を入力してください。" value="${purchase.pm_product_no }" onchange="doExecute('cnSearch')" disabled></label>
		<label>品名　　　：<input type="text" id="pm_product_name" name="pm_product_name" placeholder="品番入力後自動表示されます。"  value="${purchase.pm_product_name }" disabled></label>
		<label>納期　　　：<input type="date" id="dat" name="delivery_date" value="${purchase.delivery_date }" onblur="dodate()" disabled></label>
		<label>数量　　　：<input type="text" id="order_qty" name="order_qty" placeholder="１より大きな数字を入力してください。" value="${purchase.order_qty }" onchange="doqty()" disabled></label>
		<label>備　考：<textarea id="etc" name="etc" placeholder="コメントはこちらに記述してください。" value="${product.etc }" rows="10"  cols="50" disabled></textarea></label>
		<button type="button" onClick="doExecute('insert')">登録</button>
		<button type="button" onClick="doExecute('reset')">キャンセル</button>
	</form>
</main>
<footer>&#xa9;&#xa0;beeSystem</footer>
<script>
function doExecute(args) {
		var form = document.forms[0];

		if(args == null){
			form.siji.value = form.dousa.value;

		}else{
		form.siji.value = args;
		}

		if(args!="reset") {
         if(document.getElementById("pm_product_name").value !==""&& doqty()==1) {
        	 return
         }
		}
		document.getElementById("pm_product_name").removeAttribute("disabled");
		document.getElementById("pm_product_name").setAttribute("readonly",true);
		document.getElementById("cm_customer_name").removeAttribute("disabled");
		document.getElementById("cm_customer_name").setAttribute("readonly",true);

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
	scrset();
}

function scrset() {
	var recvCustomerName = "${purchase.cm_customer_name}";
	var recvProductName = "${purchase.pm_product_name}";


	if ((recvCustomerName != null && recvCustomerName != "")) {
		document.getElementById("cm_customer_no").removeAttribute("disabled");
		document.getElementById("pm_product_no").removeAttribute("disabled");


	}
	if ((recvProductName != null && recvProductName != "")) {
		document.getElementById("cm_customer_no").removeAttribute("disabled");
		document.getElementById("pm_product_no").removeAttribute("disabled");
		document.getElementById("dat").removeAttribute("disabled");
		document.getElementById("order_qty").removeAttribute("disabled");
		document.getElementById("etc").removeAttribute("disabled");

	}
}


 </script>
 <script>
 function dateset() {
                var today = new Date();
                var todayValue = today.getFullYear() + "-" + addZero(today.getMonth() + 1) + "-" + addZero(today.getDate())
                var cale = document.getElementById("dat");
                cale.setAttribute("min", todayValue);
                cale.value = todayValue;

            }
 function addZero(hikisu) {
     if (hikisu < 10) {
         return "0" + hikisu;
     }
     return hikisu;
 }
 function dodate(){
	  var today = new Date();
      var todayValue = today.getFullYear() + "-" + addZero(today.getMonth() + 1) + "-" + addZero(today.getDate())
	 if(document.getElementById("dat").value <todayValue){
			alert("過去日は入力できません。");
	 }
 }
 function doqty(){
	 if(document.getElementById("order_qty").value <1){
			alert("数量は1以上を入力してください。");
			return 1;
 }
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
</body>
</html>