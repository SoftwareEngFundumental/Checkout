package checkout;

public class SaleRecordLine {
    private int productID;
    private int quantity;

    public SaleRecordLine(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        return "SaleRecordLine{" +
                "productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
