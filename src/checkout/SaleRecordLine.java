package checkout;

public class SaleRecordLine {
    private Product product;
    private int quantity;

    public SaleRecordLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        return product.getName()
                + ", price: " + product.getPrice()
                + ", quantity: " + quantity
                + ", total: " + quantity*product.getPrice();
    }
}
