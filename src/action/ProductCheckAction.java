package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Product_Master_Stock_CheckDAO;
import tool.Action;

public class ProductCheckAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Product_Master_Stock_CheckDAO pmsc = new Product_Master_Stock_CheckDAO();
		pmsc.Stock_Checker();
		return "";
	}

}
