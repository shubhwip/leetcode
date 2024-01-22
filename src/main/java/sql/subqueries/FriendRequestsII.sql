-- Approach 1 with CTEs

with cte1 as (
select requester_id as id, count(requester_id) as num
from RequestAccepted
group by requester_id
),
cte2 as (
select accepter_id as id, count(accepter_id) as num
from RequestAccepted
group by accepter_id
),

cte3 as (
select * from cte1 c1
UNION ALL
select * from cte2 c2
)

-- select *
-- from cte3;

select id, sum(num) as num
from cte3
group by id
order by num desc
limit 1;



