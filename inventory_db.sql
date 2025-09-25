-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 25, 2025 at 06:59 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(1, 'Beverages', 'Soft drinks, juices, and bottled water', '2025-09-24 15:25:37', '2025-09-24 15:25:37'),
(2, 'Snacks', 'Chips, crackers, and nuts', '2025-09-24 15:25:37', '2025-09-24 15:25:37'),
(3, 'Dairy', 'Milk, yogurt, and cheese', '2025-09-24 15:25:38', '2025-09-24 15:25:38');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `supplier_id` bigint(20) UNSIGNED DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `stock_quantity` int(11) NOT NULL DEFAULT 0 CHECK (`stock_quantity` >= 0),
  `description` varchar(255) DEFAULT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `category_id`, `supplier_id`, `name`, `sku`, `price`, `stock_quantity`, `description`, `expiry_date`, `image_url`, `created_at`, `updated_at`) VALUES
(1, 1, 2, 'Coca-Cola 330ml Can', 'SKU-COC-330', 1.20, 38, 'Classic Coke, 330ml can', NULL, NULL, '2025-09-24 15:25:38', '2025-09-24 15:25:38'),
(2, 1, 2, 'Orange Juice 1L', 'SKU-OJ-1L', 2.90, 30, '100% orange juice, 1 liter', NULL, NULL, '2025-09-24 15:25:38', '2025-09-24 15:25:38'),
(3, 2, 1, 'Potato Chips 200g', 'SKU-CHP-200', 2.30, 135, 'Salted potato chips, 200 grams', NULL, NULL, '2025-09-24 15:25:38', '2025-09-24 15:25:38'),
(4, 3, 1, 'Whole Milk 1L', 'SKU-MLK-1L', 1.10, 225, 'Whole milk, 1 liter', NULL, NULL, '2025-09-24 15:25:38', '2025-09-24 15:25:38');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact_email` varchar(191) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `contact_info` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `contact_email`, `phone`, `address`, `created_at`, `updated_at`, `contact_info`) VALUES
(1, 'Acme Foods Ltd', 'sales@acmefoods.example', '+1-202-555-0100', '101 Market St, Springfield', '2025-09-24 15:25:37', '2025-09-24 15:25:37', ''),
(2, 'Global Drinks Inc.', 'orders@globaldrinks.example', '+1-202-555-0111', '55 Beverage Ave, Metropolis', '2025-09-24 15:25:37', '2025-09-24 15:25:37', '');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `supplier_id` bigint(20) UNSIGNED DEFAULT NULL,
  `transaction_type` enum('SALE','PURCHASE','RETURN','ADJUSTMENT') NOT NULL,
  `status` enum('PENDING','COMPLETED','CANCELLED') NOT NULL DEFAULT 'COMPLETED',
  `total_products` int(11) NOT NULL DEFAULT 0 CHECK (`total_products` >= 0),
  `total_price` decimal(38,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `update_at` datetime(6) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `user_id`, `supplier_id`, `transaction_type`, `status`, `total_products`, `total_price`, `description`, `note`, `created_at`, `updated_at`, `update_at`, `product_id`) VALUES
(1, 1, 1, 'PURCHASE', 'COMPLETED', 300, 350.00, 'Initial stock purchase', 'PO-ACME-0001', '2025-09-24 15:25:38', '2025-09-24 15:25:38', NULL, NULL),
(2, 2, NULL, 'SALE', 'COMPLETED', 17, 25.90, 'Walk-in sale', 'POS-0001', '2025-09-24 15:25:38', '2025-09-24 15:25:38', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_items`
--

CREATE TABLE `transaction_items` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `transaction_id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `quantity` int(11) NOT NULL CHECK (`quantity` > 0),
  `unit_price` decimal(10,2) NOT NULL CHECK (`unit_price` >= 0),
  `line_total` decimal(12,2) GENERATED ALWAYS AS (`quantity` * `unit_price`) STORED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction_items`
--

INSERT INTO `transaction_items` (`id`, `transaction_id`, `product_id`, `quantity`, `unit_price`) VALUES
(1, 1, 3, 100, 1.80),
(2, 1, 4, 200, 0.85),
(3, 2, 1, 12, 1.20),
(4, 2, 3, 5, 2.30);

--
-- Triggers `transaction_items`
--
DELIMITER $$
CREATE TRIGGER `trg_apply_stock_after_tx_items` AFTER INSERT ON `transaction_items` FOR EACH ROW BEGIN
  DECLARE tx_type   ENUM('SALE','PURCHASE','RETURN','ADJUSTMENT');
  DECLARE tx_status ENUM('PENDING','COMPLETED','CANCELLED');

  SELECT transaction_type, status
    INTO tx_type, tx_status
  FROM transactions
  WHERE id = NEW.transaction_id;

  IF tx_status = 'COMPLETED' THEN
    IF tx_type = 'PURCHASE' THEN
      UPDATE products SET stock_quantity = stock_quantity + NEW.quantity
      WHERE id = NEW.product_id;
    ELSEIF tx_type = 'SALE' THEN
      UPDATE products SET stock_quantity = GREATEST(0, stock_quantity - NEW.quantity)
      WHERE id = NEW.product_id;
    ELSEIF tx_type = 'RETURN' THEN
      UPDATE products SET stock_quantity = stock_quantity + NEW.quantity
      WHERE id = NEW.product_id;
    -- ADJUSTMENT: decide your own policy; leaving as no-op here
    END IF;
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL DEFAULT 'USER',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `role`, `created_at`, `updated_at`, `name`, `password`, `phone_number`) VALUES
(1, 'admin@example.com', 'ADMIN', '2025-09-24 15:25:37', '2025-09-24 16:27:21', '', '$2y$10$examplehashADMIN', ''),
(2, 'clerk@example.com', 'STAFF', '2025-09-24 15:25:37', '2025-09-24 16:27:21', '', '$2y$10$examplehashCLERK', ''),
(3, 'alimarat131@gmail.com', 'MANAGER', '2025-09-24 16:28:25', '2025-09-24 16:28:25', 'Ali Marat', '$2a$10$Zd9Bb.jhWBiQdLNMKTj3u.aqQAqyabfMo6UpMSMn7PGgAVt6ce2ju', '033944400');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_categories_name` (`name`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_products_sku` (`sku`),
  ADD KEY `ix_products_name` (`name`),
  ADD KEY `ix_products_category` (`category_id`),
  ADD KEY `ix_products_supplier` (`supplier_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_suppliers_name` (`name`),
  ADD KEY `ix_suppliers_name` (`name`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ix_transactions_type` (`transaction_type`),
  ADD KEY `ix_transactions_status` (`status`),
  ADD KEY `ix_transactions_supplier` (`supplier_id`),
  ADD KEY `ix_transactions_user` (`user_id`);

--
-- Indexes for table `transaction_items`
--
ALTER TABLE `transaction_items`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_tx_product` (`transaction_id`,`product_id`),
  ADD KEY `ix_tx_items_product` (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_users_email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaction_items`
--
ALTER TABLE `transaction_items`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_products_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_products_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `fk_transactions_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_transactions_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `transaction_items`
--
ALTER TABLE `transaction_items`
  ADD CONSTRAINT `fk_tx_items_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tx_items_transaction` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
