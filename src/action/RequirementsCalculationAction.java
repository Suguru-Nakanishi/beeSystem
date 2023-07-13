package action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductMasterTable;
import bean.RequirementsCalculation;
import bean.RequirementsCalculationResult;
import dao.RequirementDAO;
import tool.Action;
import tool.DateUtils;

public class RequirementsCalculationAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String order = request.getParameter("siji");
		String nextscr = request.getParameter("nextscr2");
		if (nextscr != null && !nextscr.equals(""))
			return session.getAttribute("user_id") == null || nextscr == null ? "login" : nextscr;
		if (session.getAttribute("control") == null) {
			session.setAttribute("control", 0);
		}
		//セッションに保持した値を操作する変数cnを取得する
		int cn = (int) (session.getAttribute("control"));
		RequirementDAO dao = new RequirementDAO();
		List<ProductMasterTable> list = new ArrayList<>();
		list = dao.getProductMaster();
		//発注するデータがある場合、pnとresultで取得する
		List<RequirementsCalculationResult> result = null;
		if (session.getAttribute("result") == null) {
			result = new ArrayList<>();
		} else {
			result = (List<RequirementsCalculationResult>) session.getAttribute("result");
		}
		switch (order) {
		case "start":
			//品番リストの数よりsession変数が多いときの条件分岐
			if (cn < list.size()) {
				ProductMasterTable pm = list.get(cn);
				String num = pm.getProduct_no();
				request.setAttribute("product", num);
				System.out.println(num);
				List<RequirementsCalculation> rc = new ArrayList<>();
				//発注テーブルにデータがなかった場合の条件分岐
				rc = dao.getOrderData(num, pm);
				if (rc != null && !rc.isEmpty()) {
					int zaiko = rc.get(0).getStock_qty() - pm.getBasestock();
					rc = rc.stream().sorted(Comparator.comparing(RequirementsCalculation::getDelivery_date))
							.collect(Collectors.toList());
					for (RequirementsCalculation req : rc) {
						String a = req.getDelivery_date().replace("-", "/");
						Date dd = DateUtils.toDateWithSlash(a);
						Calendar cal = Calendar.getInstance();
						cal.setTime(dd);
						cal.add(cal.DATE, -req.getDelivery_leadtime());
						dd = cal.getTime();
						String delivery = DateUtils.getFormatDateWithSlash(dd);
						String day = DateUtils.getFormatDateWithSlash(
								DateUtils.addDay(DateUtils.getDate(), pm.getLeadtime() + req.getDelivery_leadtime()));
						if (zaiko < 0) {
							RequirementsCalculationResult res = Purchase(pm, req, Math.abs(zaiko), day);
							result.add(res);
						}
						if (req.getOm_fin_flg() == 0) {
							zaiko += (req.getOrder_qty()-req.getDue_qty());
							if (zaiko < 0) {
								int quantity = Math.abs(zaiko) / pm.getLot();
								int p = Math.abs(zaiko) % pm.getLot();
								if (p > 0) {
									quantity = quantity * pm.getLot() + pm.getLot();
								} else {
									quantity = quantity * pm.getLot();
								}
								RequirementsCalculationResult res = Order(pm, req, quantity, delivery);
								result.add(res);
								zaiko += quantity;
							}
						} else {
							zaiko -= req.getDue_qty();
							if (zaiko < 0) {
								int quantity = Math.abs(zaiko) / pm.getLot();
								int p = Math.abs(zaiko) % pm.getLot();
								if (p > 0) {
									quantity = quantity * pm.getLot() + pm.getLot();
								} else {
									quantity = quantity * pm.getLot();
								}
								RequirementsCalculationResult res = Purchase(pm, req, quantity, delivery);
								result.add(res);
								zaiko += quantity;
							}
						}
					}
					session.setAttribute("result", result);
				}
			} else {
				if (session.getAttribute("result") == null) {
					request.setAttribute("message", "発注の必要はありません");
					session.removeAttribute("control");
					return "requirements_calculation";
				} else {
					session.removeAttribute("control");
					System.out.println("セッションを削除しました");
					return "requirements_calculation_result";
				}
			}
			break;
		case "confirm":
			//セッションに保持しておいたListを取得
			result = (List<RequirementsCalculationResult>) session.getAttribute("result");
			for (int i = 0; i < result.size(); i++) {
				RequirementsCalculationResult rcr = result.get(i);
				ProductMasterTable pm = dao.getProductMasterChoice(rcr.getProduct_no());
				//ログインセッションから情報を取得
				String user = (String) session.getAttribute("user_id");
				int insert = dao.insertOrder(rcr, rcr.getResult(), rcr.getDelivery_date(), user);
				if (insert == 1) {
					session.removeAttribute("result");
					request.setAttribute("message", "発注完了しました");
				} else {
					request.setAttribute("message", "うまく発注できませんでした");
				}
			}
			break;
		case "cancel":
			session.removeAttribute("control");
			session.removeAttribute("result");
			request.setAttribute("siji", "cancel");
			return "requirements_calculation";
		default:
		}
		//sijiの値を保持するためにsetAttributeする
		request.setAttribute("siji", order);
		cn++;
		session.setAttribute("control", cn);
		return "requirements_calculation";
	}

	public RequirementsCalculationResult Purchase(ProductMasterTable pm, RequirementsCalculation req, int a,
			String dead) {
		RequirementsCalculationResult res = new RequirementsCalculationResult();
		res.setProduct_no(req.getProduct_no());
		res.setProduct_name(pm.getProduct_name());
		res.setDelivery_date(dead);
		res.setOrder_qty(req.getOrder_qty());
		res.setDue_qty(req.getDue_qty());
		res.setSupplier_no(req.getSupplier_no());
		res.setResult(a);
		return res;
	}

	public RequirementsCalculationResult Order(ProductMasterTable pm, RequirementsCalculation req, int a, String dead) {
		RequirementsCalculationResult res = new RequirementsCalculationResult();
		res.setProduct_no(req.getProduct_no());
		res.setProduct_name(pm.getProduct_name());
		res.setDelivery_date(dead);
		res.setOrder_qty(req.getOrder_qty());
		res.setDue_qty(req.getDue_qty());
		res.setSupplier_no(req.getSupplier_no());
		res.setResult(a);
		return res;
	}
}
