package com.study.springgithub.homework4_0116.template;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springgithub.homework4_0116.entity.Item;
import com.study.springgithub.homework4_0116.entity.ItemProduct;

@Repository
public class ItemDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public List<Item> sqlToList(String sql, String key){
		ResultSetExtractor<List<Item>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys(key)
				.newResultSetExtractor(Item.class);
		List<Item> itemJdbcTemplate = jdbcTemplate.query(sql, resultSetExtractor);
		return itemJdbcTemplate;
	}
	
	// 每一張發票有那些商品?
	public void queryProductOnEachInvoice(){
		String sql="select i.id ,i.amount ,i.ipid ,i.invid ,\n"
				+ "	p.id as itemproduct_id, p.`text` as itemproduct_text, p.price as itemproduct_price, p.inventory as itemproduct_inventory\n"
				+ "from item as i join itemproduct as p on i.ipid = p.id \n"
				+ "order by i.invid;";
		String key="i.id";
		List<Item> itemJdbcTemplate=sqlToList(sql, key);
		for(Item i:itemJdbcTemplate) {
			System.out.println(
					"發票編號: "+i.getInvid()+
					" 商品名稱: "+i.getItemProducts().stream().
								 map(ItemProduct::getText).
								 collect(Collectors.toList()));
		}
	}
	
	// 每一張發票有幾件商品?
	public void howManyItemsForEachInvoice(){
		String sql="select i.id ,i.amount ,i.ipid ,i.invid ,\r\n"
				+ "	p.id as itemproduct_id, count(p.`text`) as itemproduct_text, p.price as itemproduct_price, p.inventory as itemproduct_inventory\r\n"
				+ "from item as i join itemproduct as p on i.ipid = p.id\r\n"
				+ "group by i.invid \r\n"
				+ "order by i.invid;";
		String key="i.id";
		List<Item> itemJdbcTemplate=sqlToList(sql, key);
		for(Item i:itemJdbcTemplate) {
			System.out.println("發票編號:"+i.getInvid()+"有"+i.getItemProducts().stream().
								 map(ItemProduct::getText).
								 collect(Collectors.toList())+"件商品");
		}
	}
	
	// 每一張發票價值多少?
	public void valueOfEachInvoice() {
		String sql = "select a.id, a.amount, a.ipid, a.invid,\r\n"
				+ "	a.itemproduct_id as itemproduct_id, a.itemproduct_text as itemproduct_text, sum(a.itemproduct_price) as itemproduct_price, a.itemproduct_inventory as itemproduct_inventory \r\n"
				+ "from(\r\n"
				+ "select i.id ,i.amount ,i.ipid ,i.invid ,\r\n"
				+ "	p.id as itemproduct_id, p.`text` as itemproduct_text, (p.price*i.amount) as itemproduct_price, p.inventory as itemproduct_inventory\r\n"
				+ "from item as i join itemproduct as p on i.ipid = p.id\r\n"
				+ ")a\r\n"
				+ "group by a.invid;";
		String key="a.id";
		List<Item> itemJdbcTemplate=sqlToList(sql, key);
		for(Item i:itemJdbcTemplate) {
			System.out.println("發票編號:"+i.getInvid()+"號, 總共"+i.getItemProducts().stream().
											map(ItemProduct::getPrice).
											collect(Collectors.toList())+"元");
		}
		
	}
	
	// 每一樣商品各賣了多少?
	public void sellCountForEachProduct() {
		String sql="select i.id ,sum(i.amount) as amount ,i.ipid ,i.invid ,\r\n"
				+ "			   p.id as itemproduct_id, p.`text` as itemproduct_text, \r\n"
				+ "			   p.price as itemproduct_price, \r\n"
				+ "			   p.inventory as itemproduct_inventory\r\n"
				+ "		from item as i join itemproduct as p on i.ipid = p.id\r\n"
				+ "		group by i.ipid\r\n"
				+ "		order by i.ipid;";
		String key="i.id";
		List<Item> itemJdbcTemplate=sqlToList(sql, key);
		for(Item i:itemJdbcTemplate) {
			System.out.println(i.getItemProducts().stream().map(ItemProduct::getText).collect(Collectors.toList())+
								"\t總共賣了"+
								i.getAmount()+"個");
		}
		
		
	}
	
	// 哪一件商品賣得錢最多?
	public void hotProduct() {
		String sql="select a.id, (sumAmount*itemproduct_price) as amount, a.ipid, a.invid,\r\n"
				+ "	   a.itemproduct_id as itemproduct_id, a.itemproduct_text as itemproduct_text, \r\n"
				+ "	   a.itemproduct_price as itemproduct_price, \r\n"
				+ "	   a.itemproduct_inventory as itemproduct_inventory \r\n"
				+ "from(\r\n"
				+ "		select i.id ,sum(i.amount) as sumAmount ,i.ipid ,i.invid ,\r\n"
				+ "			   p.id as itemproduct_id, p.`text` as itemproduct_text, \r\n"
				+ "			   p.price as itemproduct_price, \r\n"
				+ "			   p.inventory as itemproduct_inventory\r\n"
				+ "		from item as i join itemproduct as p on i.ipid = p.id\r\n"
				+ "		group by i.ipid\r\n"
				+ ")a \r\n"
				+ "order by amount desc \r\n"
				+ "limit 1";
		String key="a.id";
		List<Item> itemJdbcTemplate=sqlToList(sql, key);
		for(Item i:itemJdbcTemplate) {
			System.out.println(i.getItemProducts().stream().map(ItemProduct::getText).collect(Collectors.toList())+
								"賣的錢最多, 共"+i.getAmount()+"元");
		}
	}
	
	// 哪一張發票價值最高?
	public void TheMostOfMoneyInInvoices() {
		String sql="select a.id, sum(sumAmount) as amount, a.ipid, a.invid,\r\n"
				+ "	   a.itemproduct_id as itemproduct_id, a.itemproduct_text as itemproduct_text, \r\n"
				+ "	   a.itemproduct_price as itemproduct_price, \r\n"
				+ "	   a.itemproduct_inventory as itemproduct_inventory \r\n"
				+ "from(\r\n"
				+ "		select i.id ,(i.amount*p.price) as sumAmount ,i.ipid ,i.invid ,\r\n"
				+ "			   p.id as itemproduct_id, p.`text` as itemproduct_text, \r\n"
				+ "			   p.price as itemproduct_price, \r\n"
				+ "			   p.inventory as itemproduct_inventory\r\n"
				+ "		from item as i join itemproduct as p on i.ipid = p.id\r\n"
				+ "		order by i.invid\r\n"
				+ ")a \r\n"
				+ "group by a.invid\r\n"
				+ "order by amount desc \r\n"
				+ "limit 1;";
		String key="a.id";
		List<Item> itemJdbcTemplate=sqlToList(sql, key);
		for(Item i:itemJdbcTemplate) {
			System.out.println(i.getInvid()+"號發票的金額最高, 共"+i.getAmount()+"元");
		}
	}
}
