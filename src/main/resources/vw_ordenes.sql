create or replace view vw_ordenes as
select o.orderdate as fechaOrden, c.customerid as idCliente, c.companyname as nombreCompania, e.employeeid as idEmpleado, e.lastname || ' ' || e.firstname as nombreEmpleado, round(sum(od.unitprice * od.quantity)::numeric(12,2)) as totalOrdenes
from orders o
inner join customers c using(customerid)
inner join employees e using(employeeid)
iner join orderdetails od using(orderid)
group by o.order_id, o.order_date, c.customer_id, c.company_name,e.employee_id, e.last_name, e.first_name;