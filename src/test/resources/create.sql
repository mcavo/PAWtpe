CREATE TABLE direccion (
	id SERIAL NOT NULL,
	calle varchar(30) NOT NULL,
	provincia varchar(30) NOT NULL,
	localidad varchar(30) NOT NULL,
	numero int NOT NULL,
	piso int,
	departamento char,
	barrio VARCHAR(40) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE credencial (
	id SERIAL NOT NULL,
	mail VARCHAR(40) NOT NULL,
	psw VARCHAR(16) NOT NULL,
	rol VARCHAR(20) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(mail)
);

CREATE TABLE usuario (
	userid INTEGER NOT NULL,
	nombre VARCHAR(30) NOT NULL,
	apellido VARCHAR(30) NOT NULL,
	nacimiento DATE NOT NULL,
	dirid INTEGER NOT NULL,
	PRIMARY KEY(userid),
	FOREIGN KEY(userid) REFERENCES credencial(id) ON DELETE CASCADE,
	FOREIGN KEY(dirid) REFERENCES direccion(id) ON DELETE CASCADE
);

CREATE TABLE restaurante (
	id SERIAL NOT NUL,
	gerid INTEGER NOT NULL,
	dirid INTEGER NOT NULL,
	nombre VARCHAR(30) NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	desde FLOAT NOT NULL,
	hasta FLOAT NOT NULL,
	montomin FLOAT NOT NULL,
	regis TIMESTAMP NOT NULL,
	costoenvio FLOAT,	
	PRIMARY KEY(id),
	UNIQUE(dirid), 
	FOREIGN KEY(gerid) REFERENCES TO usuario(userid) ON DELETE CASCADE, 
	FOREIGN KEY(dirid) REFERENCES TO direccion(id) ON DELETE CASCADE
);

CREATE TABLE calificacion (
	userid INTEGER NOT NULL,
	restid INTEGER NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	puntaje INTEGER NOT NULL,
	PRIMARY KEY(userid,restid),
	FOREIGN KEY(userid) REFERENCES usuario(userid) ON DELETE CASCADE, 
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE 
);

CREATE TABLE tipos (
	restid INTEGER NOT NULL,
	tipo VARCHAR(30) NOT NULL,
	PRIMARY KEY(restid,tipo),
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE
);

CREATE TABLE plato (
	id SERIAL NOT NULL,
	restid INTEGER NOT NULL,
	nombre VARCHAR(40) NOT NULL,
	seccion VARCHAR(30) NOT NULL,
	descripcion VARCHAR(200) NOT NULL,
	precio FLOAT NOT NULL,
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE,
	PRIMARY KEY(id),
	UNIQUE(restid,nombre)
);


CREATE TABLE pedido (
	id SERIAL NOT NULL, 
	restid INTEGER NOT NULL, 
	userid INTEGER NOT NULL, 
	horario TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	estado INTEGER NOT NULL, 
	PRIMARY KEY(id), 
	FOREIGN KEY(userid) REFERENCES usuario(userid) ON DELETE CASCADE, 
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE  
);


CREATE TABLE prodPedidos (
	pedid INTEGER NOT NULL, 
	platoid INTEGER NOT NULL, 
	cant INTEGER NOT NULL, 
	PRIMARY KEY(pedid,platoid), 
	FOREIGN KEY(pedid) REFERENCES pedido(id) ON DELETE CASCADE, 
	FOREIGN KEY(platoid) REFERENCES plato(id) ON DELETE CASCADE
);

CREATE TABLE gerente (
	userid INT NOT NULL,
	restid INT NOT NULL,
	PRIMARY KEY(userid),
	FOREIGN KEY(userid) REFERENCES usuario(userid) ON DELETE CASCADE,
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE,
}