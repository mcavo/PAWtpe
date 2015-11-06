INSERT INTO barrio (id,nombre) VALUES (1,'Palermo');
INSERT INTO barrio (id,nombre) VALUES (2,'Villa Urquiza');
INSERT INTO barrio (id,nombre) VALUES (3,'Belgrano');
INSERT INTO barrio (id,nombre) VALUES (4,'Flores');
INSERT INTO barrio (id,nombre) VALUES (5,'Floresta');
INSERT INTO barrio (id,nombre) VALUES (6,'Puerto Madero');
INSERT INTO barrio (id,nombre) VALUES (7,'San Cristobal');
INSERT INTO barrio (id,nombre) VALUES (8,'Los Alisos');
INSERT INTO barrio (id,nombre) VALUES (9,'San Telmo');
INSERT INTO barrio (id,nombre) VALUES (10,'Devoto');

INSERT INTO direccion (id,calle,numero,barrioid,provincia,localidad) VALUES (1,'Valdenegro',3283,2,'Buenos Aires','Capital Federal');
INSERT INTO direccion (id,calle,numero,barrioid,provincia,localidad) VALUES (2,'Gorriti',4116,1,'Buenos Aires','Capital Federal');
INSERT INTO direccion (id,calle,numero,barrioid,provincia,localidad) VALUES (3,'Soler',5608,1,'Buenos Aires','Capital Federal');
INSERT INTO direccion (id,calle,numero,barrioid,provincia,localidad) VALUES (4,'Soler',5608,1,'Buenos Aires','Capital Federal');
INSERT INTO direccion (id,calle,numero,barrioid,provincia,localidad) VALUES (5,'Del Cardón',4,8,'Buenos Aires','Nordelta Tigre');
INSERT INTO direccion (id,calle,numero,barrioid,provincia,localidad) VALUES (6,'Soler',5581,1,'Buenos Aires','Capital Federal');
INSERT INTO direccion (id,calle,numero,barrioid,provincia,localidad) VALUES (7,'Av. Alicia Moreau de Justo',1010,6,'Buenos Aires','Capital Federal');

INSERT INTO preguntas (id,pregunta) VALUES (1,'¿Cuál es tu animal favorito?');
INSERT INTO preguntas (id,pregunta) VALUES (2,'¿Cuál es tu color favorito?');
INSERT INTO preguntas (id,pregunta) VALUES (3,'¿Cómo se llama tu madre?');
INSERT INTO preguntas (id,pregunta) VALUES (4,'¿Cómo se llama tu padre?');
INSERT INTO preguntas (id,pregunta) VALUES (5,'¿Cuál es el nombre de tu mejor amigo/a de la infancia?');
INSERT INTO preguntas (id,pregunta) VALUES (6,'¿Cuál es tu película favorita?');
INSERT INTO preguntas (id,pregunta) VALUES (7,'¿Cuántos hermanos tenés?');

INSERT INTO credencial (id,mail,psw,rol) VALUES (1,'mvictoria.cavo@gmail.com',123456789,'admin');
INSERT INTO credencial (id,mail,psw,rol) VALUES (2,'msakuda@itba.edu.ar',123456789,'gerente');
INSERT INTO credencial (id,mail,psw,rol) VALUES (3,'juli@gmail.com',123456789,'gerente');
INSERT INTO credencial (id,mail,psw,rol) VALUES (4,'jacksparrow@elpirata.com',123456789,'usuario');
INSERT INTO credencial (id,mail,psw,rol) VALUES (5,'carlita@gmail.com',123456789,'usuario');

INSERT INTO usuario (userid,nombre,apellido,nacimiento,dirid,pregid,respuesta) VALUES (1,'María Victoria','Cavo',NAC,5,7,'2');
INSERT INTO usuario (userid,nombre,apellido,nacimiento,dirid,pregid,respuesta) VALUES (2,'María Eugenia','Sakuda',NAC,4,2,'Violeta');
INSERT INTO usuario (userid,nombre,apellido,nacimiento,dirid,pregid,respuesta) VALUES (3,'Julieta','Sal-Lari',NAC,2,1,'Perro');
INSERT INTO usuario (userid,nombre,apellido,nacimiento,dirid,pregid,respuesta) VALUES (4,'Jack','Sparrow',NAC,2,6,'Piratas del Caribe');
INSERT INTO usuario (userid,nombre,apellido,nacimiento,dirid,pregid,respuesta) VALUES (5,'Carla','Planta',NAC,5,1,'Juli');

INSERT INTO restaurante (id,dirid,nombre,descripcion,desde,hasta,montomin,costoenvio,deliverydesde,deliveryhasta) VALUES (1,6,'Taco Box','Taco Box es una Restaurant que inaugura un modo diferente de disfrutar la comida Mexicana. Con un estilo refinado Taco box opta por una ambientacion que genera la atmósfera de un delicado bistró.',12,24,80,20,12,24);
INSERT INTO restaurante (id,dirid,nombre,descripcion,desde,hasta,montomin,costoenvio,deliverydesde,deliveryhasta) VALUES (2,7,'T.G.I. Fridays','TGI Fridays es mucho más que un restaurante de comida americana. Es un lugar para reunirte con tus amigos y conocer gente nueva.',12,24,100,20,12,24);

INSERT INTO delivery (restid,barrioid,costo) VALUES (1,1,20);
INSERT INTO delivery (restid,barrioid,costo) VALUES (1,2,20);
INSERT INTO delivery (restid,barrioid,costo) VALUES (1,3,20);
INSERT INTO delivery (restid,barrioid,costo) VALUES (1,4,20);
INSERT INTO delivery (restid,barrioid,costo) VALUES (2,6,20);
INSERT INTO delivery (restid,barrioid,costo) VALUES (2,5,20);
INSERT INTO delivery (restid,barrioid,costo) VALUES (2,10,20);
INSERT INTO delivery (restid,barrioid,costo) VALUES (2,9,20);

INSERT INTO tipos (restid,tipo) VALUES (2,'Norteamericana');
INSERT INTO tipos (restid,tipo) VALUES (1,'Mexicana');

INSERT INTO gerente (userid,restid) VALUES (2,1);
INSERT INTO gerente (userid,restid) VALUES (3,2);


