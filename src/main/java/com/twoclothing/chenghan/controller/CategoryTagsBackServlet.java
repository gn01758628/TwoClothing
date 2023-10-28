package com.twoclothing.chenghan.controller;

import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/back/tags/*")
public class CategoryTagsBackServlet extends HttpServlet {

    // 簡單的CRUD捨棄service

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
        TODO 判定員工是否有此項功能的權限
        */

        String servletPath = request.getServletPath() + request.getPathInfo();
        switch (servletPath) {
            case "/back/tags/list" -> doList(request, response);
            case "/back/tags/add" -> doAdd(request, response);
            case "/back/tags/save" -> doSave(request, response);
            case "/back/tags/modify" -> doModify(request, response);
            case "/back/tags/update" -> doUpdate(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryTags> categoryTags = categoryTagsDAO.getAll();
        Map<Integer,String> tagsName = new HashMap<>();
        for (CategoryTags tags: categoryTags) {
            tagsName.put(tags.getTagId(),tags.getCategoryName());
        }
        request.setAttribute("categoryTags", categoryTags);
        request.setAttribute("tagsName", tagsName);
        request.getRequestDispatcher("/back_end/categorytags/CategoryTagsList.jsp").forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryTags> categoryTags = categoryTagsDAO.getAll();
        request.setAttribute("categoryTags", categoryTags);
        request.getRequestDispatcher("/back_end/categorytags/CategoryTagsAdd.jsp").forward(request, response);
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String superTagId = request.getParameter("superTagId");
        String categoryName = request.getParameter("categoryName");

        // TODO empId先寫死,之後要從session取
        Integer empId = 9527;

        CategoryTags categoryTags = new CategoryTags(Integer.parseInt(superTagId),categoryName,empId);
        categoryTagsDAO.insert(categoryTags);
        response.sendRedirect(request.getContextPath() + "/back/tags/list");
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tagId = request.getParameter("tagId");
        CategoryTags tags = categoryTagsDAO.getByPrimaryKey(Integer.parseInt(tagId));
        Integer currentSuperTagId = tags.getSuperTagId();
        String currentCategoryName = tags.getCategoryName();
        List<CategoryTags> categoryTags = categoryTagsDAO.getAll();
        request.setAttribute("categoryTags", categoryTags);
        request.setAttribute("currentSuperTagId", currentSuperTagId);
        request.setAttribute("currentCategoryName",currentCategoryName);
        request.getRequestDispatcher("/back_end/categorytags/CategoryTagsModify.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String tagId = request.getParameter("tagId");
        String superTagId = request.getParameter("superTagId");
        String categoryName = request.getParameter("categoryName");

        // TODO empId先寫死,之後要從session取
        Integer empId = 9527;

        CategoryTags categoryTags = new CategoryTags();
        categoryTags.setTagId(Integer.parseInt(tagId));
        categoryTags.setSuperTagId(Integer.parseInt(superTagId));
        categoryTags.setCategoryName(categoryName);
        categoryTags.setEmpId(empId);
        categoryTagsDAO.update(categoryTags);
        response.sendRedirect(request.getContextPath() + "/back/tags/list");
    }
}
