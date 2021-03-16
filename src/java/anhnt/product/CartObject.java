/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class CartObject implements Serializable {

    Map<Integer, Integer> items;

    public CartObject() {
    }

    public CartObject(Map<Integer, Integer> items) {
        this.items = items;
    }

    public void addItemToCart(int productId) {
        //If there is no cart yet, retrieve a new cart
        if (this.items == null) {
            this.items = new HashMap<Integer, Integer>();
        }
        //The cart now will exist regardless
        //The default quantity of an item will be 1
        int quantity = 1;
        //If the cart contains the item, add 1 to its current quantity
        if (this.items.containsKey(productId)) {
            quantity = this.items.get(productId) + 1;
        }
        this.items.put(productId, quantity);
    }

    public void removeFromCart(int productId) {
        //If there is no cart, nothing to remove
        if (this.items == null) {
            return;
        }
        //If the cart exists, check the items inside
        if (this.items.containsKey(productId)) {
            this.items.remove(productId);
            //After removal, if the cart is empty, also return the cart's memory 
            //region to the Container
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
