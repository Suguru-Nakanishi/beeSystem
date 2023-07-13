package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Outsourcing;
import bean.ProductStock;
import dao.OutsourcingDAO;
import dao.ShipmentDAO;
import tool.Action;
import tool.DateUtils;

public class OutsourcingAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String nextscr = request.getParameter("nextscr");
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;

		// 画面からどの指示が届いたかを判断する
		String siji = request.getParameter("siji");
		//画面の入力内容を取得 osビーンに入れる getParameterはString型intの場合は型変換する
		//Outsourcingビーン インスタンス化
		Outsourcing os = new Outsourcing();
		if (request.getParameter("order_no") != null
				&& !request.getParameter("order_no").isEmpty()) {
			os.setOrder_no(request.getParameter("order_no"));
		}
		os.setOrderdate(request.getParameter("orderdate"));
		os.setProduct_no(request.getParameter("product_no"));
		os.setProduct_name(request.getParameter("product_name"));
		if (request.getParameter("order_qty") != null
				&& !request.getParameter("order_qty").isEmpty()) {
			os.setOrder_qty(Integer.parseInt(request.getParameter("order_qty")));
		}
		if (request.getParameter("due_qty") != null
				&& !request.getParameter("due_qty").isEmpty()) {
			os.setDue_qty(Integer.parseInt(request.getParameter("due_qty")));
		}

		switch (siji) {

		case "idSearch":
			OutsourcingDAO dao = new OutsourcingDAO();
			Outsourcing getOutsourcingInfo = dao.idSearch(os.getOrder_no());

			if (getOutsourcingInfo == null || os.getOrder_no().isEmpty()) {
				request.setAttribute("message", "注文番号が存在しません ");
			}

			else {
				request.setAttribute("os", getOutsourcingInfo);
			}

			break;
		//------------------------------------
		case "check":
			if (os.getOrder_qty() != os.getDue_qty()) {
				request.setAttribute("message", "入力された数量と注文数量に差があります。");
			}
			//リクエスト属性にアウトソ－スビーンをosという名前で保存する 処理の後元の画面に返る
			request.setAttribute("os", os);

			break;
		//----------------------------------------------
		//キャンセルボタン押下時
		case "clear":
			request.setAttribute("order_no", "");
			request.setAttribute("orderdate", "");
			request.setAttribute("product_no", "");
			request.setAttribute("product_name", "");
			request.setAttribute("order_qty", "");
			request.setAttribute("due_qty", "");

			break;
		//------------------------------------------------------
		//更新①と②

		case "update":

			if (os.getOrder_qty() != os.getDue_qty()) {
				request.setAttribute("message", "入力された数量と注文数量に差があります。");
				request.setAttribute("os", os);
				break;
			}
			//リクエスト属性にアウトソ－スビーンをosという名前で保存する 処理の後元の画面に返る

			//パラメータを画面より取得
			String stock_info_date = DateUtils.getFormatDateWithYYYYMM(); //(在庫テーブル検索用)
			String product_no = request.getParameter("product_no");

			//OutsourcingDAOをインスタンス化,メソッドsearchProductStock(年月と品番一致データを検索し取得)を呼び出す。
			ShipmentDAO dao4 = new ShipmentDAO();
			//ProductStockビーンのインスタンス化
			ProductStock ps = new ProductStock();
			ps = dao4.searchProductStock(stock_info_date, product_no);
			//在庫テーブル
			os.setStock_info_date(stock_info_date);
			os.setProduct_no(os.getProduct_no());
			os.setStock_qty(ps.getStock_qty() + os.getDue_qty());
			os.setT_nyuko(ps.getT_nyuko() + os.getDue_qty());
			os.setUp_date(DateUtils.getSystemDateWithSlash());
			//発注テーブル
			os.setDue_date(DateUtils.getSystemDateWithSlash());
			os.setDue_qty(os.getDue_qty());
			os.setOutsourcinguser(os.getOutsourcinguser());
			os.setOutsourcinguser((String) session.getAttribute("user_id"));

			//OutsourcingDAO updateOutsourcingメソッド
			OutsourcingDAO dao3 = new OutsourcingDAO();
			int sqlreslt = dao3.updateOutsourcing(os);
			if (sqlreslt == 2) {
				request.setAttribute("message", "納入情報を登録しました。");
			} else {
				request.setAttribute("message", "納入情報を登録できませんでした。");
			}
			break;
		default:
		}
		return "outsourcing";
	}
}
