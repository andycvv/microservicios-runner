-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: runner_cibertec_db
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_boleta`
--

DROP TABLE IF EXISTS `tb_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_boleta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `direccion` varchar(100) NOT NULL,
  `estado` varchar(100) NOT NULL,
  `id_tienda` int NOT NULL,
  `id_trabajador` int NOT NULL,
  `id_usuario` int NOT NULL,
  `igv` decimal(38,2) NOT NULL,
  `nombre_cliente` varchar(100) NOT NULL,
  `numero_documento` varchar(8) NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  `subtotal` decimal(38,2) NOT NULL,
  `tipo_tarjeta` varchar(100) NOT NULL,
  `total` decimal(38,2) NOT NULL,
  `total_descuento` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjdqftkaa6kot4twnuaaykthrp` (`id_tienda`),
  KEY `FKmepgbhbn2688jkhkhj31wvvxw` (`id_trabajador`),
  KEY `FKck9nxiwf5e2os2svcv0qdqj7j` (`id_usuario`),
  CONSTRAINT `FKck9nxiwf5e2os2svcv0qdqj7j` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`),
  CONSTRAINT `FKjdqftkaa6kot4twnuaaykthrp` FOREIGN KEY (`id_tienda`) REFERENCES `tb_tienda` (`id`),
  CONSTRAINT `FKmepgbhbn2688jkhkhj31wvvxw` FOREIGN KEY (`id_trabajador`) REFERENCES `tb_trabajador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_branch`
--

DROP TABLE IF EXISTS `tb_branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_subcategoria` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKid4cp5yraen46e4d6rb45ywd9` (`id_subcategoria`),
  CONSTRAINT `FKid4cp5yraen46e4d6rb45ywd9` FOREIGN KEY (`id_subcategoria`) REFERENCES `tb_subcategoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_calzado`
--

DROP TABLE IF EXISTS `tb_calzado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_calzado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `stock` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_color` int DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  `id_talla` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKegpw4soq8qxxvcdenrxyge719` (`id_color`),
  KEY `FKryq0cedo4863ho6tcos8ogakw` (`id_producto`),
  KEY `FKg0ggpvgcdkgklmd2o1vjxjatf` (`id_talla`),
  CONSTRAINT `FKegpw4soq8qxxvcdenrxyge719` FOREIGN KEY (`id_color`) REFERENCES `tb_color` (`id`),
  CONSTRAINT `FKg0ggpvgcdkgklmd2o1vjxjatf` FOREIGN KEY (`id_talla`) REFERENCES `tb_talla` (`id`),
  CONSTRAINT `FKryq0cedo4863ho6tcos8ogakw` FOREIGN KEY (`id_producto`) REFERENCES `tb_producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_categoria`
--

DROP TABLE IF EXISTS `tb_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_color`
--

DROP TABLE IF EXISTS `tb_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_departamento`
--

DROP TABLE IF EXISTS `tb_departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_departamento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_pais` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3t1l3yjip02wql5ia2wrmfkjg` (`id_pais`),
  CONSTRAINT `FK3t1l3yjip02wql5ia2wrmfkjg` FOREIGN KEY (`id_pais`) REFERENCES `tb_pais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_distrito`
--

DROP TABLE IF EXISTS `tb_distrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_distrito` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_provincia` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmohmb8m74ctfw4ja7tom40hqr` (`id_provincia`),
  CONSTRAINT `FKmohmb8m74ctfw4ja7tom40hqr` FOREIGN KEY (`id_provincia`) REFERENCES `tb_provincia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_marca`
--

DROP TABLE IF EXISTS `tb_marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_marca` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_material`
--

DROP TABLE IF EXISTS `tb_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_material` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_pais`
--

DROP TABLE IF EXISTS `tb_pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `abreviatura` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `simbolo` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_producto`
--

DROP TABLE IF EXISTS `tb_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` decimal(38,2) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_branch` int DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  `id_marca` int DEFAULT NULL,
  `id_material` int DEFAULT NULL,
  `id_subcategoria` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKvumi8p0hm9e8ani9n39lb63y` (`id_branch`),
  KEY `FK7tyog6khc1m2yuqvcswna17fd` (`id_categoria`),
  KEY `FK505d3ixi019eukymfjh8npa0d` (`id_marca`),
  KEY `FK4fk19r9v5e2e9k5cg41uxoyyq` (`id_material`),
  KEY `FK1uupep6hffh170d17wxph72x9` (`id_subcategoria`),
  CONSTRAINT `FK1uupep6hffh170d17wxph72x9` FOREIGN KEY (`id_subcategoria`) REFERENCES `tb_subcategoria` (`id`),
  CONSTRAINT `FK4fk19r9v5e2e9k5cg41uxoyyq` FOREIGN KEY (`id_material`) REFERENCES `tb_material` (`id`),
  CONSTRAINT `FK505d3ixi019eukymfjh8npa0d` FOREIGN KEY (`id_marca`) REFERENCES `tb_marca` (`id`),
  CONSTRAINT `FK7tyog6khc1m2yuqvcswna17fd` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categoria` (`id`),
  CONSTRAINT `FKvumi8p0hm9e8ani9n39lb63y` FOREIGN KEY (`id_branch`) REFERENCES `tb_branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_provincia`
--

DROP TABLE IF EXISTS `tb_provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_provincia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_departamento` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoohygt3yxf1fy051wfxj14y6d` (`id_departamento`),
  CONSTRAINT `FKoohygt3yxf1fy051wfxj14y6d` FOREIGN KEY (`id_departamento`) REFERENCES `tb_departamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_rol`
--

DROP TABLE IF EXISTS `tb_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_subcategoria`
--

DROP TABLE IF EXISTS `tb_subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_subcategoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsa2vm4sw2h45pj0qs81ym69i` (`id_categoria`),
  CONSTRAINT `FKsa2vm4sw2h45pj0qs81ym69i` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_talla`
