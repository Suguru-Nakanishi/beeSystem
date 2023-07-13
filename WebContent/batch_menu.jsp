<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<title>バッチメニュー</title>
</head>
<body id="menu">
<header>
	<span>${user}さんようこそ</span>
	<a href="javascript:logout()">ログアウト</a>
	<h1>生産管理システムメニュー面</h1>
</header>
<main>
	<form action="action.ProductCheck.action" method="post">
		<button type="submit" >品番マスタ在庫マスタ照合</button>
	</form>
