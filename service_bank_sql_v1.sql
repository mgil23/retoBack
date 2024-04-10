-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.3.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para service_bank
CREATE DATABASE IF NOT EXISTS `service_bank` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `service_bank`;

-- Volcando estructura para tabla service_bank.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `account_id` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `initial_balance` decimal(38,2) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_d7wccbpluupn8cbm0o7nc1mhj` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla service_bank.accounts: ~1 rows (aproximadamente)
INSERT INTO `accounts` (`account_id`, `account_number`, `account_type`, `customer_id`, `initial_balance`, `state`) VALUES
	(1, '123456789', 'Savings', '3', 2000.00, 'A');

-- Volcando estructura para tabla service_bank.accounts_seq
CREATE TABLE IF NOT EXISTS `accounts_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- Volcando datos para la tabla service_bank.accounts_seq: ~1 rows (aproximadamente)
INSERT INTO `accounts_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(50001, 1, 9223372036854775806, 1, 50, 1000, 0, 0);

-- Volcando estructura para tabla service_bank.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `identification` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla service_bank.customers: ~1 rows (aproximadamente)
INSERT INTO `customers` (`customer_id`, `address`, `age`, `gender`, `identification`, `name`, `phone_number`, `password`, `state`) VALUES
	(3, '123 Main St, Anytown, USA', 30, 'M', '123456789', 'John Doe', '1234567890', 'password123', 'A');

-- Volcando estructura para tabla service_bank.movements
CREATE TABLE IF NOT EXISTS `movements` (
  `movements_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) DEFAULT NULL,
  `current_balance` decimal(38,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`movements_id`),
  KEY `FK1a6nru7corjv5b2vidld4ef5r` (`account_id`),
  CONSTRAINT `FK1a6nru7corjv5b2vidld4ef5r` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla service_bank.movements: ~1 rows (aproximadamente)
INSERT INTO `movements` (`movements_id`, `amount`, `current_balance`, `date`, `type`, `account_id`) VALUES
	(1, 1000.00, 5000.00, '2024-04-10', 'DEPOSITO', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
