package checkout;

public class SaleRecordLine {
    private int productID;
    private int quantity;

    public SaleRecordLine(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        return Product.getProductByID(productID).getName()
                + ", price: " + Product.getProductByID(productID).getPrice()
                + ", quantity: " + quantity
                + ", total: " + quantity*Product.getProductByID(productID).getPrice();
    }
}
