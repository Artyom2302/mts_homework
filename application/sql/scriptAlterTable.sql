alter table animals.creature 
add column if not exists birth_date date not null default current_date