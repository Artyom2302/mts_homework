create table if not exists animals.animal_type
(
    type_id int generated always as identity,
    type    varchar(255),
    is_wild bool default false,
    constraint prIdType_key primary key (type_id)
);
create table animals.breed
(
    breed_id serial not null,
    name     varchar(255),
    primary key (breed_id)
);
create table if not exists animals.creature
(
    id_creature int generated always as identity,
    name        text not null,
    type_id     int,
    age         smallint,
    id_breed    int,
    birth_date  date,
    constraint type_id_key foreign key (type_id)
        references animals.animal_type (type_id)
        ON delete cascade,
    constraint prIdCreature_key primary key (id_creature)
);
create table animals.habitat
(
    id_area   bigint generated always as identity,
    area_name text not null,
    constraint PK_area primary key (id_area)
);
create table animals.provider
(
    id_provider bigint generated always as identity,
    name        text,
    phone       nchar(50),

    constraint PK_provider primary key (id_provider)
);