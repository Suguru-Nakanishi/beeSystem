package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SupplierMaster;
import bean.ZipData;
import dao.SupplierMasterDAO;
import dao.ZipDAO;
import tool.Action;

public class SupplierMasterAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String nextscr = request.getParameter("nextscr");
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;

		// 画面からどの指示が届いたかを判断する
		String siji = request.getParameter("siji");
		//どのボタンがクリックされたかを保存する
		String dousa = request.getParameter("dousa");
		//画面の入力内容を取得beanに入れる getParameterはString型intの場合は型変換する
		//全項目受け取ってgetIDなどで使うものを選ぶのも良い
		SupplierMaster sm = new SupplierMaster();
		if (request.getParameter("supplier_no") != null
				&& !request.getParameter("supplier_no").isEmpty()) {
			//（supplier_no のカラム6桁構成左側０埋め）の指示が↓これで正しいか要確認
			sm.setSupplier_no(String.format("%6s", request.getParameter("supplier_no")));

		}
		String menu = request.getParameter("menu");
		sm.setSupplier_name(request.getParameter("supplier_name"));
		sm.setBranch_name(request.getParameter("branch_name"));
		sm.setZipno(request.getParameter("zipno"));
		sm.setAddress_1(request.getParameter("address_1"));
		sm.setAddress_2(request.getParameter("address_2"));
		sm.setAddress_3(request.getParameter("address_3"));
		sm.setTel(request.getParameter("tel"));
		sm.setFax(request.getParameter("fax"));
		sm.setManager(request.getParameter("manager"));
		sm.setEtc(request.getParameter("etc"));
		sm.setRegistdate(request.getParameter("registdate"));
		String name = (String) session.getAttribute("user_id"); //registuser登録者の書き方
		sm.setRegistuser(name);

		//sijiの値で分岐
		switch (siji) {
		//id検索
		case "idSearch":
			//DAOをインスタンス化
			SupplierMasterDAO dao = new SupplierMasterDAO();
			SupplierMaster getSupplierMasterInfo = dao.idSearch(sm.getSupplier_no());
			if (getSupplierMasterInfo == null) {
				request.setAttribute("message", "入力された仕入先コードは登録されていません。");
				request.setAttribute("sm", sm);
			} else {
				request.setAttribute("sm", getSupplierMasterInfo);
			}
			//リクエスト属性にアウトソ－スビーンをsmという名前で保存する 処理の後元の画面に返る
			break;

		case "zipno":
			ZipDAO zd = new ZipDAO();
			List<ZipData> lzd = zd.searchAllByZipNo(sm.getZipno());
			if (lzd.size() == 0) {
				request.setAttribute("message", "郵便番号が存在しません。");
			} else {
				sm.setAddress_1(lzd.get(0).getPref());
				sm.setAddress_2(lzd.get(0).getCity());
				//sm.setAddress_3(lzd.get(0).getVillege());
			}

			request.setAttribute("sm", sm);
			break;
		//新規登録
		case "regist":

			String result = "";
			request.setAttribute("message", result);
			//会社名supplier_name 空白のエラー表示
			if (sm.getSupplier_name() == null || sm.getSupplier_name().isEmpty()) {
				result = result + "会社名を入力してください。";

			}

			//電話番号tel 空白のエラー表示
			if (sm.getTel() == null || sm.getTel().isEmpty()) {
				result = result + "電話番号を入力してください。";
			}

			if (result == "") {
				int res = sinki(sm);
				if (res == 1) {
					result = "登録に成功しました";
					sm = new SupplierMaster();
					dousa = "";
					menu = "";
					request.setAttribute("sm", sm);

				} else {
					result = "登録に失敗しました";
				}
			}
			request.setAttribute("message", result);
			break;

		//リクエスト属性にアウトソ－スビーンをsmという名前で保存する 処理の後元の画面に返る

		//更新
		case "update":
			result = "";
			if (sm.getSupplier_no() == null) {
				result = "入力された仕入先コードは登録されていません。";
			}

			//会社名supplier_name 空白のエラー表示
			if (sm.getSupplier_name() == null || sm.getSupplier_name().isEmpty()) {
				result = result + "会社名を入力してください。";
			}
			//電話番号tel 空白のエラー表示
			if (sm.getTel() == null || sm.getTel().isEmpty()) {
				result = result + "電話番号を入力してください。";
			}

			if (result == "") {
				int res = kousin(sm);

				if (res == 1) {
					result = "更新に成功しました";
					sm = new SupplierMaster();
					dousa = "";
					menu = "";
					request.setAttribute("sm", sm);

				} else {
					result = "更新に失敗しました";

				}
			} else {
				request.setAttribute("sm", sm);
			}
			request.setAttribute("message", result);
			break;

		//削除
		case "delete":
			//int getSupplier_no = Integer.parseInt(request.getParameter("supplier_no"));
			int res = sakujo(sm);
			if (res == 1) {
				result = "削除に成功しました";
				sm = new SupplierMaster();
				dousa = "";
				menu = "";
				request.setAttribute("sm", sm);

			} else {
				result = "削除に失敗しました";
			}
			request.setAttribute("message", result);
			break;
		//クリアボタン
		case "clear":
			sm = new SupplierMaster();
			dousa = "";
			menu = "";
			request.setAttribute("sm", sm);
		default:
		}
		//クリックされたボタンの動作をリクエスト属性に保存
		request.setAttribute("dousa", dousa);
		request.setAttribute("menu", menu);

		return "supplier_master";
	}

	private int sakujo(SupplierMaster sm) {//
		SupplierMasterDAO dao = new SupplierMasterDAO();

		int result = dao.deleteSupplierMaster(sm.getSupplier_no());
		return result; //
	}

	private int kousin(SupplierMaster sm) {
		SupplierMasterDAO dao = new SupplierMasterDAO();

		int result = dao.updateSupplierMaster(sm);
		return result; //
	}

	private int sinki(SupplierMaster sm) {
		//SupplierMasterDAO daoをインスタンス化
		SupplierMasterDAO dao = new SupplierMasterDAO();
		//insertSupplierMasterメソッドをよびだす
		//結果を整数型で取得
		int result = dao.insertSupplierMaster(sm);
		return result;

	}
}
