package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests
@RestController // this help the Spring can handle HTTP requests
@RequestMapping("categories")  // Maps all requests that start with /categories to this controller
@CrossOrigin // this help when the frontend and backend are separate
public class CategoriesController
{
    @Autowired  //used to access  category data
    private CategoryDao categoryDao;

    @Autowired  //used to access product data
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao

    // add the appropriate annotation for a get action
    @GetMapping
    public List<Category> getAll()
    {
        // find and return all categories
        List<Category> allCategories = categoryDao.getAllCategories(); //Get all categories from database
        System.out.println("all categories: " + allCategories);     // Print the categories to the console

        return allCategories;
     //return the list categories to the caller
    }

    // add the appropriate annotation for a get action
    @GetMapping("/{id}")     // Handles GET requests to /categories/{id}

    public Category getById(@PathVariable int id) {
        try
        {
            var category = categoryDao.getById(id);

            if(category == null)             // If not found, return a 404 Not Found error

                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return category;
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

        // get the category by id
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products

    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId

        try
        {
            var products = productDao. listByCategoryId(categoryId);

            if(products == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return products;
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function

    @PostMapping
@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category addCategory(@RequestBody Category category)
    {
        // insert the category
        return categoryDao.create(category);
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("/{id}")

    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        categoryDao.update(id,category);
        // update the category by id
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    // Handles DELETE requests to /categories/{id}
    // Only users with the ADMIN role can delete categories

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
        // delete the category by id
    }
}
