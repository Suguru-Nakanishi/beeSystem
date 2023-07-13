package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.EntryExitInfo;
import bean.EntryExitInfoAndProductStock;
import bean.ProductMaster;
import bean.ProductStock;
import dao.EntryExitInfoDAO;
import dao.ProductMasterDAO;
import tool.Action;
import tool.DateUtils;

public class EntryExitInfoAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String siji = request.getParameter("siji");
		String product_no = null;
		int count = 0;
		ProductStock ps = new ProductStock();
		EntryExitInfo eei = new EntryExitInfo();
		EntryExitInfoAndProductStock eeips = new EntryExitInfoAndProductStock();

		if (request.getParameter("product_no") != null && !request.getParameter("product_no").isEmpty()) {
			product_no = request.getParameter("product_no");

		}
		if (request.getParameter("count") != null && !request.getParameter("count").isEmpty()) {
			count = Integer.parseInt(request.getParameter("count"));
		}
		String nextscr = request.getParameter("nextscr2");
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;
		switch (siji) {
		case ("pnSearch"):
			//品番テーブルに登録のある品名を使うので、ProductMasterDAOをインスタンス化する。
			ProductMasterDAO pmdao = new ProductMasterDAO();
			//searchProductNoメソッドを呼び出し、新たなProductMasterのビーンに値を格納。
			ProductMaster productInfo = pmdao.searchProductNo(product_no);
			//ProductMasterのビーンから品番を取得して、値がnullかnullでないかを判断する。
			if (productInfo == null) {
				request.setAttribute("message", "入力された品番は登録されていません。");
			} else {
				eei.setProduct_no(productInfo.getProduct_no());
				request.setAttribute("ProductMasterForEntry", productInfo);
			}
			break;
		case "confirm":
			if (count < 1) {
				request.setAttribute("message", "数量は１からの数値を入力してください。");
				return "entry_exit_info";
			} else {
				eei.setEn_ex_date(DateUtils.getSystemDateWithSlash());
				eei.setProduct_no(product_no);
				if (request.getParameter("radio").equals("instore")) {
					eei.setNyuko_qty(count);
					eei.setSyuko_qty(0);
				} else {
					eei.setSyuko_qty(count);
					eei.setNyuko_qty(0);
				}
				eei.setReason(request.getParameter("reason"));
				eei.setRegistdate(DateUtils.getSystemDateWithSlash());
				String session_id = (String) session.getAttribute("user_id");
				eei.setRegistuser(session_id);
				request.setAttribute("info", eei);
			}
			String stock_info_date = DateUtils.getFormatDateWithYYYYMM();

			EntryExitInfoDAO eeidao = new EntryExitInfoDAO();
			int result = eeidao.insertEntryUpdatePStock(eei, stock_info_date, product_no);

			if (result == 2) {
				request.setAttribute("message", "入出庫処理が完了しました。");
			} else {
				request.setAttribute("message", "入出庫処理が失敗しました。");
			}
		case "reset":
			return "entry_exit_info";
		}
		return "entry_exit_info";
	}

}
