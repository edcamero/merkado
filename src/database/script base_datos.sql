CREATE TABLE USUARIOS(
   ID SERIAL,
   username VARCHAR(40) NOT NULL, 
   password VARCHAR(40) NOT NULL 
);

INSERT INTO public.usuarios(username, password)VALUES ('admin', 'admin');

CREATE TABLE PRODUCTOS(
   ID SERIAL,
   nombre VARCHAR(40) NOT NULL, 
   precio INT NOT NULL,
   cantidad INT NOT NULL
);