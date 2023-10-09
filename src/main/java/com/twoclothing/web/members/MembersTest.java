package com.twoclothing.web.members;

public class MembersTest {
    public static void main(String[] args) {
        Members m1 = new Members("email1@example.com", "hash1", 0);
        Members m2 = new Members("email2@example.com", "hash2", 1);
        Members m3 = new Members("email3@example.com", "hash3", 0);
        Members m4 = new Members("email4@example.com", "hash4", 1);
        Members m5 = new Members("email5@example.com", "hash5", 0);
        Members m6 = new Members("email6@example.com", "hash6", 0);
        Members m7 = new Members("email7@example.com", "hash7", 1);
        Members m8 = new Members("email8@example.com", "hash8", 0);
        Members m9 = new Members("email9@example.com", "hash9", 0);
        Members m10 = new Members("email10@example.com", "hash10", 1);

        // MembersTest
        MembersDAO membersDAO = new MembersJDBCDAO();

        // 插入測試
        Members[] arr = {m1, m2, m4, m5, m6, m7, m8, m9, m10};
        for (Members m : arr) {
            membersDAO.insert(m);
        }

        System.out.println("=====================================================================================================================================");
    }
}
