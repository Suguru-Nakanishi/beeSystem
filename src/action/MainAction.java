package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class MainAction extends Action {
	private String nextscr;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//session.setAttribute("user_id","BeeSystem");//ダミーセッション。
		try {
			this.nextscr = request.getParameter("nextscr");
		} catch (Exception e) {e.printStackTrace();}
		return session.getAttribute("user_id") == null || this.nextscr == null ? "login" : this.nextscr;
		//return this.nextscr == null ? "menu" : this.nextscr;
	}
}