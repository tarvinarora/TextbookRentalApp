package model;

public class Seller {

    private String sellerName;
    private int sellerId;

    public Seller(String sellerName, int sellerId) {
        this.sellerName = sellerName;
        this.sellerId = sellerId;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public int getSellerId() {
        return sellerId;
    }

}
