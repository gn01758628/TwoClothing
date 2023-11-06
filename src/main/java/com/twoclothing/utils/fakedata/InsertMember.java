package com.twoclothing.utils.fakedata;

import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/insertMember")
public class InsertMember extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        MembersDAO membersDAO = new MembersHibernateDAO(sessionFactory);
        String[] names = {"阿強", "小娟", "樂樂", "曉曉", "老張", "貓貓", "天天", "小飛", "笨笨", "兔子"};
        // 新增資料
        for (int i = 1; i <= 10; i++) {
            Members members = new Members();
            members.setMbrName(names[i - 1]);
            String email = String.format("test%02d@gmail.com", i);
            members.setEmail(email);
            String psw = String.format("test%02d", i);
            members.setPswdHash(psw);
            membersDAO.insert(members);
        }

        // 修改帳號狀態
        List<Members> mbrList = membersDAO.getAll();
        for (Members mbr : mbrList) {
            mbr.setMbrStatus(1);
        }
    }
}
