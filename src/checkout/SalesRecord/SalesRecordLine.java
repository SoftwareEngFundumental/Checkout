package checkout.SalesRecord;

import checkout.Product.Product;
import checkout.Product.Promotion;

public class SalesRecordLine {
    private Product product;
    private int quantity;
    private PromotionLine promotionLine;

    public SalesRecordLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;

        if (product.isHasPromo()) {
            Promotion promotion = Promotion.getPromoByProduct(product);
            if (quantity/promotion.getCondition() >= 1) {
                PromotionLine promotionLine = new PromotionLine(promotion, quantity/promotion.getCondition());
                this.promotionLine = promotionLine;
            }
        }
    }


    public  boolean hasProduct(Product product) {
        return this.product.equals(product);
    }

    public double totalCost() {
        double total = product.getPrice()*quantity;

        if (promotionLine != null) {
            total += promotionLine.totalCost();
        }
        return total;
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
        // TODO: 13/05/2017 check promo
        this.quantity = quantity;

        if (product.isHasPromo()) {
            Promotion promotion = Promotion.getPromoByProduct(product);
            if (quantity/promotion.getCondition() >= 1) {
                PromotionLine promotionLine = new PromotionLine(promotion, quantity/promotion.getCondition());
                this.promotionLine = promotionLine;
            }
        }
    }

    public PromotionLine getPromotionLine() {
        return promotionLine;
    }

    public void setPromotionLine(PromotionLine promotionLine) {
        this.promotionLine = promotionLine;
    }

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        if (promotionLine != null) {
            return product.getName()
                    + ", price: " + product.getPrice()
                    + ", quantity: " + quantity
                    + ", total: " + quantity* product.getPrice()
                    + "\n" + promotionLine;
        }
        else {
            return product.getName()
                    + ", price: " + product.getPrice()
                    + ", quantity: " + quantity
                    + ", total: " + quantity* product.getPrice();
        }
    }
}
