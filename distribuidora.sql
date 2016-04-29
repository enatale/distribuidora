-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: distribuidora
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `estado_cliente`
--

DROP TABLE IF EXISTS `estado_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_cliente` (
  `id_estado_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_estado` varchar(45) NOT NULL,
  PRIMARY KEY (`id_estado_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_cliente`
--

LOCK TABLES `estado_cliente` WRITE;
/*!40000 ALTER TABLE `estado_cliente` DISABLE KEYS */;
INSERT INTO `estado_cliente` VALUES (1,'Aprobado'),(2,'Rechazado'),(3,'Pendiente');
/*!40000 ALTER TABLE `estado_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_pedido`
--

DROP TABLE IF EXISTS `estado_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_pedido` (
  `id_estado_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_estado` varchar(45) NOT NULL,
  PRIMARY KEY (`id_estado_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_pedido`
--

LOCK TABLES `estado_pedido` WRITE;
/*!40000 ALTER TABLE `estado_pedido` DISABLE KEYS */;
INSERT INTO `estado_pedido` VALUES (1,'Pendiente'),(2,'Preparado'),(3,'Entregado'),(4,'Pagado'),(5,'Cancelado');
/*!40000 ALTER TABLE `estado_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_pedido`
--

DROP TABLE IF EXISTS `linea_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linea_pedido` (
  `numero_pedido` int(11) NOT NULL,
  `codProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`numero_pedido`,`codProducto`),
  KEY `FK_productos_idx` (`codProducto`),
  CONSTRAINT `FK_pedidos` FOREIGN KEY (`numero_pedido`) REFERENCES `pedidos` (`numero_pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_productos` FOREIGN KEY (`codProducto`) REFERENCES `productos` (`codProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_pedido`
--

LOCK TABLES `linea_pedido` WRITE;
/*!40000 ALTER TABLE `linea_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `linea_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `numero_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_pedido` date NOT NULL,
  `fecha_cancelacion` date DEFAULT NULL,
  `id_estado_pedido` int(11) DEFAULT NULL,
  `dni` int(11) NOT NULL,
  PRIMARY KEY (`numero_pedido`),
  KEY `FK_personas_idx` (`dni`),
  KEY `FK_estado_pedido_idx` (`id_estado_pedido`),
  CONSTRAINT `FK_dni_personas` FOREIGN KEY (`dni`) REFERENCES `personas` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_idestado_estado_pedido` FOREIGN KEY (`id_estado_pedido`) REFERENCES `estado_pedido` (`id_estado_pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` int(30) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `legajo` int(10) DEFAULT NULL,
  `codCliente` int(10) DEFAULT NULL,
  `CUIT` bigint(20) DEFAULT NULL,
  `id_estado_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `idEstado_idx` (`id_estado_cliente`),
  CONSTRAINT `FK_idestado_estado_cliente` FOREIGN KEY (`id_estado_cliente`) REFERENCES `estado_cliente` (`id_estado_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (2455688,'Aurelio','asda',5466,'Belgrano 2481','5446','en','lalala',NULL,8,564548,3),(11111111,'Federico','Aguirre',4999999,'lalal 3665','aa@gmail.com','federico','aguirre',NULL,1,20165151985,1),(20663549,'Juan','Perez',4988025,'San Martin 2030','juance@yahoo.com','juan','123456',NULL,9,3222211,1),(22222222,'Juan Carlos','Almeida',4999999,'lalal 3665','aa@gmail.com','jc','almeida',NULL,2,564,2),(32213365,'Juan Pablo','Morettu',4645899,'Juan D. Peron 1010','jp@gmail.com','jp','jpjpjp',NULL,10,21322133655,3),(33333333,'Diego','Stroppiana',4999999,'lalal 3665','aa@gmail.com','diego','stroppiana',NULL,3,65465,1),(44444444,'Nicolas','Totaro',4999999,'lalal 3665','aa@gmail.com','nicolas','totaro',NULL,4,56546,3),(55555555,'Franco','Hernandez',4999999,'lalal 3665','aa@gmail.com','franco','hernandez',NULL,5,65446,1),(66666666,'Marco','Brusa',4999999,'lalal 3665','aa@gmail.com','marco','brusa',NULL,6,654564,2),(77777777,'Valentin','Marimon',4999999,'lalal 3665','aa@gmail.com','valentin','marimon',NULL,7,65456,1),(88888888,'Esteban','Natale',4999999,'lalal 3665','aa@gmail.com','esteban','natale',88888,NULL,NULL,NULL),(99999999,'Magali','Platero',4999999,'lalal 3665','aa@gmail.com','magali','platero',99999,NULL,NULL,NULL);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios`
--

DROP TABLE IF EXISTS `precios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precios` (
  `codProducto` int(11) NOT NULL,
  `fecha_desde` date NOT NULL,
  `importe` float NOT NULL,
  PRIMARY KEY (`codProducto`,`fecha_desde`),
  CONSTRAINT `FK_codproducto_productos` FOREIGN KEY (`codProducto`) REFERENCES `productos` (`codProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios`
--

LOCK TABLES `precios` WRITE;
/*!40000 ALTER TABLE `precios` DISABLE KEYS */;
INSERT INTO `precios` VALUES (1,'2015-10-25',20),(1,'2016-03-25',30),(2,'2016-03-25',50.25),(3,'2016-03-25',75.66),(4,'2016-03-25',88.99),(5,'2016-03-25',77),(6,'2016-03-25',20),(7,'2016-03-25',30),(8,'2016-03-25',40),(8,'2016-12-25',153.25),(9,'2016-03-25',50),(9,'2016-04-01',55),(10,'2016-03-25',60),(11,'2016-12-25',563),(12,'2016-04-28',50),(13,'2016-03-16',100),(14,'2016-04-28',12.36),(15,'2015-04-28',20),(16,'2014-03-20',35),(17,'2016-04-28',30),(18,'2016-04-28',18),(19,'2016-04-26',12),(20,'2016-04-26',20),(21,'2014-04-26',50),(22,'2016-04-26',65);
/*!40000 ALTER TABLE `precios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `codProducto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`codProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'coca cola',1025),(2,'azucar chango',1025),(3,'yerba',1025),(4,'fernet 1882',1025),(5,'vino toro',1025),(6,'escobas',1025),(7,'arroz susarelli',1025),(8,'fernet branca',1025),(9,'arroz peleador',1025),(10,'vino termidor',1025),(11,'Yerba Aguantadora',1025),(12,'Vino ViÃ±as de Balbo',1235),(13,'Vino Santa Julia',150),(14,'Galletitas Pepas Terepin',130),(15,'Yerba mate Amanda',103),(16,'Sal fina Celusal',3025),(17,'Sal gruesa Celusal',145),(18,'Sal fina Rinsal',654),(19,'Sal gruesa Rinsal',878),(20,'Aceite Marolio',5468),(21,'Aceite de oliva',3548),(22,'Chicles beldent x caja',65498);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-28 20:09:53
