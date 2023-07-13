package action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.EntryExitInfoList;
import dao.EntryExitInfoListDAO;
import tool.Action;

public class EntryExitInfoListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		OrderListAction ola = new OrderListAction();
		String product_no = null;
		String startdate = request.getParameter("start_date");
		String enddate = request.getParameter("end_date");
		String nextscr = request.getParameter("nextscr2");
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;

		List<EntryExitInfoList> list = new ArrayList<>();
		if (request.getParameter("product_no") != null && !request.getParameter("product_no").isEmpty()) {
			product_no = request.getParameter("product_no");
			EntryExitInfoListDAO eeildao = new EntryExitInfoListDAO();
			list = eeildao.selectEeiView(product_no);
			if (list.size() == 0) {
				request.setAttribute("message", "品番が登録されていません。");
				return "entry_exit_info_list";
			} else if (list.get(0).getEb_ex_id() == null) {
				request.setAttribute("message", "入出庫履歴がありません。");
				return "entry_exit_info_list";
			} else {
			}
		}

		String radio = request.getParameter("radio");
		String radio2 = request.getParameter("radio2");
		String siji = request.getParameter("siji");

		//日付検索による条件分岐
		//日付が入力されたとき　または　日付の値がある時に動く
		if (siji.equals("start_end") || (startdate != null || enddate != null)) {
			//startdateがnullかつ空文字じゃない場合 かつ enddateがnullかつ空文字じゃない場合
			if ((startdate != null && startdate != "")
					&& (enddate != null && enddate != "")) {
				//中西氏作成のchechDate()メソッドを用いた日付フォーマットチェック
				if ((ola.checkDate(startdate) == true) && (ola.checkDate(enddate) == true)) {
					//戻り値がtrueの場合、日付の絞り込み
					list = list.stream().filter(str -> str.getEn_ex_date().compareTo(startdate) >= 0)
							.filter(str -> str.getEn_ex_date().compareTo(enddate) <= 0)
							.collect(Collectors.toList());
				} else {
					request.setAttribute("message", "日付はyyyy/MM/dd方式で入力して下さい。");
				}
				//startdateがnullかつ空文字じゃない場合 かつ enddateがnullかつ空文字の場合
			} else if ((startdate != null && startdate != "") || (enddate == null && enddate == "")) {
				//中西氏作成のchechDate()メソッドを用いた日付フォーマットチェック
				if (ola.checkDate(startdate) == true) {
					//戻り値がtrueの場合、日付の絞り込み
					list = list.stream().filter(str -> str.getEn_ex_date().compareTo(startdate) >= 0)
							.collect(Collectors.toList());
				} else {
					request.setAttribute("message", "日付はyyyy/MM/dd方式で入力して下さい。");
				}
				//startdateがnullかつ空文字の場合 かつ enddateがnullかつ空文字じゃない場合
			} else if ((startdate == null && startdate == "") || (enddate != null && enddate != "")) {
				//中西氏作成のchechDate()メソッドを用いた日付フォーマットチェック
				if (ola.checkDate(enddate) == true) {
					//戻り値がtrueの場合、日付の絞り込み
					list = list.stream().filter(str -> str.getEn_ex_date().compareTo(enddate) <= 0)
							.collect(Collectors.toList());
				} else {
					request.setAttribute("message", "日付はyyyy/MM/dd方式で入力して下さい。");
				}
			} else {
			}
		}
		//ラジオボタンでのソート及び絞り込み
		switch (radio2 == null ? "" : radio2) {
		case "all":
			break;
		case "instore":
			//ラムダ式：EntryExitInfoListのgetSituation()の中身が "入" のモノだけを絞り込む。
			list = list.stream().filter(str -> str.getSituation().equals("入")).collect(Collectors.toList());
			break;
		case "shipping":
			//ラムダ式：EntryExitInfoListのgetSituation()の中身が "出" のモノだけを絞り込む。
			list = list.stream().filter(str -> str.getSituation().equals("出")).collect(Collectors.toList());
			break;
		default:
			break;
		}
		switch (radio == null ? "" : radio) {
		case "date":
			list = list.stream().sorted(Comparator.comparing(EntryExitInfoList::getEn_ex_date))
					.collect(Collectors.toList());
			break;
		case "qty":
			list = list.stream().sorted(Comparator.comparing(EntryExitInfoList::getCount))
					.collect(Collectors.toList());
			break;
		default:
			break;
		}
		if (siji.equals("clear")) {
			product_no = null;
			list = null;
			radio = "";
			radio2 = "";
			request.setAttribute("start_date", "");
			request.setAttribute("end_date", "");
			request.removeAttribute("message");
		} else {
		}
		request.setAttribute("list", list);
		request.setAttribute("radio", radio);
		request.setAttribute("radio2", radio2);
		request.setAttribute("product_no", product_no);
		request.setAttribute("start_date", startdate);
		request.setAttribute("end_date", enddate);

		//javaScriptで条件分岐させる用
		request.setAttribute("siji", siji);
		return "entry_exit_info_list";
	}

}
