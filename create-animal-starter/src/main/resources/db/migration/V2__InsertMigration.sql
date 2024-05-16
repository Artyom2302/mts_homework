insert into animals.animal_type(type,is_wild)
values ('Собака',false),
       ('Кошка',false),
       ('Волк',true);

insert into animals.habitat (area_name)
values ('Дом'),
       ('Квартира'),
       ('Лес'),
       ('Море');

insert into animals.provider (name,phone)
values('Вася',5692344),
      ('Петя',5692345);

insert into animals.creature (name,id_breed,type_id,age,birth_date)
values (concat('Животное #', floor(random()*100)),
        (select breed_id from animals.breed order by random() limit 1),
        (select type_id from animals.animal_type order by random() limit 1),
        FLOOR(RANDOM() * 10),
        now() - ((floor(random()*10) || ' Years'))::interval
        ),
       (concat('Животное #', floor(random()*100)),
        (select breed_id from animals.breed order by random() limit 1),
        (select type_id from animals.animal_type order by random() limit 1),
        FLOOR(RANDOM() * 10),
        now() - ((floor(random()*10) || ' Years'))::interval
       ),
       (concat('Животное #', floor(random()*100)),
        (select breed_id from animals.breed order by random() limit 1),
        (select type_id from animals.animal_type order by random() limit 1),
        FLOOR(RANDOM() * 10),
        now() - ((floor(random()*10) || ' Years'))::interval
       ),
       (concat('Животное #', floor(random()*100)),
        (select breed_id from animals.breed order by random() limit 1),
        (select type_id from animals.animal_type order by random() limit 1),
        FLOOR(RANDOM() * 10),
        now() - ((floor(random()*10) || ' Years'))::interval
       );