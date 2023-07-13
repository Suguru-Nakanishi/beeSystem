<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../html/header.html"%>
<title>マスタメニュー</title>
</head>
<body id="master_menu">
<header>
	<a href="javascript:scrset('menu')">メニューに戻る</a>
	<a href="javascript:logout()">ログアウト</a>
	<h1>生産管理マスタメニュー画面</h1>
</header>
<main>
	<form action="Main.action" method="post">
		<button type="button" onClick="scrset('product_master')">品番マスタ</button>
		<button type="button" onClick="scrset('customer_master')">顧客先マスタ</button>
		<button type="button" onClick="scrset('supplier_master')">仕入先マスタ</button>
		<button type="button" onClick="scrset('user_master')">ユーザマスタ</button>
		<input type="hidden" name="nextscr">
	</form>
<%@include file="../html/footer.html"%>