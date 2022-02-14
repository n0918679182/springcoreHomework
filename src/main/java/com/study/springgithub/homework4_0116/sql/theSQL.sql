create table ItemProduct(
	id Integer not null auto_increment,
	text varchar(50) not null,
	price Integer not null,
	inventory Integer not null,
	primary key (id)
);

create table Invoice(
	id Integer not null auto_increment,
	invdate Date not null,
	primary key(id)
);

create table Item(
	id Integer not null auto_increment,
	amount Integer not null,
	ipid Integer not null,
	invid Integer not null,
	primary key(id),
	foreign key(ipid) references ItemProduct(id),
	foreign key(invid) references Invoice(id)
);


insert into Invoice(invdate) values("2020-11-23");
insert into Invoice(invdate) values("2020-11-22");
insert into Invoice(invdate) values("2020-11-21");

insert into ItemProduct(text, price, inventory) values("Pen", 10, 20);
insert into ItemProduct(text, price, inventory) values("Book", 15, 50);
insert into ItemProduct(text, price, inventory) values("Toy", 20, 40);

insert into Item(amount, ipid, invid) values(5,1,1);
insert into Item(amount, ipid, invid) values(3,2,1);
insert into Item(amount, ipid, invid) values(4,1,2);
insert into Item(amount, ipid, invid) values(1,3,2);
insert into Item(amount, ipid, invid) values(6,2,3);

-- 每張發票有哪些商品?
select i.invid, p.text  
from item as i join itemproduct as p on i.ipid = p.id 
order by 1;

-- -------------------------------------------------------

select i.id ,i.amount ,i.ipid ,i.invid ,
	   p.id as itemproduct_id, p.`text` as itemproduct_text, 
	   p.price as itemproduct_price, p.inventory as itemproduct_inventory
from item as i join itemproduct as p on i.ipid = p.id 
order by i.invid;

--================================================================================

-- 每一張發票有幾件商品?
select i.invid, count(p.text)  
from item as i inner join itemproduct as p on i.ipid = p.id 
group by i.invid;

-- ---------------------------------------------------------

select i.id ,i.amount ,i.ipid ,i.invid ,
	   p.id as itemproduct_id, count(p.`text`) as itemproduct_text, 
	   p.price as itemproduct_price, p.inventory as itemproduct_inventory
from item as i join itemproduct as p on i.ipid = p.id
group by i.invid 
order by i.invid;

--================================================================================

-- 每一張發票價值多少?
select a.invid, sum(a.totalPrice) 
from(
	select i.invid, i.amount *p.price as totalPrice
	from item as i inner join itemproduct as p on i.ipid = p.id 
	order by 1
)a
group by a.invid;

-- ------------------------------------------------------------

select a.id, a.amount, a.ipid, a.invid,
	   a.itemproduct_id as itemproduct_id, a.itemproduct_text as itemproduct_text, 
	   sum(a.itemproduct_price) as itemproduct_price, 
	   a.itemproduct_inventory as itemproduct_inventory 
from(
		select i.id ,i.amount ,i.ipid ,i.invid ,
			   p.id as itemproduct_id, p.`text` as itemproduct_text, 
			   (p.price*i.amount) as itemproduct_price, 
			   p.inventory as itemproduct_inventory
		from item as i join itemproduct as p on i.ipid = p.id
)a
group by a.invid;


--================================================================================

-- 每一樣商品各賣了多少?
select i.ipid, sum(i.amount) 
from item as i inner join itemproduct as p on i.ipid = p.id 
group by 1;

-- ---------------------------------------------------------

select i.id ,sum(i.amount) as amount ,i.ipid ,i.invid ,
	   p.id as itemproduct_id, p.`text` as itemproduct_text, 
	   p.price as itemproduct_price, 
	   p.inventory as itemproduct_inventory
from item as i join itemproduct as p on i.ipid = p.id
group by i.ipid
order by i.ipid;



--================================================================================


-- 哪一件商品賣得錢最多?
select a.b, sumAmount*c.price 
from(
select i.ipid as b, sum(i.amount)  as sumAmount
from item as i inner join itemproduct as p on i.ipid = p.id 
group by 1
)a inner join itemproduct as c on a.b = c.id
order by 2 desc
limit 1;

-- ----------------------------------------------------------

select a.id, (sumAmount*itemproduct_price) as amount, a.ipid, a.invid,
	   a.itemproduct_id as itemproduct_id, a.itemproduct_text as itemproduct_text, 
	   a.itemproduct_price as itemproduct_price, 
	   a.itemproduct_inventory as itemproduct_inventory 
from(
		select i.id ,sum(i.amount) as sumAmount ,i.ipid ,i.invid ,
			   p.id as itemproduct_id, p.`text` as itemproduct_text, 
			   p.price as itemproduct_price, 
			   p.inventory as itemproduct_inventory
		from item as i join itemproduct as p on i.ipid = p.id
		group by i.ipid
)a 
order by amount desc 
limit 1;



--================================================================================



-- 哪一張發票價值最高?
select a.invid, sum(a.totalPrice) 
from(
	select i.invid, i.amount *p.price as totalPrice
	from item as i inner join itemproduct as p on i.ipid = p.id 
	order by 1
)a
group by a.invid
order by 2 desc
limit 1;
-- -----------------------------------------------------------
select a.id, sum(sumAmount) as amount, a.ipid, a.invid,
	   a.itemproduct_id as itemproduct_id, a.itemproduct_text as itemproduct_text, 
	   a.itemproduct_price as itemproduct_price, 
	   a.itemproduct_inventory as itemproduct_inventory 
from(
		select i.id ,(i.amount*p.price) as sumAmount ,i.ipid ,i.invid ,
			   p.id as itemproduct_id, p.`text` as itemproduct_text, 
			   p.price as itemproduct_price, 
			   p.inventory as itemproduct_inventory
		from item as i join itemproduct as p on i.ipid = p.id
		order by i.invid
)a 
group by a.invid
order by amount desc 
limit 1;










