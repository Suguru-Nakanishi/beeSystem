<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/flex.css">
    <script type="text/javascript">
        function doExecute(atype) {
            var form = document.forms[0];
            form.siji.value = atype;
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
        function scr() {
            var recvDousa = "${siji}";
            var recvRadio1 = "${radio1}";
            var recvRadio2 = "${radio2}";

            if (recvDousa == "cancel") {
                document.getElementById("notSelect").setAttribute("checked", true);
                document.getElementById("orderDate").setAttribute("checked", true);
                document.getElementById("orderQTY").setAttribute("disabled", true);
                document.getElementById("date1").setAttribute("disabled", true);
                document.getElementById("date2").setAttribute("disabled", true);
                document.getElementById("deliveryDate").setAttribute("disabled", true);
                document.getElementById("finFlg0").setAttribute("disabled", true);
                document.getElementById("finFlg1").setAttribute("disabled", true);
                document.getElementById("supplierNo").setAttribute("disabled", true);
            }
            if (recvDousa == "") {
                document.getElementById("notSelect").setAttribute("checked", true);
                document.getElementById("orderDate").setAttribute("checked", true);
                document.getElementById("orderQTY").setAttribute("disabled", true);
                document.getElementById("date1").setAttribute("disabled", true);
                document.getElementById("date2").setAttribute("disabled", true);
                document.getElementById("deliveryDate").setAttribute("disabled", true);
                document.getElementById("finFlg0").setAttribute("disabled", true);
                document.getElementById("finFlg1").setAttribute("disabled", true);
                document.getElementById("supplierNo").setAttribute("disabled", true);
            }
        }
    </script>
</head>

<body onload="scr()">

    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <header class="space-between">
            <a class="link" href="javascript:scrset2('menu')">メニューに戻る</a>
            </header>
            <main>
                <div class="flex center">
                    <h1 class="title">発注一覧画面</h1>
                </div>
                <form class="space-between mainbox" action="OrderList.action" method="POST">
                    <input type="hidden" name="siji">
                    <input type="hidden" name="nextscr2">
                    <div class="flex"><span class="product_no">品　番<input class="textbox" name="number" type="text"
                                onchange="doExecute('number')" value="${productNo}"></span>
                        <button class="button" type="button" id="cancel" onclick="doExecute('cancel')">リセット</button>
                    </div>
                    <div class="flex margin">品　名　<span class="product_name">${productName}</span></div>
                    <div class="flex margin">検索条件:　発注日　
                        <input type="text" name="date1" id="date1" class="textbox" placeholder="例:yyyy/MM/dd"
                            onchange="doExecute('date')" value="${dateA}">~
                        <input type="text" name="date2" id="date2" class="textbox" placeholder="例:yyyy/MM/dd"
                            onchange="doExecute('date')" value="${dateB}">
                    </div>
                    <div class="margin">絞り込み:
                        <input type="radio" id="notSelect" name="radio2" value="notSelect"
                            onchange="doExecute('notSelect')">全件表示
                        <input type="radio" id="finFlg0" name="radio2" value="finFlg0"
                            onchange="doExecute('finFlg0')">未納入
                        <input type="radio" id="finFlg1" name="radio2" value="finFlg1"
                            onchange="doExecute('finFlg1')">納入済み
                    </div>
                    <div class="margin">並び替え:
                        <input type="radio" id="orderDate" name="radio1" value="orderDate"
                            onchange="doExecute('orderDate')">発注日
                        <input type="radio" id="deliveryDate" name="radio1" value="deliveryDate"
                            onchange="doExecute('deliveryDate')">納期
                        <input type="radio" id="orderQTY" name="radio1" value="orderQTY"
                            onchange="doExecute('orderQTY')">数量
                        <input type="radio" id="supplierNo" name="radio1" value="supplierNo"
                            onchange="doExecute('supplierNo')">仕入先CD
                    </div>
                </form>
                <table class="margin table" id="table1">
                    <thead>
                        <tr>
                            <th class="textcenter">発注日</th>
                            <th class="textcenter">発注番号</th>
                            <th class="textcenter">納期</th>
                            <th class="textcenter">納入日</th>
                            <th class="textcenter">発注数量</th>
                            <th class="textcenter">仕入先名</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${list2}">
                            <tr>
                                <td class="textcenter">${item.order_date }</td>
                                <td class="textcenter">${item.order_no }</td>
                                <td class="textcenter">${item.delivery_date }</td>
                                <td class="textcenter">${item.due_date }</td>
                                <td class="textcenter">${item.order_qty }</td>
                                <td class="textcenter" title="${item.supplier_no }">${item.supplier_name }</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </main>
            <footer>&#xa9;&#xa0;2021 Free Style Co.,Ltd. All rights reserved.</footer>
    </body>
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

</html>