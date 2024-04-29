insert into animal_type(type,is_wild) 
values ('Собака',false),
('Кошка',false),
('Волк',true);

insert into habitat (area_name)
values ('Дом'),
('Квартира'),
('Лес'),
('Море')

insert into provider (name,phone)
values('Вася',5692344),
('Петя',5692345)

insert into animals.creature (name,type_id,age,birth_date)
values ('Барсик',3,4,now() - interval '4 years'),
('Шарик',2,5,now() - interval '5 years'),
('Акула №1',1,2,now() - interval '2 years'),
('Волк №2',4,5,now() - interval '5 years');

insert into animals_habitats(id_animal_type,id_area)
values (1,4),
(2,1),
(3,2),
(4,3)

insert into animal_providers(id_animal_type,id_provider)
values (2,1),
(3,2)
