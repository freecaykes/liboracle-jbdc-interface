package pizzawatch.utils;

import pizzawatch.datamodels.Order;
import pizzawatch.sql.sqlreader.SqlScriptReader;

public class OrderUtils
{
    private static final SqlScriptReader SQL_READER = SqlScriptReader.getInstance();
    private static final String REPLACE_CHAR = "<*>";

    /**
     * Add an order to the database
     * Insert Query
     * @param order
     */
    public static void addOrder(Order order)
    {
        String query = "INSERT INTO PizzaOrder VALUES <*>";
        query = query.replace(REPLACE_CHAR, "('" + order.getOrderID() + "', '" + order.getDeliveryMethod() + "', '" +
                                             order.getPizzaType() + "', '" + order.getUserID() + "', '" + order.getAddress() + "')");
        SQL_READER.insertUpdateCreateDelete(query);
    }

    private OrderUtils() {}
}
