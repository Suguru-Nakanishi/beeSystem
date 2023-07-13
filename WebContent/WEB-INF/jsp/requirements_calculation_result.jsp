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
        document.addEventListener('DOMContentLoaded', messageAlert());
        function messageAlert() {
            var recvMSG = "${message}";

            if (recvMSG != null && recvMSG != "") {
                alert(recvMSG);
            }
        }
    </script>
</head>

<body onload="scr()">
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <header class="space-between"><a href=""></a></header>
            <main>
                <div class="flex center">
                    <h1 class="title">所要量計算結果</h1>
                </div>
                <form class="flex center" action="RequirementsCalculation.action" method="POST">
                    <input type="hidden" name="siji">
                    <button class="button" type="button" id="start" onclick="doExecute('confirm')">発注</button>
                    <button class="button" type="button" id="cancel" onclick="doExecute('cancel')">キャンセル</button>
                </form>
                <table class="margin table" id="table1">
                    <thead>
                        <tr>
                            <th class="textcenter">品番</th>
                            <th class="textcenter">商品名</th>
                            <th class="textcenter">納期</th>
                            <th class="textcenter">発注数量</th>
                            <th class="textcenter">仕入先番号</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${result}">
                            <tr>
                                <td>${item.product_no }</td>
                                <td>${item.product_name }</td>
                                <td>${item.delivery_date }</td>
                                <td>${item.result }</td>
                                <td>${item.supplier_no }</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
            <footer>&#xa9;&#xa0;2021 Free Style Co.,Ltd. All rights reserved.</footer>
    </body>
    <script type="text/javascript" charset="utf-8" src="../js/javascript.js"></script>
</html>