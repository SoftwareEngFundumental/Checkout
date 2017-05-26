package checkout.Supply;

import checkout.Product.Product;

/**
 * Created by hu on 26/5/17.
 */
public class Supplier
{
    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail()
    {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail)
    {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierPhone()
    {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone)
    {
        this.supplierPhone = supplierPhone;
    }

    public int getSupplyId()
    {
        return supplyId;
    }

    public void setSupplyId(int supplyId)
    {
        this.supplyId = supplyId;
    }

    private Product product;
    private String supplierName;
    private String supplierEmail;
    private String supplierPhone;
    private int supplyId;
}
