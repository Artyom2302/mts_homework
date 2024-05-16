create table provider(
	id_provider bigint generated always as identity,
	name text,
	phone nchar(50),

	constraint PK_provider primary key(id_provider)
);

create table animal_providers(
	id_animal_type bigint,
	id_provider bigint,

	constraint FK_animal_type foreign key(id_animal_type)
	references animals.animal_type(id_type),

	constraint FK_provider foreign key(id_provider)
	references animals.provider(id_provider)
)