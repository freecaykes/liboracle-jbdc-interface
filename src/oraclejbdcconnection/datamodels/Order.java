package oraclejbdcconnection.datamodels;


public class Order {
    private final String userID;
    private final int orderID;
    private final String address;
    private final String pizzaType;
    private final String deliveryMethod;
    private final String isDelivered;
    private final String isCancellationRequested;

    public Order(int oid, String uid, String from, String pizzaType, String deliveryMethod, int isDelivered, int isCancellationRequested) {
        this.address = from;
        this.orderID = oid;
        this.pizzaType = pizzaType;
        this.deliveryMethod = deliveryMethod;
        this.userID = uid;
        this.isDelivered = Integer.toString(isDelivered);
        this.isCancellationRequested = Integer.toString(isCancellationRequested);

    }

    public String getUserID() {
        return userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getAddress() {
        return address;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public String getIsDelivered() {
        return isDelivered;
    }

    public String getIsCancellationRequested() {
        return isCancellationRequested;
    }
}
