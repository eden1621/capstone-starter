# 🛒 EasyShop E-Commerce Backend 🛒
A Spring Boot-based RESTful API for managing products, categories, and shopping carts in an e-commerce system. EasyShop is designed to support modern online shopping platforms by handling dynamic product management, secure user shopping carts, and category filtering. The system supports admin-level CRUD operations and customer-level interactions.

## 📌 Features
✅ Product Management
Add, update, delete products (admin only)

Search products by:

Category

Price range (min & max)

## Color

Retrieve product details by ID

## ✅ Category Management
Add, update, delete categories (admin only)

View all categories

View all products by category

## ✅ Shopping Cart
Authenticated users can:

Add items to cart

Update item quantity

Remove individual items or clear the entire cart

View current cart

## ✅ Role-Based Access
ADMIN: Full access to manage products and categories

CUSTOMER: Can browse and shop

### 💻 How It Works
REST API endpoints allow secure and scalable access to data

Role-based access is managed using Spring Security

Shopping carts are unique per authenticated user

Data is stored and retrieved from a MySQL database

Controllers return standard HTTP status codes for easy frontend integration

### 🧱 Project Structure
swift
Copy
```Edit
src/main/java/org/yearup/
├── controllers/
│   ├── CategoriesController.java
│   ├── ProductsController.java
│   └── ShoppingCartController.java
│
├── data/
│   ├── CategoryDao.java
│   ├── ProductDao.java
│   ├── ShoppingCartDao.java
│
├── data/mysql/
│   ├── MySqlCategoryDao.java
│   ├── MySqlProductDao.java
│   └── MySqlShoppingCartDao.java
│
├── models/
│   ├── Category.java
│   ├── Product.java
│   ├── ShoppingCart.java
│   └── ShoppingCartItem.java
```
### 🛠️ Technologies Used
Java 17+

Spring Boot

Spring Security

MySQL

JDBC Template

RESTful APIs

Role-based Authorization (@PreAuthorize)

JSON (for request/response handling)

🚀 Getting Started
1️⃣ Clone the Repository
bash
Copy
```Edit
git clone https://github.com/your-username/easyshop-backend.git
cd easyshop-backend
2️⃣ Set Up MySQL Database
Create a MySQL database and update your application.properties with the correct credentials:
```
properties
Copy
Edit
```spring.datasource.url=jdbc:mysql://localhost:3306/easyshop_db
spring.datasource.username=root
spring.datasource.password=yourpassword
Run the provided SQL schema to create required tables (products, categories, shopping_cart, etc.).
```
3️⃣ Run the Application
bash
Copy
Edit
```
./mvnw spring-boot:run
Or run from IntelliJ:
```
bash
Copy
Edit
```
Run > EasyShopApplication.main()
```
## 📦 Key Endpoints
Method	Endpoint	Role	Description
GET	/products	All	Search products with filters
GET	/products/{id}	All	View single product
POST	/products	ADMIN	Create new product
PUT	/products/{id}	ADMIN	Update product
DELETE	/products/{id}	ADMIN	Delete product
GET	/categories	All	View all categories
GET	/categories/{id}	All	View specific category
GET	/categories/{id}/products	All	View products in category
POST	/categories	ADMIN	Add category
PUT	/categories/{id}	ADMIN	Update category
DELETE	/categories/{id}	ADMIN	Delete category
GET	/cart	CUSTOMER	View shopping cart
POST	/cart/products/{productId}	CUSTOMER	Add product to cart
PUT	/cart/products/{productId}	CUSTOMER	Update cart item quantity
DELETE	/cart	CUSTOMER	Clear entire cart

## 🔒 Authentication & Authorization
All cart operations require authentication via JWT (or Spring Security configuration)

Admin-only actions are secured using @PreAuthorize("hasRole('ROLE_ADMIN')")

## 🔮 Future Enhancements
Add order processing and payment logic

Implement user profile and address management

Add favorites/wishlist functionality

Switch to Spring Data JPA

Frontend integration with React or Angular

## 🙋 Author
Eden Mengistu
Java Developer | Spring Boot Enthusiast
