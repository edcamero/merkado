DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS productos;
CREATE TABLE USUARIOS(
   ID SERIAL,
   username VARCHAR(40) NOT NULL, 
   password VARCHAR(40) NOT NULL 
);

INSERT INTO public.usuarios(username, password)VALUES ('admin', 'admin');

CREATE TABLE PRODUCTOS(
   prod_id SERIAL,
   prod_nombre VARCHAR(40) NOT NULL, 
   prod_precio_compra INT NOT NULL,
   prod_precio_venta INT NOT NULL,
   prod_cantidad INT NOT NULL,
   prod_descripcion VARCHAR(200)
);