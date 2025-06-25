package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/cart")  // All methods in this class use URLs starting with /cart
@CrossOrigin      // Allows frontend and backend to work together
public class ShoppingCartController
{
    private final ShoppingCartDao shoppingCartDao;
    private final UserDao userDao;
    private final ProductDao productDao;


    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao)
    {
        // Constructor to connect ShoppingCartDao and UserDao

        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }
    // Get the current user's shopping cart

    @GetMapping("")
    public ShoppingCart getCart(Principal principal)
    {
        try
        {
            String username = principal.getName();
            User user = userDao.getByUserName(username);

            if (user == null)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");

            return shoppingCartDao.getByUserId(user.getId());
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get cart.");
        }
    }
    // Add a product to the current user's cart

    @PostMapping("/products/{productId}")
    public void addProductToCart(@PathVariable int productId, Authentication authentication)
    {
        try
        {
            String username = authentication.getName();
            User user = userDao.getByUserName(username);

            if (user == null)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");

            shoppingCartDao.addProductToCart(user.getId(), productId);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add product to cart.");
        }
    }

    // Update  a product in the cart

    @PutMapping("/products/{productId}")
    public void updateCartItem(@PathVariable int productId, @RequestBody Map<String,Integer> items, Principal principal)
    {
        try
        {
            //get the user name
            int quantity = items.get("quantity");
            String username = principal.getName();
            //find the user
            User user = userDao.getByUserName(username);
//this shows error
            if (user == null)
               throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");
//update the cart
            shoppingCartDao.updateCartItem(user.getId(), productId,quantity);
        }
        catch (Exception ex)
        {
            //when something goes wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update cart item.");
        }
    }
    // Remove all items
    @DeleteMapping("")
    public void clearCart(Principal principal)
    {
        try
        {

            //Get the user name
            String username = principal.getName();
            User user = userDao.getByUserName(username);
//when the user not found
            if (user == null)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");
// clear the cart
            shoppingCartDao.clearCart(user.getId());
        }
        catch (Exception ex)
        {
            //when something goes wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to clear cart.");
        }
    }
}
