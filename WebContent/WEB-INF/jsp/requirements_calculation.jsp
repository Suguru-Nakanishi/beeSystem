<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所要量計算画面</title>
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
<script>
	function scrset2(args){
		form=document.forms[0];
		form.nextscr2.value=args;
		form.submit()
	}
	</script>
<script>
function scr(){
	var recvDousa="${siji}";
	if (recvDousa == "start") {
		document.getElementById("start").setAttribute("disabled",true);
		document.getElementById("cancel").removeAttribute("disabled");
		doExecute('start');
	}
	if (recvDousa == "cancel") {
		document.getElementById("start").removeAttribute("disabled");
		document.getElementById("cnacel").setAttribute("disabled",true);
	}}
</script>
<script>
function sleep() {
    if (form.siji.value == "start") {
        sleep(5000);
    } else {
        return true;
    }
}
</script>
</head>
<body onload="scr()">
 <body>
        <header class="space-between"><a class="link" href="javascript:scrset2('menu')">メニューに戻る</a></header>
        <main>
            <div class="flex center">
                <h1 class="title">所要量計算画面</h1>
            </div>
            <form class="flex center" action="RequirementsCalculation.action" method="POST">
                <input type="hidden" name="siji">
                <input type="hidden" name="nextscr2">
                <button class="button" type="button" id="start" onclick="doExecute('start')">所要量計算開始</button>
                <button class="button" type="button" id="cancel" onclick="doExecute('cancel')" disabled>キャンセル</button>
            </form>
            <div>
                進捗
                <div class="flex center">
                    <div class="flex  result box">
                        ${product}
                    </div>
                </div>
            </div>
        </main>
        <footer>&#xa9;&#xa0;2021 Free Style Co.,Ltd. All rights reserved.</footer>
    </body>
    <script type="text/javascript" charset="utf-8" src="../js/javascript.js"></script>
</html>