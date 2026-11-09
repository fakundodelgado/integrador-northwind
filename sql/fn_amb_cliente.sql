create or replace function fn_amb_cliente(pt_accion text, pi_id text, pt_nombre text, pt_contact text, pt_ciudad text, pt_pais text)
returns text as
$$
begin
    if pt_accion not in ('A', 'M', 'B') then
        rise exception 'accion invalida: %' pt_accion;
    end if;

    if pt_id is null or pt_id !~ '^[A-Za-z]{5}$' then
        rise exception 'El id no se igreso correctamente.'
    end if;
    pt_id := upper(pt_id);
    if pt_accion = 'A' then
        if exists (select customerid from customer where customerid = pt_id) then
            reise exception 'Este cliente ya existe %', pt_id;
        end if;
        if coalesce(trim(pt_nombre), ' ') = ' ' then
            rise exception 'Ingrese nombre de cliente es obligatorio';
        end if;
        insert into customers(customerid, companyname, contactname, city, country)
        values(pt_id, pt_nombre, pt_contacto, pt_ciudad, pt_pais);
        --podra ingresarse un mensaje de alta?
    end if;

    --valido si el cliente ingresado existe
    if not exists (select customerid from customers where customerid = pt_id) then
        raise exception 'El cliente ingresado no existe %', pt_id;
    end if;

    if pt_accion = 'M' then
        update customers set companyname = pt_nombre, contactname = _pt_contacto, city = pt_ciudad, country = pt_pais
        where customerid = pt_id;
    end if;
    --mensaje de modificacion?


    if exists (select 1 from orders where customerid = pt_id) then
        raise exception 'No es posible borrar este cliente, tiene ordenes asociadas';
    end if;

    delete from customers where customerid = pt_id;
    --mensaje cliente eliminado?
    
end;
$$
language plpgsql;