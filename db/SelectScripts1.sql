select p.name from person p left join company c on c.id = company_id where c.id != 5;
select p.name, c.name from person p left join company c on c.id = company_id;
WITH g AS (
select company.name, count(person) as c from company inner join person on (company.id = person.company_id) group by company.name
)
SELECT * FROM g WHERE g.c = (SELECT MAX(g.c) FROM g)
