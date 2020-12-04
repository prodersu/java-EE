-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Дек 04 2020 г., 08:14
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
-- База данных: `all_in_shop`
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
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `logo_url` text DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `categories`
--

INSERT INTO `categories` (`id`, `logo_url`, `name`) VALUES
(1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Smartphone_icon_-_Noun_Project_283536.svg/1024px-Smartphone_icon_-_Noun_Project_283536.svg.png', 'Smartphones, Gadgets'),
(2, 'https://icons-for-free.com/iconfiles/png/512/television+tv+icon-1320086462225673727.png', 'TV, Audio, Video'),
(3, 'data:image/png;base64,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', 'Everything for gamers'),
(5, 'data:image/png;base64,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', 'PC, Laptops'),
(6, 'https://icons-for-free.com/iconfiles/png/512/market+basket+shopping+basket+store+icon+icon-1320085906374523217.png', 'Other');

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
(1, '2020-11-11 11:55:57', 'Диагональ (дюйм): 6.2\r\nРазрешение (пикс): 3200х1440\r\nВстроенная память (Гб): 128\r\nФотокамера (Мп): 64 + 12 + 12 (тройная)\r\nПроцессор: Samsung Exynos 990', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/5/f/5f60490e6b0ccd4f550a8fc536fbb385bcf96000_215397_2as.jpg', 'Samsung Galaxy S20', 279990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/5/f/5f60490e6b0ccd4f550a8fc536fbb385bcf96000_215397_2as.jpg', 5, 1),
(2, '2020-11-12 08:48:26', 'Диагональ дисплея (дюйм): 6.1\r\n Разрешение дисплея (пикс): 3040x1440\r\n Встроенная память (Гб): 128\r\n Основная камера (Мп): 12 + 12 + 16 (тройная)\r\n Оптический зум: x2\r\n Процессор: Samsung Exynos 9820 Octa', b'0', 'https://www.ixbt.com/img/n1/news/2020/0/5/GalaxyS10LitePR_main_large_0_large.jpg', 'Samsung Galaxy S10', 229900, 'https://www.ixbt.com/img/n1/news/2020/0/5/GalaxyS10LitePR_main_large_0_large.jpg', 4, 1),
(3, '2020-11-12 08:48:46', 'Диагональ дисплея, дюйм: 15.6\r\nМодель процессора: 3050U\r\nОбъём оперативной памяти, ГБ: 8\r\nОбъём HDD накопителя, ГБ: 1000\r\nОперационная система: DOS', b'0', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/f/d/fd6fc46240ee5fc0ad89dbc92c4a68b6c71e3a48_227228_12.jpg', 'Ноутбук Lenovo IdeaPad 3 Athlon', 159990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/8/5/85f650f2a3e4496f62d839a4a59de3132a12978d_227228_1.jpg', 3, 6),
(4, '2020-11-12 08:30:00', 'Серия: PlayStation 4 Pro\r\nОперативная память, ГБ: 8\r\nОбъем встроенной памяти, ГБ: 1000\r\nИнтерфейс: Bluetooth, Ethernet, AUX, USB 3.1, USB, Wi-Fi', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/7/6/7690550144eb188aa86eac2ec3e7bd186916d8b6_12088042029086.jpg', 'Sony Play Station 4 Pro', 207990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/c/b/cb0951bbd36ecd8d442f9de4f8eaf43a0407c2bf_12088039243806.jpg', 4, 2),
(5, '2020-11-12 08:31:56', 'Поддержка платформ: Android, iOS\r\nВибрация: Да\r\nДиагональ, дюйм: 1.1', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/1/6/166d8f673121142773ef5da2069e65dfb01efa6b_220274_1.jpg', 'Смарт Браслет Xiaomi Mi Band 5, Black', 19990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/1/6/166d8f673121142773ef5da2069e65dfb01efa6b_220274_1.jpg', 4, 4),
(6, '2020-11-12 08:34:04', 'Диагональ экрана, см: 32\" (81 см)\r\nРазрешение экрана: 1366x768 HD Ready\r\nПоддержка технологии \"SMART TV\": Да\r\nТехнология: LED\r\nТип экрана: Прямой\r\nФормат крепления VESA: 100х100 мм\r\nGear icon\r\n', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/9/9/9974aa85cb29c8048936d47670ae831321a77892_214409_2.jpg', 'Телевизор 32\" Xiaomi L32M5-5ARU LED HD Android Black', 86990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/0/b/0ba1059fff0ede03e5f69850e7d5272b6da10c35_214409_1.jpg', 3, 4),
(7, '2020-11-12 08:40:34', 'Тип стиральной машины: Воздушно-пузырькового типа\r\nМаксимальная загрузка белья, кг: 6.5\r\nМаксимальная скорость вращения при отжиме, об/мин: 1200\r\nТип мотора: Инверторный\r\nТип управления: Электронное\r\nОбработка паром: Да', b'0', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/c/6/c6b90cb32d7ecfb881929f94f7c4a4107c686c1e_215024_2.jpg', ' Стиральная машина LG F-12B8WDS7', 129990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/c/6/c6b90cb32d7ecfb881929f94f7c4a4107c686c1e_215024_2.jpg', 4, 8),
(8, '2020-11-12 08:48:07', 'Диагональ дисплея, дюйм: 13.3\r\nСерия процессора: Intel Core i3\r\nМодель процессора: 1000NG4\r\nОбъём оперативной памяти, ГБ: 8\r\nОбъём SSD накопителя, ГБ: 256\r\nОперационная система: Mac OS Catalina', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/8/4/8466e7b5d760d451094215bea25c0f91164efa75_217125_1m.jpg', 'Ноутбук Apple MacBook Air Retina', 561990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/8/4/8466e7b5d760d451094215bea25c0f91164efa75_217125_1m.jpg', 5, 5),
(9, '2020-11-12 08:51:29', 'Диагональ дисплея, дюйм: 6.1\r\nРазрешение дисплея: 2532x1170\r\nОперационная система: iOS 14\r\nОбъем оперативной памяти: 4\r\nОбъём встроенной памяти: 128\r\nКоличество SIM-карт: 1\r\nМодельный год: 2020', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/a/0/a03f2b954cb450951c791dcf1099c5bfa2b615a6_228613_1.jpg', 'Apple iPhone 12 128GB Black', 524990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/a/0/a03f2b954cb450951c791dcf1099c5bfa2b615a6_228613_1.jpg', 5, 5),
(10, '2020-11-12 08:53:54', 'Диагональ, дюйм: 9.7\r\nТип матрицы дисплея: IPS\r\nОперационная система: Chrome OS\r\nОбъем оперативной памяти, ГБ: 4\r\nОбъём встроенной памяти, ГБ: 32\r\nВремя работы, ч: 9', b'0', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/6/7/678fab68dc1d60cc9cd1c1941d5504d2716cfac0_12095938527262.jpg', 'Планшет Acer Chromebook TAB', 179990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/2/f/2fc95edb077f02ee331bc333d9ecefef0ed0602e_12095937478686.jpg', 4, 3),
(11, '2020-11-12 08:58:24', 'Диагональ, дюйм: 49\r\nТип матрицы: VA\r\nРазрешение экрана: 5120х1440\r\nФормат: 32:9\r\nВремя отклика, мс: 1\r\nЧастота обновления кадров, Гц: 240\r\nЯркость матрицы, кд/м²: 420\r\nИнтерфейс: 1x HDMI, 2x DP', b'1', 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/7/6/769d431e59c61007d0f2fbde8b713ae9bd7ca0b0_227184_1qw.jpg', 'Монитор Игровой 49\" Samsung LC49G95TSSIXCI 5120x1440 32:9 VA 240ГЦ (HDMI+2DP) Curved White', 759990, 'https://www.technodom.kz/media/catalog/product/cache/1366e688ed42c99cd6439df4031862c6/7/6/769d431e59c61007d0f2fbde8b713ae9bd7ca0b0_227184_1qw.jpg', 5, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `items_categories`
--

CREATE TABLE `items_categories` (
  `items_id` bigint(20) NOT NULL,
  `categories_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `items_categories`
--

INSERT INTO `items_categories` (`items_id`, `categories_id`) VALUES
(2, 1),
(1, 1),
(6, 2),
(3, 5),
(4, 3),
(5, 1),
(8, 5),
(9, 1),
(10, 1),
(10, 5),
(11, 5),
(11, 3),
(7, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `role`, `desc`) VALUES
(1, 'ROLE_ADMIN', 'Administrator role'),
(2, 'ROLE_USER', 'Simple user role'),
(3, 'ROLE_MODERATOR', 'Moderator role');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `email`, `full_name`, `password`) VALUES
(1, 'admin@mail.ru', 'admin', '$2y$12$OjGhkaq.jMvbfXh2j0BL8e.S1j2S4FlHR23ZlkApNAq53K.sNGEpW'),
(2, 'user1@mail.ru', 'user1', '$2y$12$Psi4R2sND23DKrgv2XcTHOR3c.SmIx/0RIfVyDcIOets0bSbrKdiq'),
(3, 'user2@mail.ru', 'user2', '$2a$10$UKz0BbBAfQuGWM8Og8ADvesukFekjViym1jlcVZiHUUgjtrQ8Nzra'),
(5, 'moderator@mail.ru', 'Moderator', '$2a$10$zZWW86jEyhg2Btx82LWhceGJ4vnFvlR4M1m4Ja9SazFbBzRKLL9.e');

-- --------------------------------------------------------

--
-- Структура таблицы `users_roles`
--

CREATE TABLE `users_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `users_roles`
--

INSERT INTO `users_roles` (`users_id`, `roles_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 2),
(5, 2),
(5, 3);

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
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

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
-- Индексы таблицы `items_categories`
--
ALTER TABLE `items_categories`
  ADD KEY `FKyxf17r34k73ewrqm0icx1xar` (`categories_id`),
  ADD KEY `FK16oha2jb2sw0ghqe659n752bh` (`items_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
  ADD KEY `FKml90kef4w2jy7oxyqv742tsfc` (`users_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `brands`
--
ALTER TABLE `brands`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `items`
--
ALTER TABLE `items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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

--
-- Ограничения внешнего ключа таблицы `items_categories`
--
ALTER TABLE `items_categories`
  ADD CONSTRAINT `FK16oha2jb2sw0ghqe659n752bh` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`),
  ADD CONSTRAINT `FKyxf17r34k73ewrqm0icx1xar` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`);

--
-- Ограничения внешнего ключа таблицы `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKml90kef4w2jy7oxyqv742tsfc` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
