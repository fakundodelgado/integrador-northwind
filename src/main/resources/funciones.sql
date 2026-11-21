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
    raise exception 'No se ingreso una accion a realizar';
elseif (p_accion not in ('A', 'B', 'M')) then
    raise exception 'La accion ingresada no es valida';
end if;

-- bloque para la alta
if (p_accion = 'A') then

    if (p_id is null or p_id !~ '^[A-Z]+$' or length(p_id) != 5) then 
        raise exception 'El id ingresado no es valido';
    elseif (exists (select 1 from customers where customerid = p_id)) then
        raise exception 'El cliente ya se encuentra registrado';
    end if;

    if p_companyname is not null and length(trim(p_companyname)) > 0 then
        if length(p_companyname) > 40 then
            raise exception 'El nombre de la empresa no puede superar los 40 caracteres.';
        end if;
    else
    	raise exception 'El nombre de la empresa es obligatorio';
    end if;
    
    if p_contactname is not null and length(trim(p_contactname)) > 0 then
        if length(p_contactname) > 30 then
            raise exception 'El nombre de contacto no puede superar los 30 caracteres.';
        elseif (p_contactname !~ '^[[:alpha:] ]+$') then
            raise exception 'El nombre de contacto solo debe contener caracteres y espacios';
        end if;
    else
    	raise exception 'El nombre de contacto es obligatorio';
    end if;
    
    if p_contacttitle is not null and length(trim(p_contacttitle)) > 0 then
        if length(p_contacttitle) > 30 then
            raise exception 'El titulo de contacto no puede superar los 30 caracteres.';
        elseif (p_contacttitle !~ '^[[:alpha:] .-]+$') then
            raise exception 'El titulo de contacto solo debe contener caracteres, espacios, puntos o guiones';
        end if;
    end if;
    
    if p_address is not null and length(trim(p_address)) > 0 then
        if length(p_address) > 60 then
            raise exception 'La direccion no puede superar los 60 caracteres.';
        end if;
    end if;
    
    if p_city is not null and length(trim(p_city)) > 0 then
        if length(p_city) > 15 then
            raise exception 'La ciudad no puede superar los 15 caracteres.';
        elseif (p_city !~ '^[[:alpha:] ]+$') then
            raise exception 'El nombre de la ciudad solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_region is not null and length(trim(p_region)) > 0 then
        if length(p_region) > 15 then
            raise exception 'La region no puede superar los 15 caracteres.';
        elseif (p_region !~ '^[[:alpha:] ]+$') then
            raise exception 'La region solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_postalcode is not null and length(trim(p_postalcode)) > 0 then
        if length(p_postalcode) > 10 then
            raise exception 'El codigo postal no puede superar los 10 caracteres.';
        end if;
    else
    	raise exception 'El codigo postal es obligatorio';
    end if;
    
    if p_country is not null and length(trim(p_country)) > 0 then
        if length(p_country) > 15 then
            raise exception 'El pais no puede superar los 15 caracteres.';
        elseif (p_country !~ '^[[:alpha:] ]+$') then
            raise exception 'El pais solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_phone is not null and length(trim(p_phone)) > 0 then
        if length(p_phone) > 24 then
            raise exception 'El telefono no puede superar los 24 caracteres.';
        elseif p_phone !~ '^[0-9\s\+\-\(\)\.]+$' then
        	raise exception 'El telefono solo debe contener números, espacios y los símbolos +, -, (), .';
    	end if;
    else
    	raise exception 'El telefono es obligatorio';
    end if;
    
    if p_fax is not null and length(trim(p_fax)) > 0 then
        if length(p_fax) > 24 then
            raise exception 'El fax no puede superar los 24 caracteres.';
        elseif p_fax !~ '^[0-9\s\+\-\(\)\.]+$' then
        	raise exception 'El fax solo debe contener números, espacios y los símbolos +, -, (), .';
    	end if;
    end if;

	-- Actualizado para que guarde nulls en caso de que se dejen los campos vacios
	insert into customers (
    customerid, companyname, contactname, contacttitle, 
    address, city, region, postalcode, country, phone, fax
	) 
	values (
	p_id, 
    trim(p_companyname), 
    trim(p_contactname), 
    nullif(trim(p_contacttitle), ''), 
    nullif(trim(p_address), ''), 
    nullif(trim(p_city), ''), 
    nullif(trim(p_region), ''), 
    trim(p_postalcode), 
    nullif(trim(p_country), ''), 
    trim(p_phone), 
    nullif(trim(p_fax), '')
	);

	return 'EXITO: El registro fue creado correctamente.';

elseif(p_accion = 'B') then
	
	if (p_id is null or p_id !~ '^[A-Z]+$' or length(p_id) != 5) then 
        raise exception 'El id ingresado no es valido';
    elseif (not exists (select 1 from customers where customerid = p_id)) then
		raise exception 'El cliente no existe.';
	elseif (exists (select 1 from orders where customerid = p_id )) then
		raise exception 'No se puede eliminar el cliente porque tiene ordenes asociadas.';
	end if;
	
	delete from customers where customerid = p_id;

	return 'EXITO: El registro fue eliminado correctamente.';