--

DROP TABLE IF EXISTS `tb_talla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_talla` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `numero` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tienda`
--

DROP TABLE IF EXISTS `tb_tienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tienda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `direccion` varchar(100) NOT NULL,
  `id_departamento` int NOT NULL,
  `id_distrito` int NOT NULL,
  `id_pais` int NOT NULL,
  `id_provincia` int NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `nombre_legal` varchar(100) NOT NULL,
  `ruc` varchar(100) NOT NULL,
  `ruta_imagen` varchar(200) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `ubicacion` varchar(100) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg3m1objtxeq7cn9j7995n8o85` (`id_departamento`),
  KEY `FKfvffnhr4qwv9fm1opa9221ax1` (`id_distrito`),
  KEY `FK2nrocsjll0f01t0rf7air0x18` (`id_pais`),
  KEY `FKmdlwketg5mgmw6u0fs08ij4t9` (`id_provincia`),
  CONSTRAINT `FK2nrocsjll0f01t0rf7air0x18` FOREIGN KEY (`id_pais`) REFERENCES `tb_pais` (`id`),
  CONSTRAINT `FKfvffnhr4qwv9fm1opa9221ax1` FOREIGN KEY (`id_distrito`) REFERENCES `tb_distrito` (`id`),
  CONSTRAINT `FKg3m1objtxeq7cn9j7995n8o85` FOREIGN KEY (`id_departamento`) REFERENCES `tb_departamento` (`id`),
  CONSTRAINT `FKmdlwketg5mgmw6u0fs08ij4t9` FOREIGN KEY (`id_provincia`) REFERENCES `tb_provincia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_trabajador`
--

DROP TABLE IF EXISTS `tb_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_trabajador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `horas_laborales` int NOT NULL,
  `id_tienda` int NOT NULL,
  `id_usuario` int NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `salario` double DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoqjxw0fbsmthxxygnicyp5gly` (`id_tienda`),
  KEY `FKp0f0pntqwnh3t29vgeuab026a` (`id_usuario`),
  CONSTRAINT `FKoqjxw0fbsmthxxygnicyp5gly` FOREIGN KEY (`id_tienda`) REFERENCES `tb_tienda` (`id`),
  CONSTRAINT `FKp0f0pntqwnh3t29vgeuab026a` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_transaccion`
--

