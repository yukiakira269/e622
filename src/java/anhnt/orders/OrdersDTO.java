/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.orders;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class OrdersDTO implements Serializable {

    private int orderId;
    private String custName;
    private String custAddress;
    private Date date;

    public OrdersDTO() {
    }

    public OrdersDTO(int orderId, String custName, String custAddress, Date date) {
        this.orderId = orderId;
        this.custName = custName;
        this.custAddress = custAddress;
        this.date = date;
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
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the custAddress
     */
    public String getCustAddress() {
        return custAddress;
    }

    /**
     * @param custAddress the custAddress to set
     */
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
