package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CustomerMaster;
import bean.ProductMaster;
import bean.PurchaseOrder;
import dao.CustomerMasterDAO;
import dao.ProductMasterDAO;
import dao.PurchaseOrderDAO;
import tool.Action;
import tool.DateUtils;

public class PurchaseOrderAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String nextscr = request.getParameter("nextscr");
		if (nextscr != null && !nextscr.equals(""))return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;
		//画面からどの指示がなされたか判断する。（どのボタンが押されたか判断）
		String siji = request.getParameter("siji");
		String dousa = request.getParameter("dousa");
		//画面からどのボタンが押されたかを保存する

		PurchaseOrder purchase = new PurchaseOrder();
		purchase.setCm_customer_no(request.getParameter("cm_customer_no"));
		purchase.setCm_customer_name(request.getParameter("cm_customer_name"));
		purchase.setPm_product_no(request.getParameter("pm_product_no"));
		purchase.setPm_product_name(request.getParameter("pm_product_name"));

		purchase.setDelivery_date((request.getParameter("delivery_date")));

		if (request.getParameter("order_qty") != null
				&& !request.getParameter("order_qty").isEmpty()) {
			purchase.setOrder_qty(Integer.parseInt(request.getParameter("order_qty")));
		}
		purchase.setEtc(request.getParameter("etc"));

		purchase.setRegistdate(DateUtils.getSystemDateWithSlash());

		String name2 = (String) session.getAttribute("user_id");
		purchase.setRegistuser(name2);

		switch (siji) {
		case "idSearch": //メソッドなし
			//purchaseDAOをインスタンス化
			CustomerMasterDAO dao = new CustomerMasterDAO();
			CustomerMaster getPurchaseInfo = dao.searchCustomerNo(purchase.getCm_customer_no());
			if (getPurchaseInfo.getCustomer_name() == null) {
				request.setAttribute("message", "顧客マスタに登録ありません。");
			} else {
				purchase.setCm_customer_name(getPurchaseInfo.getCustomer_name());

			}
			request.setAttribute("purchase", purchase);

			break;

		case "cnSearch": //メソッドなし
			//purchaseDAOをインスタンス化
			ProductMasterDAO pro = new ProductMasterDAO();
			ProductMaster pm = pro.searchProductNo(purchase.getPm_product_no());

			if (pm == null) {
				purchase.setPm_product_name("");
				request.setAttribute("message", "品番マスタに登録ありません。");
			} else {
				purchase.setPm_product_name(pm.getProduct_name());
			}
			request.setAttribute("purchase", purchase);
			break;

		case "insert":
			String result = null;


				//sinkiメソッドを呼び出す（下の方に記入）
				result = sinki(purchase);
				dousa = "";


			request.setAttribute("message", result);

			break;

		case "reset":
			return "purchase_order";
		}

		return "purchase_order";
	}
	private String sinki(PurchaseOrder purchase) {

		//PurchaseOrderDAOをインスタンス化
		PurchaseOrderDAO dao = new PurchaseOrderDAO();
		//insertPurchaseメソッドを呼び出し結果を整数型で取得
		int result = dao.insertPurchaseOrder(purchase);
		if (result != 0) {
			return "追加に成功しました。" ;
		} else {
			return "追加に失敗しました。";
		}
}
}