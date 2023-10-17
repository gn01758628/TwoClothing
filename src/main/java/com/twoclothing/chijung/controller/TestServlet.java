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
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.test.generic.GenerciHibernateDAO;
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

    GenerciHibernateDAO<BidItem> bidItemHDAO = new GenerciHibernateDAO<>(BidItem.class);
    GenerciHibernateDAO<BidItemBrowsing> bidItemBrowsingHDAO = new GenerciHibernateDAO<>(BidItemBrowsing.class);
    GenerciHibernateDAO<Employee> employeeHDAO = new GenerciHibernateDAO<>(Employee.class,HibernateUtil.getSessionFactory());

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	QueryCondition qc = new QueryCondition();
    	qc.toMap("and","empId","=",5);
    	qc.toMap("and","empName","like","七");
    	qc.toMap("and","empStatus","between",0,1);
//    	qc.printList();
    
    	List<Employee> B3 = new ArrayList<>();
    	B3 = employeeHDAO.getByQueryConditions(qc.getList());
    	for (Object item : B3) {
    		System.out.println(item);
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	BidItem A1 = new BidItem();
//    	List<BidItem> B1 = new ArrayList<>();
//    	List<BidItem> C1 = new ArrayList<>();
//    	A1=bidItemHDAO.getByPK(10);
//    	B1=bidItemHDAO.getAll();
//    	C1=bidItemHDAO.getAllDescByPK();
//    	System.out.println(A1);
//    	System.out.println("================");
//    	for (Object item : B1) {
//    		System.out.println(item);
//    	}
//    	System.out.println("================");
//    	for (Object item : C1) {
//    		System.out.println(item);
//    	}
    	
    	
//==========================================================
    	
    	
//    	BidItemBrowsing A2 = new BidItemBrowsing();
//    	List<BidItemBrowsing> B2 = new ArrayList<>();
//    	List<BidItemBrowsing> C2 = new ArrayList<>();
//    	CompositeDrtail AA2 = new CompositeDrtail();
//    	AA2.setBiditemid(2);
//    	AA2.setMbrId(2);
//    	A2.setCompositeKey(AA2);
//    	
//    	A2=bidItemBrowsingHDAO.getByPK(A2.getCompositeKey());
//    	B2=bidItemBrowsingHDAO.getAll();
//    	C2=bidItemBrowsingHDAO.getAllDescByPK();
//    	System.out.println(A2);
//    	System.out.println("================");
//    	for (Object item : B2) {
//    		System.out.println(item);
//    	}
//    	System.out.println("================");
//    	for (Object item : C2) {
//    		System.out.println(item);
//    	}
    }
}
