package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserMaster;
import dao.UserMasterDAO;
import tool.Action;

public class LoginAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String loginName = request.getParameter("login"), pass = request.getParameter("password"), file = "";
		String loginId[] = loginName.split(" ");
		boolean flag = (loginId.length == 2 && loginId[1].equals("admin") || loginId.length == 1 && loginId[0].length() == loginName.length()) && loginName != null && !loginName.equals("") && pass != null && !pass.equals(""), state = session.getAttribute("login") != null, mode = loginId.length == 2 && loginId[1].equals("admin");
		UserMaster user = new UserMaster();
		UserMasterDAO dao = null;
		if (flag) {
			user.setUser_id(loginId[0]);
			user.setPassword(pass);
			dao = new UserMasterDAO();
		}
		if (flag && dao.searchUser(user.getUser_id(), user.getPassword())) {
			file = mode ? "master_menu" : "menu";
			session.setAttribute("user_id", request.getParameter("login"));
			session.setAttribute("user", dao.getUser().getName() == null || dao.getUser().getName().equals("") ? dao.getUser().getUser_id() : dao.getUser().getName());
		} else {
			file = session.getAttribute("user_id") != null ? "menu" : "login";
			request.setAttribute("message", state ? "" : "ユーザ名またはパスワードに誤りがあります。");
		}
		return file;
	}
}