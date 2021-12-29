/*
 Navicat Premium Data Transfer

 Source Server         : db_local
 Source Server Type    : MySQL
 Source Server Version : 100411
 Source Host           : localhost:3306
 Source Schema         : db_blfc

 Target Server Type    : MySQL
 Target Server Version : 100411
 File Encoding         : 65001

 Date: 27/12/2021 20:28:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jadwal
-- ----------------------------
DROP TABLE IF EXISTS `jadwal`;
CREATE TABLE `jadwal`  (
  `idjadwal` int(11) NOT NULL,
  `team1` int(11) NULL DEFAULT NULL,
  `team2` int(11) NULL DEFAULT NULL,
  `goal1` int(11) NULL DEFAULT NULL,
  `goal2` int(11) NULL DEFAULT NULL,
  `hari` datetime(0) NULL DEFAULT NULL,
  `tempat` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `liga_idliga` int(11) NOT NULL,
  `flag` int(11) NULL DEFAULT NULL,
  `matchday` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idjadwal`, `liga_idliga`) USING BTREE,
  INDEX `fk_jadwal_liga1_idx`(`liga_idliga`) USING BTREE,
  CONSTRAINT `fk_jadwal_liga1` FOREIGN KEY (`liga_idliga`) REFERENCES `liga` (`idliga`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jadwal
-- ----------------------------

-- ----------------------------
-- Table structure for klasmen
-- ----------------------------
DROP TABLE IF EXISTS `klasmen`;
CREATE TABLE `klasmen`  (
  `id_klasmen` int(11) NOT NULL,
  `liga_idliga` int(11) NOT NULL,
  `gambar` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_klasmen`, `liga_idliga`) USING BTREE,
  INDEX `fk_team_has_liga_liga1_idx`(`liga_idliga`) USING BTREE,
  CONSTRAINT `fk_team_has_liga_liga1` FOREIGN KEY (`liga_idliga`) REFERENCES `liga` (`idliga`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of klasmen
-- ----------------------------

-- ----------------------------
-- Table structure for liga
-- ----------------------------
DROP TABLE IF EXISTS `liga`;
CREATE TABLE `liga`  (
  `idliga` int(11) NOT NULL,
  `namaliga` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jumlahteam` int(11) NULL DEFAULT NULL,
  `logo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idliga`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liga
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id_news` int(11) NOT NULL,
  `title` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priview` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `image` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datecreated` datetime(0) NULL DEFAULT NULL,
  `dateupdate` datetime(0) NULL DEFAULT NULL,
  `user_id_user` int(11) NOT NULL,
  `statuspublish` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id_news`) USING BTREE,
  INDEX `fk_news_user_idx`(`user_id_user`) USING BTREE,
  CONSTRAINT `fk_news_user` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for pemain
-- ----------------------------
DROP TABLE IF EXISTS `pemain`;
CREATE TABLE `pemain`  (
  `id` int(11) NOT NULL,
  `nama` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fakultas` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nopunggung` int(11) NULL DEFAULT NULL,
  `image` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `team_idteam` int(11) NOT NULL,
  PRIMARY KEY (`id`, `team_idteam`) USING BTREE,
  INDEX `fk_pemain_team1_idx`(`team_idteam`) USING BTREE,
  CONSTRAINT `fk_pemain_team1` FOREIGN KEY (`team_idteam`) REFERENCES `team` (`idteam`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pemain
-- ----------------------------

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `idteam` int(11) NOT NULL,
  `namateam` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idteam`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES (1, 'Universitas Gadjah Mada', NULL);
INSERT INTO `team` VALUES (2, 'Institut Pertanian Bogor', NULL);
INSERT INTO `team` VALUES (3, 'Institut Teknologi Sepuluh Nopember', NULL);
INSERT INTO `team` VALUES (4, 'Universitas Indonesia', NULL);
INSERT INTO `team` VALUES (5, 'Universitas Airlangga', NULL);
INSERT INTO `team` VALUES (6, 'Institut Teknologi Bandung', NULL);
INSERT INTO `team` VALUES (7, 'Universitas Bina Nusantara', NULL);
INSERT INTO `team` VALUES (8, 'Universitas Sebelas Maret UNS Surakarta', NULL);
INSERT INTO `team` VALUES (9, 'Universitas Diponegoro', NULL);
INSERT INTO `team` VALUES (10, 'Universitas Jember', NULL);
INSERT INTO `team` VALUES (11, 'Universitas Brawijaya', NULL);
INSERT INTO `team` VALUES (12, 'Telkom University', NULL);
INSERT INTO `team` VALUES (13, 'Universitas Pendidikan Indonesia', NULL);
INSERT INTO `team` VALUES (14, 'Universitas Hasanuddin', NULL);
INSERT INTO `team` VALUES (15, 'Universitas Muhammadiyah Yogyakarta', NULL);
INSERT INTO `team` VALUES (16, 'Universitas Padjadjaran Bandung', NULL);
INSERT INTO `team` VALUES (17, 'Universitas Syiah Kuala', NULL);
INSERT INTO `team` VALUES (18, 'Universitas Negeri Yogyakarta', NULL);
INSERT INTO `team` VALUES (19, 'Universitas Sumatera Utara', NULL);
INSERT INTO `team` VALUES (20, 'Universitas Negeri Malang', NULL);
INSERT INTO `team` VALUES (21, 'BLFC', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id_user` int(11) NOT NULL,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datecreated` datetime(0) NULL DEFAULT NULL,
  `dateupdate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test', '1223', '2021-12-26 17:56:14', '2021-12-26 17:56:18');

SET FOREIGN_KEY_CHECKS = 1;
