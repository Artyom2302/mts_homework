create table habitat(
		id_area bigint generated always as identity,
		area_name text not null,
		constraint PK_area primary key(id_area)
);

create table animals_habitats(
		id_animal_type bigint,
		id_area bigint,

		constraint FK_animal_type foreign key(id_animal_type)
		references animals.animal_type(id_type),

		constraint FK_area_id foreign key(id_area)
		references animals.habitat(id_area)
)