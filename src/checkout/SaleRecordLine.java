package checkout;

public class SaleRecordLine {
    private Item item;
    private int quantity;

    public SaleRecordLine(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
        return item.getName()
                + ", price: " + item.getPrice()
                + ", quantity: " + quantity
                + ", total: " + quantity*item.getPrice();
    }
}
