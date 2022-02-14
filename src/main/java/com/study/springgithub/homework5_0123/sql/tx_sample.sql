-- 交易TX (Transcation)所需要的資料表
-- book(書籍資料) stock(庫存資料) wallet(客戶雲端錢包)

-- 建立book(書籍資料)
	create table if not exists book(
		bid integer not null auto_increment,
		bname varchar(20) not null,
		price integer default 0,
		ct timestamp default current_timestamp,
		primary key(bid) -- 主鍵
	);
-- 建立stock(庫存資料)
	create table if not exists stock(
		sid integer not null auto_increment,
		bid integer not null, -- book(書籍資料)的id
		amount integer default 0,
		primary key(sid),
		foreign key(bid) references book(bid) -- 外鍵
	);
-- 建立wallet(客戶雲端錢包)
	create table if not exists wallet(
		wid integer not null auto_increment,
		wname varchar(20) not null,
		money integer default 0,
		ct timestamp default current_timestamp,
		primary key(wid) -- 主鍵
	);
	
-- Homework 建立交易紀錄 order_log 資料表
-- vincent在2020/1/23 PM 2:07:51 買了java書2本共300元
-- vincent在2020/1/23 PM 2:08:51 買了python書2本共200元
-- vincent在2020/1/23 PM 2:10:51 買了java書4本共600元
-- 注意：若 book 的 price 欄位有變動，order_log 則不影響
-- 試問：資料表應如合創建 ?

	create table if not exists order_log(
		oid integer not null auto_increment,
		bname varchar(20) not null,
		wname varchar(20) not null,
		amount integer default 0,
		totalprice integer default 0,
		ct timestamp default current_timestamp,
		primary key(oid)
	);
	
	insert into order_log(bname, wname,amount,totalprice) values(?, ?,?,?);
	
	
	
	
	