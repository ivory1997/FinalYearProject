-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 07, 2019 at 04:39 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id11003303_finalyearproject2`
--

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `id` int(11) NOT NULL,
  `email` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Ireland` int(5) NOT NULL,
  `Madagascar` int(5) NOT NULL,
  `Bhutan` int(5) NOT NULL,
  `AX` int(5) NOT NULL,
  `XK` int(5) NOT NULL,
  `Suriname` int(5) NOT NULL,
  `CI` int(5) NOT NULL,
  `RE` int(5) NOT NULL,
  `BN` int(5) NOT NULL,
  `TL` int(5) NOT NULL,
  `FK` int(5) NOT NULL,
  `Isle_of_Man` int(5) NOT NULL,
  `Rwanda` int(5) NOT NULL,
  `Burundi` int(5) NOT NULL,
  `Equatorial_Guinea` int(5) NOT NULL,
  `Gabon` int(5) NOT NULL,
  `SS` int(5) NOT NULL,
  `CG` int(5) NOT NULL,
  `Haiti` int(5) NOT NULL,
  `Vanuatu` int(5) NOT NULL,
  `Togo` int(5) NOT NULL,
  `Burkina_Faso` int(5) NOT NULL,
  `Solomon_Islands` int(5) NOT NULL,
  `CD` int(5) NOT NULL,
  `Laos` int(5) NOT NULL,
  `Belize` int(5) NOT NULL,
  `North_Korea` int(5) NOT NULL,
  `French_Guiana` int(5) NOT NULL,
  `Eritrea` int(5) NOT NULL,
  `Djibouti` int(5) NOT NULL,
  `Central_African_Republic` int(5) NOT NULL,
  `Lesotho` int(5) NOT NULL,
  `Liberia` int(5) NOT NULL,
  `Sierra_Leone` int(5) NOT NULL,
  `Guinea` int(5) NOT NULL,
  `Guinea_Bissau` int(5) NOT NULL,
  `Malawi` int(5) NOT NULL,
  `Svalbard_and_Jan_Mayen` int(5) NOT NULL,
  `Greenland` int(5) NOT NULL,
  `Romania` int(5) NOT NULL,
  `Angola` int(5) NOT NULL,
  `Republic_of_the_Congo` int(5) NOT NULL,
  `Somalia` int(5) NOT NULL,
  `Papua_New_Guinea` int(5) NOT NULL,
  `Western_Sahara` int(5) NOT NULL,
  `Mali` int(5) NOT NULL,
  `Nigeria` int(5) NOT NULL,
  `Chad` int(5) NOT NULL,
  `Liechtenstein` int(5) NOT NULL,
  `Maldives` int(5) NOT NULL,
  `Sudan` int(5) NOT NULL,
  `Zimbabwe` int(5) NOT NULL,
  `Mauritania` int(5) NOT NULL,
  `Mozambique` int(5) NOT NULL,
  `Swaziland` int(5) NOT NULL,
  `Tanzania` int(5) NOT NULL,
  `Iraq` int(5) NOT NULL,
  `Guyana` int(5) NOT NULL,
  `Namibia` int(5) NOT NULL,
  `Senegal` int(5) NOT NULL,
  `Turkmenistan` int(5) NOT NULL,
  `Afghanistan` int(5) NOT NULL,
  `Andorra` int(5) NOT NULL,
  `Fiji` int(5) NOT NULL,
  `Uzbekistan` int(5) NOT NULL,
  `Cameroon` int(5) NOT NULL,
  `Cuba` int(5) NOT NULL,
  `Faroe_Islands` int(5) NOT NULL,
  `El_Salvador` int(5) NOT NULL,
  `Caribbean` int(5) NOT NULL,
  `Ethiopia` int(5) NOT NULL,
  `Mongolia` int(5) NOT NULL,
  `Puerto_Rico` int(5) NOT NULL,
  `Samoa` int(5) NOT NULL,
  `Myanmar` int(5) NOT NULL,
  `Nicaragua` int(5) NOT NULL,
  `Tajikistan` int(5) NOT NULL,
  `Barbados` int(5) NOT NULL,
  `Dominican_Republic` int(5) NOT NULL,
  `Libya` int(5) NOT NULL,
  `Panama` int(5) NOT NULL,
  `Bahrain` int(5) NOT NULL,
  `Benin` int(5) NOT NULL,
  `Bolivia` int(5) NOT NULL,
  `Ghana` int(5) NOT NULL,
  `Montenegro` int(5) NOT NULL,
  `Syria` int(5) NOT NULL,
  `Ecuador` int(5) NOT NULL,
  `Honduras` int(5) NOT NULL,
  `Tunisia` int(5) NOT NULL,
  `Botswana` int(5) NOT NULL,
  `Cyprus` int(5) NOT NULL,
  `Algeria` int(5) NOT NULL,
  `Bahamas` int(5) NOT NULL,
  `New_Caledonia` int(5) NOT NULL,
  `Uganda` int(5) NOT NULL,
  `Yemen` int(5) NOT NULL,
  `Zambia` int(5) NOT NULL,
  `Antarctica` int(5) NOT NULL,
  `Paraguay` int(5) NOT NULL,
  `Jamaica` int(5) NOT NULL,
  `Bosnia_and_Herzegovina` int(5) NOT NULL,
  `Vietnam` int(5) NOT NULL,
  `Luxembourg` int(5) NOT NULL,
  `Kenya` int(5) NOT NULL,
  `Palestinian` int(5) NOT NULL,
  `Nepala` int(5) NOT NULL,
  `Niger` int(5) NOT NULL,
  `Kuwait` int(5) NOT NULL,
  `Hawaii` int(5) NOT NULL,
  `Cambodia` int(5) NOT NULL,
  `Uruguay` int(5) NOT NULL,
  `Kyrgyzstan` int(5) NOT NULL,
  `Saudi_Arabia` int(5) NOT NULL,
  `Indonesia` int(5) NOT NULL,
  `Azerbaijan` int(5) NOT NULL,
  `United_Arab_Emirates` int(5) NOT NULL,
  `Mauritius` int(5) NOT NULL,
  `Alberta` int(5) NOT NULL,
  `Morocco` int(5) NOT NULL,
  `Albania` int(5) NOT NULL,
  `South_Korea` int(5) NOT NULL,
  `Kazakhstan` int(5) NOT NULL,
  `Macedonia` int(5) NOT NULL,
  `Venezuela` int(5) NOT NULL,
  `Taiwan` int(5) NOT NULL,
  `Qatar` int(5) NOT NULL,
  `Jordan` int(5) NOT NULL,
  `Iceland` int(5) NOT NULL,
  `Guatemala` int(5) NOT NULL,
  `Costa_Rica` int(5) NOT NULL,
  `San_Marino` int(5) NOT NULL,
  `Colombia` int(5) NOT NULL,
  `Moldova` int(5) NOT NULL,
  `Armenia` int(5) NOT NULL,
  `Egypt` int(5) NOT NULL,
  `Nepal` int(5) NOT NULL,
  `Malta` int(5) NOT NULL,
  `Lebanon` int(5) NOT NULL,
  `Malaysia` int(5) NOT NULL,
  `Serbia` int(5) NOT NULL,
  `Peru` int(5) NOT NULL,
  `Trinidad_and_Tobago` int(5) NOT NULL,
  `Lithuania` int(5) NOT NULL,
  `Estonia` int(5) NOT NULL,
  `Georgia` int(5) NOT NULL,
  `Iran` int(5) NOT NULL,
  `Chile` int(5) NOT NULL,
  `Latvia` int(5) NOT NULL,
  `Thailand` int(5) NOT NULL,
  `Slovenia` int(5) NOT NULL,
  `Mexico` int(5) NOT NULL,
  `Belarus` int(5) NOT NULL,
  `Slovakia` int(5) NOT NULL,
  `Sri_Lanka` int(5) NOT NULL,
  `Croatia` int(5) NOT NULL,
  `Philippines` int(5) NOT NULL,
  `Turkey` int(5) NOT NULL,
  `Italy` int(5) NOT NULL,
  `South_Africa` int(5) NOT NULL,
  `Bangladesh` int(5) NOT NULL,
  `Hungary` int(5) NOT NULL,
  `Pakistan` int(5) NOT NULL,
  `Portugal` int(5) NOT NULL,
  `Ukraine` int(5) NOT NULL,
  `Greece` int(5) NOT NULL,
  `Argentina` int(5) NOT NULL,
  `Singapore` int(5) NOT NULL,
  `Bulgaria` int(5) NOT NULL,
  `Japan` int(5) NOT NULL,
  `Czech_Republic` int(5) NOT NULL,
  `China` int(5) NOT NULL,
  `Oman` int(5) NOT NULL,
  `Brazil` int(5) NOT NULL,
  `Finland` int(5) NOT NULL,
  `Norway` int(5) NOT NULL,
  `Austria` int(5) NOT NULL,
  `Denmark` int(5) NOT NULL,
  `Belgium` int(5) NOT NULL,
  `New_Zealand` int(5) NOT NULL,
  `Spain` int(5) NOT NULL,
  `Switzerland` int(5) NOT NULL,
  `Russia` int(5) NOT NULL,
  `Poland` int(5) NOT NULL,
  `Israel` int(5) NOT NULL,
  `Sweden` int(5) NOT NULL,
  `Netherlands` int(5) NOT NULL,
  `France` int(5) NOT NULL,
  `Australia` int(5) NOT NULL,
  `Canada` int(5) NOT NULL,
  `India` int(5) NOT NULL,
  `Germany` int(5) NOT NULL,
  `United_Kingdom` int(5) NOT NULL,
  `United_States` int(5) NOT NULL,
  `Unknown_region` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`id`, `email`, `Ireland`, `Madagascar`, `Bhutan`, `AX`, `XK`, `Suriname`, `CI`, `RE`, `BN`, `TL`, `FK`, `Isle_of_Man`, `Rwanda`, `Burundi`, `Equatorial_Guinea`, `Gabon`, `SS`, `CG`, `Haiti`, `Vanuatu`, `Togo`, `Burkina_Faso`, `Solomon_Islands`, `CD`, `Laos`, `Belize`, `North_Korea`, `French_Guiana`, `Eritrea`, `Djibouti`, `Central_African_Republic`, `Lesotho`, `Liberia`, `Sierra_Leone`, `Guinea`, `Guinea_Bissau`, `Malawi`, `Svalbard_and_Jan_Mayen`, `Greenland`, `Romania`, `Angola`, `Republic_of_the_Congo`, `Somalia`, `Papua_New_Guinea`, `Western_Sahara`, `Mali`, `Nigeria`, `Chad`, `Liechtenstein`, `Maldives`, `Sudan`, `Zimbabwe`, `Mauritania`, `Mozambique`, `Swaziland`, `Tanzania`, `Iraq`, `Guyana`, `Namibia`, `Senegal`, `Turkmenistan`, `Afghanistan`, `Andorra`, `Fiji`, `Uzbekistan`, `Cameroon`, `Cuba`, `Faroe_Islands`, `El_Salvador`, `Caribbean`, `Ethiopia`, `Mongolia`, `Puerto_Rico`, `Samoa`, `Myanmar`, `Nicaragua`, `Tajikistan`, `Barbados`, `Dominican_Republic`, `Libya`, `Panama`, `Bahrain`, `Benin`, `Bolivia`, `Ghana`, `Montenegro`, `Syria`, `Ecuador`, `Honduras`, `Tunisia`, `Botswana`, `Cyprus`, `Algeria`, `Bahamas`, `New_Caledonia`, `Uganda`, `Yemen`, `Zambia`, `Antarctica`, `Paraguay`, `Jamaica`, `Bosnia_and_Herzegovina`, `Vietnam`, `Luxembourg`, `Kenya`, `Palestinian`, `Nepala`, `Niger`, `Kuwait`, `Hawaii`, `Cambodia`, `Uruguay`, `Kyrgyzstan`, `Saudi_Arabia`, `Indonesia`, `Azerbaijan`, `United_Arab_Emirates`, `Mauritius`, `Alberta`, `Morocco`, `Albania`, `South_Korea`, `Kazakhstan`, `Macedonia`, `Venezuela`, `Taiwan`, `Qatar`, `Jordan`, `Iceland`, `Guatemala`, `Costa_Rica`, `San_Marino`, `Colombia`, `Moldova`, `Armenia`, `Egypt`, `Nepal`, `Malta`, `Lebanon`, `Malaysia`, `Serbia`, `Peru`, `Trinidad_and_Tobago`, `Lithuania`, `Estonia`, `Georgia`, `Iran`, `Chile`, `Latvia`, `Thailand`, `Slovenia`, `Mexico`, `Belarus`, `Slovakia`, `Sri_Lanka`, `Croatia`, `Philippines`, `Turkey`, `Italy`, `South_Africa`, `Bangladesh`, `Hungary`, `Pakistan`, `Portugal`, `Ukraine`, `Greece`, `Argentina`, `Singapore`, `Bulgaria`, `Japan`, `Czech_Republic`, `China`, `Oman`, `Brazil`, `Finland`, `Norway`, `Austria`, `Denmark`, `Belgium`, `New_Zealand`, `Spain`, `Switzerland`, `Russia`, `Poland`, `Israel`, `Sweden`, `Netherlands`, `France`, `Australia`, `Canada`, `India`, `Germany`, `United_Kingdom`, `United_States`, `Unknown_region`) VALUES
(1, 'mkivory97@gmail.com', 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 2, 2, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 2, 2, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(30) CHARACTER SET latin1 NOT NULL,
  `password` varchar(30) CHARACTER SET latin1 NOT NULL,
  `name` varchar(30) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `name`) VALUES
(1, 'mkivory97@gmail.com', 'go', 'Mark');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
