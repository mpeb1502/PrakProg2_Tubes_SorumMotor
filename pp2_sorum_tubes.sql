-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2022 at 05:23 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pp2_sorum_tubes`
--

-- --------------------------------------------------------

--
-- Table structure for table `mermo`
--

CREATE TABLE `mermo` (
  `id` varchar(255) NOT NULL,
  `merekmotor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mermo`
--

INSERT INTO `mermo` (`id`, `merekmotor`) VALUES
('4369605f-51fd-4eed-b750-7c93ff1f51c6', 'Mio'),
('77639b48-2dcd-44be-a9c6-49f1580434ca', 'Vespa'),
('87bb3d71-9dff-4c33-8609-afaa7bed11ec', 'Yamaha'),
('d3aa866a-acb1-4a43-9df9-e497f0184b99', 'kawasaki');

-- --------------------------------------------------------

--
-- Table structure for table `namot`
--

CREATE TABLE `namot` (
  `id` varchar(255) NOT NULL,
  `namamotor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `namot`
--

INSERT INTO `namot` (`id`, `namamotor`) VALUES
('01a9777a-3b55-4e2a-a215-3ba37a001ac2', 'Mio Karbu'),
('2c36299c-66b0-4bab-9ace-2d10cf8961e5', 'Jupiter'),
('ac19b0de-5bf3-4eca-865e-4b26b7ce4210', 'kawasaki Zx'),
('c7749c0d-7785-464d-9e7c-df3ff18648bc', 'Beat Hember'),
('ea263dc8-2161-43fa-98ab-9fd77e046611', 'Mio soul');

-- --------------------------------------------------------

--
-- Table structure for table `sorum`
--

CREATE TABLE `sorum` (
  `id` varchar(255) NOT NULL,
  `namot_id` varchar(255) DEFAULT NULL,
  `mermo_id` varchar(255) DEFAULT NULL,
  `namapem` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `jenismot` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sorum`
--

INSERT INTO `sorum` (`id`, `namot_id`, `mermo_id`, `namapem`, `alamat`, `jenismot`) VALUES
('2fee55e1-b3ce-4708-a76f-2c37d8f42dc2', '2c36299c-66b0-4bab-9ace-2d10cf8961e5', '87bb3d71-9dff-4c33-8609-afaa7bed11ec', 'Ikhsan', 'Jl.kartin', 'Modern'),
('42bef38f-7545-4f52-8ae6-886221a4b3c8', '2c36299c-66b0-4bab-9ace-2d10cf8961e5', '87bb3d71-9dff-4c33-8609-afaa7bed11ec', 'Sans', 'Jl.kartin', 'Modern'),
('c64a0657-fdb6-44e8-a941-a55e98595a87', '01a9777a-3b55-4e2a-a215-3ba37a001ac2', '4369605f-51fd-4eed-b750-7c93ff1f51c6', 'MMBAPE', 'Jl.Prancis', 'Classic');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mermo`
--
ALTER TABLE `mermo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `namot`
--
ALTER TABLE `namot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sorum`
--
ALTER TABLE `sorum`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
