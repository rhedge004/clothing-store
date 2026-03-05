# Clothing Store API

This project implements a backend for a clothing store using Spring Boot. It manages products, a shopping cart, and order processing.

## API Endpoints

### Products (`/api/products`)

| Method | Endpoint | Description | Request Body | Response |
|---|---|---|---|---|
| `GET` | `/` | Retrieve all available products | None | `List<Product>` |
| `POST` | `/` | Add a new product to the inventory | `Product` | `Product` |

### Shopping Cart (`/api/cart`)

| Method | Endpoint | Description | Parameters | Response |
|---|---|---|---|---|
| `GET` | `/` | Retrieve items currently in the cart | None | `List<CartItem>` |
| `POST` | `/add/{productId}` | Add an item to the cart | `quantity` (Query Param) | `CartItem` |
| `DELETE` | `/clear` | Remove all items from the cart | None | `void` |

### Orders (`/api/orders`)

| Method | Endpoint | Description | Request Body | Response |
|---|---|---|---|---|
| `POST` | `/checkout` | Create an order from the current cart | None | `Order` |

## Data Models

### Product
Represents a clothing item available in the store.

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Unique identifier |
| `name` | `String` | Name of the product |
| `description` | `String` | Product description |
| `price` | `BigDecimal` | Price of the product |
| `category` | `String` | Category (e.g., Outerwear, Pants) |
| `stockQuantity` | `Integer` | Available stock count |
| `size` | `String` | Size (e.g., S, M, L) |

### CartItem
Represents an item added to the user's shopping cart.

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Unique identifier |
| `product` | `Product` | The product added |
| `quantity` | `Integer` | Quantity selected |

### Order
Represents a completed purchase transaction.

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Unique identifier |
| `orderDate` | `LocalDateTime` | Timestamp when the order was placed |
| `totalAmount` | `Double` | Total cost of the order |
| `status` | `String` | Order status (e.g., PENDING) |
| `items` | `List<OrderItem>` | List of items included in the order |

### OrderItem
Represents a specific line item within a completed order, preserving the price at the time of purchase.

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Unique identifier |
| `product` | `Product` | The product purchased |
| `quantity` | `Integer` | Quantity purchased |
| `priceAtPurchase` | `Double` | Price of the product at the time of purchase |