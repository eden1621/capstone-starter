package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{

    ShoppingCart getByUserId(int userId);

    void addProductToCart(int userId, int productId);

    void updateCartItem(int userId,int productId,int quantity);

    void clearCart(int userId);

    void removeItem(int userId, int productId);
}
