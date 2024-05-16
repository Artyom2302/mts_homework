create table if not exists animals.animal_type (
	id_type int generated always as identity,
	type nchar(50),
	is_wild bool default false,
	constraint prIdType_key primary key(id_type)  
);


