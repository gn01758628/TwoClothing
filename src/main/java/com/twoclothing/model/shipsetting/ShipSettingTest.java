package com.twoclothing.model.shipsetting;

import java.util.List;

public class ShipSettingTest {
    public static void main(String[] args) {

        ShipSetting s1 = new ShipSetting(null, 10, "黃希維", "0964227786", "嘉義縣布袋鎮太平路21號");
        ShipSetting s2 = new ShipSetting(null, 20, "葉愛修", "0966142594", "台南市安平區觀音街22號");
        ShipSetting s3 = new ShipSetting(null, 30, "鄭俊霖", "0992981747", "台南市下營區九分街11號");
        ShipSetting s4 = new ShipSetting(null, 10, "林依潔", "0909464812", "台中市東勢區新盛七街22號");
        ShipSetting s5 = new ShipSetting(null, 10, "張鈺琳", "0947369960", "高雄市大樹區統嶺路16號");
        ShipSetting s6 = new ShipSetting(5, 40, "周睿意", "0975190717", "彰化縣彰化市永泰街29號");
        ShipSetting s7 = new ShipSetting(6, 50, "郭怡茜", "0992346930", "南投縣國姓鄉中正路23號");

        // ShipSettingTest
        ShipSettingDAO shipSettingDAO = new ShipSettingJDBCDAO();

        // 插入測試
        ShipSetting[] arr = {s1, s2, s3, s4, s5};
        for (ShipSetting s : arr) {
            shipSettingDAO.insert(s);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢測試
        // 單筆查詢
        ShipSetting byPrimaryKey = shipSettingDAO.getByPrimaryKey(1);
        ShipSetting byPrimaryKey1 = shipSettingDAO.getByPrimaryKey(6);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey1);

        System.out.println("=====================================================================================================================================");

        // 全部查詢
        List<ShipSetting> list = shipSettingDAO.getAll();
        for (ShipSetting s : list) {
            System.out.println(s);
        }

        System.out.println("=====================================================================================================================================");

        // 查詢某個會員的物流設定
        List<ShipSetting> list2 = shipSettingDAO.getAllByMbrId(10);
        for (ShipSetting s : list2) {
            System.out.println(s);
        }

        System.out.println("=====================================================================================================================================");

        // 修改測試
        shipSettingDAO.update(s6);
        shipSettingDAO.update(s7);

        System.out.println("=====================================================================================================================================");

        //刪除測試
        shipSettingDAO.delete(4);
        shipSettingDAO.delete(6);
    }
}
