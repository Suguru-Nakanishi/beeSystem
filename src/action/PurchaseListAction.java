package action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductMaster;
import bean.PurchaseList;
import dao.ProductMasterDAO;
import dao.PurchaseListDAO;
import tool.Action;
import tool.DateUtils;

public class PurchaseListAction extends Action {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//セッション取得
		HttpSession session = request.getSession();
		String nextscr = request.getParameter("nextscr");
		//ビーンのインスタンス化
		List<PurchaseList> list = new ArrayList<>();
		//DAOのインスタンス化
		PurchaseListDAO dao = new PurchaseListDAO();
		//JSPからのラジオボタンの情報を受け取る。
		String radio1 = request.getParameter("radio1"); //絞り込み要素
		String radio2 = request.getParameter("radio2"); //並べ替え要素
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;

		//JSPにproduct_noが入力されていれば処理を行う。
		String product_no = null;
		if (request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
			product_no = request.getParameter("number");
			ProductMasterDAO pmdao = new ProductMasterDAO();
			ProductMaster spn = pmdao.searchProductNo(product_no);
			if (spn == null) {
				request.setAttribute("message", "入力された品番は存在しません。");
				session.setAttribute("product_no", product_no);
			} else {
				session.setAttribute("product_name", spn.getProduct_name());
				session.setAttribute("product_no", product_no);
				list = dao.getOrderPlane(product_no);
					if (list.size() == 0) {
						request.setAttribute("message", "入力された品番に該当する受注情報はありません。");
					} else {
						session.setAttribute("list", list);
					}
			}
		} else {
			clear(session, request);
			radio1 = "";
			radio2 = "";
		}
		//入力された日付条件で判断して以下の処理を行う。
		String test1 = request.getParameter("date1");
		String test2 = request.getParameter(
				"date2");
		System.out.println(test1);
		System.out.println(test2);
		if ((request.getParameter("date1") == null || request.getParameter("date1").isEmpty())
				&& (request.getParameter("date2") == null || request.getParameter("date2").isEmpty())) {
			list = dao.getOrderPlane(product_no);
			session.removeAttribute("date1");
			session.removeAttribute("date2");
			session.setAttribute("list", list);
		} else if (request.getParameter("date1") != null && !request.getParameter("date1").isEmpty()
				&& request.getParameter("date2") != null && !request.getParameter("date2").isEmpty()) {
			String date1 = request.getParameter("date1");
			Date d1 = DateUtils.toDate(date1, "yyyy/MM/dd");
			date1 = DateUtils.getFormatDateWithSlash(d1);
			String date2 = request.getParameter("date2");
			Date d2 = DateUtils.toDate(date2, "yyyy/MM/dd");
			date2 = DateUtils.getFormatDateWithSlash(d2);
			if (checkDate(date1) == true && checkDate(date2) == true) {
				list = dao.getOrderDateBetween(product_no, date1, date2);
				session.setAttribute("list", list);
				session.setAttribute("date1", date1);
				session.setAttribute("date2", date2);
			} else {
				request.setAttribute("message", "日付はyyyy/MM/dd方式で入力して下さい。");
			}
		} else if (request.getParameter("date1") != null && !request.getParameter("date1").isEmpty()
				|| request.getParameter("date2") == null || request.getParameter("date2").isEmpty()) {
			String date = request.getParameter("date1");
			Date d = DateUtils.toDate(date, "yyyy/MM/dd");
			date = DateUtils.getFormatDateWithSlash(d);
			if (checkDate(date) == true) {
				list = dao.getOrderDate1(product_no, date);
				session.setAttribute("list", list);
				session.setAttribute("date1", date);
				session.removeAttribute("date2");
			} else {
				request.setAttribute("message", "日付はyyyy/MM/dd方式で入力して下さい。");
			}
		} else if (request.getParameter("date1") == null || request.getParameter("date1").isEmpty()
				|| request.getParameter("date2") != null && !request.getParameter("date2").isEmpty()) {
			String date = request.getParameter("date2");
			Date d = DateUtils.toDate(date, "yyyy/MM/dd");
			date = DateUtils.getFormatDateWithSlash(d);
			if (checkDate(date) == true) {
				list = dao.getOrderDate2(product_no, date);
				session.setAttribute("list", list);
				session.setAttribute("date2", date);
				session.removeAttribute("date1");
			} else {
				request.setAttribute("message", "日付はyyyy/MM/dd方式で入力して下さい。");
			}
		}

		//JSPからの指示を受け取る。
		String siji = request.getParameter("siji");

		if (siji.equals("clear")) {
			clear(session, request);
			return "purchaselist";
		}

		//ラジオボタンの内容で分岐。（ラムダ式）
		switch (radio1 == null ? "" : radio1) {
		case "all":
			list = (List<PurchaseList>) session.getAttribute("list");
			break;
		case "fin_flg0":
			list = list.stream().filter(str -> str.getFin_flg() == 0).collect(Collectors.toList());
			break;
		case "fin_flg1":
			list = list.stream().filter(str -> str.getFin_flg() == 1).collect(Collectors.toList());
			break;
		default:
		}

		switch (radio2 == null ? "" : radio2) {
		case "order_date":
			list = list.stream().sorted(Comparator.comparing(PurchaseList::getOrder_date)).collect(Collectors.toList());
			break;
		case "delivery_date":
			list = list.stream().sorted(Comparator.comparing(PurchaseList::getDelivery_date))
					.collect(Collectors.toList());
			break;
		case "ship_date":
			list = list.stream().sorted(Comparator.comparing(PurchaseList::getShip_date)).collect(Collectors.toList());
			break;
		case "po_no":
			list = list.stream().sorted(Comparator.comparing(PurchaseList::getPo_no)).collect(Collectors.toList());
			break;
		case "order_qty":
			list = list.stream().sorted(Comparator.comparing(PurchaseList::getOrder_qty)).collect(Collectors.toList());
			break;
		case "customer_no":
			list = list.stream().sorted(Comparator.comparing(PurchaseList::getCustomer_no))
					.collect(Collectors.toList());
			break;
		default:
		}

		//リストの内容、指示、ラジオボタンの値を返す。
		request.setAttribute("list", list);
		request.setAttribute("siji", siji);
		request.setAttribute("radio1", radio1);
		request.setAttribute("radio2", radio2);

		return "purchaselist";
	}

	private void clear(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("list");
		session.removeAttribute("date1");
		session.removeAttribute("date2");
		session.removeAttribute("product_no");
		session.removeAttribute("product_name");
		request.setAttribute("siji", "clear");
		request.removeAttribute("message");

	}

	//日付の入力形式をチェックするメソッド（中西さんより）
	public boolean checkDate(String date) {
		if (date == null || date.length() != 10) {
			return false;
		}
		date = date.replace('-', '/');
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