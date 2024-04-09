CREATE TABLE empresa(
	id_empresa int auto_increment primary key,
    nombre varchar(255) not null,
    sector varchar(255) not null,
    ubicacion varchar(255) not null,
    contacto varchar(255) not null
);

insert into empresa (nombre,sector,ubicacion,contacto) values ("SisteCredito","Ayura","Ayura Center","6045067090");

CREATE TABLE vacante(
	id_vacante int auto_increment primary key,
    idEmpresa int,
    titulo varchar(255) not null,
    descripcion text not null,
    duracion varchar(255) not null,
    estado varchar(50) not null CHECK(estado IN("ACTIVA","INACTIVA")),
    constraint fk_idEmpresa foreign key(idEmpresa) references empresa(id_empresa) on delete cascade
);

CREATE TABLE coder(
	id_coder int auto_increment primary key,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    documento varchar(255) not null,
    cohorte int not null,
    cv text not null
);

CREATE TABLE contratacion(
	id_contratacion int auto_increment primary key,
    idVacante int,
    idCoder int,
    fecha_aplicacion timestamp default current_timestamp,
    estado varchar(255) not null CHECK(estado IN("ACTIVA","INACTIVA")),
    salario decimal not null,
    constraint fk_idVacante foreign key (idVacante) references vacante(id_vacante) on delete cascade,
    constraint fk_idCoder foreign key (idCoder) references coder(id_coder) on delete cascade
);

ALTER TABLE coder MODIFY column documento varchar(255) unique not null;

select * from empresa;
select * from vacante;
select * from coder;
select * from contratacion;

ALTER TABLE vacante ADD COLUMN tecnologia varchar(255) not null;
ALTER TABLE coder ADD COLUMN clan varchar(255) not null;

