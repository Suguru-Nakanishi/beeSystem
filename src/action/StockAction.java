package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Company;
import bean.Stock;
import dao.CompanyDAO;
import dao.StockDAO;
import tool.Action;
import tool.AddressManager;

public class StockAction extends Action {
	private String product_no = "", product_name = "", company_no = "", nextscr = "", siji = "";
	private List<Stock> list = null;
	private List<Company> info = null;
	private CompanyDAO dao = null;
	private StockDAO dao2 = null;
	private AddressManager add = null;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		product_no = request.getParameter("product_no");
		product_name = request.getParameter("product_name");
		company_no = request.getParameter("company_no");
		nextscr = request.getParameter("nextscr");
		siji = product_no.length() == 10?request.getParameter("siji"):"";
		if (nextscr != null && !nextscr.equals(""))return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;
		switch (siji) {
		case "company_info":
			dao = new CompanyDAO(company_no);
			info = dao.searchCompany();
			add = new AddressManager();
			info.get(0).setAddress(add.addressFromat(info.get(0).getAddress(), (char) 0x20));
			request.setAttribute("company_no", company_no);
			request.setAttribute("info", info.get(0));
		case "stock_info":
			dao2 = new StockDAO(product_no);
			list = dao2.searchStock();
			request.setAttribute("product_no", product_no);
			if (list.size() == 0) request.setAttribute("message", "この品番での登録はありません。");
			else {
				for (int i = 0; i < list.size(); i++) {
					String name = list.get(i).getProduct_name();
					String day_1 = list.get(i).getOrder_date();
					if (name != null && !name.equals("")) {
						product_name = name;
						list.remove(i);
						day_1 = list.get(i).getOrder_date();
					} else if (day_1 == null || day_1.equals("")) {
						list.add(0, list.get(i));
						list.remove(i + 1);
					}
					for (int j = i + 1; j < list.size(); j++) {
						String day_2 = list.get(j).getOrder_date();
						if (day_2 == null || day_2.equals("")) {
							list.add(0, list.get(j));
							list.remove(j + 1);
						} else if (day_1.compareTo(day_2) > 0) {
							list.add(i, list.get(j));
							list.remove(j + 1);
						}
					}
				}
				request.setAttribute("product_name", product_name);
				request.setAttribute("list", list);
			}
			break;
		default:
			request.removeAttribute("product_name");
			break;
		}
		return "stock";
	}
}