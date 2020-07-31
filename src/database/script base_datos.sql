DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS productos;
CREATE TABLE USUARIOS(
   user_id SERIAL,
   username VARCHAR(40) NOT NULL, 
   password VARCHAR(40) NOT NULL,
   CONSTRAINT usuarios_pk PRIMARY KEY(user_id)
);

INSERT INTO public.usuarios(username, password)VALUES ('admin', 'admin');

CREATE TABLE PRODUCTOS(
   prod_id SERIAL,
   prod_nombre VARCHAR(40) NOT NULL, 
   prod_precio_compra INT NOT NULL,
   prod_precio_venta INT NOT NULL,
   prod_cantidad INT NOT NULL,
   prod_descripcion VARCHAR(200),
   CONSTRAINT productos_pk PRIMARY KEY(prod_id)
);