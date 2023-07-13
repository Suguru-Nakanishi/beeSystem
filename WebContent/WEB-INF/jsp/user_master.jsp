<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@include file="../html/header.html"%><title>ユーザーマスタ</title>
</head>
<body id="user_master" onload="dataSet()">
<header>
	<a href="javascript:scrset('menu')">メニューに戻る</a>
	<a href="javascript:logout()">ログアウト</a>
	<h1>ユーザーマスタ</h1>
</header>
<main>
	<form action ="UserMaster.action" method="post">
		<label>新規<input></label>
		<label>更新<input></label>
		<label>削除<input></label>
		<label>ユーザーＩＤ<span>必須</span>：<input type="text" name="user_id" value="${usr.user_id}"></label>
		<label>ユーザー名　<span>必須</span>：<input type="text" name="name" maxlength="50" placeholder="全角入力、姓と名の間のスペースは不要" value="${usr.name}"></label>
		<label>パスワード　<span>必須</span>：<input type="text" name="password" minlength="8" maxlength="20" placeholder="半角英数8文字以上、英大･小各1文字以上" value="${usr.password}"></label>
		<label>部署名　　　<span>必須</span>：<input type="text" name="dept" placeholder="営業部･調達部･倉庫管理部のいずれか" value="${usr.dept}"></label>
		<label>入社日　　　<span>必須</span>：<input type="text" name="hire_date" maxlength="10" placeholder="yyyy/mm/dd" value="${usr.hire_date}"></label>
		<label>備　考：<textarea name="etc" maxlength="40" rows="3" cols="40" placeholder="コメントはこちらに入力してください。(全角半角30文字以内)" value="${usr.etc}"></textarea></label>
		<input type="hidden" name="siji" value="${siji}">
		<input type="hidden" name="nextscr">
	</form>
<%@include file="../html/footer.html"%>