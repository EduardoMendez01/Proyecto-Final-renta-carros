-- ELiminar base de datos
DROP DATABASE IF EXISTS car_rental;
-- Crea la base de datos
CREATE DATABASE car_rental;
-- Ingresar a la base de datos
USE car_rental;

-- CREAR TABLA VEHICULO
CREATE TABLE vehiculo(
	modelo VARCHAR(35) NOT NULL PRIMARY KEY,
    nombre VARCHAR(35) NOT NULL,
    marca VARCHAR(35) NOT NULL,
    transmision VARCHAR(35) NOT NULL,
    tarifa DOUBLE NOT NULL,
    año YEAR NOT NULL
);

-- CREAR TABLA CLIENTE
CREATE TABLE cliente(
	cliente_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(35) NOT NULL,
    apellidos VARCHAR(35) NOT NULL,
    numero_telefono CHAR(10) NOT NULL,
    contrasena VARCHAR(35) NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

-- CREAR TABLA RENTA
CREATE TABLE renta(
	renta_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_cliente VARCHAR(35) NOT NULL,
    apellidos_cliente VARCHAR(35) NOT NULL,
    numero_telefono_cliente CHAR(10) NOT NULL,
    tarifa_renta DOUBLE NOT NULL,
    fecha_inicial_renta DATE NOT NULL,
    fecha_final_renta DATE NOT NULL,
    modelo_vehiculo VARCHAR(35) NOT NULL,
    costo_final DOUBLE
);

-- CREAR TABLA CATEGORIA
CREATE TABLE categoria(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(35) NOT NULL,
    cantidad_llantas INT NOT NULL,
    uso VARCHAR(35) NOT NULL,
    peso_promedio INT NOT NULL
);

-- CREAR TABLA MARCA
CREATE TABLE marca(
	nombre VARCHAR(35) NOT NULL PRIMARY KEY,
	representante VARCHAR(35) NOT NULL,
    pais_origen VARCHAR(35) NOT NULL,
    numero_contacto CHAR(10),
    correo_contacto VARCHAR(35)
);

-- AGREGANDO CLIENTES DE PRUEBA
INSERT INTO cliente(nombre, apellidos, numero_telefono, contrasena, fecha_nacimiento) VALUE('Luis', 'Parada', '6121677249', 'Prueba01', '2001-12-24');
INSERT INTO cliente(nombre, apellidos, numero_telefono, contrasena, fecha_nacimiento) VALUE('Jose', 'Mendez', '6121231231', 'Fake01', '2003-11-24');

-- AGREGANDO CATEGORIA DE PRUEBA
INSERT INTO categoria (nombre, cantidad_llantas, uso, peso_promedio) VALUE('Familiar', 4, 'Recreativo', 1500);

select * from cliente;
select * from categoria;