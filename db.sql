-- Category Table
CREATE TABLE category (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          description TEXT
);

-- Supplier Table
CREATE TABLE supplier (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          phone VARCHAR(20),
                          address TEXT
);

-- Product Table
CREATE TABLE product (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         category_id INT,
                         price DECIMAL(10, 2),
                         quantity_in_stock INT,
                         sku VARCHAR(100),
                         supplier_id INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL,
                         FOREIGN KEY (supplier_id) REFERENCES supplier(id) ON DELETE SET NULL
);

-- Customer Table
CREATE TABLE customer (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          phone VARCHAR(20),
                          address TEXT
);

-- Order Table
CREATE TABLE `order` (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         customer_id INT,
                         order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status ENUM('PENDING', 'COMPLETED', 'CANCELLED') NOT NULL,
                         total_amount DECIMAL(10, 2),
                         FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- OrderItem Table
CREATE TABLE order_item (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            order_id INT,
                            product_id INT,
                            quantity INT NOT NULL,
                            unit_price DECIMAL(10, 2),
                            FOREIGN KEY (order_id) REFERENCES `order`(id) ON DELETE CASCADE,
                            FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

-- StockMovement Table
CREATE TABLE stock_movement (
                                id INT PRIMARY KEY AUTO_INCREMENT,
                                product_id INT,
                                quantity INT NOT NULL,
                                movement_type ENUM('INCOMING', 'OUTGOING', 'ADJUSTMENT') NOT NULL,
                                date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                reference VARCHAR(255),
                                FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

-- User Table
CREATE TABLE `user` (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        full_name VARCHAR(255) NOT NULL,
                        age INT NOT NULL,
                        phone_number VARCHAR(20) NOT NULL
);

-- Admin Table (inherits from User)
CREATE TABLE admin (
                       id INT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       FOREIGN KEY (id) REFERENCES `user`(id) ON DELETE CASCADE
);
