CREATE DATABASE  IF NOT EXISTS `db_dentalcare` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_dentalcare`;
-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: db_dentalcare
-- ------------------------------------------------------
-- Server version	9.6.0

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '2a366d86-1a94-11f1-886d-0efed0190cf0:1-364';

--
-- Table structure for table `tbl_cat_citas_estatus`
--

DROP TABLE IF EXISTS `tbl_cat_citas_estatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cat_citas_estatus` (
  `EstatusId` int NOT NULL AUTO_INCREMENT,
  `Estatus_Nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EstatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cat_citas_estatus`
--

LOCK TABLES `tbl_cat_citas_estatus` WRITE;
/*!40000 ALTER TABLE `tbl_cat_citas_estatus` DISABLE KEYS */;
INSERT INTO `tbl_cat_citas_estatus` VALUES (1,'Pendiente'),(2,'Confirmada'),(3,'Completada'),(4,'Cancelada');
/*!40000 ALTER TABLE `tbl_cat_citas_estatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cat_roles`
--

DROP TABLE IF EXISTS `tbl_cat_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cat_roles` (
  `RolId` int NOT NULL AUTO_INCREMENT,
  `Rol_Nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`RolId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cat_roles`
--

LOCK TABLES `tbl_cat_roles` WRITE;
/*!40000 ALTER TABLE `tbl_cat_roles` DISABLE KEYS */;
INSERT INTO `tbl_cat_roles` VALUES (1,'Administrador'),(2,'Dentista'),(3,'Cliente');
/*!40000 ALTER TABLE `tbl_cat_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cat_servicios`
--

DROP TABLE IF EXISTS `tbl_cat_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cat_servicios` (
  `ServicioId` int NOT NULL AUTO_INCREMENT,
  `Servicio_Nombre` varchar(45) DEFAULT NULL,
  `Servicio_Descripcion` text,
  `Servicio_Precio` decimal(10,2) DEFAULT NULL,
  `Servicio_ImagenUrl` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ServicioId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cat_servicios`
--

LOCK TABLES `tbl_cat_servicios` WRITE;
/*!40000 ALTER TABLE `tbl_cat_servicios` DISABLE KEYS */;
INSERT INTO `tbl_cat_servicios` VALUES (1,'Limpieza Dental','Eliminación de sarro y placa bacteriana con pulido dental profesional.',800.00,'/img/limpieza-dental.jpg'),(2,'Radiografía','Toma de imágenes dentales internas para diagnóstico preventivo o quirúrgico.',500.00,'/img/radiografia.jpg'),(3,'Extracción','Retiro quirúrgico de una pieza dental dañada o por necesidad de ortodoncia.',1200.00,'/img/extraccion-dental.jpg'),(4,'Ortodoncia (Mensualidad)','Ajuste mensual de brackets y seguimiento del alineamiento dental.',1500.00,'/img/brackets-mes.jpg'),(5,'Blanqueamiento Dental','Tratamiento estético para aclarar el tono de los dientes mediante agentes químicos.',2500.00,'/img/blanqueamiento.jpg'),(6,'Implante Dental','Sustitución de una raíz dental perdida con una pieza de titanio y corona.',15000.00,'/img/implantes.jpg');
/*!40000 ALTER TABLE `tbl_cat_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ope_clientes`
--

DROP TABLE IF EXISTS `tbl_ope_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ope_clientes` (
  `ClienteId` int NOT NULL AUTO_INCREMENT,
  `Cliente_UsuarioId` int DEFAULT NULL,
  `Cliente_FechaNacimiento` date DEFAULT NULL,
  `Cliente_SaldoPendiente` decimal(10,2) DEFAULT NULL,
  `Cliente_Expediente` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ClienteId`),
  KEY `Cliente_UsuarioId_idx` (`Cliente_UsuarioId`),
  CONSTRAINT `Cliente_UsuarioId` FOREIGN KEY (`Cliente_UsuarioId`) REFERENCES `tbl_ope_usuarios` (`UsuarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ope_clientes`
--

LOCK TABLES `tbl_ope_clientes` WRITE;
/*!40000 ALTER TABLE `tbl_ope_clientes` DISABLE KEYS */;
INSERT INTO `tbl_ope_clientes` VALUES (1,2,'1995-05-20',0.00,'EXP-0001'),(2,3,'1988-11-12',0.00,'EXP-0002'),(3,9,'2001-04-11',0.00,'EXP-0003'),(4,15,'2005-07-18',500.00,'EXP-0004'),(5,17,'2006-03-28',800.00,'EXP-0005'),(6,23,'2006-03-28',0.00,'EXP-0006'),(7,25,'2006-03-28',0.00,'EXP-0007'),(8,26,'2006-03-28',0.00,'EXP-0008'),(9,30,'2016-02-08',0.00,'EXP-0009'),(10,31,'2026-03-16',0.00,'EXP-0010');
/*!40000 ALTER TABLE `tbl_ope_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ope_dentistas`
--

DROP TABLE IF EXISTS `tbl_ope_dentistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ope_dentistas` (
  `DentistaId` int NOT NULL AUTO_INCREMENT,
  `Dentista_UsuarioId` int DEFAULT NULL,
  `Dentista_CedulaProf` varchar(45) DEFAULT NULL,
  `Especialidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`DentistaId`),
  KEY `Dentista_UsuarioId_idx` (`Dentista_UsuarioId`),
  CONSTRAINT `Dentista_UsuarioId` FOREIGN KEY (`Dentista_UsuarioId`) REFERENCES `tbl_ope_usuarios` (`UsuarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ope_dentistas`
--

LOCK TABLES `tbl_ope_dentistas` WRITE;
/*!40000 ALTER TABLE `tbl_ope_dentistas` DISABLE KEYS */;
INSERT INTO `tbl_ope_dentistas` VALUES (1,1,'12345678','Odontología General'),(2,14,'12513252','Especialista en Ortodoncia'),(3,16,'3253242','Ortodoncia');
/*!40000 ALTER TABLE `tbl_ope_dentistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ope_historial`
--

DROP TABLE IF EXISTS `tbl_ope_historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ope_historial` (
  `HistorialId` int NOT NULL AUTO_INCREMENT,
  `Historial_ClienteId` int DEFAULT NULL,
  `Historial_DentistaId` int DEFAULT NULL,
  `Historial_Diagnostico` text,
  `Historial_Tratamiento` text,
  `Historial_Recomendaciones` text,
  `Historial_FechaCons` datetime DEFAULT NULL,
  PRIMARY KEY (`HistorialId`),
  KEY `Historial_ClienteId_idx` (`Historial_ClienteId`),
  KEY `Historial_DentistaId_idx` (`Historial_DentistaId`),
  CONSTRAINT `Historial_ClienteId` FOREIGN KEY (`Historial_ClienteId`) REFERENCES `tbl_ope_clientes` (`ClienteId`),
  CONSTRAINT `Historial_DentistaId` FOREIGN KEY (`Historial_DentistaId`) REFERENCES `tbl_ope_dentistas` (`DentistaId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ope_historial`
--

LOCK TABLES `tbl_ope_historial` WRITE;
/*!40000 ALTER TABLE `tbl_ope_historial` DISABLE KEYS */;
INSERT INTO `tbl_ope_historial` VALUES (1,1,1,'Paciente presenta sarro leve en encías.','Limpieza ultrasónica profunda.','Usar hilo dental y enjuague sin alcohol.','2026-03-01 10:45:00'),(2,2,1,'Dolor agudo en molar inferior izquierdo.','Revisión técnica y toma de placa.','Evitar alimentos muy fríos hasta revisión de Rx.','2026-03-05 17:00:00'),(3,4,2,'prueba prueba pruebaaaa','tratamiento de prueba','casa','2026-03-12 00:37:49'),(4,4,2,'prueba prueba pruebaaaa','tratamiento de prueba','casa','2026-03-15 00:37:49');
/*!40000 ALTER TABLE `tbl_ope_historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ope_usuarios`
--

DROP TABLE IF EXISTS `tbl_ope_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ope_usuarios` (
  `UsuarioId` int NOT NULL AUTO_INCREMENT,
  `Usuario_Email` varchar(100) DEFAULT NULL,
  `Usuario_Password` varchar(80) DEFAULT NULL,
  `Usuario_RolId` int DEFAULT NULL,
  `Usuario_Registro` datetime DEFAULT NULL,
  `Usuario_Activo` tinyint DEFAULT NULL,
  `Usuario_Nombre` varchar(100) DEFAULT NULL,
  `Usuario_Apellido` varchar(100) DEFAULT NULL,
  `Usuario_Telefono` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UsuarioId`),
  UNIQUE KEY `Usuario_Email_UNIQUE` (`Usuario_Email`),
  KEY `Usuario_RolId_idx` (`Usuario_RolId`),
  CONSTRAINT `Usuario_RolId` FOREIGN KEY (`Usuario_RolId`) REFERENCES `tbl_cat_roles` (`RolId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ope_usuarios`
--

LOCK TABLES `tbl_ope_usuarios` WRITE;
/*!40000 ALTER TABLE `tbl_ope_usuarios` DISABLE KEYS */;
INSERT INTO `tbl_ope_usuarios` VALUES (1,'dr.garcia@dentcare.com','admin123',2,'2026-03-08 22:28:47',1,NULL,NULL,NULL),(2,'ana.perez@gmail.com','ana789',3,'2026-03-08 22:28:47',1,NULL,NULL,NULL),(3,'carlos.m@outlook.com','carlos456',3,'2026-03-08 22:28:47',1,NULL,NULL,NULL),(4,'irvin@irvin.com','$2a$10$ogjQ0CftxBocduBI7KW1X.EbAk/Qj80tuAcwBAEyci4CcevSGFOlG',1,'2026-03-11 01:30:20',1,'irvin','marquez','5513919121'),(7,'yare@yare.com','$2a$10$Uq1Ch3CrmLXwgcaTYYFUcOJzn7ujVhw8DEdquOk/p5aS24qiqlre6',2,'2026-03-11 01:34:06',1,'yare','rivera','5513919121'),(9,'mau@mau.com','$2a$10$kNPveo2flWL/N9zv.boCouzEgvGmAzk0nX7laqcMwdTUVz/RKiodC',3,'2026-03-11 12:53:33',1,'mau','dominguez','5542315512'),(11,'dentista@dentista.com','$2a$10$OJbQiKgP0ng9Cu1bevWvLun7eXlopPwWvJctpnlC0haLwQkZ.93bm',2,'2026-03-11 13:23:10',1,'julio','perez','5542315512'),(14,'dentista1@dentista.com','$2a$10$plZDfq6tPr4UDioc8958JOrwEViJO9KGXxKI.dQQeDxyvajb1Qb9a',2,'2026-03-11 13:25:30',1,'julio','perez','5542315512'),(15,'ejemplo@ejemplo.com','$2a$10$il4Bb3QsOgVaLSSo1lGBL.UZcHWPUJ.iiBd1giECZLiSJgS4I/Z6G',3,'2026-03-14 11:32:50',1,'irvin','marquez','7297062104'),(16,'dent@dent.com','$2a$10$5fSfBUGkPFFL6/0S/lNQQO60jd0ayY0hOL12.F4UG4qbf4NB1o4tm',2,'2026-03-21 14:10:58',1,'alan dav','mqz','5584735013'),(17,'ejemplo2@ejemplo2.com','$2a$10$NlobMrtQTnvgUtnFltM2pO2Dl/6iqBLMz/E5ZyqtmqSoaSdHzIqiG',3,'2026-03-24 12:27:53',1,'yaret','rivera','7297065124'),(23,'yare2@yare.com','$2a$10$momdE2OOmNeq.4PdfU9QD.w6F1tFL4JYBLvLndoylwpyGmvF2APlW',3,'2026-03-24 12:29:38',1,'yaret','rivera','7297065124'),(25,'yare3@yare.com','$2a$10$gEPyD2KZjeWpEWkqin58YevB.qsEtWtHjGumDUp9ryVTe4dXIPF6G',3,'2026-03-24 12:30:48',1,'yaret','rivera','7297065124'),(26,'example@example.com','$2a$10$hHCJ22lvLPG2pVG9vtptieVaYHuluvUueekoHkUAkwHcQlx6K7PbC',3,'2026-03-24 12:31:25',1,'yaret','rivera','7297065124'),(30,'prueba@prueba.com','$2a$10$68yFj2N7h8Z/2zB234icpOyYJH0Zmu2QBZ7dSwZTrJ99e8F46oOdu',3,'2026-03-24 12:46:56',1,'prueba','prueba','3254365432'),(31,'eshu@eshu.com','$2a$10$KqP1X3Zso.ry0j5O2jLrg.6bi9ulzKpHe138t2BNRnssNh5VRYPTS',3,'2026-03-24 13:42:31',1,'eshu','marquez','47923502352');
/*!40000 ALTER TABLE `tbl_ope_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rel_citas`
--

DROP TABLE IF EXISTS `tbl_rel_citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_rel_citas` (
  `CitaId` int NOT NULL AUTO_INCREMENT,
  `Cita_ClienteId` int DEFAULT NULL,
  `Cita_DentistaId` int DEFAULT NULL,
  `Cita_ServicioId` int DEFAULT NULL,
  `Cita_EstatusId` int DEFAULT NULL,
  `Cita_Fecha` date DEFAULT NULL,
  `Cita_Hora` time DEFAULT NULL,
  `Cita_CostoFinal` decimal(10,2) DEFAULT NULL,
  `Cita_Notas` text,
  PRIMARY KEY (`CitaId`),
  KEY `Citas_ClienteId_idx` (`Cita_ClienteId`),
  KEY `Citas_DentistaId_idx` (`Cita_DentistaId`),
  KEY `Citas_ServicioId_idx` (`Cita_ServicioId`),
  KEY `Citas_EstatusId_idx` (`Cita_EstatusId`),
  CONSTRAINT `Citas_ClienteId` FOREIGN KEY (`Cita_ClienteId`) REFERENCES `tbl_ope_clientes` (`ClienteId`),
  CONSTRAINT `Citas_DentistaId` FOREIGN KEY (`Cita_DentistaId`) REFERENCES `tbl_ope_dentistas` (`DentistaId`),
  CONSTRAINT `Citas_EstatusId` FOREIGN KEY (`Cita_EstatusId`) REFERENCES `tbl_cat_citas_estatus` (`EstatusId`),
  CONSTRAINT `Citas_ServicioId` FOREIGN KEY (`Cita_ServicioId`) REFERENCES `tbl_cat_servicios` (`ServicioId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rel_citas`
--

LOCK TABLES `tbl_rel_citas` WRITE;
/*!40000 ALTER TABLE `tbl_rel_citas` DISABLE KEYS */;
INSERT INTO `tbl_rel_citas` VALUES (1,1,1,1,3,'2026-03-01','10:00:00',800.00,'Paciente refiere sensibilidad al frío.'),(2,2,1,2,3,'2026-03-05','16:30:00',500.00,'Requiere radiografía por dolor punzante en zona molar.'),(3,1,1,4,2,'2026-03-15','09:00:00',1500.00,'Cita de control mensual; revisión de brackets inferiores.'),(4,4,2,2,2,'2026-04-05','08:00:00',900.00,'Cita de contorl'),(5,4,2,1,3,'2026-03-10','08:00:00',900.00,'Cita de contorl'),(6,4,2,4,4,'2026-04-01','08:00:00',900.00,'Cita de contorl'),(7,4,2,4,4,'2026-04-03','08:00:00',900.00,'Cita de contorl'),(8,4,2,4,4,'2026-04-01','09:00:00',900.00,'Cita de contorl'),(9,4,2,4,4,'2026-04-01','11:00:00',900.00,'Cita de contorl'),(10,4,2,3,2,'2026-03-17','10:00:00',NULL,NULL),(11,4,2,3,4,'2026-03-17','11:00:00',NULL,NULL),(12,4,2,6,1,'2026-03-17','09:00:00',15000.00,'me duele cambien el diente pls'),(13,4,2,1,4,'2026-04-01','09:00:00',800.00,'jaja'),(14,4,2,3,4,'2026-04-02','11:00:00',1200.00,'me duele la muela'),(15,4,3,3,2,'2026-03-03','11:00:00',1200.00,'me duele la muela'),(16,4,3,3,3,'2026-03-22','11:00:00',1200.00,'me duele la muela'),(17,4,3,3,4,'2026-03-22','11:00:00',1200.00,'me duele la muela'),(18,4,3,3,2,'2026-03-22','11:00:00',1200.00,'me duele la muela'),(19,4,3,3,2,'2026-02-03','11:00:00',1200.00,'me duele la muela'),(20,4,3,3,3,'2026-03-23','16:00:00',1200.00,'me duele la muelita un buen'),(21,4,3,3,4,'2026-03-23','17:00:00',1200.00,'me duele la muelita un buen'),(22,4,3,3,1,'2026-03-24','15:00:00',1200.00,'me duele la muelita un buen'),(23,5,3,4,2,'2026-03-25','10:00:00',1500.00,'cita de brackets mensual'),(24,5,3,1,1,'2026-03-25','11:00:00',800.00,'ayudeme doctor');
/*!40000 ALTER TABLE `tbl_rel_citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rel_estudios`
--

DROP TABLE IF EXISTS `tbl_rel_estudios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_rel_estudios` (
  `EstudioId` int NOT NULL AUTO_INCREMENT,
  `Estudio_HistorialId` int DEFAULT NULL,
  `Estudio_Nombre` varchar(45) DEFAULT NULL,
  `Estudio_RutaUrl` varchar(45) DEFAULT NULL,
  `Estudio_Tipo` varchar(45) DEFAULT NULL,
  `Estudio_Fecha` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`EstudioId`),
  KEY `Estudios_HistorialId_idx` (`Estudio_HistorialId`),
  CONSTRAINT `Estudios_HistorialId` FOREIGN KEY (`Estudio_HistorialId`) REFERENCES `tbl_ope_historial` (`HistorialId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rel_estudios`
--

LOCK TABLES `tbl_rel_estudios` WRITE;
/*!40000 ALTER TABLE `tbl_rel_estudios` DISABLE KEYS */;
INSERT INTO `tbl_rel_estudios` VALUES (1,3,'Rx Periapical Molar Inf','/img/estudios1.jpg','Radiografía','2026-03-09 04:31:45');
/*!40000 ALTER TABLE `tbl_rel_estudios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_citascompletas`
--

DROP TABLE IF EXISTS `v_citascompletas`;
/*!50001 DROP VIEW IF EXISTS `v_citascompletas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_citascompletas` AS SELECT 
 1 AS `CitaId`,
 1 AS `Cita_Fecha`,
 1 AS `Cita_Hora`,
 1 AS `Cita_CostoFinal`,
 1 AS `Cita_Notas`,
 1 AS `ClienteId`,
 1 AS `nombreCliente`,
 1 AS `Cliente_Telefono`,
 1 AS `Cliente_FechaNacimiento`,
 1 AS `DentistaId`,
 1 AS `nombreDentista`,
 1 AS `Especialidad`,
 1 AS `Dentista_CedulaProf`,
 1 AS `Servicio_Nombre`,
 1 AS `Servicio_Descripcion`,
 1 AS `Estatus_Nombre`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_citascompletas`
--

/*!50001 DROP VIEW IF EXISTS `v_citascompletas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dentalcare`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_citascompletas` AS select `trc`.`CitaId` AS `CitaId`,`trc`.`Cita_Fecha` AS `Cita_Fecha`,`trc`.`Cita_Hora` AS `Cita_Hora`,`trc`.`Cita_CostoFinal` AS `Cita_CostoFinal`,`trc`.`Cita_Notas` AS `Cita_Notas`,`toc`.`ClienteId` AS `ClienteId`,concat(`u_cli`.`Usuario_Nombre`,' ',`u_cli`.`Usuario_Apellido`) AS `nombreCliente`,`u_cli`.`Usuario_Telefono` AS `Cliente_Telefono`,`toc`.`Cliente_FechaNacimiento` AS `Cliente_FechaNacimiento`,`tod`.`DentistaId` AS `DentistaId`,concat(`u_den`.`Usuario_Nombre`,' ',`u_den`.`Usuario_Apellido`) AS `nombreDentista`,`tod`.`Especialidad` AS `Especialidad`,`tod`.`Dentista_CedulaProf` AS `Dentista_CedulaProf`,`tcs`.`Servicio_Nombre` AS `Servicio_Nombre`,`tcs`.`Servicio_Descripcion` AS `Servicio_Descripcion`,`tcce`.`Estatus_Nombre` AS `Estatus_Nombre` from ((((((`tbl_rel_citas` `trc` join `tbl_ope_clientes` `toc` on((`trc`.`Cita_ClienteId` = `toc`.`ClienteId`))) join `tbl_ope_usuarios` `u_cli` on((`toc`.`Cliente_UsuarioId` = `u_cli`.`UsuarioId`))) join `tbl_ope_dentistas` `tod` on((`trc`.`Cita_DentistaId` = `tod`.`DentistaId`))) join `tbl_ope_usuarios` `u_den` on((`tod`.`Dentista_UsuarioId` = `u_den`.`UsuarioId`))) join `tbl_cat_servicios` `tcs` on((`trc`.`Cita_ServicioId` = `tcs`.`ServicioId`))) join `tbl_cat_citas_estatus` `tcce` on((`trc`.`Cita_EstatusId` = `tcce`.`EstatusId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-26 22:23:07
