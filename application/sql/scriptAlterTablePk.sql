alter table animal_providers 
add constraint pk_providers
primary key (id_animal_type,id_provider);

alter table animals_habitats
add constraint pk_habitats
primary key (id_animal_type,id_area);