else 
	
	if (p_id is null or p_id !~ '^[A-Z]+$' or length(p_id) != 5) then 
        raise exception 'El id ingresado no es valido';
	elseif (not exists (select 1 from customers where customerid = p_id)) then
    	raise exception 'El cliente no se encuentra registrado.';
    end if;

    if p_companyname is not null and length(trim(p_companyname)) > 0 then
        if length(p_companyname) > 40 then
            raise exception 'El nombre de la empresa no puede superar los 40 caracteres.';
        end if;
    else
    	raise exception 'El nombre de la empresa es obligatorio';
    end if;
    
    if p_contactname is not null and length(trim(p_contactname)) > 0 then
        if length(p_contactname) > 30 then
            raise exception 'El nombre de contacto no puede superar los 30 caracteres.';
        elseif (p_contactname !~ '^[[:alpha:] ]+$') then
            raise exception 'El nombre de contacto solo debe contener caracteres y espacios';
        end if;
    else
    	raise exception 'El nombre de contacto es obligatorio';
    end if;
    
    if p_contacttitle is not null and length(trim(p_contacttitle)) > 0 then
        if length(p_contacttitle) > 30 then
            raise exception 'El titulo de contacto no puede superar los 30 caracteres.';
        elseif (p_contacttitle !~ '^[[:alpha:] .-]+$') then
            raise exception 'El titulo de contacto solo debe contener caracteres, espacios, puntos o guiones';
        end if;
    end if;
    
    if p_address is not null and length(trim(p_address)) > 0 then
        if length(p_address) > 60 then
            raise exception 'La direccion no puede superar los 60 caracteres.';
        end if;
    end if;
    
    if p_city is not null and length(trim(p_city)) > 0 then
        if length(p_city) > 15 then
            raise exception 'La ciudad no puede superar los 15 caracteres.';
        elseif (p_city !~ '^[[:alpha:] ]+$') then
            raise exception 'El nombre de la ciudad solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_region is not null and length(trim(p_region)) > 0 then
        if length(p_region) > 15 then
            raise exception 'La region no puede superar los 15 caracteres.';
        elseif (p_region !~ '^[[:alpha:] ]+$') then
            raise exception 'La region solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_postalcode is not null and length(trim(p_postalcode)) > 0 then
        if length(p_postalcode) > 10 then
            raise exception 'El codigo postal no puede superar los 10 caracteres.';
        end if;
    else
    	raise exception 'El codigo postal es obligatorio';
    end if;
    
    if p_country is not null and length(trim(p_country)) > 0 then
        if length(p_country) > 15 then
            raise exception 'El pais no puede superar los 15 caracteres.';
        elseif (p_country !~ '^[[:alpha:] ]+$') then
            raise exception 'El pais solo debe contener caracteres y espacios';
        end if;
    end if;
    
    if p_phone is not null and length(trim(p_phone)) > 0 then
        if length(p_phone) > 24 then
            raise exception 'El telefono no puede superar los 24 caracteres.';
        elseif p_phone !~ '^[0-9\s\+\-\(\)\.]+$' then
        	raise exception 'El telefono contiene caracteres inválidos. Solo se permiten números, espacios y los símbolos +, -, (), .';
    	end if;
    else
    	raise exception 'El telefono es obligatorio';
    end if;
    
    if p_fax is not null and length(trim(p_fax)) > 0 then
        if length(p_fax) > 24 then
            raise exception 'El fax no puede superar los 24 caracteres.';
        elseif p_fax !~ '^[0-9\s\+\-\(\)\.]+$' then
        	raise exception 'El fax contiene caracteres inválidos. Solo se permiten números, espacios y los símbolos +, -, (), .';
    	end if;
    end if;
	
	-- Si no se completan los campos nos quedamos con el valor ya existente (se autocompletaran al momento de actualizar asi el usuario sabe cuales son los datos actuales)
	-- nullif me devuelve null cuando le paso ' ' al modificar si el campo vino vacio
	-- Los campos obligatorios estaran protegidos por condiciones
	
	update customers set 
    companyname = trim(p_companyname), 
    contactname = trim(p_contactname), 
    contacttitle = nullif(trim(p_contacttitle), ''), 
    address = nullif(trim(p_address), ''), 
    city = nullif(trim(p_city), ''), 
    region = nullif(trim(p_region), ''), 
    postalcode = trim(p_postalcode), 
    country = nullif(trim(p_country), ''), 
    phone = trim(p_phone), 
    fax = nullif(trim(p_fax), '')
    where customerid = p_id;

	return 'EXITO: El registro fue modificado correctamente.';

end if;

end;

$$ language plpgsql;

-- '^[[:alpha:] ]+$' contiene basicamente todas las letras y vocales con o sin acentos y me ayuda a no tener que escribir todo uno por uno

-- FUNCION PARA CONSULTAR LA VISTA DE ORDENES

-- Lo formatee asi porque DBeaver hace cualquier cosa y era dificil de leer...
create or replace function fn_consulta_ordenes(p_cliente text, p_empleado text) returns table 
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

return query 

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
-- select * from fn_consulta_clientes('Maria', '');

-- Decidi hacerlo de esta forma para que sea más facil de llamar, sino hay que aclarar la forma del record al costado
create or replace function fn_datos_cliente(p_id varchar(5)) returns table (out_companyname varchar(40),
    out_contactname varchar(30),
    out_contacttitle varchar(30),
    out_address varchar(60),
    out_city varchar(15),
    out_region varchar(15),
    out_postalcode varchar(10),
    out_country varchar(15),
    out_phone varchar(24),
    out_fax varchar(24)) as $$
   
declare

id varchar(5); 
filas integer;  
   
begin

id = upper(trim(p_id));
    
return query select companyname, contactname, contacttitle, address, city, region, postalcode, country, phone, fax from customers where customerid = id;

get diagnostics filas = row_count;

if(filas = 0) then
	raise exception 'No se encontro el cliente buscado';
end if;

return;

end;

$$ language plpgsql;

-- Para comparar
select * from fn_datos_cliente('facuu');
-- select * from customers where customerid = 'VINET';