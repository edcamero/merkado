--funcion auditoria al insertar productos

CREATE OR REPLACE  FUNCTION ftr_i_productos() RETURNS TRIGGER AS $$
BEGIN
INSERT INTO public._productos(
	prod_id, prod_nombre, prod_precio_compra, prod_precio_venta, prod_cantidad, prod_descripcion, prod_fecha_operacion,prod_operacion)
	VALUES (new.prod_id, new.prod_nombre, new.prod_precio_compra, new.prod_precio_venta, new.prod_cantidad,new.prod_descripcion, (select current_timestamp),'I');


RETURN new ;
END;
$$ LANGUAGE plpgsql;

--trigger de insertar productos
	
CREATE TRIGGER tr_i_productos
 AFTER INSERT ON productos
 FOR EACH ROW EXECUTE PROCEDURE ftr_i_productos();