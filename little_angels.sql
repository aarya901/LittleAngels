-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2025 at 01:04 AM
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
-- Database: `little_angels`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_item` (
  `cart_item_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `cart_quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_item`
--

INSERT INTO `cart_item` (`cart_item_id`, `user_id`, `product_id`, `cart_quantity`) VALUES
(1, 1, 2, 1),
(2, 2, 3, 2),
(3, 3, 4, 3),
(4, 4, 5, 1),
(6, 6, 7, 1),
(7, 7, 1, 1),
(12, 21, 3, 1),
(13, 21, 3, 1),
(14, 21, 3, 1),
(16, 21, 3, 1),
(17, 21, 3, 1),
(18, 21, 3, 1),
(19, 21, 3, 1),
(20, 21, 3, 1),
(21, 21, 3, 1),
(22, 21, 3, 1),
(23, 21, 3, 1),
(24, 21, 3, 1),
(25, 21, 3, 1),
(26, 21, 3, 1),
(27, 21, 3, 1),
(28, 21, 3, 1),
(29, 21, 5, 1),
(30, 21, 3, 1),
(31, 21, 3, 1),
(32, 21, 3, 1),
(33, 21, 16, 3),
(34, 21, 3, 1),
(35, 21, 3, 1),
(36, 36, 3, 1),
(37, 36, 3, 1),
(38, 39, 3, 1),
(41, 40, 4, 1),
(42, 40, 4, 1),
(43, 40, 2, 1),
(44, 59, 2, 1),
(51, 62, 2, 1),
(52, 62, 4, 3),
(55, 60, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`order_id`, `user_id`, `order_date`) VALUES
(1, 1, '2024-01-01'),
(2, 2, '2024-06-15'),
(3, 3, '2024-12-25'),
(4, 4, '2025-01-10'),
(5, 5, '2025-04-20'),
(6, 6, '2025-08-05'),
(7, 7, '2025-12-31'),
(8, 21, '2025-05-16'),
(9, 21, '2025-05-16'),
(10, 21, '2025-05-16'),
(11, 21, '2025-05-16'),
(12, 21, '2025-05-16'),
(13, 21, '2025-05-16'),
(14, 36, '2025-05-16'),
(15, 36, '2025-05-16'),
(16, 39, '2025-05-18'),
(17, 39, '2025-05-18'),
(18, 39, '2025-05-18'),
(19, 39, '2025-05-18'),
(20, 39, '2025-05-18'),
(21, 40, '2025-05-18'),
(22, 40, '2025-05-18'),
(23, 40, '2025-05-19'),
(24, 59, '2025-05-19'),
(25, 59, '2025-05-19'),
(26, 60, '2025-05-19'),
(27, 60, '2025-05-19'),
(28, 60, '2025-05-20'),
(29, 60, '2025-05-20'),
(30, 60, '2025-05-20'),
(31, 60, '2025-05-20'),
(32, 62, '2025-05-20'),
(33, 62, '2025-05-20'),
(34, 62, '2025-05-20'),
(35, 60, '2025-05-20'),
(36, 60, '2025-05-20');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `order_quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`order_id`, `product_id`, `order_quantity`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 1),
(4, 4, 5),
(5, 5, 1),
(6, 6, 2),
(7, 7, 3),
(8, 3, 1),
(9, 3, 1),
(10, 4, 1),
(11, 9, 1),
(12, 3, 1),
(13, 3, 1),
(14, 3, 1),
(15, 3, 1),
(16, 3, 1),
(17, 3, 2),
(18, 3, 1),
(19, 3, 4),
(20, 4, 1),
(21, 3, 1),
(22, 4, 1),
(23, 4, 1),
(24, 2, 1),
(25, 2, 10),
(26, 3, 1),
(27, 5, 1),
(28, 3, 1),
(29, 4, 4),
(30, 3, 2),
(31, 5, 2),
(32, 4, 3),
(33, 3, 3),
(34, 5, 1),
(35, 3, 1),
(36, 89, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `product_image` varchar(50) DEFAULT NULL,
  `product_name` varchar(135) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `price`, `product_image`, `product_name`) VALUES
(2, 650.00, 'uploads/oil.jpg', 'Johnson’s Baby Moisturizing Oil - 400ml'),
(3, 2500.00, 'uploads/toothpaste.png', 'Colgate Herbal Toothpaste - 150g'),
(4, 4000.00, 'uploads/diaper_bag.png', 'Huggies Diaper Bag - Large Capacity'),
(5, 1200.00, 'uploads/baby_lego.png', 'LEGO Duplo Baby Blocks - 50 Pieces'),
(7, 600.00, 'uploads/moisturizer2.jpg', 'Eco-Store Baby Moisturizer - 200ml'),
(9, 300.00, 'uploads/toothpaste_mint.png', 'Milk Teeth Baby Toothpaste - Mint Flavor'),
(81, 400.00, 'uploads/shampoo1.jpg', 'Johnson\'s Baby Shampoo'),
(87, 400.00, 'uploads/colgate.jpg', 'Colgate Baby Toothpaste'),
(89, 4000.00, 'uploads/himalaya.png', 'Himalayan Baby Complete Care Set');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_password` varchar(100) DEFAULT NULL,
  `user_role` varchar(50) DEFAULT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  `user_phone` varchar(10) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_password`, `user_role`, `user_image`, `user_phone`, `user_email`, `first_name`, `last_name`, `address`, `dob`, `gender`) VALUES
(50, 'asmita', 'cbb673cb1ea8caf16cb26df436031a41', 'customer', 'uploads/asmita.png', '9801234567', 'asmita@gmail.com', 'Asmita', 'Shrestha', 'Kathmandu, Nepal', '1995-04-12', 'female'),
(51, 'kamala', '7ca0255133c4af0d0059e96db5cfcdc7', 'admin', 'uploads/kamala.png', '9802345678', 'kamala@gmail.com', 'Kamala', 'Koirala', 'Lalitpur, Nepal', '1990-08-05', 'female'),
(52, 'rachina', '0dd54eec3d17c1653f065a531c9c82e3', 'customer', 'uploads/rachina_gosai.png', '9803456789', 'rachina@gmail.com', 'Rachina', 'Gosai', 'Bhaktapur, Nepal', '1992-11-23', 'female'),
(53, 'aryan', '165669f10483da5f34d1b4ccc25bf308', 'admin', 'uploads/aryan_pudasaini.png', '9804567890', 'aryan@gmail.com', 'Aryan', 'Pudasaini', 'Pokhara, Nepal', '1997-02-17', 'male'),
(54, 'anushree', 'd21742dc91482ecbb251f80d9fcc5d46', 'admin', 'uploads/anushree_gami.png', '9805678901', 'anushree@gmail.com', 'Anushree', 'Gami', 'Biratnagar, Nepal', '1994-07-30', 'female'),
(55, 'kamal', '443e5a43a3a3e8e9ef46bfa37bef7600', 'admin', 'uploads/kamal.png', '9806789012', 'kamal@gmail.com', 'Kamal', 'Thapa', 'Chitwan, Nepal', '1993-03-10', 'male'),
(56, 'priya', '3e17f774c90d60dc17444c0423470e04', 'customer', 'uploads/priya_soni.png', '9807890123', 'priya@gmail.com', 'Priya', 'Soni', 'Butwal, Nepal', '1996-12-05', 'female'),
(57, 'aarya', '3f216876e87052c718a541c9218ce4ee', 'customer', 'uploads/aarya_gautam.png', '9808901234', 'aarya@gmail.com', 'Aarya', 'Gautam', 'Kathmandu, Nepal', '1998-09-21', 'female'),
(58, 'asmit', '0aa07ab86fd1a1dd85c307434ff39bc5TLtd8G5qNhv6sV1fqAnc+UsNnjSHYLM3RzQ2rXrv85CzAT0o+I=SHY', 'customer', 'uploads/asmit.png', '9809012345', 'asmit@gmail.com', 'Asmit', 'Thakur', 'Dharan, Nepal', '1991-06-14', 'male'),
(59, 'richa12', 'JO2UgZmctbJ0sVeW3b5GKTLtd8G5qNhv6sV1fqAnc+UsNnjSHYLM3RzQ2rXrv85CzAT0o+I=', 'admin', 'uploads/richa.png', '9800807089', 'richa1@gmail.com', 'Richa', 'Gautam', 'Kalanki, Kathmandu', '2003-02-06', 'Female'),
(60, 'richa123', '3vtdLOsHz/tfQqSc6TV5I6D9aymtkQJu6wQx7b/oeP0CZwmlGpOW6csGfFi+jvkvBLkcjg==', 'customer', 'uploads/6b056cfb-a3b3-4424-892c-ceb6f3d60ba2.png', '9800987892', 'maya123@gmail.com', 'Maya', 'Wagle', 'Dillibazar, Kathmandu', '2005-10-06', 'Female'),
(62, 'asmita12', 'GGbdqkS0EBdA4/zEth+GI96t05R1ufhXBDshRgtO/UzeBORy8APY4gWbOOtURXgP2ublLQ==', 'customer', 'uploads/b04e96ca-31a7-4b76-b1d0-f5940e705d16.png', '9800000005', 'asmita1@gmail.com', 'Asmita', 'Gautam', 'Kalanki, Kathmandu', '2005-03-16', 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`cart_item_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `unique_user_name` (`user_name`),
  ADD UNIQUE KEY `unique_user_phone` (`user_phone`),
  ADD UNIQUE KEY `unique_user_email` (`user_email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `cart_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE;

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  ADD CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
