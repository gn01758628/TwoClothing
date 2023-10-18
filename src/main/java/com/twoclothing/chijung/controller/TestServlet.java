package com.twoclothing.chijung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditembrowsing.BidItemBrowsing;
import com.twoclothing.model.abid.biditembrowsing.BidItemBrowsing.CompositeDrtail;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.permissions.Permissions;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.test.generic.GenerciHibernateDAO;
import com.twoclothing.utils.test.generic.GenericService;
import com.twoclothing.utils.test.generic.QueryCondition;

// @MultipartConfig
//  fileSizeThreshold = 檔案小於這個值,檔案寫入內存,提高效率
//  maxFileSize = 單個檔案大小限制
//  maxRequestSize = 單個請求全部檔案的加總限制
//  單位是bytes( 1024bytes = 1KB )
//  超過maxFileSize或maxRequestSize都會拋出IegalStateException

@WebServlet("/servlet/front/generic/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TestServlet extends HttpServlet {

	GenericService gs = GenericService.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 二版測試
		// insert
		System.out.println("========== inesrt ==========");
		Permissions pm = new Permissions("AAA", "AAA");
		gs.insert(pm);

		// update
		System.out.println("========== update ==========");
		pm.setPermissionName("BBBBBBB");
		gs.update(pm);

		// delete
		System.out.println("========== delete ==========");
		gs.delete(Permissions.class, 2);

		// query1
		System.out.println("========== query1 ==========");
		Employee emp = gs.getByPK(Employee.class, 3);

		System.out.println(emp);
		// query2
		System.out.println("========== query2 ==========");
		List<Employee> list1 = new ArrayList<>();

		list1 = gs.getAll(Employee.class);

		for (Employee enetity : list1) {
			System.out.println(enetity);
		}
		// query3
		System.out.println("========== query3 ==========");
		List<Employee> list2 = new ArrayList<>();

		list2 = gs.getAllDescByPK(Employee.class);

		for (Employee enetity : list2) {
			System.out.println(enetity);
		}
		// query4 目前僅提供 like,=,>,<,>=,<=,between 等運算子判斷
		System.out.println("========== query4 ==========");
		List<Employee> list3 = new ArrayList<>();

		QueryCondition qc = new QueryCondition();
		qc.toMap("and", "empId", "between", 2, 8);
		qc.toMap("and", "deptId", "=", 2);

		list3 = gs.getByQueryConditions(Employee.class, qc.getConditionList());

		for (Employee enetity : list3) {
			System.out.println(enetity);
		}

	}
}