DROP TABLE IF EXISTS `tb_transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_transaccion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descuento` decimal(10,2) NOT NULL,
  `id_boleta` int NOT NULL,
  `id_producto` int NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `unidades` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjo90lurhuwgj7it99uhdugmjl` (`id_boleta`),
  KEY `FK1oopfjsgji3xaxqax5epny5kb` (`id_producto`),
  CONSTRAINT `FK1oopfjsgji3xaxqax5epny5kb` FOREIGN KEY (`id_producto`) REFERENCES `tb_producto` (`id`),
  CONSTRAINT `FKjo90lurhuwgj7it99uhdugmjl` FOREIGN KEY (`id_boleta`) REFERENCES `tb_boleta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=759 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(30) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `nmr_documento` varchar(12) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `tipo_documento` varchar(30) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_departamento` int NOT NULL,
  `id_distrito` int NOT NULL,
  `id_pais` int NOT NULL,
  `id_provincia` int NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6u6rubfuhxxndylvwkk5xoc0n` (`id_departamento`),
  KEY `FK3dlaq1cw7e0gw55wq2gyj129u` (`id_distrito`),
  KEY `FK41uyb1j7delwed1wha31whek3` (`id_pais`),
  KEY `FKeyyr8s6o46dsgohiwge4u571s` (`id_provincia`),
  KEY `FK6mxbmhthgow2y4dv0l13yngc5` (`id_rol`),
  CONSTRAINT `FK3dlaq1cw7e0gw55wq2gyj129u` FOREIGN KEY (`id_distrito`) REFERENCES `tb_distrito` (`id`),
  CONSTRAINT `FK41uyb1j7delwed1wha31whek3` FOREIGN KEY (`id_pais`) REFERENCES `tb_pais` (`id`),
  CONSTRAINT `FK6mxbmhthgow2y4dv0l13yngc5` FOREIGN KEY (`id_rol`) REFERENCES `tb_rol` (`id`),
  CONSTRAINT `FK6u6rubfuhxxndylvwkk5xoc0n` FOREIGN KEY (`id_departamento`) REFERENCES `tb_departamento` (`id`),
  CONSTRAINT `FKeyyr8s6o46dsgohiwge4u571s` FOREIGN KEY (`id_provincia`) REFERENCES `tb_provincia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-30 14:55:44

USE `runner_cibertec_db`;

SET CHARACTER SET 'utf8mb4';

SET NAMES 'utf8mb4';

SET FOREIGN_KEY_CHECKS = 0;


-- ### REINICIO DE CONTADORES AUTO_INCREMENT ###

ALTER TABLE `tb_boleta` AUTO_INCREMENT = 1;
ALTER TABLE `tb_branch` AUTO_INCREMENT = 1;
ALTER TABLE `tb_calzado` AUTO_INCREMENT = 1;
ALTER TABLE `tb_categoria` AUTO_INCREMENT = 1;
ALTER TABLE `tb_color` AUTO_INCREMENT = 1;
ALTER TABLE `tb_departamento` AUTO_INCREMENT = 1;
ALTER TABLE `tb_distrito` AUTO_INCREMENT = 1;
ALTER TABLE `tb_marca` AUTO_INCREMENT = 1;
ALTER TABLE `tb_material` AUTO_INCREMENT = 1;
ALTER TABLE `tb_pais` AUTO_INCREMENT = 1;
ALTER TABLE `tb_producto` AUTO_INCREMENT = 1;
ALTER TABLE `tb_provincia` AUTO_INCREMENT = 1;
ALTER TABLE `tb_rol` AUTO_INCREMENT = 1;
ALTER TABLE `tb_subcategoria` AUTO_INCREMENT = 1;
ALTER TABLE `tb_talla` AUTO_INCREMENT = 1;
ALTER TABLE `tb_tienda` AUTO_INCREMENT = 1;
ALTER TABLE `tb_trabajador` AUTO_INCREMENT = 1;
ALTER TABLE `tb_transaccion` AUTO_INCREMENT = 1;
ALTER TABLE `tb_usuario` AUTO_INCREMENT = 1;

-- ### DATOS ORIGINALES Y ROLES ###

INSERT INTO `tb_pais` (id, abreviatura, created_at, img, is_delete, is_enabled, nombre, simbolo) VALUES (1, 'PE', '2025-10-30 15:24:40', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Flag_of_Peru_%281825%E2%80%931884%29.svg/1280px-Flag_of_Peru_%281825%E2%8O%931884%29.svg.png', 0, 1, 'Perú', 'Sol');
INSERT INTO `tb_rol` (id, created_at, is_delete, is_enabled, nombre) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'ADMIN');
INSERT INTO `tb_rol` (id, created_at, is_delete, is_enabled, nombre) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'USER');

-- ### DATOS GEOGRÁFICOS ###

INSERT INTO `tb_departamento` (id, created_at, is_delete, is_enabled, nombre, id_pais) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Lima', 1);
INSERT INTO `tb_provincia` (id, created_at, is_delete, is_enabled, nombre, id_departamento) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Lima', 1);
INSERT INTO `tb_distrito` (id, created_at, is_delete, is_enabled, nombre, id_provincia) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Miraflores', 1);
INSERT INTO `tb_departamento` (id, created_at, is_delete, is_enabled, nombre, id_pais) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Arequipa', 1);
INSERT INTO `tb_provincia` (id, created_at, is_delete, is_enabled, nombre, id_departamento) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Arequipa', 2);
INSERT INTO `tb_distrito` (id, created_at, is_delete, is_enabled, nombre, id_provincia) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Cayma', 2);
INSERT INTO `tb_distrito` (id, created_at, is_delete, is_enabled, nombre, id_provincia) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Yanahuara', 2);
INSERT INTO `tb_departamento` (id, created_at, is_delete, is_enabled, nombre, id_pais) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Cusco', 1);
INSERT INTO `tb_provincia` (id, created_at, is_delete, is_enabled, nombre, id_departamento) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Cusco', 3);
INSERT INTO `tb_distrito` (id, created_at, is_delete, is_enabled, nombre, id_provincia) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Wanchaq', 3);
INSERT INTO `tb_distrito` (id, created_at, is_delete, is_enabled, nombre, id_provincia) VALUES (5, '2025-10-30 15:24:40', 0, 1, 'Cusco Cercado', 3);
INSERT INTO `tb_departamento` (id, created_at, is_delete, is_enabled, nombre, id_pais) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'La Libertad', 1);
INSERT INTO `tb_provincia` (id, created_at, is_delete, is_enabled, nombre, id_departamento) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Trujillo', 4);
INSERT INTO `tb_distrito` (id, created_at, is_delete, is_enabled, nombre, id_provincia) VALUES (6, '2025-10-30 15:24:40', 0, 1, 'Trujillo', 4);
INSERT INTO `tb_distrito` (id, created_at, is_delete, is_enabled, nombre, id_provincia) VALUES (7, '2025-10-30 15:24:40', 0, 1, 'Víctor Larco Herrera', 4);

-- ### CATÁLOGOS DE PRODUCTOS ###

INSERT INTO `tb_categoria` (id, created_at, is_delete, is_enabled, nombre) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Hombre');
INSERT INTO `tb_categoria` (id, created_at, is_delete, is_enabled, nombre) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Mujer');
INSERT INTO `tb_categoria` (id, created_at, is_delete, is_enabled, nombre) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Niños');
INSERT INTO `tb_categoria` (id, created_at, is_delete, is_enabled, nombre) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Niñas');
INSERT INTO `tb_categoria` (id, created_at, is_delete, is_enabled, nombre) VALUES (5, '2025-10-30 15:24:40', 0, 1, 'Deportes');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Nike');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Adidas');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Puma');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Reebok');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (5, '2025-10-30 15:24:40', 0, 1, 'Converse');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (6, '2025-10-30 15:24:40', 0, 1, 'Vans');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (7, '2025-10-30 15:24:40', 0, 1, 'New Balance');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (8, '2025-10-30 15:24:40', 0, 1, 'Asics');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (9, '2025-10-30 15:24:40', 0, 1, 'Skechers');
INSERT INTO `tb_marca` (id, created_at, is_delete, is_enabled, nombre) VALUES (10, '2025-10-30 15:24:40', 0, 1, 'Fila');
INSERT INTO `tb_material` (id, created_at, is_delete, is_enabled, nombre) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Cuero');
INSERT INTO `tb_material` (id, created_at, is_delete, is_enabled, nombre) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Sintético');
INSERT INTO `tb_material` (id, created_at, is_delete, is_enabled, nombre) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Malla');
INSERT INTO `tb_material` (id, created_at, is_delete, is_enabled, nombre) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Algodón');
INSERT INTO `tb_material` (id, created_at, is_delete, is_enabled, nombre) VALUES (5, '2025-10-30 15:24:40', 0, 1, 'Gamuza');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Rojo');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Azul');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Negro');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Blanco');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (5, '2025-10-30 15:24:40', 0, 1, 'Verde');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (6, '2025-10-30 15:24:40', 0, 1, 'Gris');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (7, '2025-10-30 15:24:40', 0, 1, 'Amarillo');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (8, '2025-10-30 15:24:40', 0, 1, 'Naranja');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (9, '2025-10-30 15:24:40', 0, 1, 'Morado');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (10, '2025-10-30 15:24:40', 0, 1, 'Marrón');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (11, '2025-10-30 15:24:40', 0, 1, 'Celeste');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (12, '2025-10-30 15:24:40', 0, 1, 'Rosado');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (13, '2025-10-30 15:24:40', 0, 1, 'Beige');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (14, '2025-10-30 15:24:40', 0, 1, 'Dorado');
INSERT INTO `tb_color` (id, created_at, is_delete, is_enabled, nombre) VALUES (15, '2025-10-30 15:24:40', 0, 1, 'Plateado');
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (1, '2025-10-30 15:24:40', 0, 1, 36);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (2, '2025-10-30 15:24:40', 0, 1, 37);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (3, '2025-10-30 15:24:40', 0, 1, 38);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (4, '2025-10-30 15:24:40', 0, 1, 39);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (5, '2025-10-30 15:24:40', 0, 1, 40);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (6, '2025-10-30 15:24:40', 0, 1, 41);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (7, '2025-10-30 15:24:40', 0, 1, 42);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (8, '2025-10-30 15:24:40', 0, 1, 43);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (9, '2025-10-30 15:24:40', 0, 1, 44);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (10, '2025-10-30 15:24:40', 0, 1, 45);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (11, '2025-10-30 15:24:40', 0, 1, 46);
INSERT INTO `tb_talla` (id, created_at, is_delete, is_enabled, numero) VALUES (12, '2025-10-30 15:24:40', 0, 1, 47);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Subcat Max 1 (Cat 1)', 1);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Subcat Ignite 2 (Cat 1)', 1);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Subcat Max 3 (Cat 1)', 1);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Subcat Run 1 (Cat 2)', 2);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (5, '2025-10-30 15:24:40', 0, 1, 'Subcat Fly 2 (Cat 2)', 2);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (6, '2025-10-30 15:24:40', 0, 1, 'Subcat Zoom 3 (Cat 2)', 2);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (7, '2025-10-30 15:24:40', 0, 1, 'Subcat Ultra 1 (Cat 3)', 3);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (8, '2025-10-30 15:24:40', 0, 1, 'Subcat Beta 2 (Cat 3)', 3);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (9, '2025-10-30 15:24:40', 0, 1, 'Subcat Omega 3 (Cat 3)', 3);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (10, '2025-10-30 15:24:40', 0, 1, 'Subcat Alpha 1 (Cat 4)', 4);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (11, '2025-10-30 15:24:40', 0, 1, 'Subcat Alpha 2 (Cat 4)', 4);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (12, '2025-10-30 15:24:40', 0, 1, 'Subcat Boost 3 (Cat 4)', 4);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (13, '2025-10-30 15:24:40', 0, 1, 'Subcat Run 1 (Cat 5)', 5);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (14, '2025-10-30 15:24:40', 0, 1, 'Subcat Beta 2 (Cat 5)', 5);
INSERT INTO `tb_subcategoria` (id, created_at, is_delete, is_enabled, nombre, id_categoria) VALUES (15, '2025-10-30 15:24:40', 0, 1, 'Subcat Air 3 (Cat 5)', 5);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (1, '2025-10-30 15:24:40', 0, 1, 'Branch Omega 1 (Sub 1)', 1);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (2, '2025-10-30 15:24:40', 0, 1, 'Branch Fly 2 (Sub 1)', 1);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (3, '2025-10-30 15:24:40', 0, 1, 'Branch Boost 1 (Sub 2)', 2);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (4, '2025-10-30 15:24:40', 0, 1, 'Branch Air 2 (Sub 2)', 2);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (5, '2025-10-30 15:24:40', 0, 1, 'Branch Omega 1 (Sub 3)', 3);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (6, '2025-10-30 15:24:40', 0, 1, 'Branch Zoom 2 (Sub 3)', 3);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (7, '2025-10-30 15:24:40', 0, 1, 'Branch Ultra 1 (Sub 4)', 4);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (8, '2025-10-30 15:24:40', 0, 1, 'Branch Fly 2 (Sub 4)', 4);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (9, '2025-10-30 15:24:40', 0, 1, 'Branch Run 1 (Sub 5)', 5);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (10, '2025-10-30 15:24:40', 0, 1, 'Branch Run 2 (Sub 5)', 5);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (11, '2025-10-30 15:24:40', 0, 1, 'Branch Zoom 1 (Sub 6)', 6);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (12, '2025-10-30 15:24:40', 0, 1, 'Branch Omega 2 (Sub 6)', 6);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (13, '2025-10-30 15:24:40', 0, 1, 'Branch Alpha 1 (Sub 7)', 7);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (14, '2025-10-30 15:24:40', 0, 1, 'Branch Ignite 2 (Sub 7)', 7);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (15, '2025-10-30 15:24:40', 0, 1, 'Branch Run 1 (Sub 8)', 8);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (16, '2025-10-30 15:24:40', 0, 1, 'Branch Boost 2 (Sub 8)', 8);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (17, '2025-10-30 15:24:40', 0, 1, 'Branch Boost 1 (Sub 9)', 9);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (18, '2025-10-30 15:24:40', 0, 1, 'Branch Ignite 2 (Sub 9)', 9);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (19, '2025-10-30 15:24:40', 0, 1, 'Branch Ultra 1 (Sub 10)', 10);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (20, '2025-10-30 15:24:40', 0, 1, 'Branch Air 2 (Sub 10)', 10);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (21, '2025-10-30 15:24:40', 0, 1, 'Branch Gamma 1 (Sub 11)', 11);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (22, '2025-10-30 15:24:40', 0, 1, 'Branch Alpha 2 (Sub 11)', 11);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (23, '2025-10-30 15:24:40', 0, 1, 'Branch Max 1 (Sub 12)', 12);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (24, '2025-10-30 15:24:40', 0, 1, 'Branch Omega 2 (Sub 12)', 12);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (25, '2025-10-30 15:24:40', 0, 1, 'Branch Pro 1 (Sub 13)', 13);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (26, '2025-10-30 15:24:40', 0, 1, 'Branch Delta 2 (Sub 13)', 13);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (27, '2025-10-30 15:24:40', 0, 1, 'Branch Omega 1 (Sub 14)', 14);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (28, '2025-10-30 15:24:40', 0, 1, 'Branch Ultra 2 (Sub 14)', 14);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (29, '2025-10-30 15:24:40', 0, 1, 'Branch Air 1 (Sub 15)', 15);
INSERT INTO `tb_branch` (id, created_at, is_delete, is_enabled, nombre, id_subcategoria) VALUES (30, '2025-10-30 15:24:40', 0, 1, 'Branch Pro 2 (Sub 15)', 15);

-- ### TIENDAS ###

INSERT INTO `tb_tienda` (id, created_at, direccion, id_departamento, id_distrito, id_pais, id_provincia, is_deleted, is_enabled, mail, nombre, nombre_legal, ruc, ruta_imagen, telefono, ubicacion) VALUES (1, '2025-10-30 15:24:40', 'Jr. Larco 1313, Cusco Cercado', 3, 5, 1, 3, 0, 1, 'tienda1@runner.com', 'Runner Store Cusco Cercado', 'Runner Store Cusco Cercado S.A.C.', '20906552317', '/images/tiendas/tienda_1.jpg', '912937605', 'Piso 4, Tienda 169');
INSERT INTO `tb_tienda` (id, created_at, direccion, id_departamento, id_distrito, id_pais, id_provincia, is_deleted, is_enabled, mail, nombre, nombre_legal, ruc, ruta_imagen, telefono, ubicacion) VALUES (2, '2025-10-30 15:24:40', 'Calle Salaverry 1471, Cayma', 2, 2, 1, 2, 0, 1, 'tienda2@runner.com', 'Runner Store Cayma', 'Runner Store Cayma S.A.C.', '20714663419', '/images/tiendas/tienda_2.jpg', '946455508', 'Piso 5, Tienda 138');
INSERT INTO `tb_tienda` (id, created_at, direccion, id_departamento, id_distrito, id_pais, id_provincia, is_deleted, is_enabled, mail, nombre, nombre_legal, ruc, ruta_imagen, telefono, ubicacion) VALUES (3, '2025-10-30 15:24:40', 'Paseo El Sol 323, Yanahuara', 2, 3, 1, 2, 0, 1, 'tienda3@runner.com', 'Runner Store Yanahuara', 'Runner Store Yanahuara S.A.C.', '20611801699', '/images/tiendas/tienda_3.jpg', '930191939', 'Piso 2, Tienda 156');

-- ### USUARIOS ###

INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (1, 'Vargas', '$2a$10$RQjfVUaScbx/8fMHX7EhuuHa1HR8lxhfvbgphBrwbr5WOe56HdhW.', '2025-10-30 15:24:40', 'andymanquues@gmail.com', 0, 1, '22222222', 'Andy', '936545897', 'dni', 1, 1, 1, 1, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (2, 'Hernández', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'davhernández2@runner-mail.com', 0, 1, '23037704', 'David', '948674414', 'dni', 4, 7, 1, 4, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (3, 'Pérez', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'luipérez3@runner-mail.com', 0, 1, '36177219', 'Luis', '961617497', 'dni', 4, 6, 1, 4, 2);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (4, 'González', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'diegonzález4@runner-mail.com', 0, 1, '72324305', 'Diego', '972795203', 'dni', 1, 1, 1, 1, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (5, 'González', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'cargonzález5@runner-mail.com', 0, 1, '68542353', 'Carmen', '926135707', 'dni', 3, 5, 1, 3, 2);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (6, 'Martínez', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'elemartínez6@runner-mail.com', 0, 1, '24392519', 'Elena', '922360097', 'dni', 4, 6, 1, 4, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (7, 'Vásquez', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'lauvásquez7@runner-mail.com', 0, 1, '30334756', 'Laura', '993960457', 'dni', 2, 2, 1, 2, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (8, 'Vásquez', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'pauvásquez8@runner-mail.com', 0, 1, '90535445', 'Paula', '969376289', 'dni', 3, 4, 1, 3, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (9, 'Mendoza', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'migmendoza9@runner-mail.com', 0, 1, '94764155', 'Miguel', '972953304', 'dni', 4, 6, 1, 4, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (10, 'López', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'luclópez10@runner-mail.com', 0, 1, '54608894', 'Lucía', '939396115', 'dni', 3, 5, 1, 3, 1);
INSERT INTO `tb_usuario` (id, apellido, clave, created_at, email, is_delete, is_enabled, nmr_documento, nombre, telefono, tipo_documento, id_departamento, id_distrito, id_pais, id_provincia, id_rol) VALUES (11, 'Sánchez', '$2a$12$X3VD6rcdx2TCVhYU4JasB.v9yj2dCZQCWKFpKzjaQBrXy3FW.f7Ky', '2025-10-30 15:24:40', 'pausánchez11@runner-mail.com', 0, 1, '51947977', 'Paula', '987239181', 'dni', 1, 1, 1, 1, 2);

-- ### TRABAJADORES ###

INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (1, '2025-10-30 15:24:40', 48, 1, 1, 0, 1, 6563.29);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (2, '2025-10-30 15:24:40', 48, 2, 3, 0, 1, 3009.55);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (3, '2025-10-30 15:24:40', 48, 1, 6, 0, 1, 1712.03);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (4, '2025-10-30 15:24:40', 48, 3, 11, 0, 1, 1916.55);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (5, '2025-10-30 15:24:40', 48, 2, 9, 0, 1, 2264.44);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (6, '2025-10-30 15:24:40', 48, 1, 4, 0, 1, 1747.08);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (7, '2025-10-30 15:24:40', 48, 3, 5, 0, 1, 2008.46);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (8, '2025-10-30 15:24:40', 48, 3, 10, 0, 1, 2305.06);
INSERT INTO `tb_trabajador` (id, created_at, horas_laborales, id_tienda, id_usuario, is_delete, is_enabled, salario) VALUES (9, '2025-10-30 15:24:40', 48, 3, 2, 0, 1, 1736.85);

-- ### PRODUCTOS ###

INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (1, '2025-10-30 15:24:40', 'Perfecto para el día a día.', 0, 1, 'Reebok Flex', 201.15, 14, 3, 4, 1, 7);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (2, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'Nike Delta', 255.66, 17, 3, 1, 2, 9);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (3, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'New Balance Pro', 302.94, 25, 5, 7, 2, 13);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (4, '2025-10-30 15:24:40', 'Tecnología de punta para deporte.', 0, 1, 'Fila Delta', 113.88, 11, 2, 10, 4, 6);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (5, '2025-10-30 15:24:40', 'Tecnología de punta para deporte.', 0, 1, 'New Balance Omega', 139.13, 30, 5, 7, 3, 15);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (6, '2025-10-30 15:24:40', 'Tecnología de punta para deporte.', 0, 1, 'Asics Boost', 308.57, 4, 1, 8, 5, 2);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (7, '2025-10-30 15:24:40', 'Ideal para corredores urbanos.', 0, 1, 'Nike Alpha', 691.32, 10, 2, 1, 4, 5);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (8, '2025-10-30 15:24:40', 'Un clásico reinventado.', 0, 1, 'Adidas Ignite', 193.68, 26, 5, 2, 3, 13);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (9, '2025-10-30 15:24:40', 'Tecnología de punta para deporte.', 0, 1, 'Puma Beta', 233.08, 16, 3, 3, 2, 8);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (10, '2025-10-30 15:24:40', 'Ideal para corredores urbanos.', 0, 1, 'Nike Boost', 143.15, 30, 5, 1, 5, 15);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (11, '2025-10-30 15:24:40', 'Ideal para corredores urbanos.', 0, 1, 'Skechers Beta', 345.06, 21, 4, 9, 3, 11);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (12, '2025-10-30 15:24:40', 'Tecnología de punta para deporte.', 0, 1, 'Nike Zoom', 111.07, 15, 3, 1, 4, 8);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (13, '2025-10-30 15:24:40', 'Un clásico reinventado.', 0, 1, 'Skechers Gamma', 770.61, 28, 5, 9, 4, 14);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (14, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'Nike Delta', 582.63, 14, 3, 1, 4, 7);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (15, '2025-10-30 15:24:40', 'Ideal para corredores urbanos.', 0, 1, 'Skechers Ultra', 230.4, 8, 2, 9, 5, 4);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (16, '2025-10-30 15:24:40', 'Ideal para corredores urbanos.', 0, 1, 'Asics Flex', 147.15, 19, 4, 8, 2, 10);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (17, '2025-10-30 15:24:40', 'Perfecto para el día a día.', 0, 1, 'Asics Pro', 714.92, 10, 2, 8, 1, 5);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (18, '2025-10-30 15:24:40', 'Perfecto para el día a día.', 0, 1, 'Puma Omega', 141.21, 9, 2, 3, 5, 5);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (19, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'Vans Zoom', 326.78, 23, 4, 6, 1, 12);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (20, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'Vans Air', 396.38, 3, 1, 6, 4, 2);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (21, '2025-10-30 15:24:40', 'Perfecto para el día a día.', 0, 1, 'New Balance Delta', 497.75, 27, 5, 7, 3, 14);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (22, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'Skechers Pro', 724.31, 13, 3, 9, 4, 7);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (23, '2025-10-30 15:24:40', 'Tecnología de punta para deporte.', 0, 1, 'New Balance Air', 689.57, 6, 1, 7, 4, 3);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (24, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'New Balance Air', 655.14, 25, 5, 7, 3, 13);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (25, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'Puma Ignite', 732.07, 8, 2, 3, 2, 4);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (26, '2025-10-30 15:24:40', 'Tecnología de punta para deporte.', 0, 1, 'Adidas Max', 195.21, 4, 1, 2, 5, 2);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (27, '2025-10-30 15:24:40', 'Máxima comodidad y estilo.', 0, 1, 'Nike Boost', 269.34, 4, 1, 1, 5, 2);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (28, '2025-10-30 15:24:40', 'Perfecto para el día a día.', 0, 1, 'New Balance Run', 210.31, 13, 3, 7, 4, 7);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (29, '2025-10-30 15:24:40', 'Un clásico reinventado.', 0, 1, 'Adidas Omega', 160.39, 7, 2, 2, 3, 4);
INSERT INTO `tb_producto` (id, created_at, descripcion, is_delete, is_enabled, nombre, precio, id_branch, id_categoria, id_marca, id_material, id_subcategoria) VALUES (30, '2025-10-30 15:24:40', 'Perfecto para el día a día.', 0, 1, 'New Balance Ultra', 616.5, 9, 2, 7, 3, 5);

-- ### INVENTARIO (CALZADO) ###

INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (1, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 20, 12, 19, 12);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (2, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 23, 13, 10, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (3, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 39, 14, 29, 4);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (4, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 9, 2, 15, 4);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (5, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 49, 11, 16, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (6, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 27, 6, 2, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (7, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 13, 11, 9, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (8, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 23, 6, 25, 6);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (9, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 40, 10, 29, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (10, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 49, 9, 10, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (11, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 17, 3, 15, 10);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (12, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 18, 9, 10, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (13, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 18, 4, 15, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (14, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 32, 11, 14, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (15, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 11, 1, 14, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (16, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 28, 14, 10, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (17, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 24, 6, 27, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (18, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 24, 15, 9, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (19, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 15, 9, 24, 9);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (20, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 20, 6, 9, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (21, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 27, 8, 7, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (22, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 9, 6, 9, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (23, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 15, 1, 17, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (24, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 30, 14, 30, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (25, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 6, 12, 22, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (26, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 43, 1, 12, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (27, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 18, 8, 11, 6);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (28, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 29, 14, 11, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (29, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 31, 13, 22, 9);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (30, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 25, 15, 17, 9);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (31, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 48, 8, 23, 12);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (32, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 16, 4, 13, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (33, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 5, 7, 24, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (34, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 12, 1, 15, 12);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (35, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 49, 2, 17, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (36, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 42, 14, 10, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (37, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 40, 13, 22, 4);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (38, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 44, 4, 24, 10);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (39, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 19, 9, 9, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (40, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 16, 3, 22, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (41, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 10, 13, 26, 9);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (42, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 49, 15, 4, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (43, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 28, 9, 20, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (44, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 50, 15, 3, 12);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (45, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 20, 12, 11, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (46, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 31, 5, 9, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (47, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 20, 10, 10, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (48, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 13, 3, 12, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (49, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 6, 8, 14, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (50, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 43, 12, 8, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (51, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 31, 6, 20, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (52, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 39, 13, 19, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (53, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 11, 10, 9, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (54, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 14, 10, 27, 9);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (55, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 30, 13, 17, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (56, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 5, 14, 23, 12);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (57, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 47, 14, 19, 4);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (58, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 32, 7, 3, 6);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (59, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 43, 1, 26, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (60, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 32, 10, 17, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (61, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 34, 13, 16, 4);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (62, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 32, 12, 4, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (63, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 32, 7, 3, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (64, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 34, 9, 15, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (65, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 32, 6, 1, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (66, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 12, 15, 1, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (67, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 42, 9, 7, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (68, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 45, 4, 26, 10);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (69, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 38, 13, 16, 4);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (70, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 7, 3, 19, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (71, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 28, 12, 18, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (72, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 24, 12, 21, 12);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (73, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 21, 13, 19, 10);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (74, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 47, 11, 29, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (75, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 5, 5, 4, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (76, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 29, 8, 2, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (77, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 43, 2, 16, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (78, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 48, 6, 9, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (79, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 18, 9, 5, 7);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (80, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 13, 7, 13, 9);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (81, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 30, 13, 20, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (82, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 34, 11, 7, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (83, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 50, 9, 25, 2);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (84, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 26, 12, 8, 3);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (85, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 10, 14, 12, 6);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (86, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 6, 3, 9, 10);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (87, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 38, 2, 9, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (88, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 20, 4, 19, 10);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (89, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 46, 7, 28, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (90, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 16, 2, 23, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (91, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 45, 14, 28, 12);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (92, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 47, 12, 20, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (93, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 15, 8, 22, 11);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (94, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 34, 11, 19, 5);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (95, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 28, 5, 28, 6);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (96, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 12, 5, 17, 8);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (97, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 19, 8, 10, 4);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (98, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 27, 13, 24, 6);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (99, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 43, 14, 13, 1);
INSERT INTO `tb_calzado` (id, created_at, estado, is_delete, is_enabled, stock, id_color, id_producto, id_talla) VALUES (100, '2025-10-30 15:24:40', 'Nuevo', 0, 1, 19, 14, 30, 9);

-- ### VENTAS (BOLETAS Y TRANSACCIONES) ###

INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (1, '2025-10-30 15:24:40', 'Calle Arequipa 862, Cayma', 'Pagado', 1, 8, 3, 340.68, 'Cliente 3', '68964116', 'Ideal para corredores urbanos.', 1892.68, 'Mastercard', 2233.36, 51.19);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (1, 1.76, 1, 1, 201.15, 199.39, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (2, 6.75, 1, 17, 714.92, 1423.09, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (3, 42.68, 1, 19, 326.78, 610.88, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (2, '2025-10-30 15:24:40', 'Calle Primavera 1955, Miraflores', 'Pagado', 3, 8, 9, 142.12, 'Cliente 9', '35558003', 'Perfecto para el día a día.', 789.58, 'Mastercard', 931.70, 130.08);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (4, 19.82, 2, 9, 233.08, 213.26, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (5, 7.92, 2, 5, 139.13, 131.21, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (6, 102.34, 2, 23, 689.57, 587.23, 1);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (3, '2025-10-30 15:24:40', 'Paseo Javier Prado 1441, Cayma', 'Enviado', 3, 2, 5, 305.45, 'Cliente 5', '84761482', 'Perfecto para el día a día.', 1696.94, 'Diners', 2002.39, 75.92);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (7, 23.23, 3, 13, 770.61, 1517.99, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (8, 21.13, 3, 28, 210.31, 189.18, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (9, 31.56, 3, 19, 326.78, 295.22, 1);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (4, '2025-10-30 15:24:40', 'Av. Primavera 1246, Yanahuara', 'Enviado', 2, 9, 4, 34.27, 'Cliente 4', '29860860', 'Ideal para corredores urbanos.', 190.37, 'Diners', 224.64, 28.37);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (10, 18.47, 4, 5, 139.13, 120.66, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (11, 9.90, 4, 4, 113.88, 103.98, 1);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (5, '2025-10-30 15:24:40', 'Jr. Pardo 1035, Wanchaq', 'Enviado', 3, 6, 4, 204.48, 'Cliente 4', '64790345', 'Ideal para corredores urbanos.', 1135.98, 'Diners', 1340.46, 45.17);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (12, 16.92, 5, 19, 326.78, 636.64, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (13, 28.25, 5, 25, 732.07, 703.82, 1);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (6, '2025-10-30 15:24:40', 'Calle De la Cultura 1302, Yanahuara', 'Entregado', 3, 8, 8, 174.48, 'Cliente 8', '58868260', 'Perfecto para el día a día.', 969.34, 'AMEX', 1143.82, 63.60);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (14, 9.87, 6, 19, 326.78, 643.69, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (15, 33.42, 6, 9, 233.08, 199.66, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (16, 20.31, 6, 29, 160.39, 300.47, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (7, '2025-10-30 15:24:40', 'Paseo Salaverry 1674, Yanahuara', 'Pagado', 1, 2, 7, 41.42, 'Cliente 7', '84854060', 'Tecnología de punta para deporte.', 230.13, 'Visa', 271.55, 14.75);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (17, 14.75, 7, 10, 143.15, 271.55, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (8, '2025-10-30 15:24:40', 'Av. El Sol 848, Miraflores', 'Enviado', 2, 4, 4, 417.89, 'Cliente 4', '40514967', 'Máxima comodidad y estilo.', 2321.58, 'Visa', 2739.47, 73.01);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (18, 25.63, 8, 7, 691.32, 1357.01, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (19, 47.38, 8, 17, 714.92, 1382.46, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (9, '2025-10-30 15:24:40', 'Jr. De la Cultura 1965, Cusco Cercado', 'Entregado', 3, 4, 10, 338.25, 'Cliente 10', '67985305', 'Tecnología de punta para deporte.', 1879.19, 'Visa', 2217.44, 74.42);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (20, 16.84, 9, 2, 255.66, 238.82, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (21, 28.57, 9, 7, 691.32, 1354.07, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (22, 29.01, 9, 19, 326.78, 624.55, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (10, '2025-10-30 15:24:40', 'Jr. Ejército 440, Wanchaq', 'Pagado', 3, 7, 2, 359.99, 'Cliente 2', '82349442', 'Tecnología de punta para deporte.', 1999.92, 'AMEX', 2359.91, 67.95);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (23, 1.44, 10, 9, 233.08, 231.64, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (24, 10.68, 10, 19, 326.78, 642.88, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (25, 55.83, 10, 13, 770.61, 1485.39, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (11, '2025-10-30 15:24:40', 'Calle Salaverry 1326, Wanchaq', 'Pagado', 1, 9, 3, 245.27, 'Cliente 3', '48370090', 'Un clásico reinventado.', 1362.63, 'Diners', 1607.90, 80.84);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (26, 16.09, 11, 11, 345.06, 328.97, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (27, 48.98, 11, 19, 326.78, 604.58, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (28, 15.77, 11, 11, 345.06, 674.35, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (12, '2025-10-30 15:24:40', 'Paseo Pardo 1549, Cayma', 'Entregado', 3, 9, 3, 91.24, 'Cliente 3', '53783515', 'Máxima comodidad y estilo.', 506.90, 'Diners', 598.14, 57.00);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (29, 57.00, 12, 24, 655.14, 598.14, 1);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (13, '2025-10-30 15:24:40', 'Calle Larco 1632, Miraflores', 'Enviado', 1, 1, 10, 14.97, 'Cliente 10', '94214985', 'Un clásico reinventado.', 83.16, 'Mastercard', 98.13, 12.94);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (30, 12.94, 13, 12, 111.07, 98.13, 1);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (14, '2025-10-30 15:24:40', 'Calle Benavides 318, Miraflores', 'Enviado', 1, 9, 7, 86.89, 'Cliente 7', '64041938', 'Un clásico reinventado.', 482.73, 'Mastercard', 569.62, 71.28);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (31, 71.08, 14, 21, 497.75, 426.67, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (32, 0.20, 14, 10, 143.15, 142.95, 1);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (15, '2025-10-30 15:24:40', 'Paseo De la Cultura 1880, Cayma', 'Enviado', 3, 1, 3, 42.44, 'Cliente 3', '61494989', 'Máxima comodidad y estilo.', 235.77, 'AMEX', 278.21, 8.09);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (33, 8.09, 15, 10, 143.15, 278.21, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (16, '2025-10-30 15:24:40', 'Jr. Arequipa 1645, Miraflores', 'Entregado', 3, 4, 6, 288.31, 'Cliente 6', '29084106', 'Un clásico reinventado.', 1601.75, 'Mastercard', 1890.06, 43.94);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (34, 9.43, 16, 24, 655.14, 1300.85, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (35, 25.21, 16, 3, 302.94, 277.73, 1);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (36, 9.30, 16, 29, 160.39, 311.48, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (17, '2025-10-30 15:24:40', 'Jr. Benavides 1168, Cusco Cercado', 'Enviado', 3, 9, 7, 150.01, 'Cliente 7', '98937468', 'Tecnología de punta para deporte.', 833.41, 'Diners', 983.42, 12.08);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (37, 12.08, 17, 21, 497.75, 983.42, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (18, '2025-10-30 15:24:40', 'Paseo Ejército 1220, Miraflores', 'Pagado', 2, 7, 10, 127.10, 'Cliente 10', '60639707', 'Tecnología de punta para deporte.', 706.11, 'Mastercard', 833.21, 42.49);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (38, 7.89, 18, 12, 111.07, 214.25, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (39, 34.60, 18, 19, 326.78, 618.96, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (19, '2025-10-30 15:24:40', 'Av. Ejército 1743, Wanchaq', 'Entregado', 2, 8, 8, 211.74, 'Cliente 8', '50458899', 'Tecnología de punta para deporte.', 1176.34, 'Mastercard', 1388.08, 60.54);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (40, 60.54, 19, 22, 724.31, 1388.08, 2);
INSERT INTO `tb_boleta` (id, created_at, direccion, estado, id_tienda, id_trabajador, id_usuario, igv, nombre_cliente, numero_documento, observaciones, subtotal, tipo_tarjeta, total, total_descuento) VALUES (20, '2025-10-30 15:24:40', 'Calle Grau 872, Wanchaq', 'Pagado', 3, 1, 10, 94.35, 'Cliente 10', '40941641', 'Máxima comodidad y estilo.', 524.19, 'Mastercard', 618.54, 27.54);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (41, 14.43, 20, 26, 195.21, 375.99, 2);
INSERT INTO `tb_transaccion` (id, descuento, id_boleta, id_producto, precio_unitario, total, unidades) VALUES (42, 13.11, 20, 2, 255.66, 242.55, 1);

SET FOREIGN_KEY_CHECKS = 1;
