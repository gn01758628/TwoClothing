package com.twoclothing.utils.fakedata;

import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
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
import java.util.List;
import java.util.Random;

@WebServlet("/insertItem")
public class InsertItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ItemDAO itemDAO = new ItemHibernateDAO(sessionFactory);
        CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);
        List<Integer> tagIdList = categoryTagsDAO.getTagIdsWithoutChildren();
        Random random = new Random();
        for (int i = 1; i <= 50; i++) {
            Item item = new Item();
            String name = String.format("一般商品%02d", i);
            item.setItemName(name);
            int grade = random.nextInt(4);
            item.setGrade(grade);
            int size = random.nextInt(8);
            item.setSize(size);
            String detail = String.format("一般%02d的描述", i);
            item.setDetail(detail);
            int index = random.nextInt(tagIdList.size());
            item.setTagId(tagIdList.get(index));
            int mbrId = random.nextInt(2) + 1;
            item.setMbrId(mbrId);
            int price = (random.nextInt(200) + 1) * 50;
            item.setPrice(price);
            item.setItemStatus(0);
            int quantity = random.nextInt(20) + 1;
            item.setQuantity(quantity);
            itemDAO.insert(item);
        }
    }
}
