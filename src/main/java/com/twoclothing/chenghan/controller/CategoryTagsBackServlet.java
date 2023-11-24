package com.twoclothing.chenghan.controller;

import com.twoclothing.chijung.controller.front_end.CategoryTagsSorter;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.employee.EmployeeDAO;
import com.twoclothing.model.employee.EmployeeHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/back_end/servlet/categoryTags/*")
public class CategoryTagsBackServlet extends HttpServlet {

    // 簡單的CRUD捨棄service

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);

    private final EmployeeDAO employeeDAO = new EmployeeHibernateDAO(sessionFactory);

    private ServletContext servletContext;

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/list" -> doList(request, response);
            case "/save" -> doSave(request, response);
            case "/modify" -> doModify(request, response);
            case "/update" -> doUpdate(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryTags> categoryTags = (List<CategoryTags>) servletContext.getAttribute("categoryTags");
        // 綁定標籤名稱(父標籤使用)
        Map<Integer, String> tagsName = new HashMap<>();
        for (CategoryTags tags : categoryTags) {
            tagsName.put(tags.getTagId(), tags.getCategoryName());
        }
        // 綁定員工名稱
        Map<Integer, String> empName = new HashMap<>();
        List<Employee> employeeList = employeeDAO.getAll();
        for (Employee emp : employeeList) {
            empName.put(emp.getEmpId(),emp.getEmpName());
        }
        // 綁定有子標籤的標籤
        List<CategoryTags> haveSubTagsList = categoryTagsDAO.getAllHaveSubTags();
        request.setAttribute("tagsName", tagsName);
        request.setAttribute("empName", empName);
        request.setAttribute("haveSubTagsList", haveSubTagsList);
        request.getRequestDispatcher("/back_end/categorytags/CategoryTagsList.jsp").forward(request, response);
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String superTagId = request.getParameter("superTagId");
        String categoryName = request.getParameter("categoryName");
        Integer empId = (Integer) request.getSession().getAttribute("empId");
        CategoryTags categoryTag = new CategoryTags(Integer.parseInt(superTagId), categoryName, empId);
        categoryTagsDAO.insert(categoryTag);
        // 更新綁定在ServletContext的數據
        List<CategoryTags> categoryTags = categoryTagsDAO.getAll();
        servletContext.setAttribute("categoryTags", categoryTags);
        sessionFactory.getCurrentSession().clear();
        servletContext.setAttribute("categoryTagsSortedList", CategoryTagsSorter.sortCategoryTags(categoryTags));
        response.sendRedirect(request.getContextPath() + "/back_end/servlet/categoryTags/list");
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tagId = request.getParameter("tagId");
        CategoryTags currentTag = categoryTagsDAO.getByPrimaryKey(Integer.parseInt(tagId));
        request.setAttribute("currentTag", currentTag);
        request.getRequestDispatcher("/back_end/categorytags/CategoryTagsModify.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String tagId = request.getParameter("tagId");
        String superTagId = request.getParameter("superTagId");
        String categoryName = request.getParameter("categoryName");
        Integer empId = (Integer) request.getSession().getAttribute("empId");

        CategoryTags categoryTag = new CategoryTags();
        categoryTag.setTagId(Integer.parseInt(tagId));
        categoryTag.setSuperTagId(Integer.parseInt(superTagId));
        categoryTag.setCategoryName(categoryName);
        categoryTag.setEmpId(empId);
        categoryTagsDAO.update(categoryTag);
        // 更新綁定在ServletContext的數據
        List<CategoryTags> categoryTags = categoryTagsDAO.getAll();
        servletContext.setAttribute("categoryTags", categoryTags);
        sessionFactory.getCurrentSession().clear();
        servletContext.setAttribute("categoryTagsSortedList", CategoryTagsSorter.sortCategoryTags(categoryTags));
        response.sendRedirect(request.getContextPath() + "/back_end/servlet/categoryTags/list");
    }
}
