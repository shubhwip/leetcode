-- Approach 1
with cte1 as (
select distinct visited_on
from customer
group by visited_on
order by visited_on
offset 6
limit 1
)

select visited_on,
(
    select sum(amount)
    from customer
    where visited_on BETWEEN c.visited_on - INTERVAL '6 DAY' AND c.visited_on
) as amount,
ROUND(
        (
            SELECT (SUM(amount)::decimal / 7)
            FROM customer
            WHERE visited_on BETWEEN c.visited_on - INTERVAL '6 DAY' AND c.visited_on
        ),
        2
    ) AS average_amount
from customer c
where visited_on >= (select visited_on from cte1)
GROUP BY visited_on
order by visited_on

-- Approach 2
SELECT A.visited_on,
SUM(B.amount) AS amount,
ROUND(SUM(B.amount)::numeric / 7, 2) AS average_amount
FROM
(SELECT DISTINCT visited_on FROM Customer WHERE visited_on - 6 IN (SELECT visited_on FROM Customer)) AS A,
Customer B WHERE A.visited_on BETWEEN B.visited_on AND B.visited_on + 6
GROUP BY A.visited_on
ORDER BY visited_on ASC

-- Approach 3 Window functions
SELECT visited_on, amount, ROUND(amount::decimal/7, 2) as average_amount
FROM (
    SELECT DISTINCT visited_on,
    SUM(amount) OVER(ORDER BY visited_on RANGE BETWEEN INTERVAL '6 DAY' PRECEDING AND CURRENT ROW) amount,
    MIN(visited_on) OVER() date_first
    FROM Customer
) t
WHERE visited_on >= date_first+6
order by visited_on