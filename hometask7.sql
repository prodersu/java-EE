-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Ноя 11 2020 г., 13:16
-- Версия сервера: 10.4.10-MariaDB
-- Версия PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `hometask7`
--

-- --------------------------------------------------------

--
-- Структура таблицы `brands`
--

CREATE TABLE `brands` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `countries_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `brands`
--

INSERT INTO `brands` (`id`, `name`, `countries_id`) VALUES
(1, 'Samsung', 5),
(2, 'Sony', 4),
(3, 'Acer', 2),
(4, 'Xiaomi', 2),
(5, 'Apple', 1),
(6, 'Lenovo', 2),
(7, 'HP', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `countries`
--

CREATE TABLE `countries` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `countries`
--

INSERT INTO `countries` (`id`, `code`, `name`) VALUES
(1, 'USA', 'United States of America'),
(2, 'CHI', 'China'),
(3, 'KAZ', 'Kazakhstan'),
(4, 'JPN', 'Japan'),
(5, 'KOR', 'The Southern Republic of Korea');

-- --------------------------------------------------------

--
-- Структура таблицы `items`
--

CREATE TABLE `items` (
  `id` bigint(20) NOT NULL,
  `added_date` datetime DEFAULT NULL,
  `description` text DEFAULT NULL,
  `in_top_page` bit(1) DEFAULT NULL,
  `large_pic_url` text DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `small_pic_url` text DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `brands_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `items`
--

INSERT INTO `items` (`id`, `added_date`, `description`, `in_top_page`, `large_pic_url`, `name`, `price`, `small_pic_url`, `stars`, `brands_id`) VALUES
(1, '2020-11-11 11:55:57', 'Диагональ (дюйм): 6.2\r\nРазрешение (пикс): 3200х1440\r\nВстроенная память (Гб): 128\r\nФотокамера (Мп): 64 + 12 + 12 (тройная)\r\nПроцессор: Samsung Exynos 990', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/5/f/5f60490e6b0ccd4f550a8fc536fbb385bcf96000_215397_2as.jpg', 'Samsung Galaxy S20', 768.9, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/5/f/5f60490e6b0ccd4f550a8fc536fbb385bcf96000_215397_2as.jpg', 5, 1),
(2, '2020-11-11 12:00:41', 'Диагональ дисплея (дюйм): 6.1\r\n Разрешение дисплея (пикс): 3040x1440\r\n Встроенная память (Гб): 128\r\n Основная камера (Мп): 12 + 12 + 16 (тройная)\r\n Оптический зум: x2\r\n Процессор: Samsung Exynos 9820 Octa', b'1', 'https://www.ixbt.com/img/n1/news/2020/0/5/GalaxyS10LitePR_main_large_0_large.jpg', 'Samsung Galaxy S10', 657.99, 'https://www.ixbt.com/img/n1/news/2020/0/5/GalaxyS10LitePR_main_large_0_large.jpg', 4, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdq14oguaaf6en0obs2qy22i9p` (`countries_id`);

--
-- Индексы таблицы `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6ih61xej1h4qx5guk9u1u8pni` (`brands_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `brands`
--
ALTER TABLE `brands`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `items`
--
ALTER TABLE `items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `brands`
--
ALTER TABLE `brands`
  ADD CONSTRAINT `FKdq14oguaaf6en0obs2qy22i9p` FOREIGN KEY (`countries_id`) REFERENCES `countries` (`id`);

--
-- Ограничения внешнего ключа таблицы `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `FK6ih61xej1h4qx5guk9u1u8pni` FOREIGN KEY (`brands_id`) REFERENCES `brands` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
