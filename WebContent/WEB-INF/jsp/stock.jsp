<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@include file="../html/header.html"%><title>在庫確認画面</title></head>
<body id="stock" onload="stockSet()">
<header>
	<a href="javascript:scrset('menu')">メニューに戻る</a>
	<h1>在庫確認画面</h1>
</header>
<main>
	<form action ="Stock.action" method="POST">
		<label>品番<span>必須</span>：<input type="text" name="product_no" maxlength="10" value="${product_no}"></label>
		<p>品名　　　：${product_name}<c:if test="${!empty message}"><span>${message}</span></c:if></p>
		<input type="hidden" name="company_no">
		<input type="hidden" name="siji">
		<input type="hidden" name="nextscr">
		<input type="hidden" name="product_name" value="${product_name}">
	</form>
<c:if test="${not empty info and not empty list}"><ul>
		<li>${info.company_name}</li>
		<li>${info.branch_name}</li>
		<li>${info.zip_no}</li>
		<li>${info.address}</li>
		<li><span>${info.tel}</span><span>${info.fax}</span></li>
		<li>${info.manager}</li>
	</ul></c:if>
<c:if test="${not empty list}"><table>
		<caption>在庫確認</caption>
		<thead>
			<tr>
				<th>年月日</th>
				<th>顧客先</th>
				<th>受注番号</th>
				<th>受注数</th>
				<th>発注先</th>
				<th>発注数</th>
				<th>在庫数</th>
			</tr>
		</thead>
		<tbody>
<c:forEach var="p" items="${list}"><tr>
			<td>${p.order_date}</td>
			<td>${p.customer_no}</td>
			<td>${p.order_no}</td>
			<td>${p.purchase_order_qty}</td>
			<td>${p.supplier_no}</td>
			<td>${p.order_qty}</td>
			<td>${p.stock_qty}</td>
</tr></c:forEach>
		</tbody>
	</table></c:if><%@include file="../html/footer1.html"%>