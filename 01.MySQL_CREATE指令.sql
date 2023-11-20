DROP
DATABASE IF EXISTS twoclothing;
CREATE
DATABASE twoclothing;
USE
twoclothing;

-- 表格：會員 刪除
DROP TABLE IF EXISTS members;

-- 表格：會員 創建
CREATE TABLE members
(
    mbrid      INT AUTO_INCREMENT NOT NULL,
    mbrname      VARCHAR(20),
    email      VARCHAR(64) NOT NULL UNIQUE,
    pswdhash   VARCHAR(200) NOT NULL,
    mbrstatus  TINYINT     NOT NULL DEFAULT 0,
    avatar     MEDIUMBLOB,
    shopimg01  MEDIUMBLOB,
    shopimg02  MEDIUMBLOB,
    mbrpoint   INT                  DEFAULT 0,
    balance    INT                  DEFAULT 0,
    buystar    INT                  DEFAULT 0,
    buyrating  INT                  DEFAULT 0,
    sellstar   INT                  DEFAULT 0,
    sellrating INT                  DEFAULT 0,
    lastlogin  DATETIME,
    sellscore  INT                  DEFAULT 10,
    buyscore   INT                  DEFAULT 10,
    PRIMARY KEY (mbrid)
);

-- 表格：會員 插入假資料
INSERT INTO members (mbrname, email, pswdhash, mbrstatus, sellscore, buyscore)
VALUES
('Michael', 'email1@example.com', '$2a$12$e5CLbQ8r4jzDTQT6h9u.du4mwEM3HHaxlanlucVfO2i2hqQkQK1ke', 1, 10, 4),
('Mike', 'email2@example.com', '$2a$12$8fhjN/SuV4L/luQYQurF2uN/3IIg1NSjevfFUEKPEJJAjwnt83kJy', 1, 4, 10),
('Devil', 'email3@example.com', '$2a$12$Q.1PI/LSraLNS7gLNcS5y.0nAdv4GesHhBBZ1gJfkYiYr7Q8/Ia92', 1, 10, 4),
('Angel', 'email4@example.com', '$2a$12$6MgEzHeb5rxKVQ/e.V/emOt4GkFNAh3Ew5itTYVS1RFXlx3QShdjC', 0, 10, 10),
('Water', 'email5@example.com', '$2a$12$hKYPF0XYTkuE5ivLQkaOVe1S7NCgovGs1EujF/H4LvdCkO5uvk6ue', 0, 3, 10),
('Fire', 'email6@example.com', '$2a$12$mg7cnyiO6BgVnZwUDxrgbOjk6iAWCPj6QEAfd/Kv19KCUL9VFIAoK', 1, 10, 2),
('Sky', 'email7@example.com', '$2a$12$ho93cpKczWNVyWQnpt7R/uh66yNSH.HHW5haONW1lZ73a5j8VgKGW', 0, 1, 10),
('Apple', 'email8@example.com', '$2a$12$WzxCmLKfAB3LCDzmGtCNFeGMnjLzYiCa.xvrxLFteHjGu.gv0t64W', 0, 10, 3),
('Banana', 'email9@example.com', '$2a$12$19b7DEnLMXMjAYi.m8lwMuMnlZy/KgaKDHP3HYZGpA17zto6di3Ze', 1, 2, 10),
('John', 'email10@example.com', '$2a$12$/J1o6tKA8PC/X70t5MoaGejVpL3NkaoXvl8i0.iZj5l6qh2bJ582q', 0, 1, 7);

-- 表格：會員 檢查
-- SELECT * FROM Members;
-- DESCRIBE Members;
-- ================================================================================================================================ --
-- 表格：會員關注賣家 刪除
DROP TABLE IF EXISTS follow;

-- 表格：會員關注賣家 創建
CREATE TABLE follow
(
    mbrid    INT NOT NULL,
    followid INT NOT NULL,
    PRIMARY KEY (mbrid, followid)
);

-- 表格：會員關注賣家 插入假資料
INSERT INTO follow (mbrid, followid)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (3, 4),
       (4, 5),
       (5, 3),
       (6, 2),
       (7, 1),
       (8, 4),
       (9, 5);

-- 表格：會員關注賣家 檢查
-- SELECT * FROM follow;
-- DESCRIBE follow;

-- 表格：會員關注賣家 添加FK
-- ALTER TABLE follow
-- ADD CONSTRAINT fk_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_followid FOREIGN KEY (followid) REFERENCES Members(mbrid);
-- ================================================================================================================================ --
-- 表格：會員黑名單 刪除
DROP TABLE IF EXISTS blacklist;

-- 表格：會員黑名單 創建
CREATE TABLE blacklist
(
    mbrid   INT NOT NULL,
    blackid INT NOT NULL,
    PRIMARY KEY (mbrid, blackid)
);

-- 表格：會員黑名單 插入假資料
INSERT INTO blacklist (mbrid, blackid)
VALUES (1, 5),
       (1, 7),
       (2, 1),
       (3, 2),
       (4, 3),
       (5, 6),
       (6, 1),
       (7, 1),
       (8, 9),
       (9, 10);

-- 表格：會員黑名單 檢查
-- SELECT * FROM blacklist;
-- DESCRIBE blacklist;

-- 表格：會員黑名單 添加FK
-- ALTER TABLE blacklist
-- ADD CONSTRAINT fk_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_blackid FOREIGN KEY (blackid) REFERENCES Members(mbrid);
-- ================================================================================================================================ --
-- 表格：優惠卷 刪除
DROP TABLE IF EXISTS coupon;

-- 表格：優惠卷 創建
CREATE TABLE coupon
(
    cpnid      INT AUTO_INCREMENT NOT NULL,
    cpnname    VARCHAR(20),
    tagid      INT      NOT NULL,
    empid      INT      NOT NULL,
    createdate DATETIME NOT NULL,
    expiredate DATETIME,
    distype    TINYINT  NOT NULL,
    disvalue   INT      NOT NULL,
    minamount  INT DEFAULT 0,
    PRIMARY KEY (cpnid)
);

-- 表格：優惠卷 插入假資料
INSERT INTO coupon (cpnname, tagid, empid, createdate, expiredate, distype, disvalue, minamount)
VALUES ('優惠卷1', 1, 1, '2023-10-01', '2024-11-01', 0, 100, 500),
       ('優惠卷2', 2, 2, '2023-10-02', '2024-11-02', 1, 20, 0),
       ('優惠卷3', 3, 3, '2023-10-03', NULL, 0, 50, 550),
       ('優惠卷4', 4, 4, '2023-10-04', '2023-11-04', 1, 10, 0),
       ('優惠卷5', 5, 5, '2023-10-05', NULL, 0, 30, 200),
       ('優惠卷6', 6, 6, '2023-10-06', NULL, 0, 25, 300),
       ('優惠卷7', 7, 7, '2023-10-07', '2024-11-07', 0, 80, 200),
       ('優惠卷8', 8, 8, '2023-10-08', '2023-11-08', 1, 15, 0),
       ('優惠卷9', 9, 9, '2023-10-09', NULL, 0, 40, 350),
       ('優惠卷10', 10, 10, '2023-10-10', NULL, 0, 35, 400);

-- 表格：優惠卷 檢查
-- SELECT * FROM coupon;
-- DESCRIBE coupon;

-- 表格：優惠卷 添加FK
-- ALTER TABLE coupon
-- ADD CONSTRAINT fk_tagid FOREIGN KEY (tagid) REFERENCES categorytags(tagid),
-- ADD CONSTRAINT fk_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：會員優惠卷 刪除
DROP TABLE IF EXISTS memberscoupon;

-- 表格：會員優惠卷 創建
CREATE TABLE memberscoupon
(
    mbrid     INT     NOT NULL,
    cpnid     INT     NOT NULL,
    usedate   DATETIME DEFAULT NULL,
    cpnstatus TINYINT NOT NULL,
    PRIMARY KEY (mbrid, cpnid)
);

-- 表格：會員優惠卷 插入假資料
INSERT INTO memberscoupon (mbrid, cpnid, usedate, cpnstatus)
VALUES (1, 1, '2023-10-02', 1),
       (2, 2, NULL, 0),
       (1, 3, NULL, 2),
       (1, 4, '2023-10-04', 1),
       (2, 5, NULL, 0),
       (2, 6, '2023-10-07', 1),
       (3, 7, NULL, 1),
       (3, 8, '2023-10-09', 0),
       (2, 9, NULL, 2),
       (1, 10, NULL, 2);

