create or repalce view vw_ordenes as
select o.orderdate as fechaOrden, c.customerid as idCliente, c.companyname as nombreCompania, e.employeeid as idEmpleado, e.lastname || ' ' || e.firstname as nombreEmpleado, round(sum(od.unitprice * od.quantity)::numeric(12,2)) as totalOrdenes
from orders o
inner join customers c using(customerid)
inner join employees e using(employeeid)
iner join orderdetails od using(orderid)
group by o.orderid, o.orderdate, c.customerid;