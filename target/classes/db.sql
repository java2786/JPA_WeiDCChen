--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


--
-- Table structure for table `address_employee`
--

DROP TABLE IF EXISTS `address_employee`;
CREATE TABLE `address_employee` (
  `addresses_id` int(11) NOT NULL,
  `employees_id` int(11) NOT NULL,
  KEY `FKjpp5wunc51fyvxfvamjn7s4s8` (`employees_id`),
  KEY `FK2neny44pym7t528tfne1ha8th` (`addresses_id`),
  CONSTRAINT `FK2neny44pym7t528tfne1ha8th` FOREIGN KEY (`addresses_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKjpp5wunc51fyvxfvamjn7s4s8` FOREIGN KEY (`employees_id`) REFERENCES `employee` (`id`)
);
