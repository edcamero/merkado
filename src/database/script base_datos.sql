DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS tipo_productos;
DROP TABLE IF EXISTS _productos;

--DROP TABLE IF EXISTS _productos;

CREATE TABLE USUARIOS(
   user_id SERIAL,
   username VARCHAR(40) NOT NULL, 
   password VARCHAR(40) NOT NULL,
   CONSTRAINT usuarios_pk PRIMARY KEY(user_id)
);

INSERT INTO public.usuarios(username, password)VALUES ('admin', 'admin');


CREATE TABLE TIPO_PRODUCTOS(
tipr_id SERIAL,
tipr_nombre VARCHAR(40),
tipr_descripcion VARCHAR(200),
CONSTRAINT tipo_productos_pk PRIMARY KEY(tipr_id)
);

CREATE TABLE PRODUCTOS(
   prod_id SERIAL,
   prod_nombre VARCHAR(40) NOT NULL, 
   prod_precio_compra INT NOT NULL,
   prod_precio_venta INT NOT NULL,
   prod_cantidad INT NOT NULL,
   prod_descripcion VARCHAR(200),
   CONSTRAINT productos_pk PRIMARY KEY(prod_id)
);

------------------------------
--Auditorias
-------------------------------

--TABLA PARA AUDITORIA DE TIPO_PRODUCTOS

CREATE TABLE _TIPO_PRODUCTOS(
tipr_id SERIAL,
tipr_nombre VARCHAR(40),
tipr_descripcion VARCHAR(200),
tipr_fecha_operacion timestamp not null,
tipr_operacion VARCHAR(1) NOT NULL
);

--TABLA PARA AUDITORIA DE PRODUCTOS
CREATE TABLE _PRODUCTOS(
   prod_id numeric,
   prod_nombre VARCHAR(40) NOT NULL, 
   prod_precio_compra INT NOT NULL,
   prod_precio_venta INT NOT NULL,
   prod_cantidad INT NOT NULL,
   prod_descripcion VARCHAR(200),
   prod_fecha_operacion timestamp not null,
   prod_operacion VARCHAR(1) NOT NULL
);



