<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@include file="../html/header.html"%><title>メインメニュー</title></head>
<body id="menu" onload="greeting()">
<header>
	<span>${user}さん。</span>
	<a href="javascript:logout()">ログアウト</a>
	<h1>生産管理システムメニュー画面</h1>
</header>
<main>
	<form action="Main.action" method="post">
		<button type="button" onClick="scrset('purchase_order')">受注処理</button>
		<button type="button" onClick="scrset('entry_exit_info')">入出庫処理</button>
		<button type="button" onClick="scrset('outsourcing')">納入処理</button>
		<button type="button" onClick="scrset('shipment')">出荷処理</button>
		<button type="button" onClick="scrset('stock')">在庫確認</button>
		<button type="button" onClick="scrset('order_list')">発注一覧</button>
		<button type="button" onClick="scrset('purchaselist')">受注一覧</button>
		<button type="button" onClick="scrset('entry_exit_info_list')">入出庫一覧</button>
		<button type="button" onClick="scrset('requirements_calculation')">所要量計算</button>
		<button type="button" onClick="scrset('master_menu')">マスタ登録</button>
		<input type="hidden" name="nextscr">
	</form>
<%@include file="../html/footer.html"%>