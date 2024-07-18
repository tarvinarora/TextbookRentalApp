package model;

public class Buyer {

    private String buyerName;
    private int buyerId;

    public Buyer(String buyerName, int buyerId) {
        this.buyerName = buyerName;
        this.buyerId = buyerId;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public int getBuyerId() {
        return buyerId;
    }

}
