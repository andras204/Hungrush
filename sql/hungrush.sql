-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2023 at 10:32 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hungrush`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCourier` (IN `meansOfTransportIN` ENUM('car','bicycle','moped'), IN `nameIN` VARCHAR(40), IN `phonenumberIN` VARCHAR(40), IN `statusIN` ENUM('offline','idle','delivering'))   INSERT INTO `courier` (`courier`.`means_of_transport`,`courier`.`name`, `courier`.`phone_number`, `courier`.`status`)
 VALUES (meansOfTransportIN, nameIN, phonenumberIN, statusIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addCustomer` (IN `addressIN` VARCHAR(50), IN `emailIN` VARCHAR(50), IN `nameIN` VARCHAR(50), IN `phonenumberIN` VARCHAR(50))   INSERT INTO customer (address, email, phone_number, name)
VALUES (addressIN, emailIN, phoneNumberIN, nameIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addDelivery` (IN `courier_id_IN` INT, IN `customer_id_IN` INT, IN `restaurant_id_IN` INT, IN `statusIN` ENUM('pending','complete'))   INSERT INTO delivery (courier_id, customer_id, restaurant_id, status)
VALUES (courier_id_IN, customer_id_IN, restaurant_id_IN, statusIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addDish` (IN `category_IN` VARCHAR(50), IN `image_url_IN` VARCHAR(50), IN `name_IN` VARCHAR(50), IN `price_IN` INT)   INSERT INTO dish (category, image_url, name, price)
VALUES (category_IN, image_url_IN, name_IN, price_IN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addRestaurant` (IN `addressIN` VARCHAR(50), IN `categoryIN` VARCHAR(50), IN `closingtimeIN` TIME, IN `openingtimeIN` TIME, IN `nameIN` VARCHAR(50))   INSERT INTO restaurant (address, category, closing_time, name, opening_time)
VALUES (addressIN, cateogryIN, closingtimeIN, nameIN, openingtimeIN )$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createOrder` ()   SELECT 
	dish.id,
    dish.price AS 'Item Price', 
    dish_ordered.amount AS 'No. of Items', 
    restaurant.name AS 'Restaurant Name', 
    courier.name AS 'Courier Name', 
    customer.name AS 'Customer Name', 
    delivery.id AS 'Delivery ID'
FROM 
    delivery,
	dish
INNER JOIN 
    dish_ordered ON dish.id = dish_ordered.dish_id 
INNER JOIN 
    restaurant ON dish.restaurant.id = restaurant.id 
INNER JOIN 
    courier ON delivery.courier_id = courier.id 
INNER JOIN 
    customer ON delivery.customer_id = customer.id 
WHERE 
    delivery.id = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCourier` (IN `courier_id_IN` INT)   DELETE FROM `courier` where courier_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCustomer` (IN `customer_id_IN` INT)   DELETE FROM `customer` where customer_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDelivery` (IN `delivery_id_IN` INT)   DELETE FROM delivery WHERE delivery_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDish` (IN `dish_id_IN` INT)   DELETE FROM `dish` WHERE dish_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteRestaurant` (IN `restaurant_id_IN` INT)   DELETE FROM restaurant WHERE restaurant_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCouriers` ()   SELECT * FROM `courier`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCustomers` ()   SELECT * FROM customer$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllDeliveries` ()   SELECT * FROM `delivery`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllDishes` ()   SELECT * FROM dish$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRestaurants` ()   SELECT * FROM restaurant$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAvailableDishes` (IN `restaurant_id_IN` INT)   SELECT dish_id FROM `dish_available` WHERE restaurant_id=restaurant_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCourier` (IN `courier_id_IN` INT)   SELECT * FROM `courier` WHERE courier_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCourierStatus` (IN `courier_id_IN` INT)   SELECT `status` FROM `courier` where courier_id_IN=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCustomer` (IN `customer_id_IN` INT)   SELECT * FROM customer WHERE customer_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDelivery` (IN `delivery_id_IN` INT)   SELECT * FROM `delivery` WHERE delivery_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDish` (IN `dish_id_IN` INT)   SELECT * FROM `dish` WHERE id=dish_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDishCategory` (IN `dish_id_IN` INT)   SELECT `category` FROM dish WHERE id=dish_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdleCouriers` ()   SELECT *

FROM `courier`

WHERE status = 'idle'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getOpenRestaurants` ()   SELECT * FROM restaurant
WHERE CURRENT_TIME BETWEEN opening_time AND closing_time$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getOrderedDishes` (IN `delivery_id_IN` INT)   SELECT dish_id,amount FROM `dish_ordered` WHERE delivery_id=delivery_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getRestaurant` (IN `restaurant_id_IN` INT)   SELECT * FROM restaurant WHERE restaurant_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getRestaurantCategory` (IN `restaurant_id_IN` INT)   SELECT `category` FROM restaurant WHERE id=restaurant_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalPrice` (IN `dish_id_IN` INT, IN `amount_IN` INT)   SELECT SUM(dish.price * dish_ordered.amount) as total_order_amount
FROM dish
JOIN dish_ordered ON dish.id = dish_ordered.dish_id
WHERE dish.id = dish_id_IN
AND dish_ordered.amount = amount_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `isDishAvailable` (IN `dish_id_IN` INT)  COMMENT '0 if unavailable, 1 or more(!) if available' SELECT COUNT(*) FROM `dish_available` WHERE dish_id=dish_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `isDishAvailableAtRestaurant` (IN `dish_id_IN` INT, IN `restaurant_id_IN` INT)  COMMENT 'returns 0 if unavailable, 1 if available' SELECT COUNT(*) FROM `dish_available` WHERE dish_id=dish_id_IN AND restaurant_id=restaurant_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `isRestaurantOpen` (IN `restaurant_id_IN` INT)   SELECT
  CASE
    WHEN COUNT(*) > 0 THEN 1  -- Restaurant is open
    ELSE 0  -- Restaurant is closed
  END AS is_open
FROM restaurant
WHERE restaurant_id_IN = id
AND CURRENT_TIME BETWEEN opening_time AND closing_time$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCourier` (IN `courrier_id_IN` INT, IN `means_of_transport_IN` ENUM('car','bicycle','moped'), IN `nameIN` VARCHAR(50), IN `phone_numberIN` VARCHAR(50), IN `status` ENUM('idle','offline','delivering'))   UPDATE courier

SET means_of_transport = means_of_transportIN, name = nameIN, phone_number = phone_number_IN, status = statusIN

where courier_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCustomer` (IN `customeridIN` INT, IN `addressIN` VARCHAR(50), IN `emailIN` VARCHAR(50), IN `nameIn` VARCHAR(50), IN `phoneNumberIN` VARCHAR(50))   UPDATE customer 

SET address = addressIN, email = emailIN, name = nameIN, phone_number = phoneNumberIN

where customeridIN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDelivery` (IN `delivery_id_IN` INT, IN `courier_id_IN` INT, IN `customer_id_IN` INT, IN `restaurant_id_IN` INT, IN `statusIN` INT)   UPDATE delivery

SET courier_id = courier_id_IN, customer_id = customer_id_IN, restaurant_id = restaurant_id_IN, `status` = statusIN

where delivery_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDish` (IN `dish_id_IN` INT, IN `categoryIN` VARCHAR(50), IN `nameIN` VARCHAR(50), IN `image_url_IN` VARCHAR(50), IN `priceIN` INT)   UPDATE dish

SET category = categoryIN, name = nameIN, image_url_IN = imageUrl, price = priceIN

where dish_id_IN = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateRestaurant` (IN `addressIN` VARCHAR(50), IN `categoryIN` VARCHAR(50), IN `closingtimeIN` TIME, IN `restaurant_id_IN` INT, IN `nameIN` VARCHAR(50), IN `openingtimeIN` TIME)   UPDATE restaurant

SET category = categoryIN, name = nameIN, address = addressIN, closing_time = closing_time_IN, opening_time = opening_time_IN

where restaurant_id_IN = id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `courier`
--

CREATE TABLE `courier` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `means_of_transport` enum('bicycle','car','moped') NOT NULL,
  `phone_number` varchar(12) NOT NULL,
  `status` enum('offline','idle','delivering') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `courier`
--

INSERT INTO `courier` (`id`, `name`, `means_of_transport`, `phone_number`, `status`) VALUES
(1, 'Test Courier', 'bicycle', '+36123456789', 'idle');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(320) NOT NULL,
  `email` varchar(320) NOT NULL,
  `phone_number` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `address`, `email`, `phone_number`) VALUES
(1, 'john', 'test st.', 'john@test.com', '12345'),
(2, 'TEST1', 'TEST1', 'TEST1', '0');

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

CREATE TABLE `delivery` (
  `id` int(11) NOT NULL,
  `restaurant_id` int(11) NOT NULL,
  `courier_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `status` enum('pending','complete') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `delivery`
--

INSERT INTO `delivery` (`id`, `restaurant_id`, `courier_id`, `customer_id`, `status`) VALUES
(1, 1, 1, 1, 'complete'),
(2, 1, 1, 1, 'pending'),
(3, 1, 2, 2, 'pending'),
(4, 1, 1, 1, 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `dish`
--

CREATE TABLE `dish` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `category` varchar(64) NOT NULL,
  `price` int(11) NOT NULL,
  `image_url` varchar(320) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dish`
--

INSERT INTO `dish` (`id`, `name`, `category`, `price`, `image_url`) VALUES
(1, 'Test dish', 'test category', 1, 'test.png'),
(4, 'cupcake', 'dessert', 124, 'image.png');

-- --------------------------------------------------------

--
-- Table structure for table `dish_available`
--

CREATE TABLE `dish_available` (
  `restaurant_id` int(11) NOT NULL,
  `dish_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dish_available`
--

INSERT INTO `dish_available` (`restaurant_id`, `dish_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `dish_ordered`
--

CREATE TABLE `dish_ordered` (
  `delivery_id` int(11) NOT NULL,
  `dish_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dish_ordered`
--

INSERT INTO `dish_ordered` (`delivery_id`, `dish_id`, `amount`) VALUES
(1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `category` varchar(64) NOT NULL,
  `address` varchar(320) NOT NULL,
  `opening_time` time NOT NULL,
  `closing_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`id`, `name`, `category`, `address`, `opening_time`, `closing_time`) VALUES
(1, 'Test restaurant', 'test category', 'Test street 10', '08:00:00', '20:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courier`
--
ALTER TABLE `courier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dish`
--
ALTER TABLE `dish`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dish_available`
--
ALTER TABLE `dish_available`
  ADD PRIMARY KEY (`restaurant_id`,`dish_id`);

--
-- Indexes for table `dish_ordered`
--
ALTER TABLE `dish_ordered`
  ADD PRIMARY KEY (`delivery_id`,`dish_id`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courier`
--
ALTER TABLE `courier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `dish`
--
ALTER TABLE `dish`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
