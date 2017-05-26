package checkout.Supply;

import checkout.Product.Product;

import java.util.Date;

public class Supplier
{
    public Supplier(Product product, String supplierName, String supplierEmail, String supplierPhone, Date date)
    {
        this.product        = product;
        this.supplierName   = supplierName;
        this.supplierEmail  = supplierEmail;
        this.supplierPhone  = supplierPhone;
        this.date           = date;
    }

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

    public int getSupplierId()
    {
        return supplyId;
    }

    public void setSupplierId(int supplyId)
    {
        this.supplyId = supplyId;
    }

    public Date getDate() { return  this.date; }

    public void setDate(Date date) { this.date = date; }

    private Product product;

    private String supplierName;

    private String supplierEmail;

    private String supplierPhone;

    private int supplyId;

    private Date date;
}
