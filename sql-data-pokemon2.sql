--
-- Databas: `pokemongo`
--

-- --------------------------------------------------------

--
-- Tabellstruktur `pokemon`
--

CREATE TABLE `pokemon` (
  `id` bigint(20) NOT NULL,
  `pokedexNumber` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `combatPower` int(11) DEFAULT NULL,
  `healthPoints` int(11) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `lat` float DEFAULT NULL,
  `lng` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `pokemon`
--

INSERT INTO `pokemon` (`id`, `pokedexNumber`, `name`, `combatPower`, `healthPoints`, `ownerId`, `lat`, `lng`) VALUES
(1, 134124, 'Pokemongo', 12, 234, 30, NULL, NULL),
(2, 134124, 'Pokemongo', 12, 234, 30, NULL, NULL),
(3, 134124, 'Pokemongo', 12, 234, 30, NULL, NULL);

-- --------------------------------------------------------

--
-- Tabellstruktur `pokemondata`
--

CREATE TABLE `pokemondata` (
  `id` bigint(20) NOT NULL,
  `pokedexNumber` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `imageUrl` varchar(2083) CHARACTER SET utf8 DEFAULT NULL,
  `spriteUrl` varchar(2083) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `pokemondata`
--

INSERT INTO `pokemondata` (`id`, `pokedexNumber`, `name`, `imageUrl`, `spriteUrl`) VALUES
(1, 1, 'Bulbasaur', 'http://www.serebii.net/pokemongo/pokemon/001.png\n\n', 'http://www.serebii.net/pokearth/sprites/em/001.png'),
(4, 4, 'Charmander', 'http://www.serebii.net/pokemongo/pokemon/004.png', 'http://www.serebii.net/pokearth/sprites/em/004.png'),
(7, 7, 'Squirtle', 'http://www.serebii.net/pokemongo/pokemon/007.png', 'http://www.serebii.net/pokearth/sprites/em/007.png'),
(10, 10, 'Caterpie', 'http://www.serebii.net/pokemongo/pokemon/010.png', 'http://www.serebii.net/pokearth/sprites/em/010.png');

-- --------------------------------------------------------

--
-- Tabellstruktur `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` text,
  `postTime` datetime DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `parentPostId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `post`
--

INSERT INTO `post` (`id`, `title`, `content`, `postTime`, `authorId`, `parentPostId`) VALUES
(1, 'Försvunnen Pokemon', 'Någon som sett min Pokemon? Blå, fulsnygg och lite tjock. Sågs senast vid Stenpiren.', '2016-09-14 08:53:07', 30, NULL),
(2, 'Mat till min Pokemon', 'Vad äter en Pokemon?', '2016-09-14 08:53:27', 30, NULL),
(3, 'Fredag', 'Nån som ska med på AW på fredag? Glöm inte att uppdatera appen till dess!', '2016-09-14 08:53:45', 30, NULL);

-- --------------------------------------------------------

--
-- Tabellstruktur `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tokenId` longtext,
  `team` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `user`
--

INSERT INTO `user` (`id`, `userName`, `email`, `tokenId`, `team`) VALUES
(30, 'Testgubbe', 'gubbe@gubbe.se', '', ''),
(32, 'Max Geissler', 'max.j.geissler@gmail.com', 'eyJhbGciOiJSUzI1NiIsImtpZCI6IjAwNDgwYzBmYjQ1Mjk3Njg4NjJiZjJlNWRlNzMzYmM3YjMyNDYzMzgifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXRfaGFzaCI6Imo0M28xeHI2SXVfTWlVUENxR1VuUlEiLCJhdWQiOiI4NTM5MDQ3MzU4NC1hZWFkNDJtdDRtaHRmNWQyYnJldnFkMGRhbjQ2czBkNy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMjk3NjY1ODAzMjc4NjYyMzk2NCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiI4NTM5MDQ3MzU4NC1hZWFkNDJtdDRtaHRmNWQyYnJldnFkMGRhbjQ2czBkNy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImVtYWlsIjoibWF4LmouZ2Vpc3NsZXJAZ21haWwuY29tIiwiaWF0IjoxNDc1NjU5MTI3LCJleHAiOjE0NzU2NjI3MjcsIm5hbWUiOiJNYXggR2Vpc3NsZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDUuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1jMTJIdHBpOGxuWS9BQUFBQUFBQUFBSS9BQUFBQUFBQUNiOC9CaEpZWUI3elFzcy9zOTYtYy9waG90by5qcGciLCJnaXZlbl9uYW1lIjoiTWF4IiwiZmFtaWx5X25hbWUiOiJHZWlzc2xlciIsImxvY2FsZSI6ImVuIn0.Shr6kLOpamisvn6cq8mlVGM0xPauHssULtmzgA-LxGQfp424cdBZPW-2K2x2BMeDQgaOOF8NxKV-6Iasxei2SzUG5PvBzYxN5ranVATlkBjatQ0DDpVCi9Erw-gpLBTcsJbF_w1LVoP5977VwO7BJKQtlJP3X5FMLlUHXs559t54sOvN1Geq-BeSjCHbjgfnZ6VzNG6MZmYdusONbUfbZSDss9PhxONWSJoYxG4i3BEh_4mXXir8WwTUYQ0njSE-E9VlD-01F-An0OIijLymiWAPFbIV6RcGUyHktymTmCPznIlRZMGmOOv_QJZXOFsPP78hWaQVJ4LD4eSypagokQ', 'none');

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `pokemon`
--
ALTER TABLE `pokemon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ownerId_idx` (`ownerId`);

--
-- Index för tabell `pokemondata`
--
ALTER TABLE `pokemondata`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `posts_id_uindex` (`id`);

--
-- Index för tabell `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `pokemon`
--
ALTER TABLE `pokemon`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT för tabell `pokemondata`
--
ALTER TABLE `pokemondata`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT för tabell `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT för tabell `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- Restriktioner för dumpade tabeller
--

--
-- Restriktioner för tabell `pokemon`
--
ALTER TABLE `pokemon`
  ADD CONSTRAINT `ownerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
