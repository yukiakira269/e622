/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.ordersDetail;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class OrdersDetailDTO implements Serializable{
    private int orderId;
    private int productId;
    private int orderQuantity;

    public OrdersDetailDTO() {
    }

    public OrdersDetailDTO(int orderId, int productId, int orderQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the orderQuantity
     */
    public int getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * @param orderQuantity the orderQuantity to set
     */
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    
    
    
    
}
