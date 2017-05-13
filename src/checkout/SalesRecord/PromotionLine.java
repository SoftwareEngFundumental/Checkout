package checkout.SalesRecord;

import checkout.Product.Promotion;

public class PromotionLine {
     private Promotion promotion;
    private int quantity;

    public PromotionLine(Promotion promotion, int quantity) {
        this.promotion = promotion;
        this.quantity = quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return promotion.getName()
                + ", price: " + promotion.getPrice()
                + ", quantity: " + quantity
                + ", total: " + quantity*promotion.getPrice();
    }
}


