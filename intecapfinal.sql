-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-05-2023 a las 11:11:31
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `intecapfinal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `codigo` int(255) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `nit` int(60) NOT NULL,
  `correo` varchar(80) NOT NULL,
  `genero` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `nit`, `correo`, `genero`) VALUES
(8, 'Pedro2', 1, 'Pedro', 'm');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallefactura`
--

CREATE TABLE `detallefactura` (
  `id` int(11) NOT NULL,
  `idFactura` int(255) NOT NULL,
  `codigoProducto` int(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `cantidad` int(255) DEFAULT NULL,
  `precio` double(255,0) DEFAULT NULL,
  `subtotal` double(255,0) DEFAULT NULL,
  `fecha` date DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detallefactura`
--

INSERT INTO `detallefactura` (`id`, `idFactura`, `codigoProducto`, `nombre`, `cantidad`, `precio`, `subtotal`, `fecha`) VALUES
(25, 1, 1, 'Cama', 1, 10, 10, '2023-05-04'),
(26, 1, 1, 'edin123', 2, 10, 20, '2023-05-04'),
(27, 2, 1, 'Cama', 1, 10, 10, '2023-05-04'),
(28, 2, 1, 'edin123', 2, 10, 20, '2023-05-04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generalfactura`
--

CREATE TABLE `generalfactura` (
  `idFactura` int(255) NOT NULL,
  `codigoCliente` int(255) NOT NULL,
  `codigoVendedor` int(255) NOT NULL,
  `fecha` date DEFAULT current_timestamp(6),
  `total` double(255,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `generalfactura`
--

INSERT INTO `generalfactura` (`idFactura`, `codigoCliente`, `codigoVendedor`, `fecha`, `total`) VALUES
(1, 8, 4, '2023-05-04', 30),
(2, 8, 4, '2023-05-04', 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codigo` int(255) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(60) NOT NULL,
  `cantidad` int(255) NOT NULL,
  `precio` double(255,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codigo`, `nombre`, `descripcion`, `cantidad`, `precio`) VALUES
(1, 'Cama', 'Mueble', 1, 10),
(2, 'edin123', 'cama', 1, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `codigo` int(255) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `correo` varchar(60) NOT NULL,
  `telefono` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`codigo`, `nombre`, `direccion`, `correo`, `telefono`) VALUES
(1, 'casa1231', '10calle', '@gmail-com', '123412');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `codigo` int(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `caja` int(255) NOT NULL,
  `ventas` int(255) NOT NULL,
  `genero` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`codigo`, `nombre`, `caja`, `ventas`, `genero`, `password`, `rol`) VALUES
(1, 'admin', 0, 0, 'M', 'admin', 1),
(3, 'edin', 1, 0, 'm', 'edin', 0),
(4, 'jose2', 2, 0, 'm', 'jose', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo`) USING BTREE;

--
-- Indices de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD PRIMARY KEY (`id`,`idFactura`) USING BTREE,
  ADD KEY `idFactura` (`idFactura`);

--
-- Indices de la tabla `generalfactura`
--
ALTER TABLE `generalfactura`
  ADD PRIMARY KEY (`idFactura`,`codigoCliente`,`codigoVendedor`) USING BTREE,
  ADD KEY `idFactura` (`idFactura`),
  ADD KEY `Vendedores` (`codigoVendedor`),
  ADD KEY `Cliente` (`codigoCliente`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigo`) USING BTREE;

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codigo` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `codigo` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `codigo` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  MODIFY `codigo` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD CONSTRAINT `Factura` FOREIGN KEY (`idFactura`) REFERENCES `generalfactura` (`idFactura`);

--
-- Filtros para la tabla `generalfactura`
--
ALTER TABLE `generalfactura`
  ADD CONSTRAINT `Cliente` FOREIGN KEY (`codigoCliente`) REFERENCES `cliente` (`codigo`),
  ADD CONSTRAINT `Vendedores` FOREIGN KEY (`codigoVendedor`) REFERENCES `vendedores` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
