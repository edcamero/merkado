
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS tipo_usuarios;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS tipo_productos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS empleados;
DROP TABLE IF EXISTS personas;
DROP TABLE IF EXISTS cargos;
DROP TABLE IF EXISTS _usuarios;
DROP TABLE IF EXISTS _tipo_usuarios;
DROP TABLE IF EXISTS _tipo_productos;
DROP TABLE IF EXISTS _productos;
DROP TABLE IF EXISTS _tipo_productos;
DROP TABLE IF EXISTS _clientes;
DROP TABLE IF EXISTS _empleados;
DROP TABLE IF EXISTS _personas;
DROP TABLE IF EXISTS _cargos;




CREATE TABLE PERSONAS(
    pers_id SERIAL,
    pers_nombre VARCHAR(40) NOT NULL,
    pers_apellido VARCHAR(40) NOT NULL,
    pers_documento VARCHAR(10) NOT NULL,
    pers_telefono VARCHAR(10) NOT NULL,
    pers_direccion VARCHAR(30) NOT NULL,
    CONSTRAINT persona_pk PRIMARY KEY(pers_id)
);

CREATE TABLE CLIENTES (
    clie_id SERIAL,
    pers_id integer,
    estado BOOLEAN,
    CONSTRAINT cliente_pk PRIMARY KEY(clie_id),
    CONSTRAINT clie_pers_fk foreign key(pers_id) references PERSONAS(pers_id)
);

CREATE TABLE TIPO_USUARIOS(
    tius_id SERIAL,
    tius_nombre VARCHAR(40) NOT NULL,
    tius_descripcion VARCHAR(100) NOT NULL,
    CONSTRAINT tipo_usuario_pk PRIMARY KEY(tius_id)
);

INSERT INTO public.tipo_usuarios(tius_nombre, tius_descripcion) VALUES ( 'admin', 'administrador del sistema'), ('cajero','cajero de turno en el almacen');

CREATE TABLE USUARIOS(
   user_id SERIAL,
   username VARCHAR(40) NOT NULL UNIQUE, 
   password VARCHAR(40) NOT NULL,
   tius_id INT NOT NULL,
   user_activo boolean not null default TRUE,
   CONSTRAINT usuarios_pk PRIMARY KEY(user_id),
   CONSTRAINT user_tius_fk foreign key(tius_id) references TIPO_USUARIOS(tius_id)
);

INSERT INTO public.usuarios(username, password,tius_id)VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3',1);

CREATE TABLE CARGOS(
    carg_id SERIAL,
    carg_nombre VARCHAR(40) NOT NULL UNIQUE,
    carg_descripcion VARCHAR(200),
    carg_salario_mensual INT NOT NULL,
    carg_estado boolean not null,
    CONSTRAINT cargos_pk PRIMARY KEY(carg_id)
);
	
CREATE TABLE EMPLEADOS(
    empl_id SERIAL,
    empl_fecha_contratacion date NOT NULL,
    pers_id integer,
    carg_id integer,
    CONSTRAINT empleados_pk PRIMARY KEY(empl_id),
    CONSTRAINT empl_pers_fk foreign key(pers_id) references PERSONAS(pers_id),
    CONSTRAINT empl_carg_fk foreign key(carg_id) references CARGOS(carg_id)
);
	
	
CREATE TABLE TIPO_PRODUCTOS(
    tipr_id SERIAL,
    tipr_nombre VARCHAR(40) UNIQUE,
    tipr_descripcion VARCHAR(200),
    CONSTRAINT tipo_productos_pk PRIMARY KEY(tipr_id)
);

INSERT INTO public.tipo_productos(tipr_nombre,tipr_descripcion) VALUES ('LACTEOS','LECHES YOGURT'),('ASEO','ARTICULOS PARA EL ASEO EN EL HOGAR');

CREATE TABLE PRODUCTOS(
   prod_id SERIAL,
   prod_nombre VARCHAR(40) NOT NULL UNIQUE, 
   prod_precio_compra INT NOT NULL,
   prod_precio_venta INT NOT NULL,
   prod_cantidad INT NOT NULL,
   prod_descripcion VARCHAR(200),
   tipr_id INT NOT NULL,
   CONSTRAINT productos_pk PRIMARY KEY(prod_id),
   CONSTRAINT prod_tipr_fk foreign key(tipr_id) references TIPO_PRODUCTOS(tipr_id)
);

CREATE TABLE PROVEEDOR(
	prov_id SERIAL,
	prov_nombre VARCHAR(40) NOT NULL UNIQUE,
	prov_nit VARCHAR(12) NOT NULL,
	prov_direccion VARCHAR(50) NOT NULL,
	prov_telefono VARCHAR(10) NOT NULL,
	CONSTRAINT proveedor_pk PRIMARY KEY(prov_id),
	
	);
	
