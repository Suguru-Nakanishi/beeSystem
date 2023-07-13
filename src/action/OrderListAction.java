package action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderList;
import dao.OrderListDAO;
import tool.Action;

public class OrderListAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String order = request.getParameter("siji");
		String radio1 = "";
		String radio2 = "";
		String nextscr = request.getParameter("nextscr2");
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;
		if (request.getParameter("radio1") != null) {
			radio1 = request.getParameter("radio1");
		}
		if (request.getParameter("radio2") != null) {
			radio2 = request.getParameter("radio2");
		}
		OrderListDAO dao = new OrderListDAO();
		List<OrderList> list=null;
		if (session.getAttribute("table") == null) {
			list = new ArrayList<>();
		} else {
			list = (List<OrderList>) session.getAttribute("table");
		}
		String productNo = null;
		if (request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
			productNo = request.getParameter("number");
		} else {
			order="cancel";
			request.setAttribute("siji", order);
			session.removeAttribute("productNo");
			return "order_list";
		}
		switch (order) {
		case "number":
			list = dao.getOrderPlane(productNo);
			if (list.size() == 0) {
				request.setAttribute("message", "入力された品番に該当する商品はありません。");
				session.removeAttribute("table");
				session.removeAttribute("dateA");
				session.removeAttribute("dateB");
				session.removeAttribute("productNo");
				session.removeAttribute("productName");
				request.setAttribute("siji", "cancel");
			} else {
				String name = list.get(0).getProduct_name();
				if(list != null && !list.isEmpty()) {
					name="";
				}
				session.setAttribute("productName", name);
				session.setAttribute("productNo", productNo);
				session.setAttribute("table", list);
				request.setAttribute("list2", list);
			}
			break;
		case "date":
			String test1 = request.getParameter("date1");
			String test2 = request.getParameter("date2");
			System.out.println(test1);
			System.out.println(test2);
			if ((request.getParameter("date1") == null || request.getParameter("date1").isEmpty()) &&
					(request.getParameter("date2") == null || request.getParameter("date2").isEmpty())) {
				list = dao.getOrderPlane(productNo);
				session.removeAttribute("dateA");
				session.removeAttribute("dateB");
				session.setAttribute("table", list);
				request.setAttribute("list2", list);
			} else if (request.getParameter("date1") != null && !request.getParameter("date1").isEmpty() &&
					request.getParameter("date2") != null && !request.getParameter("date2").isEmpty()) {
				String date1 = request.getParameter("date1");
				String date2 = request.getParameter("date2");
				if (checkDate(date1) == true && checkDate(date2) == true) {
					list = dao.getOrderDateBetween(productNo, date1, date2);
					session.setAttribute("table", list);
					session.setAttribute("dateA", date1);
					session.setAttribute("dateB", date2);
					request.setAttribute("list2", list);
				} else {
					request.setAttribute("message", "yyyy/MM/dd方式で日付を入力して下さい。");
				}
			} else if (request.getParameter("date1") != null && !request.getParameter("date1").isEmpty() ||
					request.getParameter("date2") == null || request.getParameter("date2").isEmpty()) {
				String date = request.getParameter("date1");
				if (checkDate(date) == true) {
					list = dao.getOrderDate1(productNo, date);
					session.setAttribute("table", list);
					session.setAttribute("dateA", date);
					session.removeAttribute("dateB");
					request.setAttribute("list2", list);
				} else {
					request.setAttribute("message", "yyyy/MM/dd方式で日付を入力して下さい。");
				}
			} else if (request.getParameter("date1") == null || request.getParameter("date1").isEmpty() ||
					request.getParameter("date2") != null && !request.getParameter("date2").isEmpty()) {
				String date = request.getParameter("date2");
				if (checkDate(date) == true) {
					list = dao.getOrderDate2(productNo, date);
					session.setAttribute("table", list);
					session.setAttribute("dateB", date);
					session.removeAttribute("dateA");
					request.setAttribute("list2", list);
				} else {
					request.setAttribute("message", "yyyy/MM/dd方式で日付を入力して下さい。");
				}
			}
			break;
		case "cancel":
			session.removeAttribute("table");
			session.removeAttribute("dateA");
			session.removeAttribute("dateB");
			session.removeAttribute("productNo");
			session.removeAttribute("productName");
			request.setAttribute("siji", "cancel");
			return "order_list";
		default:
		}
		switch (radio1) {
		case "orderDate":
			list = list.stream().sorted(Comparator.comparing(OrderList::getOrder_date)).collect(Collectors.toList());
			break;
		case "deliveryDate":
			list = list.stream().sorted(Comparator.comparing(OrderList::getDelivery_date)).collect(Collectors.toList());
			break;
		case "orderQTY":
			list = list.stream().sorted(Comparator.comparing(OrderList::getOrder_qty)).collect(Collectors.toList());
			break;
		case "supplierNo":
			list = list.stream().sorted(Comparator.comparing(OrderList::getSupplier_no)).collect(Collectors.toList());
			break;
		default:
		}
		switch (radio2) {
		case "finFlg0":
			list = list.stream().filter(fin -> fin.getFin_flg() == 0).collect(Collectors.toList());
			break;
		case "finFlg1":
			list = list.stream().filter(fin -> fin.getFin_flg() == 1).collect(Collectors.toList());
			break;
		default:
		}

		request.setAttribute("siji", order);
		request.setAttribute("radio1", radio1);
		request.setAttribute("radio2", radio2);
		request.setAttribute("list2", list);
		return "order_list";
	}

	public boolean checkDate(String date) {
		if (date == null || date.length() != 10) {
			return false;
		}
		DateFormat format = DateFormat.getDateInstance();
		format.setLenient(false);
		try {
			format.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
