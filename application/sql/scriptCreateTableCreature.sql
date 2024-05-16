create table if not exists animals.creature (
	id_creature bigint generated always as identity,
	name text not null,
	type_id int,
	age smallint,
	constraint type_id_key foreign key(type_id)
	references animals.animal_type(id_type) 
	ON delete cascade,
	constraint prIdCreature_key primary key(id_creature) 
);