CREATE TABLE PRODUCTO_PROVEEDOR(
	prpr_id serial,
	prod_id numeric,
	prov_id numeric,
	CONSTRAINT producto_proveedor_pk PRIMARY KEY(prpr_id),
	CONSTRAINT prpr_prod_fk foreign key(prod_id) references PRODUCTOS(prod_id),
	CONSTRAINT prpr_prov_fk foreign key(prov_id) references PRODUCTOS(prov_id),
);	


------------------------------
--Auditorias
-------------------------------}

--TABLA PARA AUDITORIA DE TIPO_PRODUCTOS
CREATE TABLE _TIPO_USUARIOS(
    tius_id numeric,
    tius_nombre VARCHAR(40) NOT NULL,
    tius_descripcion VARCHAR(100) NOT NULL,
    tius_fecha_operacion timestamp not null,
    tius_operacion VARCHAR(1) NOT NULL
);

CREATE TABLE _USUARIOS(
    user_id numeric,
    username VARCHAR(40) NOT NULL, 
    password VARCHAR(40) NOT NULL,
    tius_id INT NOT NULL,	
    user_activo boolean not null,
    user_fecha_operacion timestamp not null,
    user_operacion VARCHAR(1) NOT NULL
);

--TABLA PARA AUDITORIA DE TIPO_PRODUCTOS

CREATE TABLE _TIPO_PRODUCTOS(
    tipr_id integer,
    tipr_nombre VARCHAR(40),
    tipr_descripcion VARCHAR(200),
    tipr_fecha_operacion timestamp not null,
    tipr_operacion VARCHAR(1) NOT NULL
);

--TABLA PARA AUDITORIA DE PRODUCTOS
CREATE TABLE _PRODUCTOS(
   prod_id integer,
   prod_nombre VARCHAR(40) NOT NULL UNIQUE, 
   prod_precio_compra INT NOT NULL,
   prod_precio_venta INT NOT NULL,
   prod_cantidad INT NOT NULL,
   prod_descripcion VARCHAR(200),
   tipr_id INT NOT NULL,
   prod_fecha_operacion timestamp not null,
   prod_operacion VARCHAR(1) NOT NULL
);

--TABLA  PARA AUDITORIA DE CARGOS
CREATE TABLE _CARGOS(
    carg_id integer,
    carg_nombre VARCHAR(40) NOT NULL UNIQUE,
    carg_descripcion VARCHAR(200),
    carg_estado boolean,
    carg_salario_mensual INT NOT NULL,
    carg_fecha_operacion timestamp not null,
    carg_operacion VARCHAR(1) NOT NULL
);
	
	
--TABLA PARA AUDITORIA DE PERSONAS

CREATE TABLE _PERSONAS(
    pers_id integer,
    pers_nombre VARCHAR(40) NOT NULL,
    pers_apellido VARCHAR(40) NOT NULL,
    pers_documento VARCHAR(10) NOT NULL,
    pers_telefono VARCHAR(10) NOT NULL,
    pers_direccion VARCHAR(30) NOT NULL,
	pers_fecha_operacion timestamp not null,
    pers_operacion VARCHAR(1) NOT NULL
);

--TABLA PARA AUDITORIA DE CLIENTES

CREATE TABLE _CLIENTES (
    clie_id integer,
    fk_pers_id SERIAL,
    estado BOOLEAN,
	pers_fecha_operacion timestamp not null,
    pers_operacion VARCHAR(1) NOT NULL
);

--TABLA PARA AUDITORIA DE EMPLEADOS
CREATE TABLE _EMPLEADOS(
    empl_id integer,
    empl_fecha_contratacion date NOT NULL,
    pers_id numeric,
    carg_id numeric,
    empl_fecha_operacion timestamp not null,
    empl_operacion VARCHAR(1) NOT NULL
);
	
CREATE TABLE _PROVEEDOR(
	prov_id integer,
	prov_nombre VARCHAR(40) NOT NULL UNIQUE,
	prov_nit VARCHAR(12) NOT NULL,
	prov_direccion VARCHAR(30) NOT NULL,
	prov_telefono VARCHAR(10) NOT NULL,
	prov_fecha_operacion timestamp not null,
        prov_operacion VARCHAR(1) NOT NULL
	
	);
	
CREATE TABLE _PRODUCTO_PROVEEDOR(
	prpr_id integer,
	prod_id numeric,
	prov_id numeric,
	prpr_fecha_operacion timestamp not null,
        prpr_operacion VARCHAR(1) NOT NULL
);	
