package checkout.SalesRecord;

import checkout.Item.Item;
import checkout.Item.Product;
import checkout.Item.Promotion;

public class SaleRecordLine {
    private Item item;
    private int quantity;

    public SaleRecordLine(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public  boolean hasItem(Item item) {
        return this.item.getClass() == item.getClass() && this.item.equals(item);
    }

    public  boolean canApplyPromo() {
        if (this.item.getClass() == Product.class) {
            Product product= (Product)this.item;
            if (product.isHasPromo()) {
                Promotion promotion = Promotion.getPromoByProduct(product);
                if (quantity >= promotion.getCondition()) {
                    return true;
                }
            }
        }
        return false;
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
