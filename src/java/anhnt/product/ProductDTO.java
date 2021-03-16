/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.product;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ProductDTO implements Serializable{
    private int productId;
    private int storeQuantity;
    private int tags;
    private String productDesc;

    public ProductDTO() {
    }

    public ProductDTO(int productId, int storeQuantity, int tags, String productDesc) {
        this.productId = productId;
        this.storeQuantity = storeQuantity;
        this.tags = tags;
        this.productDesc = productDesc;
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
     * @return the storeQuantity
     */
    public int getStoreQuantity() {
        return storeQuantity;
    }

    /**
     * @param storeQuantity the storeQuantity to set
     */
    public void setStoreQuantity(int storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    /**
     * @return the tags
     */
    public int getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(int tags) {
        this.tags = tags;
    }

    /**
     * @return the productDesc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * @param productDesc the productDesc to set
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    
    
    
    
    
}
