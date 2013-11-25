package pizzawatch.sql.functions.test;

import org.junit.Test;
import pizzawatch.gui.functions.Order;
import pizzawatch.gui.functions.User;

public class FunctionsTest {
	
	@Test
	public void test()
	{
		User testUser = new User("Raphael");
		Order order = new Order(testUser.getUserID(),"Shredder Hideout, New York", "Pepperoni", "boat");
		order.addOrder();
		User adminTestUser = new User("Master Splinter");
		adminTestUser.checkAdmin("Master Splinter");
		adminTestUser.deleteOrders(testUser.getUserID(), order.getOrderID());
	}
	
}
