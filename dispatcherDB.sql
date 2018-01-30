
 CREATE SCHEMA dispacher
  AUTHORIZATION postgres;


CREATE SEQUENCE dispacher.empleado_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 9
  CACHE 1;
ALTER TABLE dispacher.empleado_seq
  OWNER TO postgres;

CREATE SEQUENCE dispacher.llamada_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 53
  CACHE 1;
ALTER TABLE dispacher.llamada_seq
  OWNER TO postgres;


CREATE TABLE dispacher.cargo
(
  id numeric(3,0) NOT NULL,
  nombre character varying(100),
  prioridad numeric(3,0),
  CONSTRAINT id_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE dispacher.empleado
(
  identificacion character varying(20) NOT NULL,
  nombre character varying(100),
  id_cargo numeric(3,0),
  id numeric(8,0) NOT NULL DEFAULT nextval('dispacher.empleado_seq'::regclass),
  ocupado boolean,
  CONSTRAINT id_empleado PRIMARY KEY (id),
  CONSTRAINT id_cargo_fk FOREIGN KEY (id_cargo)
      REFERENCES dispacher.cargo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE dispacher.llamada
(
  id numeric(3,0) NOT NULL DEFAULT nextval('dispacher.llamada_seq'::regclass),
  id_call character varying(30),
  fromcall character varying(10),
  tocall character varying(10),
  date date,
  id_empleado numeric(8,0),
  waiting boolean,
  CONSTRAINT id_llamada PRIMARY KEY (id),
  CONSTRAINT id_empledo_llamada FOREIGN KEY (id_empleado)
      REFERENCES dispacher.empleado (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

INSERT INTO dispacher.cargo (id, nombre, prioridad) VALUES (1, 'Operador', 1);
INSERT INTO dispacher.cargo (id, nombre, prioridad) VALUES (2, 'Supervisor', 2);
INSERT INTO dispacher.cargo (id, nombre, prioridad) VALUES (3, 'Director', 3);

INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('1234568', 'supervisor 1', 2, 3, false);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('234567', 'supervisor 2', 2, 4, false);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('123456', 'director', 3, 1, false);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('4111111', 'operador 7', 1, 10, true);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('23333', 'operador 8', 1, 11, true);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('76589', 'operador 4', 1, 8, true);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('09876', 'operador 3', 1, 7, true);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('76534', 'operador 5', 1, 9, false);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('345678', 'operador 1', 1, 5, false);
INSERT INTO dispacher.empleado (identificacion, nombre, id_cargo, id, ocupado) VALUES ('321456', 'operador 2', 1, 6, false);
 
