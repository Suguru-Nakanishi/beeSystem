package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") != null) {
			session.removeAttribute("user_id");
			session.removeAttribute("user");
			request.setAttribute("message", "ログアウトしました。");
		} else request.setAttribute("message", "既にログアウトしています。");
		return "login";
	}
}