-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Ноя 12 2020 г., 10:56
-- Версия сервера: 10.4.14-MariaDB
-- Версия PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
(7, 'HP', 1),
(8, 'LG', 5);

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
(5, 'KOR', 'The Republic of Korea');

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
(2, '2020-11-12 08:48:26', 'Диагональ дисплея (дюйм): 6.1\r\n Разрешение дисплея (пикс): 3040x1440\r\n Встроенная память (Гб): 128\r\n Основная камера (Мп): 12 + 12 + 16 (тройная)\r\n Оптический зум: x2\r\n Процессор: Samsung Exynos 9820 Octa', b'0', 'https://www.ixbt.com/img/n1/news/2020/0/5/GalaxyS10LitePR_main_large_0_large.jpg', 'Samsung Galaxy S10', 657.99, 'https://www.ixbt.com/img/n1/news/2020/0/5/GalaxyS10LitePR_main_large_0_large.jpg', 4, 1),
(3, '2020-11-12 08:48:46', 'Диагональ дисплея, дюйм: 15.6\r\nМодель процессора: 3050U\r\nОбъём оперативной памяти, ГБ: 8\r\nОбъём HDD накопителя, ГБ: 1000\r\nОперационная система: DOS', b'0', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/f/d/fd6fc46240ee5fc0ad89dbc92c4a68b6c71e3a48_227228_12.jpg', 'Ноутбук Lenovo IdeaPad 3 Athlon', 159990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/8/5/85f650f2a3e4496f62d839a4a59de3132a12978d_227228_1.jpg', 3, 6),
(4, '2020-11-12 08:30:00', 'Серия: PlayStation 4 Pro\r\nОперативная память, ГБ: 8\r\nОбъем встроенной памяти, ГБ: 1000\r\nИнтерфейс: Bluetooth, Ethernet, AUX, USB 3.1, USB, Wi-Fi', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/7/6/7690550144eb188aa86eac2ec3e7bd186916d8b6_12088042029086.jpg', 'Sony Play Station 4 Pro', 207990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/c/b/cb0951bbd36ecd8d442f9de4f8eaf43a0407c2bf_12088039243806.jpg', 4, 2),
(5, '2020-11-12 08:31:56', 'Поддержка платформ: Android, iOS\r\nВибрация: Да\r\nДиагональ, дюйм: 1.1', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/1/6/166d8f673121142773ef5da2069e65dfb01efa6b_220274_1.jpg', 'Смарт Браслет Xiaomi Mi Band 5, Black', 19990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/1/6/166d8f673121142773ef5da2069e65dfb01efa6b_220274_1.jpg', 4, 4),
(6, '2020-11-12 08:34:04', 'Диагональ экрана, см: 32\" (81 см)\r\nРазрешение экрана: 1366x768 HD Ready\r\nПоддержка технологии \"SMART TV\": Да\r\nТехнология: LED\r\nТип экрана: Прямой\r\nФормат крепления VESA: 100х100 мм\r\nGear icon\r\n', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/9/9/9974aa85cb29c8048936d47670ae831321a77892_214409_2.jpg', 'Телевизор 32\" Xiaomi L32M5-5ARU LED HD Android Black', 86990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/0/b/0ba1059fff0ede03e5f69850e7d5272b6da10c35_214409_1.jpg', 3, 4),
(7, '2020-11-12 08:40:34', 'Тип стиральной машины: Воздушно-пузырькового типа\r\nМаксимальная загрузка белья, кг: 6.5\r\nМаксимальная скорость вращения при отжиме, об/мин: 1200\r\nТип мотора: Инверторный\r\nТип управления: Электронное\r\nОбработка паром: Да', b'0', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/c/6/c6b90cb32d7ecfb881929f94f7c4a4107c686c1e_215024_2.jpg', ' Стиральная машина LG F-12B8WDS7', 129990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/c/6/c6b90cb32d7ecfb881929f94f7c4a4107c686c1e_215024_2.jpg', 4, 8),
(8, '2020-11-12 08:48:07', 'Диагональ дисплея, дюйм: 13.3\r\nСерия процессора: Intel Core i3\r\nМодель процессора: 1000NG4\r\nОбъём оперативной памяти, ГБ: 8\r\nОбъём SSD накопителя, ГБ: 256\r\nОперационная система: Mac OS Catalina', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/8/4/8466e7b5d760d451094215bea25c0f91164efa75_217125_1m.jpg', 'Ноутбук Apple MacBook Air Retina', 561990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/8/4/8466e7b5d760d451094215bea25c0f91164efa75_217125_1m.jpg', 5, 5),
(9, '2020-11-12 08:51:29', 'Диагональ дисплея, дюйм: 6.1\r\nРазрешение дисплея: 2532x1170\r\nОперационная система: iOS 14\r\nОбъем оперативной памяти: 4\r\nОбъём встроенной памяти: 128\r\nКоличество SIM-карт: 1\r\nМодельный год: 2020', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/a/0/a03f2b954cb450951c791dcf1099c5bfa2b615a6_228613_1.jpg', 'Apple iPhone 12 128GB Black', 524990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/a/0/a03f2b954cb450951c791dcf1099c5bfa2b615a6_228613_1.jpg', 5, 5),
(10, '2020-11-12 08:53:54', 'Диагональ, дюйм: 9.7\r\nТип матрицы дисплея: IPS\r\nОперационная система: Chrome OS\r\nОбъем оперативной памяти, ГБ: 4\r\nОбъём встроенной памяти, ГБ: 32\r\nВремя работы, ч: 9', b'0', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/6/7/678fab68dc1d60cc9cd1c1941d5504d2716cfac0_12095938527262.jpg', 'Планшет Acer Chromebook TAB', 179990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/2/f/2fc95edb077f02ee331bc333d9ecefef0ed0602e_12095937478686.jpg', 4, 3),
(11, '2020-11-12 08:58:24', 'Диагональ, дюйм: 49\r\nТип матрицы: VA\r\nРазрешение экрана: 5120х1440\r\nФормат: 32:9\r\nВремя отклика, мс: 1\r\nЧастота обновления кадров, Гц: 240\r\nЯркость матрицы, кд/м²: 420\r\nИнтерфейс: 1x HDMI, 2x DP', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/7/6/769d431e59c61007d0f2fbde8b713ae9bd7ca0b0_227184_1qw.jpg', 'Монитор Игровой 49\" Samsung LC49G95TSSIXCI 5120x1440 32:9 VA 240ГЦ (HDMI+2DP) Curved White', 759990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/7/6/769d431e59c61007d0f2fbde8b713ae9bd7ca0b0_227184_1qw.jpg', 5, 1),
(12, '2020-11-12 09:02:50', 'Модель консоли: PlayStation 4\r\nТип манипулятора: Джойстик\r\nСовместимость: PlayStation\r\nТип подключения: Беспроводной\r\nОбратная связь: Да', b'0', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/6/2/623f8a2d1714389c8f6e7dcecbb9b1414ad4f5d3_41954_1zxc.jpg', 'Джойстик беспроводной PS4 Sony DualShock V2 (CUH-ZCT2E/Black)', 33590, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/6/2/623f8a2d1714389c8f6e7dcecbb9b1414ad4f5d3_41954_1zxc.jpg', 3, 2);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `items`
--
ALTER TABLE `items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

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
