package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CustomerMaster;
import bean.ZipData;
import dao.CustomerMasterDAO;
import dao.ZipDAO;
import tool.Action;
import tool.DateUtils;
import tool.NumberManager;

public class CustomerMasterAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//画面からどのどの指示が届いたかを判断する。
		String siji = request.getParameter("siji");
		//どのボタンがクリックされたかを保存する。
		String dousa = request.getParameter("dousa");
		//CustomerMasterを変数名cmとしてインスタンス化
		CustomerMaster cm = new CustomerMaster();
		//cmに画面から入力された値を格納する
		if (request.getParameter("customer_no") != null && !request.getParameter("customer_no").isEmpty()) {
			cm.setCustomer_no(request.getParameter("customer_no"));
		}
		cm.setCustomer_name(request.getParameter("customer_name"));
		cm.setBranch_name(request.getParameter("branch_name"));
		cm.setZip_no(request.getParameter("zip_no"));
		cm.setAddress_1(request.getParameter("address_1"));
		cm.setAddress_2(request.getParameter("address_2"));
		cm.setAddress_3(request.getParameter("address_3"));
		cm.setTel(request.getParameter("tel"));
		cm.setFax(request.getParameter("fax"));
		cm.setManager(request.getParameter("manager"));
		if (request.getParameter("delivery_leadtime") != null && !request.getParameter("delivery_leadtime").isEmpty()) {
			cm.setDelivery_leadtime(Integer.parseInt(request.getParameter("delivery_leadtime")));
		}
		cm.setEtc(request.getParameter("etc"));
		cm.setRegistdate(DateUtils.getSystemDateWithSlash());
		String name = (String) session.getAttribute("user_id");
		cm.setRegistuser(name);
		String nextscr = request.getParameter("nextscr2");
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;

		//sijiの値で条件分岐させる。
		switch (siji) {
		case "noSearch":
			//CustomerDAOをインスタンス化
			CustomerMasterDAO dao = new CustomerMasterDAO();
			//ビーンからcustomer_noを取ってきて、daoのsearchCustomerNoメソッドが稼働。
			//メソッドの戻り値をgetCustomerNoInfoビーンに格納
			CustomerMaster getCustomerNoInfo = dao.searchCustomerNo(cm.getCustomer_no());
			//getCustomerNoInfoからgetCustomer_noメソッドを呼び出し、nullと比較。
			if (getCustomerNoInfo.getCustomer_no() == null) {
				request.setAttribute("message", "入力された顧客コードは登録されていません。");
			} else {
				request.setAttribute("CustomerMaster", getCustomerNoInfo);
			}
			break;

		case "zipno":
			ZipDAO zd = new ZipDAO();
			List<ZipData> lzd = zd.searchAllByZipNo(cm.getZip_no());
			if (lzd.size() == 0) {
				request.setAttribute("message", "郵便番号が存在しません。");
			} else {
				cm.setAddress_1(lzd.get(0).getPref());
				cm.setAddress_2(lzd.get(0).getCity());
//				cm.setAddress_3(lzd.get(0).getVillege());
			}
			request.setAttribute("CustomerMaster", cm);
			break;
		case "regist":
			//会社名supplier_name 空白のエラー表示
			if (cm.getCustomer_name() == null || cm.getCustomer_name().isEmpty()) {
				request.setAttribute("message","会社名を入力してください。");
				request.setAttribute("CustomerMaster", cm);
				return "customer_master";
			}
			//電話番号tel 空白のエラー表示
			if (cm.getTel() == null || cm.getTel().isEmpty()) {
				request.setAttribute("message","電話番号を入力してください。");
				request.setAttribute("CustomerMaster", cm);
				return "customer_master";
			}

			String result = sinki(cm);
			request.setAttribute("message", result);
			dousa = "";
			break;
		case "update":
			if (cm.getCustomer_name() == null || cm.getCustomer_name().isEmpty()) {
				request.setAttribute("message","会社名を入力してください。");
				request.setAttribute("CustomerMaster", cm);
				return "customer_master";
			}
			//電話番号tel 空白のエラー表示
			if (cm.getTel() == null || cm.getTel().isEmpty()) {
				request.setAttribute("message","電話番号を入力してください。");
				request.setAttribute("CustomerMaster", cm);
				return "customer_master";
			}
			result = kousin(cm);
			request.setAttribute("message", result);
			dousa = "";
			break;
		case "delete":
			result = sakuzyo(cm.getCustomer_no());
			request.setAttribute("message", result);
			dousa = "";
			break;
		case "reset":
			return "customer_master";
		default:
		}
		//クリックされたボタンの動作をリクエスト属性に保存。
		request.setAttribute("dousa", dousa);
		return "customer_master";
	}

	private String sakuzyo(String cm) {
		CustomerMasterDAO dao = new CustomerMasterDAO();
		int result = dao.deleteCustomerMaster(cm);
		if (result == 1) {
			return "削除に成功しました！";
		} else {
			return "削除に失敗しました...";
		}
	}

	private String kousin(CustomerMaster cm) {
		//CustomerMasterDAOをインスタンス化
		CustomerMasterDAO dao = new CustomerMasterDAO();
		//updateCustomerMasterメソッドを呼び出す。結果を整数型で取得
		int result = dao.updateCustomerMaster(cm);
		if (result == 1) {
			return "更新に成功しました！";
		} else {
			return "更新に失敗しました...";
		}
	}

	private String sinki(CustomerMaster customer) throws Exception {
		//CustomerMasterDAOをインスタンス化
		CustomerMasterDAO dao = new CustomerMasterDAO();
		//insertCustomerMasterメソッドを呼び出す。結果を整数型で取得
		String customer_no = dao.getSequence("customer_no");
		NumberManager numMan = new NumberManager(customer_no);
		customer.setCustomer_no("" + numMan.customer_no());
		int result = dao.insertCustomerMaster(customer);
		if (result == 1) {
			return "追加に成功しました！ 顧客コード : " + customer.getCustomer_no();
		} else {
			return "追加に失敗しました...";

		}
	}
}
