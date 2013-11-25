package pizzawatch.gui.functions;

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
	
	public String getOrderID()
	{
		return this.orderID;
	}
	
	public Order(String uid, String from, String pizzaType, String deliveryMethod)
	{
		Random rng = new Random();
		this.address = from;
		this.orderID= Integer.toString(rng.nextInt(10000000));
		this.pizzaType = pizzaType;
		this.deliveryMethod = deliveryMethod;
		this.userID = uid;
	}
	
	/**
	 * Insert Query
	 */
	public void addOrder()
	{
		SqlScriptReader sqlreader = new pizzawatch.sql.sqlreader.SqlScriptReader();
		String query = "INSERT INTO PizzaOrder VALUES <*>";
		query = query.replace(replaceChar, "(" + "'" +orderID.toString() + "'" +"," + "'" +deliveryMethod + "'" + "," + "'" +pizzaType + "'" + "," + "'" + userID + "'" +"," + "'" + address + "'" +")");
		sqlreader.insertUpdateCreateDelete(query);
		System.out.print("Order: from:" + address + " for Pizza Type:" + pizzaType + " is submitted");
	}
	
}