-- 表格：會員優惠卷 檢查
-- SELECT * FROM memberscoupon;
-- DESCRIBE memberscoupon;

-- 表格：會員優惠卷 添加FK
-- ALTER TABLE memberscoupon
-- ADD CONSTRAINT fk_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_cpnid FOREIGN KEY (cpnid) REFERENCES coupon(cpnid);
-- ================================================================================================================================ --
-- 表格：會員點數異動紀錄 刪除
DROP TABLE IF EXISTS pointhistory;

-- 表格：會員點數異動紀錄 創建
CREATE TABLE pointhistory
(
    pointid    INT AUTO_INCREMENT NOT NULL,
    mbrid      INT      NOT NULL,
    orderid    INT		NOT NULL,
    changedate    DATETIME NOT NULL,
    changevalue   INT      NOT NULL,
    PRIMARY KEY (pointid)
);

-- 表格：會員點數異動紀錄 插入假資料
INSERT INTO pointhistory (mbrid, orderid, changedate, changevalue)
VALUES (1, 101, '2023-10-02', 50),
       (2, 201, '2023-10-05', -30),
       (3, 102, '2023-10-03', 20),
       (4, 103, '2023-10-04', 40),
       (5, 202, '2023-10-06', -15),
       (6, 104, '2023-10-01', 25),
       (7, 203, '2023-10-07', -10),
       (8, 105, '2023-10-09', 15),
       (9, 106, '2023-10-08', 10),
       (10, 204, '2023-10-10', -5);

-- 表格：會員點數異動紀錄 檢查
-- SELECT * FROM pointhistory;
-- DESCRIBE pointhistory;

-- 表格：會員點數異動紀錄 添加FK
-- ALTER TABLE pointhistory
-- ADD CONSTRAINT fk_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_orderid FOREIGN KEY (orderid) REFERENCES itemorder(orderid),
-- ================================================================================================================================ --
-- 表格：會員虛擬錢包提款申請 刪除
DROP TABLE IF EXISTS withdrawrequest;

-- 表格：會員虛擬錢包提款申請 創建
CREATE TABLE withdrawrequest
(
    wrid      INT AUTO_INCREMENT NOT NULL,
    mbrid     INT         NOT NULL,
    amount    INT         NOT NULL,
    mbraccount   VARCHAR(20) NOT NULL,
    reqdate   DATETIME    NOT NULL,
    reqstatus TINYINT     NOT NULL DEFAULT 0 ,
    empid     INT,
    checkdate DATETIME,
    note      VARCHAR(20),
    PRIMARY KEY (wrid)
);

-- 表格：會員虛擬錢包提款申請 插入假資料
INSERT INTO withdrawrequest (mbrid, amount, mbraccount, reqdate, reqstatus, empid, checkdate, note)
VALUES (1, 500, '1234567890', '2023-10-01', 0, NULL, NULL, '申請中'),
       (2, 300, '9876543210', '2023-10-02', 0, NULL, NULL, '申請中'),
       (3, 800, '5678901234', '2023-10-03', 0, NULL, NULL, '申請中'),
       (4, 600, '3456789012', '2023-10-04', 0, NULL, NULL, '申請中'),
       (5, 700, '6789012345', '2023-10-05', 0, NULL, NULL, '申請中'),
       (6, 200, '2345678901', '2023-10-06', 0, NULL, NULL, '申請中'),
       (7, 900, '4567890123', '2023-10-07', 0, NULL, NULL, '申請中'),
       (8, 400, '7890123456', '2023-10-08', 0, NULL, NULL, '申請中'),
       (9, 1000, '0123456789', '2023-10-09', 0, NULL, NULL, '申請中'),
       (10, 250, '9876123450', '2023-10-10', 0, NULL, NULL, '申請中');

-- 表格：會員虛擬錢包提款申請 檢查
-- SELECT * FROM withdrawrequest;
-- DESCRIBE withdrawrequest;

-- 表格：會員虛擬錢包提款申請 添加FK
-- ALTER TABLE withdrawrequest
-- ADD CONSTRAINT fk_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_empid FOREIGN KEY (empid) REFERENCES employee(employee);
-- ================================================================================================================================ --
-- 表格：會員虛擬錢包異動紀錄 刪除
DROP TABLE IF EXISTS balancehistory;

-- 表格：會員虛擬錢包異動紀錄 創建
CREATE TABLE balancehistory
(
    balanceid  INT AUTO_INCREMENT NOT NULL,
    mbrid      INT      NOT NULL,
    orderid    INT,
    bidorderid INT,
    wrid       INT,
    changedate    DATETIME NOT NULL,
    changevalue   INT      NOT NULL,
    PRIMARY KEY (balanceid)
);

-- 表格：會員虛擬錢包異動紀錄 插入假資料
INSERT INTO balancehistory (mbrid, orderid, bidorderid, wrid, changedate, changevalue)
VALUES (1, 101, NULL, NULL, '2023-10-01', 50),
       (2, NULL, 201, NULL, '2023-10-02', -30),
       (3, 102, NULL, NULL, '2023-10-03', 20),
       (4, 103, NULL, NULL, '2023-10-04', 40),
       (5, NULL, 202, NULL, '2023-10-05', -15),
       (6, 104, NULL, NULL, '2023-10-06', 25),
       (7, NULL, 203, NULL, '2023-10-07', -10),
       (8, 105, NULL, NULL, '2023-10-08', 15),
       (9, 106, NULL, NULL, '2023-10-09', 10),
       (10, NULL, 204, NULL, '2023-10-10', -5);

-- 表格：會員虛擬錢包異動紀錄 檢查
-- SELECT * FROM balancehistory;
-- DESCRIBE balancehistory;

-- 表格：會員虛擬錢包異動紀錄 添加FK
-- ALTER TABLE balancehistory
-- ADD CONSTRAINT fk_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_orderid FOREIGN KEY (orderid) REFERENCES itemorder(orderid),
-- ADD CONSTRAINT fk_bidorderid FOREIGN KEY (bidorderid) REFERENCES bidorder(bidorderid),
-- ADD CONSTRAINT fk_wrid FOREIGN KEY (wrid) REFERENCES withdrawrequest(wrid);
-- ================================================================================================================================ --
-- 表格：會員物流設定 刪除
DROP TABLE IF EXISTS shipsetting;

-- 表格：會員物流設定 創建
CREATE TABLE shipsetting
(
    shipid  INT AUTO_INCREMENT NOT NULL,
    mbrid   INT NOT NULL,
    receivename    VARCHAR(20) NOT NULL,
    receivephone   VARCHAR(10) NOT NULL,
    receiveaddress VARCHAR(100) NOT NULL,
    PRIMARY KEY (shipid)
);

-- 表格：會員物流設定 插入假資料（使用台灣手機號碼格式）
INSERT INTO shipsetting (mbrid, receivename, receivephone, receiveaddress)
VALUES (1, '收件人1', '0912345678', '台北市1號'),
       (2, '收件人2', '0923456789', '台北市2號'),
       (3, '收件人3', '0934567890', '台北市3號'),
       (4, '收件人4', '0945678901', '台北市4號'),
       (5, '收件人5', '0956789012', '台北市5號'),
       (6, '收件人6', '0967890123', '台北市6號'),
       (7, '收件人7', '0978901234', '台北市7號'),
       (8, '收件人8', '0989012345', '台北市8號'),
       (9, '收件人9', '0990123456', '台北市9號'),
       (10, '收件人10', '0911122334', '台北市10號');


-- 表格：會員物流設定 檢查
-- SELECT * FROM shipsetting;
-- DESCRIBE shipsetting;

-- 表格：會員物流設定 添加FK
-- ALTER TABLE shipsetting
-- ADD CONSTRAINT fk_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid);
-- ================================================================================================================================ --
-- 表格：聊天室紀錄 刪除
DROP TABLE IF EXISTS chatroomlog;

-- 表格：聊天室紀錄 創建
CREATE TABLE chatroomlog
(
    logid     INT AUTO_INCREMENT NOT NULL,
    receiveid INT,
    sentid    INT,
    empid     INT,
    message   VARCHAR(255) NOT NULL,
    messagetime      DATETIME     NOT NULL,
    PRIMARY KEY (logid)
);

