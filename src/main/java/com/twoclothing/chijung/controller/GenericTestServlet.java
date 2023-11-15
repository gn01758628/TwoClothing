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

import com.twoclothing.chijung.service.MembersCouponService;
import com.twoclothing.chijung.service.MembersCouponServiceImpl;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
import com.twoclothing.model.dto.MembersCouponDTO;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.permissions.Permissions;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.generic.DAOSelector;
import com.twoclothing.utils.generic.GenericDAO;
import com.twoclothing.utils.generic.GenericService;
import com.twoclothing.utils.generic.QueryCondition;

// @MultipartConfig
//  fileSizeThreshold = 檔案小於這個值,檔案寫入內存,提高效率
//  maxFileSize = 單個檔案大小限制
//  maxRequestSize = 單個請求全部檔案的加總限制
//  單位是bytes( 1024bytes = 1KB )
//  超過maxFileSize或maxRequestSize都會拋出IegalStateException

@WebServlet("/servlet/front/generic/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GenericTestServlet extends HttpServlet {

	GenericService gs = GenericService.getInstance();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 二版測試
		// insert
//		System.out.println("========== inesrt ==========");
//		Permissions pm = new Permissions("AAA", "AAA");
//		gs.insert(pm);

		// update
//		System.out.println("========== update ==========");
//		pm= new Permissions("CCCCC", "AAA");
//		pm.setPermissionId(4);
//		gs.update(pm);

		// delete
//		System.out.println("========== delete ==========");
//		gs.delete(Permissions.class, 2);
//
//		// query1
//		System.out.println("========== query1 ==========");
//		Employee emp = gs.getByPrimaryKey(Employee.class, 3);
//
//		System.out.println(emp);
//		// query2
//		System.out.println("========== query2 ==========");
//		List<Employee> list1 = new ArrayList<>();
//
//		list1 = gs.getAll(Employee.class);
//
//		for (Employee enetity : list1) {
//			System.out.println(enetity);
//		}
//		// query3
//		System.out.println("========== query3 ==========");
//		List<Employee> list2 = new ArrayList<>();
//
//		list2 = gs.getAllDescByPK(Employee.class);
//
//		for (Employee enetity : list2) {
//			System.out.println(enetity);
//		}
//		// query4 目前僅提供 like,=,>,<,>=,<=,between 等運算子判斷
//		// !!複合查詢會導致Servlet要提前處理業務邏輯 所以拿掉通用service中複合查詢的功能 如有需要請在自己的Servie調用通用DAO
//		System.out.println("========== query4 ==========");
//		List<Employee> list3 = new ArrayList<>();
//		//生成通用DAO方法
//		GenericDAO dao = DAOSelector.getDAO(Employee.class);
//
//		QueryCondition qc = new QueryCondition();
//		qc.toMap("and", "empId", "between", 2, 8);
//		qc.toMap("and", "deptId", "=", 2);
//		list3 = dao.getByQueryConditions(qc.getConditionList());
//
//		for (Employee enetity : list3) {
//			System.out.println(enetity);
//		}
//		// query5 查詢資料總筆數
//		System.out.println(gs.getTotal(Employee.class));
//		// query6 依單一條件查詢
//		List<Employee> list4 = new ArrayList<>();
//		list4 = gs.getBy(Employee.class, "deptId", 2);
//		for (Employee enetity : list4) {
//			System.out.println(enetity);
//			System.out.println(enetity.getFormatEmpId());
//		}
//		
//		// 
//		ItemDAO itDao = new ItemHibernateDAO(HibernateUtil.getSessionFactory());
//		
//		List<Item> list = itDao.getAllSubByTagId(1);
//		for(Item item : list) {
//			System.out.println(item);
//		}
//		
//		System.out.println("==================================");
//		
//		list = itDao.getAllSubByTagId(2);
//		for(Item item : list) {
//			System.out.println(item);
//		}
//		
//		System.out.println("==================================");
		MembersCouponService mcs = new MembersCouponServiceImpl();
		List<MembersCouponDTO> mcList = mcs.getAllMembersCouponDTOByMemberId(1);
		
		for(MembersCouponDTO item : mcList) {
			System.out.println(item);
		}
		
		//新增判斷是否關聯到資料庫 如果沒有就在console印出XXX類別沒有關聯資料庫不予受理
		GenericDAO dao1 = DAOSelector.getDAO(String.class);
		
	}
}
