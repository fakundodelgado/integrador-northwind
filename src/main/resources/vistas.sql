-- Vista centrada en ordenes
create or replace
view vista_ordenes as 
select
	c.customerid as cliente_id,
	c.contactname as cliente_contacto,
	o.orderid as orden_id,
	o.orderdate as orden_fecha,
	sum(o2.quantity) as orden_cantidad_items,
	coalesce(round(sum(o2.quantity * o2.unitprice * (1-o2.discount / 100.00))::numeric, 2), 0) as orden_importe_total,
	e.employeeid as empleado_id,
	e.lastname || ' ' || e.firstname as empleado_nombre
from
	customers c
inner join orders o
		using (customerid)
inner join orderdetails o2
		using (orderid)
inner join employees e
		using (employeeid)
group by 
	o.orderid, c.customerid, e.employeeid
order by 
	o.orderid;

-- Probar la vista
select * from vista_ordenes;

-- Vista centrada en clientes
create or replace
view vista_clientes as
select
	c.customerid as id,
	c.companyname as nombre_de_compania,
	c.contactname as nombre_de_contacto,
	c.country as pais,
	c.city as ciudad,
	c.address as direccion,
	count(distinct o.orderid) as ordenes_registradas,
	coalesce(round(sum(o2.quantity * o2.unitprice - (1-o2.discount / 100.00))::numeric, 2), 0) as total_gastado
from
	customers c
left join orders o
		using (customerid)
left join orderdetails o2
		using (orderid)
group by 
	c.customerid
order by
	c.customerid,
	c.companyname,
	c.contactname;

-- Probar la vista
select * from vista_clientes;