-- 表格：聊天室紀錄 插入假資料
INSERT INTO chatroomlog (receiveid, sentid, empid, message, messagetime)
VALUES (1, 2, 101, 'Hello!', '2023-10-02 08:00:00'),
       (2, 1, 102, 'Hi there!', '2023-10-02 08:05:00'),
       (3, 4, 103, 'Good morning!', '2023-10-02 08:10:00'),
       (4, 3, 104, 'Hi, how are you?', '2023-10-02 08:15:00'),
       (5, 6, 105, 'Im doing great!', '2023-10-02 08:20:00'),
       (6, 5, 106, 'That s good to hear!', '2023-10-02 08:25:00'),
       (7, 8, 107, 'What are you up to?', '2023-10-02 08:30:00'),
       (8, 7, 108, 'Just working on some stuff.', '2023-10-02 08:35:00'),
       (9, 10, 109, 'Anything interesting?', '2023-10-02 08:40:00'),
       (10, 9, 110, 'Not really, just the usual.', '2023-10-02 08:45:00');

-- 表格：聊天室紀錄 檢查
-- SELECT * FROM chatroomlog;
-- DESCRIBE chatroomlog;

-- 表格：聊天室紀錄 添加FK
-- ALTER TABLE chatroomlog
-- ADD CONSTRAINT fk_receiveid FOREIGN KEY (receiveid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_sentid FOREIGN KEY (sentid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：員工 刪除
DROP TABLE IF EXISTS employee;

-- 表格：員工 創建
CREATE TABLE employee
(
    empid     INT AUTO_INCREMENT NOT NULL,
    deptid    INT         NOT NULL,
    empname   VARCHAR(30)          DEFAULT '',
    phone     VARCHAR(10)          DEFAULT '',
    address   VARCHAR(100)         DEFAULT '',
    email     VARCHAR(64)          DEFAULT '',
    pswdhash  VARCHAR(60) NOT NULL DEFAULT '',
    empstatus TINYINT              DEFAULT 0,
    avatar    MEDIUMBLOB,
    empmissions	VARCHAR(8) NOT NULL DEFAULT '00000000',
    PRIMARY KEY (empid)
);

-- 表格：員工 插入假資料
INSERT INTO employee (deptid, empname, phone, address, email, pswdhash, empstatus,empmissions)
VALUES (1, '張三', '0912345678', '台北市中正區123號', 'zhangsan@example.com', '$2a$12$ge8vavXJ721wWNdYQqEZdulwCYrH3M6ldh70uDlgAqWMMEY0.6dqG', 0,'11000000'),
       (2, '李四', '0923456789', '台北市大安區456號', 'lisi@example.com', '$2a$12$vnK8ufIPn8/wbqqR7qOn2uYc8Rbn3CbSSF5XHX/Mojy5abZAH0rOO', 1,'01100000'),
       (3, '王五', '0934567890', '台北市信義區789號', 'wangwu@example.com', '$2a$12$CLhZ6y0U10VIvlBHDqrWYOVQ.ZZHnL0KTQt6GfmXszWEFLaZC7612', 0,'00011100'),
       (1, '陳六', '0945678901', '台北市松山區101號', 'chenliu@example.com', '$2a$12$FvmYeCRuxXbOWclvTZ5.c.Dy4V9lh1BSWDXl5e7saB3JbS11eJY7i', 0,'00000011'),
       (2, '趙七', '0956789012', '台北市中山區202號', 'zhaoqi@example.com', '$2a$12$YjcqHX4GvXxGTH.vORZa3OBerkavVzCIvCafWzq7QRcZ301kiNRmW', 1,'11000000'),
       (3, '孫八', '0967890123', '台北市大同區303號', 'sunba@example.com', '$2a$12$kYFwxNGmGUjpPOMHK0o0gOLKHVDDXP32w9uiUBjE5NC1z9QsN3Lti', 0,'01100000'),
       (1, '周九', '0978901234', '台北市萬華區404號', 'zhoujiu@example.com', '$2a$12$AjaI67SmrK7rw.gE1xCVXe2tMROQsXC0bcohRJz7jIaImLOxg3l7a', 0,'00011100'),
       (2, '吳十', '0989012345', '台北市文山區505號', 'wushi@example.com', '$2a$12$NWXPKvXoOhoZRmY69s6Br.8DCEtNfaGx4GtjMO9L8LluGHSsmOTD2', 1,'00000011'),
       (3, '劉十一', '0990123456', '台北市南港區606號', 'liuyi@example.com', '$2a$12$BZkb90iKZZDpxXZs5iWE7OvI9TtIUk0TXNfY57fiF8rB9vLCwFaw.', 0,'11111111'),
       (1, '蔡十二', '0912233445', '台北市北投區707號', 'caishier@example.com', '$2a$12$kQRH5CBWSWW2jC2HjVyC6.YR7upxxaobWVqFUytWQtvWiXatpkuNS', 0,'00000000');

-- 表格：員工 檢查
-- SELECT * FROM employee;
-- DESCRIBE employee;

-- 表格：員工 添加FK
-- ALTER TABLE employee
-- ADD CONSTRAINT fk_deptid FOREIGN KEY (deptid) REFERENCES department(deptid);
-- ================================================================================================================================ --
-- 表格：部門 刪除
DROP TABLE IF EXISTS department;

-- 表格：部門 創建
CREATE TABLE department
(
    deptid   INT AUTO_INCREMENT NOT NULL, -- 部門編號
    deptname VARCHAR(20) DEFAULT '',      -- 部門名稱 (預設為空字串)
    PRIMARY KEY (deptid)
);

-- 表格：部門 插入假資料
INSERT INTO department (deptname)
VALUES ('IT Department'),
       ('Sales Department'),
       ('Marketing Department');

-- 表格：部門 檢查
-- SELECT * FROM department;
-- DESCRIBE department;
-- ================================================================================================================================ --
-- 表格：員工權限配置 刪除
DROP TABLE IF EXISTS empmissions;

-- 表格：員工權限配置 創建
CREATE TABLE empmissions
(
    empid        INT NOT NULL,
    permissionid INT NOT NULL,
    PRIMARY KEY (empid, permissionid)
);

-- 表格：員工權限配置 插入假資料
INSERT INTO empmissions (empid, permissionid)
VALUES (1, 1),(1, 2),
       (2, 2),(2, 3),
       (3, 5),(3, 6),(3, 7),
       (4, 7),(4, 8),
       (5, 1), (5, 2),
       (6, 2),(6, 3),
       (7, 7),(7, 8),
       (8, 1),(8, 2),
       (9, 2), (9, 3),
       (10, 5),(10, 6),(10, 7);

-- 表格：員工權限配置 檢查
-- SELECT * FROM empmissions;
-- DESCRIBE empmissions;

-- 表格：員工權限配置 添加FK
-- ALTER TABLE empmissions
-- ADD CONSTRAINT fk_empmissions_empid FOREIGN KEY (empid) REFERENCES employee(empid),
-- ADD CONSTRAINT fk_empmissions_permissionid FOREIGN KEY (permissionid) REFERENCES permissions(permissionid);
-- ================================================================================================================================ --
-- 表格：權限清單 刪除
DROP TABLE IF EXISTS permissions;

-- 表格：權限清單 創建
CREATE TABLE permissions
(
    permissionid   INT AUTO_INCREMENT NOT NULL, -- 權限編號
    permissionname VARCHAR(10) DEFAULT '',      -- 權限名稱 (預設為空字串)
    descriptions   VARCHAR(40) DEFAULT '',      -- 描述 (預設為空字串)
    PRIMARY KEY (permissionid)
);

-- 表格：權限清單 插入假資料
INSERT INTO permissions (permissionname, descriptions)
VALUES ('競標管理', '管理競標商品上下架'),
	   ('商城管理', '管理一般商品類別及優惠券發放'),
	   ('最新消息管理', '最新消息刊登及編輯'),
	   ('商品檢舉管理', '檢舉查核'),
	   ('一般訂單管理', '一般商品訂單退貨、檢舉處理'),
	   ('競標訂單管理', '競標商品訂單退貨、檢舉處理'),
       ('客服中心', '線上即時客服'),
       ('虛擬錢包提款審核', '提款申請查核');

-- 表格：權限清單 檢查
-- SELECT * FROM permissions;
-- DESCRIBE permissions;
-- ================================================================================================================================ --
-- 表格：商品類別標籤 刪除
DROP TABLE IF EXISTS categorytags;

-- 表格：商品類別標籤 創建
CREATE TABLE categorytags
(
    tagid        INT AUTO_INCREMENT NOT NULL,
    supertagid   INT,
    categoryname VARCHAR(20)  NOT NULL,
    empid        INT NOT NULL,
    PRIMARY KEY (tagid),
    CONSTRAINT fK_supertag
    FOREIGN KEY (supertagid)
    REFERENCES categorytags(tagid)
);

-- 表格：商品類別標籤 插入假資料
INSERT INTO categorytags (supertagid, categoryname, empid)
VALUES (NULL, '所有種類', 1),
       (1, '衣服', 1),
	   (1, '褲子', 1),
	   (1, '鞋子', 1),
	   (1, '飾品', 1),
       (2, 'T-shirt', 1),
	   (2, '長袖', 1),
       (3, '牛仔褲', 1),
	   (3, '休閒褲', 1),
       (4, '運動鞋', 1),
       (4, '休閒鞋', 1),
       (5, '項鏈', 1),
       (5, '耳環', 1);

-- 表格：商品類別標籤 檢查
-- SELECT * FROM categorytags;
-- DESCRIBE categorytags;

-- 表格：商品類別標籤 添加FK
-- ALTER TABLE categorytags
-- ADD CONSTRAINT fk_categorytags_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：最新消息 刪除
DROP TABLE IF EXISTS latestnews;

-- 表格：最新消息 創建
CREATE TABLE latestnews
(
    newsid    INT AUTO_INCREMENT NOT NULL, -- 消息編號
    title     VARCHAR(100)  DEFAULT '',    -- 標題 (預設為空字串)
    startdate DATETIME DEFAULT NULL,       -- 發佈時間
    enddate	  DATETIME DEFAULT NULL,	   -- 結束時間
    coverimage MEDIUMBLOB DEFAULT NULL,	   -- 封面圖
    content   MEDIUMTEXT,    			   -- 內容
    empid     INT NOT NULL,                -- 員工編號 (外鍵參照 employee 表格)
    PRIMARY KEY (newsid)
);

-- 表格：最新消息 插入假資料
INSERT INTO latestnews (title, content, startdate, empid)
VALUES ('New Product Launch', 'Introducing our latest product lineup!', '2023-10-01 09:00:00', 1),
       ('Special Promotion', 'Limited time discounts on selected items!', '2023-09-28 15:30:00', 2),
       ('Company Event', 'Join us for our annual company event!', '2023-09-25 14:00:00', 3),
       ('Holiday Closure', 'Our store will be closed for the holidays.', '2023-09-20 18:00:00', 4),
       ('Grand Opening', 'Celebrate the grand opening of our new store!', '2023-09-15 10:00:00', 5),
       ('Product Showcase', 'Explore our featured products in-store.', '2023-09-10 11:45:00', 6),
       ('Seasonal Sale', 'Enjoy discounts on seasonal items!', '2023-09-05 12:30:00', 7),
       ('Customer Appreciation', 'Thank you for your continued support!', '2023-09-01 13:15:00', 8),
       ('New Collection', 'Discover the latest arrivals in our collection.', '2023-08-28 14:20:00', 9),
       ('Store Renovation', 'Our store is undergoing renovations.', '2023-08-25 16:00:00', 10);

-- 表格：最新消息 檢查
-- SELECT * FROM latestnews;
-- DESCRIBE latestnews;

-- 表格：最新消息 添加FK
-- ALTER TABLE latestnews
-- ADD CONSTRAINT fk_latestnews_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：最新消息圖片 刪除
DROP TABLE IF EXISTS newsimages;

-- 表格：最新消息圖片 創建
CREATE TABLE newsimages
(
    imageid      INT AUTO_INCREMENT NOT NULL,
    newsid       INT NOT NULL,
    image        MEDIUMBLOB,
    descriptions VARCHAR(255) DEFAULT '',
    PRIMARY KEY (imageid)
);

-- 表格：最新消息圖片 插入假資料
INSERT INTO newsimages (newsid, image, descriptions)
VALUES (1, 'image_data_1', 'Image for News 1'),
       (2, 'image_data_2', 'Image for News 2'),
       (3, 'image_data_3', 'Image for News 3'),
       (4, 'image_data_4', 'Image for News 4'),
       (5, 'image_data_5', 'Image for News 5'),
       (6, 'image_data_6', 'Image for News 6'),
       (7, 'image_data_7', 'Image for News 7'),
       (8, 'image_data_8', 'Image for News 8'),
       (9, 'image_data_9', 'Image for News 9'),
       (10, 'image_data_10', 'Image for News 10');

-- 表格：最新消息圖片 檢查
-- SELECT * FROM newsimages;
-- DESCRIBE newsimages;

-- 表格：最新消息圖片 添加FK
-- ALTER TABLE newsimages
-- ADD CONSTRAINT fk_newsimages_newsid FOREIGN KEY (newsid) REFERENCES latestnews(newsid);
-- ================================================================================================================================ --
-- 表格：一般商品 刪除
DROP TABLE IF EXISTS item;

-- 表格：一般商品 創建
CREATE TABLE item (
    itemid INT AUTO_INCREMENT NOT NULL,
    itemname VARCHAR(20) NOT NULL,
    grade    TINYINT NOT NULL,
    size     TINYINT,
    detail VARCHAR(200),
    tagid INT NOT NULL,
    mbrid INT NOT NULL,
    price INT NOT NULL,
    itemstatus TINYINT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (itemid)
);

-- 表格：一般商品 插入假資料
INSERT INTO item (itemname, grade, size, detail, tagid, mbrid, price, itemstatus, quantity) VALUES
('Product 1', 0, 0, 'Description for Product 1', 1, 1, 100, 0, 10),
('Product 2', 1, 1, 'Description for Product 2', 2, 2, 150, 0, 15),
('Product 3', 2, 2, 'Description for Product 3', 1, 2, 75, 0, 20),
('Product 4', 3, 3, 'Description for Product 4', 3, 1, 120, 0, 12),
('Product 5', 1, 4, 'Description for Product 5', 2, 3, 90, 0, 8),
('Product 6', 2, 5, 'Description for Product 6', 3, 3, 200, 0, 5),
('Product 7', 2, 6, 'Description for Product 7', 1, 1, 80, 0, 18),
('Product 8', 3, 7, 'Description for Product 8', 2, 4, 130, 0, 14),
('Product 9', 3, 1, 'Description for Product 9', 1, 5, 110, 0, 11),
('Product 10', 1, 2, 'Description for Product 10', 3, 6, 160, 0, 7);

-- 表格：一般商品 檢查
-- SELECT * FROM item;
-- DESCRIBE item;

-- 表格：一般商品 添加FK
-- ALTER TABLE item
-- ADD CONSTRAINT fk_item_tagid FOREIGN KEY (tagid) REFERENCES categorytags(tagid),
-- ADD CONSTRAINT fk_item_mbrid FOREIGN KEY (mbrid) REFERENCES members(mbrid);
-- ================================================================================================================================ --
-- 表格：一般商品圖片 刪除
DROP TABLE IF EXISTS itemimage;

-- 表格：一般商品圖片 創建
CREATE TABLE itemimage
(
    imgid  INT AUTO_INCREMENT NOT NULL,
    itemid INT NOT NULL,
    image  MEDIUMBLOB,
    PRIMARY KEY (imgid)
);

-- 表格：一般商品圖片 插入假資料
INSERT INTO itemimage (itemid, image)
VALUES (1, 'image_data_1'),
       (2, 'image_data_2'),
       (3, 'image_data_3'),
       (4, 'image_data_4'),
       (5, 'image_data_5'),
       (6, 'image_data_6'),
       (7, 'image_data_7'),
       (8, 'image_data_8'),
       (9, 'image_data_9'),
       (10, 'image_data_10');

-- 表格：一般商品圖片 檢查
-- SELECT * FROM itemimage;
-- DESCRIBE itemimage;

-- 表格：一般商品圖片 添加FK
-- ALTER TABLE itemimage
-- ADD CONSTRAINT fk_itemimage_itemid FOREIGN KEY (itemid) REFERENCES item(itemid);
-- ================================================================================================================================ --
-- 表格：購物車明細 刪除
DROP TABLE IF EXISTS cartdetail;

-- 表格：購物車明細 創建
CREATE TABLE cartdetail
(
    cartid   INT AUTO_INCREMENT NOT NULL,
    mbrid    INT NOT NULL,
    itemid   INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (cartid)
);

-- 表格：購物車明細 插入假資料
INSERT INTO cartdetail (mbrid, itemid, quantity)
VALUES (1, 101, 1),
       (2, 102, 1),
       (3, 103, 1),
       (4, 104, 1),
       (5, 105, 1),
       (6, 106, 1),
       (7, 107, 1),
       (8, 108, 1),
       (9, 109, 1),
       (1, 110, 1);

-- 表格：購物車明細 檢查
-- SELECT * FROM cartdetail;
-- DESCRIBE cartdetail;

-- 表格：購物車明細 添加FK
-- ALTER TABLE cartdetail
-- ADD CONSTRAINT fk_cartdetail_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_cartdetail_itemid FOREIGN KEY (itemid) REFERENCES item(itemid);
-- ================================================================================================================================ --
-- 表格：一般商品訂單 刪除
DROP TABLE IF EXISTS itemorder;

-- 表格：一般商品訂單 創建
CREATE TABLE itemorder
(
    orderid          INT AUTO_INCREMENT NOT NULL,
    buymbrid         INT      NOT NULL,
    sellmbrid        INT      NOT NULL,
    buystar          INT,
    buyerratingdesc  VARCHAR(200),
    sellstar         INT,
    sellerratingdesc VARCHAR(200),
    orderdate        DATETIME NOT NULL,
    paytype          TINYINT,
    payinfo          VARCHAR(50),
    amount           INT      NOT NULL,
    pointdiscount    INT,
    finalamount      INT      NOT NULL,
    orderstatus      TINYINT  NOT NULL,
    receiveaddress          VARCHAR(100),
    receivename             VARCHAR(20),
    receivephone            VARCHAR(10),
    remarks          VARCHAR(200),
    PRIMARY KEY (orderid)
);

-- 表格：一般商品訂單 插入假資料
INSERT INTO itemorder (buymbrid, sellmbrid, buystar, buyerratingdesc, sellstar, sellerratingdesc, orderdate, paytype,
                       payinfo, amount, pointdiscount, finalamount, orderstatus, receiveaddress, receivename, receivephone, remarks)
VALUES (1, 10, 4, 'Good seller', 5, 'Excellent buyer', '2023-10-01 09:00:00', 0, 'Credit Card', 150, 0, 150, 3,
        '123 Main St, City', 'John Doe', '1234567890', 'N/A'),
       (2, 9, 5, 'Great transaction', 4, 'Responsive buyer', '2023-09-28 15:30:00', 1, 'Bank Transfer', 200, 0, 200, 3,
        '456 Elm St, Town', 'Jane Smith', '9876543210', 'Please handle with care'),
       (3, 8, 3, 'Decent seller', 3, 'Regular buyer', '2023-09-25 14:00:00', 0, 'Credit Card', 80, 0, 80, 3,
        '789 Oak St, Village', 'Robert Johnson', '5551234567', 'N/A'),
       (4, 7, 5, 'Excellent seller', 4, 'Polite buyer', '2023-09-20 18:00:00', 1, 'Bank Transfer', 120, 0, 120, 3,
        '101 Pine St, Town', 'Sarah Lee', '2223334444', 'Fragile items'),
       (5, 6, 4, 'Responsive seller', 5, 'Prompt buyer', '2023-09-15 10:00:00', 0, 'Credit Card', 90, 0, 90, 3,
        '202 Cedar St, City', 'David Wilson', '7778889999', 'Handle with care'),
       (6, 5, 3, 'Average seller', 4, 'Regular buyer', '2023-09-10 11:45:00', 2, 'Virtual Wallet', 60, 0, 60, 3,
        '303 Elm St, Village', 'Susan Brown', '1112223333', 'N/A'),
       (7, 4, 5, 'Outstanding seller', 5, 'Excellent buyer', '2023-09-05 12:30:00', 0, 'Credit Card', 70, 0, 70, 3,
        '404 Oak St, Town', 'Michael Davis', '9990001111', 'Please deliver on time'),
       (8, 3, 4, 'Polite seller', 3, 'Decent buyer', '2023-09-01 13:15:00', 1, 'Bank Transfer', 110, 0, 110, 3,
        '505 Pine St, City', 'Karen Miller', '4445556666', 'N/A'),
       (9, 2, 3, 'Average seller', 4, 'Prompt buyer', '2023-08-28 14:20:00', 2, 'Virtual Wallet', 130, 0, 130, 3,
        '606 Cedar St, Village', 'James Taylor', '8889990000', 'Handle with care'),
       (10, 1, 5, 'Excellent seller', 5, 'Excellent buyer', '2023-08-25 16:00:00', 0, 'Credit Card', 70, 0, 70, 3,
        '707 Oak St, City', 'Emily White', '2223334444', 'Fragile items');

-- 表格：一般商品訂單 檢查
-- SELECT * FROM itemorder;
-- DESCRIBE itemorder;

-- 表格：一般商品訂單 添加FK
-- ALTER TABLE itemorder
-- ADD CONSTRAINT fk_itemorder_buymbrid FOREIGN KEY (buymbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_itemorder_sellmbrid FOREIGN KEY (sellmbrid) REFERENCES Members(mbrid);
-- ================================================================================================================================ --
-- 表格：一般商品訂單明細 刪除
DROP TABLE IF EXISTS orderdetails;

-- 表格：一般商品訂單明細 創建
CREATE TABLE orderdetails
(
    orderid       INT NOT NULL,
    itemid        INT NOT NULL,
    quantity      INT NOT NULL,
    price         INT NOT NULL,
    discountprice INT,
    buyingprice   INT NOT NULL,
    PRIMARY KEY (orderid, itemid)
);

-- 表格：一般商品訂單明細 插入假資料
INSERT INTO orderdetails (orderid, itemid, quantity, price, discountprice, buyingprice)
VALUES (1, 10, 2, 100, NULL, 90),
       (1, 9, 3, 150, 10, 140),
       (2, 8, 1, 200, NULL, 180),
       (2, 7, 2, 80, NULL, 80),
       (3, 6, 1, 120, 20, 100),
       (3, 5, 4, 90, NULL, 85),
       (4, 4, 3, 60, NULL, 60),
       (4, 3, 2, 70, NULL, 70),
       (5, 2, 1, 110, NULL, 110),
       (5, 1, 2, 130, NULL, 130);

-- 表格：一般商品訂單明細 檢查
-- SELECT * FROM orderdetails;
-- DESCRIBE orderdetails;

-- 表格：一般商品訂單明細 添加FK
-- ALTER TABLE orderdetails
-- ADD CONSTRAINT fk_orderdetails_orderid FOREIGN KEY (orderid) REFERENCES itemorder(orderid),
-- ADD CONSTRAINT fk_orderdetails_itemid FOREIGN KEY (itemid) REFERENCES item(itemid);
-- ================================================================================================================================ --
-- 表格：一般商品訂單評價圖片 刪除
DROP TABLE IF EXISTS orderratingimage;

-- 表格：一般商品訂單評價圖片 創建
CREATE TABLE orderratingimage
(
    imageid INT AUTO_INCREMENT NOT NULL,
    orderid INT NOT NULL,
    image   MEDIUMBLOB,
    PRIMARY KEY (imageid)
);

-- 表格：一般商品訂單評價圖片 插入假資料
INSERT INTO orderratingimage (orderid, image)
VALUES (1, 'image_data_1'),
       (2, 'image_data_2'),
       (3, 'image_data_3'),
       (4, 'image_data_4'),
       (5, 'image_data_5'),
       (6, 'image_data_6'),
       (7, 'image_data_7'),
       (8, 'image_data_8'),
       (9, 'image_data_9'),
       (10, 'image_data_10');

-- 表格：一般商品訂單評價圖片 檢查
-- SELECT * FROM orderratingimage;
-- DESCRIBE orderratingimage;

-- 表格：一般商品訂單評價圖片 添加FK
-- ALTER TABLE orderratingimage
-- ADD CONSTRAINT fk_orderratingimage_orderid FOREIGN KEY (orderid) REFERENCES itemorder(orderid);
-- ================================================================================================================================ --
-- 表格：一般訂單通知 刪除
DROP TABLE IF EXISTS ordernotify;

-- 表格：一般訂單通知 創建
CREATE TABLE ordernotify
(
    notifyid   INT AUTO_INCREMENT NOT NULL,
    mbrid      INT          NOT NULL,
    orderid    INT          NOT NULL,
    notifydate DATETIME     NOT NULL,
    title      VARCHAR(20)  NOT NULL,
    content    VARCHAR(200) NOT NULL,
    PRIMARY KEY (notifyid)
);

-- 表格：一般訂單通知 插入假資料
INSERT INTO ordernotify (mbrid, orderid, notifydate, title, content)
VALUES (1, 1, '2023-10-01 09:00:00', '訂單更新', '您的訂單已更新。'),
       (2, 2, '2023-09-28 15:30:00', '訂單狀態', '您的訂單狀態已變更。'),
       (3, 3, '2023-09-25 14:00:00', '新訂單', '您有一個新的訂單。'),
       (4, 4, '2023-09-20 18:00:00', '訂單更新', '您的訂單已更新。'),
       (5, 5, '2023-09-15 10:00:00', '訂單狀態', '您的訂單狀態已變更。'),
       (6, 6, '2023-09-10 11:45:00', '新訂單', '您有一個新的訂單。'),
       (7, 7, '2023-09-05 12:30:00', '訂單更新', '您的訂單已更新。'),
       (8, 8, '2023-09-01 13:15:00', '訂單狀態', '您的訂單狀態已變更。'),
       (9, 9, '2023-08-28 14:20:00', '新訂單', '您有一個新的訂單。'),
       (10, 10, '2023-08-25 16:00:00', '訂單更新', '您的訂單已更新。');

-- 表格：一般訂單通知 檢查
-- SELECT * FROM ordernotify;
-- DESCRIBE ordernotify;

-- 表格：一般訂單通知 添加FK
-- ALTER TABLE ordernotify
-- ADD CONSTRAINT fk_ordernotify_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_ordernotify_orderid FOREIGN KEY (orderid) REFERENCES itemorder(orderid);
-- ================================================================================================================================ --
-- 表格：商品瀏覽紀錄 刪除
DROP TABLE IF EXISTS itembrowsing;

-- 表格：商品瀏覽紀錄 創建
CREATE TABLE itembrowsing
(
    itemid       INT      NOT NULL,
    mbrid        INT      NOT NULL,
    browsingtime DATETIME NOT NULL,
    PRIMARY KEY (itemid, mbrid)
);

-- 表格：商品瀏覽紀錄 插入假資料
INSERT INTO itembrowsing (itemid, mbrid, browsingtime)
VALUES (1, 1, '2023-10-01 09:00:00'),
       (2, 1, '2023-09-28 15:30:00'),
       (3, 1, '2023-09-25 14:00:00'),
       (4, 1, '2023-09-20 18:00:00'),
       (5, 1, '2023-09-15 10:00:00'),
       (6, 1, '2023-09-10 11:45:00'),
       (7, 1, '2023-09-05 12:30:00'),
       (8, 1, '2023-09-01 13:15:00'),
       (9, 1, '2023-08-28 14:20:00'),
       (10, 10, '2023-08-25 16:00:00');

-- 表格：商品瀏覽紀錄 檢查
-- SELECT * FROM itembrowsing;
-- DESCRIBE itembrowsing;

-- 表格：商品瀏覽紀錄 添加FK
-- ALTER TABLE itembrowsing
-- ADD CONSTRAINT fk_itembrowsing_itemid FOREIGN KEY (itemid) REFERENCES item(itemid),
-- ADD CONSTRAINT fk_itembrowsing_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid);
-- ================================================================================================================================ --
-- 表格：一般商品追蹤清單 刪除
DROP TABLE IF EXISTS itemtracking;

-- 表格：一般商品追蹤清單 創建
CREATE TABLE itemtracking
(
    itemid       INT      NOT NULL,
    mbrid        INT      NOT NULL,
    trackingtime DATETIME NOT NULL,
    PRIMARY KEY (itemid, mbrid)
);

-- 表格：一般商品追蹤清單 插入假資料
INSERT INTO itemtracking (itemid, mbrid, trackingtime)
VALUES (1, 1, '2023-10-01 09:00:00'),
       (2, 2, '2023-09-28 15:30:00'),
       (3, 3, '2023-09-25 14:00:00'),
       (4, 4, '2023-09-20 18:00:00'),
       (5, 5, '2023-09-15 10:00:00'),
       (6, 1, '2023-09-10 11:45:00'),
       (7, 1, '2023-09-05 12:30:00'),
       (8, 1, '2023-09-01 13:15:00'),
       (9, 1, '2023-08-28 14:20:00'),
       (10, 10, '2023-08-25 16:00:00');

-- 表格：一般商品追蹤清單 檢查
-- SELECT * FROM itemtracking;
-- DESCRIBE itemtracking;

-- 表格：一般商品追蹤清單 添加FK
-- ALTER TABLE itemtracking
-- ADD CONSTRAINT fk_itemtracking_itemid FOREIGN KEY (itemid) REFERENCES item(itemid),
-- ADD CONSTRAINT fk_itemtracking_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid);
-- ================================================================================================================================ --
-- 表格：一般商品檢舉 刪除
DROP TABLE IF EXISTS itemreport;

-- 表格：一般商品檢舉 創建
CREATE TABLE itemreport
(
    reportid    INT AUTO_INCREMENT NOT NULL,
    itemid      INT          NOT NULL,
    mbrid       INT          NOT NULL,
    empid       INT,
    reportdate  DATETIME     NOT NULL,
    description VARCHAR(200) NOT NULL,
    rstatus     TINYINT      NOT NULL,
    auditdate   DATETIME DEFAULT NULL,
    result      TINYINT,
    note        VARCHAR(200),
    PRIMARY KEY (reportid)
);

-- 表格：一般商品檢舉 插入假資料
INSERT INTO itemreport (itemid, mbrid, empid, reportdate, description, rstatus, auditdate, result, note)
VALUES (1, 1, 2, '2023-10-01 09:00:00', '商品存在問題', 1, '2023-09-25 14:00:00', 0, 'N/A'),
       (2, 2, 2, '2023-09-28 15:30:00', '可疑行為', 1, '2023-08-25 16:00:00', 1, 'N/A'),
       (3, 3, 3, '2023-09-25 14:00:00', '垃圾廣告', 1, '2023-10-01 09:00:00', 0, 'N/A'),
       (4, 4, NULL, '2023-09-20 18:00:00', '虛假宣傳', 0, NULL, NULL, 'N/A'),
       (5, 5, NULL, '2023-09-15 10:00:00', '侵權行為', 0, NULL, NULL, 'N/A'),
       (6, 6, NULL, '2023-09-10 11:45:00', '欺詐行為', 0, NULL, NULL, 'N/A'),
       (7, 7, NULL, '2023-09-05 12:30:00', '其他問題', 0, NULL, NULL, 'N/A'),
       (8, 8, NULL, '2023-09-01 13:15:00', '欺騙行為', 0, NULL, NULL, 'N/A'),
       (9, 9, NULL, '2023-08-28 14:20:00', '問題描述', 0, NULL, NULL, 'N/A'),
       (10, 10, NULL, '2023-08-25 16:00:00', '舉報內容', 0, NULL, NULL, 'N/A');

-- 表格：一般商品檢舉 檢查
-- SELECT * FROM itemreport;
-- DESCRIBE itemreport;

-- 表格：一般商品檢舉 添加FK
-- ALTER TABLE itemreport
-- ADD CONSTRAINT fk_itemreport_itemid FOREIGN KEY (itemid) REFERENCES item(itemid),
-- ADD CONSTRAINT fk_itemreport_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_itemreport_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：一般商品訂單檢舉 刪除
DROP TABLE IF EXISTS orderreport;

-- 表格：一般商品訂單檢舉 創建
CREATE TABLE orderreport
(
    reportid    INT AUTO_INCREMENT NOT NULL,
    orderid     INT          NOT NULL,
    empid       INT,
    reportdate  DATETIME     NOT NULL,
    description VARCHAR(200) NOT NULL,
    rstatus     TINYINT      NOT NULL,
    auditdate   DATETIME DEFAULT NULL,
    result      TINYINT,
    note        VARCHAR(200),
    PRIMARY KEY (reportid)
);

-- 表格：一般商品訂單檢舉 插入假資料
INSERT INTO orderreport (orderid, empid, reportdate, description, rstatus, auditdate, result, note)
VALUES (1, 2, '2023-10-01 09:00:00', '商品存在問題', 1, '2023-08-28 14:20:00', 1, 'N/A'),
       (2, 2, '2023-09-28 15:30:00', '可疑行為', 1, '2023-09-25 14:00:00', 1, 'N/A'),
       (3, NULL, '2023-09-25 14:00:00', '垃圾廣告', 0, NULL, NULL, 'N/A'),
       (4, NULL, '2023-09-20 18:00:00', '虛假宣傳', 0, NULL, NULL, 'N/A'),
       (5, NULL, '2023-09-15 10:00:00', '侵權行為', 0, NULL, NULL, 'N/A'),
       (6, NULL, '2023-09-10 11:45:00', '欺詐行為', 0, NULL, NULL, 'N/A'),
       (7, NULL, '2023-09-05 12:30:00', '其他問題', 0, NULL, NULL, 'N/A'),
       (8, NULL, '2023-09-01 13:15:00', '欺騙行為', 0, NULL, NULL, 'N/A'),
       (9, NULL, '2023-08-28 14:20:00', '問題描述', 0, NULL, NULL, 'N/A'),
       (10, NULL, '2023-08-25 16:00:00', '舉報內容', 0, NULL, NULL, 'N/A');

-- 表格：一般商品訂單檢舉 檢查
-- SELECT * FROM orderreport;
-- DESCRIBE orderreport;

-- 表格：一般商品訂單檢舉 添加FK
-- ALTER TABLE orderreport
-- ADD CONSTRAINT fk_orderreport_orderid FOREIGN KEY (orderid) REFERENCES itemorder(orderid),
-- ADD CONSTRAINT fk_orderreport_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：競標商品 刪除
DROP TABLE IF EXISTS biditem;

-- 表格：競標商品 創建
CREATE TABLE biditem
(
    biditemid    INT AUTO_INCREMENT NOT NULL,
    bidname      VARCHAR(50)  NOT NULL,
    grade        TINYINT      NOT NULL,
    size         TINYINT,
    detail       VARCHAR(255) NOT NULL,
    tagid        INT          NOT NULL,
    mbrid        INT          NOT NULL,
    startprice   INT          NOT NULL,
    reserveprice INT,
    directprice  INT,
    bidstatus    TINYINT      NOT NULL DEFAULT 0,
    starttime    DATETIME,
    endtime      DATETIME,
    empid        INT,
    PRIMARY KEY (biditemid)
);

-- 表格：競標商品 檢查
-- SELECT * FROM biditem;
-- DESCRIBE biditem;

-- 表格：競標商品 添加FK
-- ALTER TABLE biditem
-- ADD CONSTRAINT fk_biditem_tagid FOREIGN KEY (tagid) REFERENCES categorytags(tagid),
-- ADD CONSTRAINT fk_biditem_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_biditem_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：競標商品圖片 刪除
DROP TABLE IF EXISTS biditemimage;

-- 表格：競標商品圖片 創建
CREATE TABLE biditemimage
(
    imageid   INT NOT NULL AUTO_INCREMENT,
    biditemid INT NOT NULL,
    image     MEDIUMBLOB NOT NULL,
    PRIMARY KEY (imageid)
);

-- 表格：競標商品圖片 插入假資料
-- INSERT INTO biditemimage (biditemid, image)
-- VALUES (1, '假圖片1'),
--        (2, '假圖片2'),
--        (3, '假圖片3'),
--        (4, '假圖片4'),
--        (5, '假圖片5'),
--        (6, '假圖片6'),
--        (7, '假圖片7'),
--        (8, '假圖片8'),
--        (9, '假圖片9'),
--        (10, '假圖片10');

-- 表格：競標商品圖片 檢查
-- SELECT * FROM biditemimage;
-- DESCRIBE biditemimage;

-- 表格：競標商品圖片 添加FK
-- ALTER TABLE biditemimage
-- ADD CONSTRAINT fk_biditemimage_biditemid FOREIGN KEY (biditemid) REFERENCES biditem(biditemid);
-- ================================================================================================================================ --
-- 表格：競標商品訂單 刪除
DROP TABLE IF EXISTS bidorder;

-- 表格：競標商品訂單 創建
CREATE TABLE bidorder
(
    bidorderid       INT      NOT NULL AUTO_INCREMENT,
    biditemid        INT      NOT NULL,
    buymbrid         INT      NOT NULL,
    sellmbrid        INT      NOT NULL,
    buystar          INT,
    buyerratingdesc  VARCHAR(200),
    sellstar         INT,
    sellerratingdesc VARCHAR(200),
    orderdate        DATETIME NOT NULL,
    paytype          TINYINT,
    payinfo          VARCHAR(50),
    amount           INT      NOT NULL,
    orderstatus      TINYINT  NOT NULL,
    receiveaddress          VARCHAR(100),
    receivename          VARCHAR(20),
    receivephone            VARCHAR(10),
    remarks          VARCHAR(200),
    PRIMARY KEY (bidorderid)
);

-- 表格：競標商品訂單 插入假資料
INSERT INTO bidorder (biditemid, buymbrid, sellmbrid, buystar, buyerratingdesc, sellstar, sellerratingdesc, orderdate,
                      paytype, payinfo, amount, orderstatus, receiveaddress, receivename, receivephone, remarks)
VALUES (1, 301, 401, 5, '很好的交易', 4, '快速出貨', '2023-10-01 09:30:00', 0, '信用卡', 1200, 3, '台北市123號',
        '買家1', '0912345678', '特殊需求'),
       (2, 302, 402, 4, '滿意的交易', 5, '準時交貨', '2023-10-02 14:45:00', 1, '轉帳', 1500, 3, '新北市456號', '買家2',
        '0923456789', '備註內容'),
       (3, 303, 403, 3, '一般的交易', 3, '適度交貨', '2023-10-03 11:55:00', 2, '虛擬錢包', 800, 2, '台中市789號',
        '買家3', '0934567890', NULL),
       (4, 304, 404, 4, '不錯的交易', 4, '按時交貨', '2023-10-04 17:30:00', 0, '信用卡', 2100, 3, '高雄市101號',
        '買家4', '0945678901', NULL),
       (5, 305, 405, 5, '非常滿意', 5, '迅速送達', '2023-10-05 10:15:00', 1, '轉帳', 1800, 3, '台北市202號', '買家5',
        '0956789012', '特殊需求內容'),
       (6, 306, 406, 3, '一般交易', 3, '有點晚交貨', '2023-10-06 16:00:00', 2, '虛擬錢包', 950, 2, '新北市303號',
        '買家6', '0967890123', NULL),
       (7, 307, 407, 5, '完美交易', 5, '速度超快', '2023-10-07 12:20:00', 0, '信用卡', 2800, 3, '台中市404號', '買家7',
        '0978901234', '無'),
       (8, 308, 408, 4, '滿意程度高', 4, '出貨效率高', '2023-10-08 09:45:00', 1, '轉帳', 1650, 3, '高雄市505號',
        '買家8', '0989012345', '無'),
       (9, 309, 409, 5, '很滿意的交易', 5, '超快出貨', '2023-10-09 17:00:00', 0, '信用卡', 1100, 3, '台北市606號',
        '買家9', '0990123456', NULL),
       (10, 310, 410, 3, '普通的交易', 3, '準時交貨', '2023-10-10 13:40:00', 1, '轉帳', 1350, 3, '新北市707號',
        '買家10', '0991234567', '備註内容');

-- 表格：競標商品訂單 檢查
-- SELECT * FROM bidorder;
-- DESCRIBE bidorder;

-- 表格：表格中文名稱 添加FK
-- ALTER TABLE bidorder
-- ADD CONSTRAINT fk_bidorder_biditemid FOREIGN KEY (biditemid) REFERENCES biditem(biditemid),
-- ADD CONSTRAINT fk_bidorder_buymbrid FOREIGN KEY (buymbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_bidorder_sellmbrid FOREIGN KEY (sellmbrid) REFERENCES Members(mbrid);
-- ================================================================================================================================ --
-- 表格：競標訂單評價圖片 刪除
DROP TABLE IF EXISTS bidorderratingimage;

-- 表格：競標訂單評價圖片 創建
CREATE TABLE bidorderratingimage
(
    imageid    INT AUTO_INCREMENT NOT NULL,
    bidorderid INT NOT NULL,
    image      MEDIUMBLOB,
    PRIMARY KEY (imageid)
);

-- 表格：競標訂單評價圖片 插入假資料
INSERT INTO bidorderratingimage (bidorderid, image)
VALUES (1, '假圖片1'),
       (2, '假圖片2'),
       (3, '假圖片3'),
       (4, '假圖片4'),
       (5, '假圖片5'),
       (6, '假圖片6'),
       (7, '假圖片7'),
       (8, '假圖片8'),
       (9, '假圖片9'),
       (10, '假圖片10');

-- 表格：競標訂單評價圖片 檢查
-- SELECT * FROM bidorderratingimage;
-- DESCRIBE bidorder;

-- 表格：競標訂單評價圖片 添加FK
-- ALTER TABLE bidorderratingimage
-- ADD CONSTRAINT fk_bidorderratingimage_bidorderid FOREIGN KEY (bidorderid) REFERENCES bidorder(bidorderid);
-- ================================================================================================================================ --
-- 表格：競標訂單通知 刪除
DROP TABLE IF EXISTS bidordernotify;

-- 表格：競標訂單通知 創建
CREATE TABLE bidordernotify
(
    notifyid   INT AUTO_INCREMENT NOT NULL,
    mbrid      INT          NOT NULL,
    bidorderid INT          NOT NULL,
    notifydate DATETIME     NOT NULL,
    title      VARCHAR(20)  NOT NULL,
    content    VARCHAR(200) NOT NULL,
    PRIMARY KEY (notifyid)
);

-- 表格：競標訂單通知 插入假資料
INSERT INTO bidordernotify (mbrid, bidorderid, notifydate, title, content)
VALUES (1, 1, '2023-10-01 09:00:00', '通知標題1', '通知内容1'),
       (2, 2, '2023-09-28 15:30:00', '通知標題2', '通知内容2'),
       (3, 3, '2023-09-25 14:00:00', '通知標題3', '通知内容3'),
       (4, 4, '2023-09-20 18:00:00', '通知標題4', '通知内容4'),
       (5, 5, '2023-09-15 10:00:00', '通知標題5', '通知内容5'),
       (6, 6, '2023-09-10 11:45:00', '通知標題6', '通知内容6'),
       (7, 7, '2023-09-05 12:30:00', '通知標題7', '通知内容7'),
       (8, 8, '2023-09-01 13:15:00', '通知標題8', '通知内容8'),
       (9, 9, '2023-08-28 14:20:00', '通知標題9', '通知内容9'),
       (0, 10, '2023-08-25 16:00:00', '通知標題10', '通知内容10');

-- 表格：競標訂單通知 檢查
-- SELECT * FROM bidordernotify;
-- DESCRIBE bidordernotify;

-- 表格：競標訂單通知 添加FK
-- ALTER TABLE bidordernotify
-- ADD CONSTRAINT fk_bidordernotify_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_bidordernotify_bidorderid FOREIGN KEY (bidorderid) REFERENCES bidorder(bidorderid);
-- ================================================================================================================================ --
-- 表格：競標商品檢舉 刪除
DROP TABLE IF EXISTS biditemreport;

-- 表格：競標商品檢舉 創建
CREATE TABLE biditemreport
(
    reportid       INT AUTO_INCREMENT NOT NULL,
    biditemid      INT          NOT NULL,
    mbrid          INT          NOT NULL,
    empid          INT,
    reportdate     DATETIME     NOT NULL,
    biddescription VARCHAR(200) NOT NULL,
    bidstatus      TINYINT      NOT NULL,
    auditdate      DATETIME DEFAULT NULL,
    result         TINYINT,
    note           VARCHAR(200),
    PRIMARY KEY (reportid)
);

-- 表格：競標商品檢舉 插入假資料
INSERT INTO biditemreport (biditemid, mbrid, empid, reportdate, biddescription, bidstatus, auditdate, result, note)
VALUES (1, 1, NULL, '2023-10-01 09:00:00', '商品檢舉內容1', 0, NULL, 0, NULL),
       (2, 2, NULL, '2023-09-28 15:30:00', '商品檢舉內容2', 0, NULL, 0, NULL),
       (3, 3, 1, '2023-09-25 14:00:00', '商品檢舉內容3', 1, '2023-09-26 10:00:00', 1, '檢舉已審核，不處分'),
       (4, 4, NULL, '2023-09-20 18:00:00', '商品檢舉內容4', 0, NULL, 0, NULL),
       (5, 5, NULL, '2023-09-15 10:00:00', '商品檢舉內容5', 0, NULL, 0, NULL),
       (6, 6, 2, '2023-09-10 11:45:00', '商品檢舉內容6', 1, '2023-09-12 14:00:00', 0, '檢舉處分'),
       (7, 7, NULL, '2023-09-05 12:30:00', '商品檢舉內容7', 0, NULL, 0, NULL),
       (8, 8, NULL, '2023-09-01 13:15:00', '商品檢舉內容8', 0, NULL, 0, NULL),
       (9, 9, 3, '2023-08-28 14:20:00', '商品檢舉內容9', 1, '2023-08-30 12:00:00', 0, '檢舉處分'),
       (10, 10, NULL, '2023-08-25 16:00:00', '商品檢舉內容10', 0, NULL, 0, NULL);

-- 表格：競標商品檢舉 檢查
-- SELECT * FROM biditemreport;
-- DESCRIBE biditemreport;

-- 表格：競標商品檢舉 添加F
-- ALTER TABLE biditemreport
-- ADD CONSTRAINT fk_biditemreport_biditemid FOREIGN KEY (biditemid) REFERENCES biditem(biditemid),
-- ADD CONSTRAINT fk_biditemreport_mbrid FOREIGN KEY (mbrid) REFERENCES Members(mbrid),
-- ADD CONSTRAINT fk_biditemreport_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --
-- 表格：競標商品訂單檢舉 刪除
DROP TABLE IF EXISTS bidorderreport;

-- 表格：競標商品訂單檢舉 創建
CREATE TABLE bidorderreport
(
    reportid       INT AUTO_INCREMENT NOT NULL,
    bidorderid     INT          NOT NULL,
    empid          INT,
    reportdate     DATETIME     NOT NULL,
    biddescription VARCHAR(200) NOT NULL,
    bidstatus      TINYINT      NOT NULL,
    auditdate      DATETIME DEFAULT NULL,
    result         TINYINT      NOT NULL,
    note           VARCHAR(200),
    PRIMARY KEY (reportid)
);

-- 表格：競標商品訂單檢舉 插入假資料
INSERT INTO bidorderreport (bidorderid, empid, reportdate, biddescription, bidstatus, auditdate, result, note)
VALUES (1, NULL, '2023-10-01 09:00:00', '訂單檢舉內容1', 0, NULL, 0, NULL),
       (2, NULL, '2023-09-28 15:30:00', '訂單檢舉內容2', 0, NULL, 0, NULL),
       (3, 1, '2023-09-25 14:00:00', '訂單檢舉內容3', 1, '2023-09-26 10:00:00', 1, '檢舉已審核，不處分'),
       (4, NULL, '2023-09-20 18:00:00', '訂單檢舉內容4', 0, NULL, 0, NULL),
       (5, NULL, '2023-09-15 10:00:00', '訂單檢舉內容5', 0, NULL, 0, NULL),
       (6, 2, '2023-09-10 11:45:00', '訂單檢舉內容6', 1, '2023-09-12 14:00:00', 0, '檢舉處分'),
       (7, NULL, '2023-09-05 12:30:00', '訂單檢舉內容7', 0, NULL, 0, NULL),
       (8, NULL, '2023-09-01 13:15:00', '訂單檢舉內容8', 0, NULL, 0, NULL),
       (9, 3, '2023-08-28 14:20:00', '訂單檢舉內容9', 1, '2023-08-30 12:00:00', 0, '檢舉處分'),
       (10, NULL, '2023-08-25 16:00:00', '訂單檢舉內容10', 0, NULL, 0, NULL);

-- 表格：競標商品訂單檢舉 檢查
-- SELECT * FROM bidorderreport;
-- DESCRIBE bidorderreport;

-- 表格：bidorderreport 添加外键
-- ALTER TABLE bidorderreport
-- ADD CONSTRAINT fk_bidorderid FOREIGN KEY (bidorderid) REFERENCES bidorder(bidorderid),
-- ADD CONSTRAINT fk_empid FOREIGN KEY (empid) REFERENCES employee(empid);
-- ================================================================================================================================ --





