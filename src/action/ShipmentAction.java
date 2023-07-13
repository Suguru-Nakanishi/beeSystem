package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductStock;
import bean.Shipment;
import bean.ShipmentUpdate;
import dao.ShipmentDAO;
import tool.Action;
import tool.DateUtils;

public class ShipmentAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session=request.getSession();
		String nextscr = request.getParameter("nextscr");
		if (nextscr != null && !nextscr.equals(""))return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;

		//message用
		String str = null;
		//ShipmentDAOのインスタンス化
		ShipmentDAO sdao = new ShipmentDAO();

		//JSPからの指示を受け取る。
		String siji = request.getParameter("siji");

		switch (siji) {

		//sijiの値がidSearchなら検索処理を行う。
		case "idSearch":
			//shipmentビーンのインスタンス化
			Shipment ship = new Shipment();
			//リクエストパラメータ(JSP）からpo_noを取得する。
			String po_no = request.getParameter("po_no");
			//ShipmentDAOクラスのメソッドsarchByIdを呼び出す。
			ship = sdao.searchById(po_no);
			//受け取った結果がnullかどうかで分岐する。
			if (ship == null) {
				str = "入力された受注番号は登録されていません。";
				request.setAttribute("message", str);
				//受け取った結果がnullではないが、完了フラグが1の時はエラーを返す。
			} else {
				int fin_flg = ship.getFin_flg();
				if (fin_flg == 1) {
					str = "入力された受注番号に対する出荷は既に完了しています。";
					request.setAttribute("message", str);
				} else {
					//どちらでもなければ受け取った結果を画面に返す。
					request.setAttribute("ship", ship);
				}
			}
			break;

		//sijiの値がupdateなら更新処理を行う。
		case "update":
			//リクエストパラメータ(JSP）から必要な値を取得。
			String p_no = request.getParameter("po_no");
			String s_date = request.getParameter("ship_date");
			String stock_info_date = DateUtils.getFormatDateWithYYYYMM();
			String product_no = request.getParameter("product_no");
			int order_qty = Integer.parseInt(request.getParameter("order_qty"));
			//ProductStockビーンのインスタンス化
			ProductStock ps = new ProductStock();
			//ShipmentDAOクラスのメソッドsearchProductStockを呼び出す。
			ps = sdao.searchProductStock(stock_info_date, product_no);
			//ShipmentUpdateビーンのインスタンス化
			ShipmentUpdate sud = new ShipmentUpdate();
			//必要な値をsud(インスタンス化したShipmentUpdateのbean)に格納
			sud.setPo_no(p_no);
			sud.setShip_date(s_date);
			sud.setShipment_registdate(DateUtils.getSystemDateWithSlash());
			sud.setShipment_registuser((String)session.getAttribute("user_id"));
			sud.setStock_qty(ps.getStock_qty() - order_qty);
			sud.setT_syuka(ps.getT_syuka() + order_qty);
			sud.setUp_date(DateUtils.getSystemDateWithSlash());
			sud.setStock_info_date(DateUtils.getFormatDateWithYYYYMM());
			sud.setProduct_no(product_no);

			//受注番号が入っていない、もしくは出荷日が入っていない場合はエラーを返す。
			if ((p_no == null || p_no.isEmpty()) || (s_date == null || s_date.isEmpty())) {
				str = "受注番号及び出荷日を入力してから確定ボタンを押してください。";
				//受け取った結果を画面に返す。
				request.setAttribute("message", str);
			} else {
				//エラーがなければ次の処理へ
				//ShipmentDAOクラスのメソッドupdateShipmentを呼び出す。
				int sqlresult = sdao.updateShipmentUpdate(sud);
				//受け取った結果がnullかどうかで分岐する。
				if (sqlresult == 2) {
					str = "出荷情報を登録しました。";
				} else {
					str = "出荷情報を登録できませんでした。";
				}
				//受け取った結果を画面に返す。
				request.setAttribute("message", str);
			}
			break;

		case "clear":
			//空文字を画面に返す。
			request.setAttribute("po_no", "");
			request.setAttribute("order_date", "");
			request.setAttribute("customer_no", "");
			request.setAttribute("customer_name", "");
			request.setAttribute("branch_name", "");
			request.setAttribute("product_no", "");
			request.setAttribute("product_name", "");
			request.setAttribute("order_qty", "");
			request.setAttribute("ship_date", "");

			break;
		default:
		}

		return "shipment";
	}
}
