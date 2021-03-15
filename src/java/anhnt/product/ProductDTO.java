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
    private byte[] img;
    private int tag;
    private int likes;
    private String base64Image;
    
    public ProductDTO() {
    }

    public ProductDTO(int productId, int storeQuantity, byte[] img, int tag, int likes, String base64Image) {
        this.productId = productId;
        this.storeQuantity = storeQuantity;
        this.img = img;
        this.tag = tag;
        this.likes = likes;
        this.base64Image = base64Image;
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
     * @return the img
     */
    public byte[] getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(byte[] img) {
        this.setImg(img);
    }

    /**
     * @return the tag
     */
    public int getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(int tag) {
        this.tag = tag;
    }

    /**
     * @return the likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }


    /**
     * @return the base64Image
     */
    public String getBase64Image() {
        return base64Image;
    }

    /**
     * @param base64Image the base64Image to set
     */
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    
    
    
    
}
