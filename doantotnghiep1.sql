-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2021 at 03:23 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doantotnghiep1`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id` bigint(20) NOT NULL,
  `academic_rank` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `academic_rank`, `degree`, `name`) VALUES
(1, 'Tiến Sĩ', 'Đại học', 'Tố Hữu'),
(2, 'Tiến Sĩ', 'Đại học', 'Quyết');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_topic` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `image`, `name`, `id_topic`, `topic_id`) VALUES
(8, '254262890_267598015318883_273328658810393308_n.jpg', 'Hưng Yên', 4, NULL),
(31, '', 'haha', 4, NULL),
(32, '', 'Ha Noi', 30, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `page_number` varchar(255) DEFAULT NULL,
  `publishing_year` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `visit_number` bigint(20) DEFAULT NULL,
  `id_category` bigint(20) DEFAULT NULL,
  `id_publishing_company` bigint(20) DEFAULT NULL,
  `main_author` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `document`
--

INSERT INTO `document` (`id`, `file_name`, `image`, `page_number`, `publishing_year`, `status`, `update_at`, `visit_number`, `id_category`, `id_publishing_company`, `main_author`, `name`) VALUES
(11, '1', '1', '1', '1', b'1', '2021-11-01 20:45:23', 123, NULL, NULL, '1', '2'),
(13, '1', '1', '1', '1', b'1', '2021-11-01 20:45:23', 123, 8, 1, '1', '1'),
(23, '1', '1', '1', '1', b'1', '2021-11-01 20:45:23', 123, 8, 1, '1', '1'),
(25, 'TranThanhHai.pptx', 'db5.png', '123', '1996', NULL, NULL, NULL, 8, 1, '2', 'CNTT'),
(26, 'TranThanhHai1.pptx', '6.png', '123', '123', NULL, NULL, NULL, 8, 1, '2', 'Test');

-- --------------------------------------------------------

--
-- Table structure for table `documents_authors`
--

CREATE TABLE `documents_authors` (
  `id_document` bigint(20) NOT NULL,
  `id_author` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=FIXED;

--
-- Dumping data for table `documents_authors`
--

INSERT INTO `documents_authors` (`id_document`, `id_author`) VALUES
(25, 2),
(26, 2);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(33),
(1);

-- --------------------------------------------------------

--
-- Table structure for table `publishing_company`
--

CREATE TABLE `publishing_company` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `publishing_company`
--

INSERT INTO `publishing_company` (`id`, `address`, `email`, `name`, `phone`) VALUES
(1, 'Xóm Đồng Mạc, Xã Tiên Hội, Huyện Đại Từ, Tỉnh Thái Nguyên', 'haidevutc@gmail.com', 'Kim Đồng', '0396355470');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `name`, `status`) VALUES
(3, 'Vũ quang minhhh', NULL),
(4, 'Hưng Yên', NULL),
(27, 'CNTT', b'0'),
(28, 'Công Trình', b'0'),
(29, 'Cơ Khí', b'0'),
(30, 'Điện - Điện Tử', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `avt` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `catalog` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `student_code` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `avt`, `password`, `catalog`, `gender`, `id_card`, `status`, `student_code`, `user_name`, `address`, `full_name`, `phone`, `email`) VALUES
(1, NULL, '$2a$10$HNctoJa6FfE.zgISn25tE.AFe8PfBRfEOdQVx2/9Vgm6bKbO6fm2m', 'Quản trị viên', NULL, NULL, NULL, NULL, 'admin', NULL, 'admin', '0396355470', NULL),
(18, 'https://firebasestorage.googleapis.com/v0/b/demoupload-d290c.appspot.com/o/avatar.jpg?alt=media&token=9ac8b329-207a-4c5b-9581-98d5269b160d', '$2a$10$30ee7AaIphT9k5kAnXXf0um5G085oCQcnZKBQI3SejRhCSXwtsUxu', '2', NULL, NULL, NULL, NULL, 'hailit', NULL, 'Trần Thanh Hải', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=FIXED;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2),
(16, 2),
(17, 2),
(18, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `FKrbeg8p9xjlp9n2slmaxocxvin` (`id_topic`) USING BTREE,
  ADD KEY `FK2vkrm6ckwf6eiq9k4dv1dx5hh` (`topic_id`);

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `FKmvuiffh6wjg1sghrl49lyijml` (`id_category`) USING BTREE,
  ADD KEY `FKsjwryaop2mycqcjrfp6aesfdt` (`id_publishing_company`) USING BTREE;

--
-- Indexes for table `documents_authors`
--
ALTER TABLE `documents_authors`
  ADD KEY `FKhuk7189ma905iju92ivqgx6fp` (`id_author`) USING BTREE,
  ADD KEY `FK80o2y9ssb0efxqt3doyiahnsj` (`id_document`) USING BTREE;

--
-- Indexes for table `publishing_company`
--
ALTER TABLE `publishing_company`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`) USING HASH,
  ADD UNIQUE KEY `UK_1ep90ws9w518nst3415yen9dv` (`id_card`) USING HASH,
  ADD UNIQUE KEY `UK_nc5pd9yb9sj945f59dnxem5a2` (`student_code`) USING HASH,
  ADD UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`) USING HASH;

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `document`
--
ALTER TABLE `document`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `publishing_company`
--
ALTER TABLE `publishing_company`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
