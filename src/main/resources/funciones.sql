-- FUNCION PARA REALIZAR ALTA, BAJA O MODIFICACIÓN SOBRE LA TABLA CUSTOMERS

create or replace function fn_abm_cliente(
	p_accion text,
	p_id text,
	p_companyname text,
	p_contactname text,
	p_contacttitle text,
	p_address text,
	p_city text,
	p_region text,
	p_postalcode text,
	p_country text,
	p_phone text,
	p_fax text
) returns text as $$

begin
		
if (p_accion is null) then 
    raise exception 'ERROR: No se ingreso una accion a realizar';
elseif (p_accion not in ('A', 'B', 'M')) then
    raise exception 'ERROR: La accion ingresada no es valida';
end if;

-- bloque para la alta
if (p_accion = 'A') then

    if (p_id is null or p_id !~ '^[A-Za-z]+$' or length(p_id) != 5) then 
        raise exception 'ERROR: El customerid ingresado no es valido';
    elseif (exists (select 1 from customers where customerid = p_id)) then
        raise exception 'ERROR: El customerid ya se encuentra registrado';
    end if;

    if p_companyname is not null and length(trim(p_companyname)) > 0 then
        if length(p_companyname) > 40 then
            raise exception 'ERROR: El nombre de la empresa no puede superar los 40 caracteres.';
        end if;
    end if;
    
    if p_contactname is not null and length(trim(p_contactname)) > 0 then
        if length(p_contactname) > 30 then
            raise exception 'ERROR: El nombre de contacto no puede superar los 30 caracteres.';
        elseif (p_contactname !~ '^[A-Za-z ]+$') then
            raise exception 'ERROR: El nombre de contacto solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_contacttitle is not null and length(trim(p_contacttitle)) > 0 then
        if length(p_contacttitle) > 30 then
            raise exception 'ERROR: El titulo de contacto no puede superar los 30 caracteres.';
        end if;
    end if;
    
    if p_address is not null and length(trim(p_address)) > 0 then
        if length(p_address) > 60 then
            raise exception 'ERROR: La direccion no puede superar los 60 caracteres.';
        end if;
    end if;
    
    if p_city is not null and length(trim(p_city)) > 0 then
        if length(p_city) > 15 then
            raise exception 'ERROR: La ciudad no puede superar los 15 caracteres.';
        end if;
    end if;
    
    if p_region is not null and length(trim(p_region)) > 0 then
        if length(p_region) > 15 then
            raise exception 'ERROR: La region no puede superar los 15 caracteres.';
        elseif (p_region !~ '^[A-Za-z ]+$') then
            raise exception 'ERROR: La region solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_postalcode is not null and length(trim(p_postalcode)) > 0 then
        if length(p_postalcode) > 10 then
            raise exception 'ERROR: El codigo postal no puede superar los 10 caracteres.';
        end if;
    end if;
    
    if p_country is not null and length(trim(p_country)) > 0 then
        if length(p_country) > 15 then
            raise exception 'ERROR: El pais no puede superar los 15 caracteres.';
        elseif (p_country !~ '^[A-Za-z ]+$') then
            raise exception 'ERROR: El pais solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_phone is not null and length(trim(p_phone)) > 0 then
        if length(p_phone) > 24 then
            raise exception 'ERROR: El telefono no puede superar los 24 caracteres.';
        end if;
    end if;
    
    if p_fax is not null and length(trim(p_fax)) > 0 then
        if length(p_fax) > 24 then
            raise exception 'ERROR: El fax no puede superar los 24 caracteres.';
        end if;
    end if;

	insert into customers (
    customerid, companyname, contactname, contacttitle, 
    address, city, region, postalcode, country, phone, fax
	) 
	values (
    p_id, p_companyname, p_contactname, p_contacttitle, 
    p_address, p_city, p_region, p_postalcode, p_country, p_phone, p_fax
	);

	return 'EXITO: El registro fue creado correctamente.';

elseif(p_accion = 'B') then
	
	if (p_id is null or p_id !~ '^[A-Za-z]+$' or length(p_id) != 5) then 
        raise exception 'ERROR: El customerid ingresado no es valido';
    end if;

	if (not exists (select 1 from customers where customerid = p_id)) then
		raise exception 'ERROR: El registro no existe.';
	end if;
	
	delete from customers where customerid = p_id;

	return 'EXITO: El registro fue eliminado correctamente.';

