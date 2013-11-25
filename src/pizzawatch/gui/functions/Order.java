package pizzawatch.gui.functions;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import pizzawatch.sql.sqlreader.SqlScriptReader;

public class Order {
	
	private static final String sqlFile = "SQL_Scripts/addOrders.sql";
	private static final String replaceChar = "<*>";
	private String userID;
	private String orderID;
	private String address;
	private String pizzaType;
	private String deliveryMethod;
	
	public Order(String uid, String from, String pizzaType, String deliveryMethod)
	{
		Random rng = new Random();
		this.address = from;
		this.orderID= Integer.toString(rng.nextInt(10000000));
		this.pizzaType = pizzaType;
		this.deliveryMethod = deliveryMethod;
		this.userID = uid;
	}
	
	public void addOrder()
	{
		SqlScriptReader sqlreader = new pizzawatch.sql.sqlreader.SqlScriptReader();
		try {
			List<String> insertSql = sqlreader.readTextFile(sqlFile);
			for(String line:insertSql)
			{
				if (line.contains(replaceChar)){
					line.replace(replaceChar, "(" + orderID +"," + deliveryMethod + "," + pizzaType + "," + userID + "," + address + ")");
					sqlreader.writeTextFile(insertSql, sqlFile);
					sqlreader.insertUpdateCreateDelete(sqlFile);
					
					//restore addOrders
					line.replace("(" + orderID +"," + deliveryMethod + "," + pizzaType + "," + userID + "," + address + ")", replaceChar);
					sqlreader.writeTextFile(insertSql, sqlFile);
				}
				break;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlreader.insertUpdateCreateDelete(sqlFile);
		System.out.print("Order: from:" + address + " for Pizza Type:" + pizzaType + " is submitted");
	}
	
}