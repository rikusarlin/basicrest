package entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Generated by sarlirik on 23/09/17.
 */
@Entity
@Table(name = "orderdetails", catalog = "")
@IdClass(OrderDetailsPK.class)
public class OrderDetails {
    private int orderNumber;
    private String productCode;
    private int quantityOrdered;
    private BigDecimal priceEach;
    private short orderLineNumber;

    @Id
    @Column(name = "orderNumber", nullable = false)
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Id
    @Column(name = "productCode", nullable = false, length = 15)
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Basic
    @Column(name = "quantityOrdered", nullable = false)
    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    @Basic
    @Column(name = "priceEach", nullable = false, precision = 2)
    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    @Basic
    @Column(name = "orderLineNumber", nullable = false)
    public short getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(short orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetails that = (OrderDetails) o;

        if (orderNumber != that.orderNumber) return false;
        if (quantityOrdered != that.quantityOrdered) return false;
        if (orderLineNumber != that.orderLineNumber) return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (priceEach != null ? !priceEach.equals(that.priceEach) : that.priceEach != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNumber;
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + quantityOrdered;
        result = 31 * result + (priceEach != null ? priceEach.hashCode() : 0);
        result = 31 * result + (int) orderLineNumber;
        return result;
    }
}