else 
	
	if (p_id is null or p_id !~ '^[A-Za-z]+$' or length(p_id) != 5) then 
        raise exception 'ERROR: El customerid ingresado no es valido';
	elseif (not exists (select 1 from customers where customerid = p_id)) then
    	raise exception 'ERROR: El cliente no se encuentra registrado.';
    end if;

    if p_companyname is not null and length(trim(p_companyname)) > 0 then
        if length(p_companyname) > 40 then
            raise exception 'ERROR: El nombre de la empresa no puede superar los 40 caracteres.';
        end if;
    end if;
    
    if p_contactname is not null and length(trim(p_contactname)) > 0 then
        if length(p_contactname) > 30 then
            raise exception 'ERROR: El nombre de contacto no puede superar los 30 caracteres.';
        elseif (p_contactname !~ '^[A-Za-z ]+$') then
            raise exception 'ERROR: El nombre de contacto solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_contacttitle is not null and length(trim(p_contacttitle)) > 0 then
        if length(p_contacttitle) > 30 then
            raise exception 'ERROR: El titulo de contacto no puede superar los 30 caracteres.';
        end if;
    end if;
    
    if p_address is not null and length(trim(p_address)) > 0 then
        if length(p_address) > 60 then
            raise exception 'ERROR: La direccion no puede superar los 60 caracteres.';
        end if;
    end if;
    
    if p_city is not null and length(trim(p_city)) > 0 then
        if length(p_city) > 15 then
            raise exception 'ERROR: La ciudad no puede superar los 15 caracteres.';
        end if;
    end if;
    
    if p_region is not null and length(trim(p_region)) > 0 then
        if length(p_region) > 15 then
            raise exception 'ERROR: La region no puede superar los 15 caracteres.';
        elseif (p_region !~ '^[A-Za-z ]+$') then
            raise exception 'ERROR: La region solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_postalcode is not null and length(trim(p_postalcode)) > 0 then
        if length(p_postalcode) > 10 then
            raise exception 'ERROR: El codigo postal no puede superar los 10 caracteres.';
        end if;
    end if;
    
    if p_country is not null and length(trim(p_country)) > 0 then
        if length(p_country) > 15 then
            raise exception 'ERROR: El pais no puede superar los 15 caracteres.';
        elseif (p_country !~ '^[A-Za-z ]+$') then
            raise exception 'ERROR: El pais solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_phone is not null and length(trim(p_phone)) > 0 then
        if length(p_phone) > 24 then
            raise exception 'ERROR: El telefono no puede superar los 24 caracteres.';
        end if;
    end if;
    
    if p_fax is not null and length(trim(p_fax)) > 0 then
        if length(p_fax) > 24 then
            raise exception 'ERROR: El fax no puede superar los 24 caracteres.';
        end if;
    end if;
	
	update customers set 
    companyname = coalesce(nullif(trim(p_companyname), ''),companyname), 
    contactname = coalesce(nullif(trim(p_contactname), ''),contactname), 
    contacttitle = coalesce(nullif(trim(p_contacttitle), ''),contacttitle), 
    address = coalesce(nullif(trim(p_address), ''),address), 
    city = coalesce(nullif(trim(p_city), ''),city), 
    region = coalesce(nullif(trim(p_region), ''),region), 
    postalcode = coalesce(nullif(trim(p_postalcode), ''),postalcode), 
    country = coalesce(nullif(trim(p_country), ''),country), 
    phone = coalesce(nullif(trim(p_phone), ''),phone), 
    fax = coalesce(nullif(trim(p_fax), ''),fax)
    where customerid = p_id;

	return 'EXITO: El registro fue modificado correctamente.';
--nullif me devuelve null cuando le paso ' ' al modificar si el campo vino vacio
end if;

end;

$$ language plpgsql;

-- FUNCION PARA CONSULTAR LA VISTA DE ORDENES

-- Lo formatee asi porque DBeaver hace cualquier cosa y era dificil de leer...
create 
or 
replace
function 
fn_consulta_ordenes(p_cliente text, p_empleado text) 
returns 
table 
(
cliente_id varchar(5),
cliente_contacto varchar(30),
orden_id integer,
orden_fecha date,
orden_cantidad_items bigint,
orden_importe_total numeric,
empleado_id integer,
empleado_nombre varchar
) as $$

declare 
cliente text;
empleado text;

begin
cliente := trim(p_cliente);
empleado := trim(p_empleado);

return 
query 
select
vo.cliente_id,
vo.cliente_contacto,
vo.orden_id,
vo.orden_fecha,
vo.orden_cantidad_items,
vo.orden_importe_total,
vo.empleado_id,
vo.empleado_nombre::varchar -- Hay que castear sino da un error, al parecer al concatenar los nombres del empleado no devuelve varchar aunque ambas columnas lo sean.
from
vista_ordenes vo
where
coalesce(vo.cliente_contacto, '') ilike '%' || cliente || '%'
and 
coalesce(vo.empleado_nombre, '') ilike '%' || empleado || '%' 
order by 
vo.orden_id ;

return;
end;

$$ language plpgsql;

-- Comprobar que filtra adecuadamente
select * from fn_consulta_ordenes('Maria', 'Suya');

-- FUNCION PARA CONSULTAR LA VISTA CLIENTES

-- Lo formatee asi porque DBeaver hace cualquier cosa y era dificil de leer...
create 
or 
replace
function 
fn_consulta_clientes(p_nombre text, p_lugar text) 
returns 
table 
(
id varchar(5),
nombre_de_compania varchar(40),
nombre_de_contacto varchar(30),
pais varchar(15),
ciudad varchar(15),
direccion varchar(60),
ordenes_registradas bigint,
total_gastado numeric
) as $$

declare
nombre text;
lugar text;

begin	
nombre := trim(p_nombre);
lugar := trim(p_lugar);

return 
query
select
vc.id,
vc.nombre_de_compania,
vc.nombre_de_contacto,
vc.pais,
vc.ciudad,
vc.direccion,
vc.ordenes_registradas,
vc.total_gastado
from
vista_clientes vc
where
(
coalesce(vc.nombre_de_compania, '') ilike '%' || nombre || '%'
or 
coalesce(vc.nombre_de_contacto, '') ilike '%' || nombre || '%'
)
and
(
coalesce(vc.pais, '') ilike '%' || lugar || '%'
or 
coalesce(vc.ciudad, '') ilike '%' || lugar || '%'
or 
coalesce(vc.direccion, '') ilike '%' || lugar || '%'
)
order by
vc.id;

return;
end;

$$ language plpgsql;

select * from customers;

-- Comprobar que filtra adecuadamente
select * from fn_consulta_clientes('Maria', '');

