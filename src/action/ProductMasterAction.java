package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductMaster;
import dao.ProductMasterDAO;
import tool.Action;
import tool.DateUtils;

public class ProductMasterAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String nextscr = request.getParameter("nextscr");
		if (nextscr != null && !nextscr.equals(""))return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;
		//画面からどの指示がなされたか判断する。（どのボタンが押されたか判断）
		String siji = request.getParameter("siji");
		String dousa = request.getParameter("dousa");
		//画面からどのボタンが押されたかを保存する

		ProductMaster product = new ProductMaster();
		if (request.getParameter("product_no") != null && !request.getParameter("product_no").isEmpty()) {
			product.setProduct_no(request.getParameter("product_no"));
		}
		product.setProduct_name(request.getParameter("product_name"));
		product.setSm_supplier_no(request.getParameter("sm_supplier_no"));
		product.setSm_supplier_name(request.getParameter("sm_supplier_name"));

		if (request.getParameter("unitprice") != null
				&& !request.getParameter("unitprice").isEmpty()) {
			product.setUnitprice(Integer.parseInt(request.getParameter("unitprice")));
		}

		if (request.getParameter("sellingprice") != null
				&& !request.getParameter("sellingprice").isEmpty()) {
			product.setSellingprice(Integer.parseInt(request.getParameter("sellingprice")));
		}

		if (request.getParameter("leadtime") != null
				&& !request.getParameter("leadtime").isEmpty()) {
			product.setLeadtime(Integer.parseInt(request.getParameter("leadtime")));
		}

		if (request.getParameter("lot") != null
				&& !request.getParameter("lot").isEmpty()) {
			product.setLot(Integer.parseInt(request.getParameter("lot")));
		}

		product.setLocation(request.getParameter("location"));

		if (request.getParameter("basestock") != null
				&& !request.getParameter("basestock").isEmpty()) {
			product.setBasestock(Integer.parseInt(request.getParameter("basestock")));
		}

		product.setEtc(request.getParameter("etc"));
		product.setRegistdate(DateUtils.getSystemDateWithSlash());

		String name2 = (String) session.getAttribute("user_id");
		product.setRegistuser(name2);

		switch (siji) {
		case "idSearch": //メソッドなし
			//productDAOをインスタンス化
			ProductMasterDAO dao = new ProductMasterDAO();
			ProductMaster getProductInfo = dao.searchProductNo(product.getProduct_no());
			if (getProductInfo.getProduct_no() == null) {
				request.setAttribute("message", "品番は登録されていません。");
				request.setAttribute("product", product);
			} else {
				request.setAttribute("product", getProductInfo);
			}
			break;

		case "snSearch": //メソッドなし
			//productDAOをインスタンス化
			ProductMasterDAO sup = new ProductMasterDAO();
			String name = sup.searchSupplierNo(product);
			if (name == null) {
				product.setSm_supplier_name("");
				request.setAttribute("message", "仕入先が登録されておりません。");
			} else {
				product.setSm_supplier_name(name);
			}
			request.setAttribute("product", product);
			break;

		case "insert":
			String result = null;
			if (product.getProduct_name() == null || product.getProduct_name().isEmpty()) {
				result = "品名が入力されておりません。";
				request.setAttribute("product", product);
			} else {
				//sinkiメソッドを呼び出す（下の方に記入）
				result = sinki(product);
				dousa = "";
			}

			request.setAttribute("message", result);

			break;
		case "update":
			//kousinメソッドを呼び出す（下の方に記入）
			result = kousin(product);
			request.setAttribute("message", result);
			dousa = "";
			break;
		case "delete":
			//sakujoメソッドを呼び出す（このソースの下から二番目に記入）
			result = sakujo(product);
			request.setAttribute("message", result);
			dousa = "";
			break;

		case "reset":
			return "product_master";

		default:
		}
		request.setAttribute("dousa", dousa);
		return "product_master";
	}

	private String sakujo(ProductMaster product) {
		//productDAOをインスタンス化
		ProductMasterDAO dao = new ProductMasterDAO();
		//deleteProductメソッドを呼び出し結果を整数型で取得
		int result = dao.deleteProductMaster(product.getProduct_no());
		if (result == 1) {
			return "削除に成功しました。";
		} else {
			return "削除に失敗しました。";
		}
	}

	private String kousin(ProductMaster product) {
		//ProductMasterDAOをインスタンス化
		ProductMasterDAO dao = new ProductMasterDAO();
		//updateProductメソッドを呼び出し結果を整数型で取得
		int result = dao.updateProductMaster(product);
		if (result == 1) {
			return "更新に成功しました。";
		} else {
			return "更新に失敗しました。";
		}
	}

	private String sinki(ProductMaster product) {

		//ProductMasterDAOをインスタンス化
		ProductMasterDAO dao = new ProductMasterDAO();
		//insertProductメソッドを呼び出し結果を整数型で取得
		String result = dao.insertProductMaster(product);
		if (result != null) {
			return "追加に成功しました。" + "新品番:" + result;
		} else {
			return "追加に失敗しました。";
		}

	}
}
