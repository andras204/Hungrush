-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2023 at 05:23 PM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAvailableDishes` (IN `restaurant_id_IN` INT)   SELECT dish_id FROM `dish_available` WHERE restaurant_id=restaurant_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCourierStatus` (IN `courier_id_IN` INT)   SELECT `status` FROM courier where courier_id_IN=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDish` (IN `dish_id_IN` INT)   SELECT * FROM `dish` WHERE id=dish_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDishCategory` (IN `dish_id_IN` INT)   SELECT `category` FROM dish WHERE id=dish_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdleCouriers` ()   SELECT *

FROM courier

WHERE status = 'idle'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getOrderedDishes` (IN `delivery_id_IN` INT)   SELECT dish_id,amount FROM `dish_ordered` WHERE delivery_id=delivery_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getRestaurantCategory` (IN `restaurant_id_IN` INT)   SELECT `category` FROM restaurant WHERE id=restaurant_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `isDishAvailable` (IN `dish_id_IN` INT)  COMMENT '0 if unavailable, 1 or more(!) if available' SELECT COUNT(*) FROM `dish_available` WHERE dish_id=dish_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `isDishAvailableAtRestaurant` (IN `dish_id_IN` INT, IN `restaurant_id_IN` INT)  COMMENT 'returns 0 if unavailable, 1 if available' SELECT COUNT(*) FROM `dish_available` WHERE dish_id=dish_id_IN AND restaurant_id=restaurant_id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `isRestaurantOpen` (IN `restaurant_id_IN` INT)   SELECT COUNT(*) FROM restaurant WHERE restaurant_id_IN = id
AND opening_time < CURRENT_TIME && CURRENT_TIME < closing_time$$

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
(1, 'Test Courier', 'bicycle', '+36123456789', 'offline');

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
(1, 'Test Customer', 'Test street 4/a', 'test@testmail.com', '+36123456789');

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
(1, 1, 1, 1, 'complete');

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
(1, 'Test dish', 'test category', 1, 'test.png');

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
  `closing_time` time NOT NULL,
  `days_open` set('sun','mon','tue','wed','thu','fri','sat') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`id`, `name`, `category`, `address`, `opening_time`, `closing_time`, `days_open`) VALUES
(1, 'Test restaurant', 'test category', 'Test street 10', '08:00:00', '20:00:00', 'mon,tue,wed,thu,fri');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `dish`
--
ALTER TABLE `dish`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
