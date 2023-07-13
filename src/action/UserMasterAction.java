package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserMaster;
import dao.UserMasterDAO;
import tool.Action;
import tool.DateUtils;
import tool.NumberManager;

public class UserMasterAction extends Action {
	/*
	 * nextscr		飛び先指定用。
	 * str			エラーメッセージ用。
	 * siji			jspからの指示。
	 * user_name	登録者名。
	 * n			ユーザ名評価用。
	 * p			パスワード評価用。
	 * d			部署名評価用。
	 * h			入社日評価用。
	 * user_id		ユーザＩＤ。
	 * usr			jspからのデータ格納用ビーン。
	 * getUserInfo	データベースからのデータ格納用ビーン。
	 * dao			ＤＡＯ。
	 */
	private String nextscr, str, siji, user_name, n, p, d, h, user_id;
	private UserMaster usr, getUserInfo;
	private UserMasterDAO dao;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		siji = request.getParameter("siji");
		user_name = (String)session.getAttribute("user");
		usr = new UserMaster();
		try {nextscr = request.getParameter("nextscr");} catch (Exception e) {e.printStackTrace();}
		if (nextscr != null && !nextscr.equals("")) {return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;}
		if (request.getParameter("user_id") != null && !request.getParameter("user_id").isEmpty()) {usr.setUser_id(request.getParameter("user_id"));}
		usr.setName(request.getParameter("name"));
		usr.setPassword(request.getParameter("password"));
		usr.setDept(request.getParameter("dept"));
		usr.setHire_date(request.getParameter("hire_date"));
		usr.setEtc(request.getParameter("etc"));
		usr.setRegistdate(DateUtils.getSystemDateWithSlash());
		usr.setRegistuser(user_name);
		switch (siji) {
		case "kousin":
		case "sakujo":
			dao = new UserMasterDAO();
			getUserInfo = dao.searchById(usr.getUser_id());
			if (getUserInfo == null) str = "入力されたIDは登録されていません。";
			else {
				request.setAttribute("usr", getUserInfo);
				request.setAttribute("siji", siji.equals("kousin") ? "update" : "delete");
			}
			break;
		case "regist":
			n = request.getParameter("name");
			p = request.getParameter("password");
			d = request.getParameter("dept");
			h = request.getParameter("hire_date");
			NumberManager numMana = new NumberManager(p);
			str = "";
			if (n == null || n.isEmpty() || n.length() < 2) str = "『ユーザー名（氏名）』を入力して下さい。\n";
			if (p == null || p.isEmpty()) str = str + "password』を入力して下さい。\n";
			if (numMana.passwordCheck() != 7) str = str + "『password』は半角8文字以上\n数字・英大文字･小文字を各1文字以上含む形式で設定して下さい。\n";
			if (d == null || d.isEmpty()) str = str + "『部署名』を入力して下さい。\n";
			if (h == null || h.isEmpty()) str = str + "『入社日』を\"yyyy/mm/dd\"形式で入力して下さい。";
			if (str.equals("")) sinki();
			break;
		case "update":
			dao = new UserMasterDAO();
			user_id = request.getParameter("user_id");
			getUserInfo = dao.searchById(user_id);
			if (getUserInfo == null) str = "入力された\"user_id\"は登録されていません。";
			else kousin();
			break;
		case "delete":
			sakujo();
			break;
		default:
			break;
		}
		request.setAttribute("message", str);
		return "user_master";
	}

	private void sakujo() {
		dao = new UserMasterDAO();
		str="削除に" + (dao.deleteUser(usr.getUser_id()) == 1 ? "成功" : "失敗") + "しました。";
	}

	private void kousin() {
		dao = new UserMasterDAO();
		str= "更新に" + (dao.updateUser(usr) == 1 ? "成功" : "失敗") + "しました。";
	}

	private void sinki() {
		dao = new UserMasterDAO();
		str = "追加に" + (dao.insertUser(usr) == 1 ? "成功" : "失敗") + "しました。";
	}
}