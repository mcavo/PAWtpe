CREATE TABLE barrio (
	id SERIAL NOT NULL,
	nombre varchar(30) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(nombre)
);

CREATE TABLE preguntas (
	id SERIAL NOT NULL,
	pregunta varchar(100) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(pregunta)
);


CREATE TABLE direccion (
	id SERIAL NOT NULL,
	calle varchar(30) NOT NULL,
	provincia varchar(30) NOT NULL,
	localidad varchar(30) NOT NULL,
	numero int NOT NULL,
	piso int,
	departamento char,
	barrioid int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(barrioid) REFERENCES barrio(id) ON DELETE CASCADE
);

CREATE TABLE credencial (
	id SERIAL NOT NULL,
	mail VARCHAR(40) NOT NULL,
	psw VARCHAR(16) NOT NULL,
	rol VARCHAR(20) NOT NULL,
	lastpassupdate TIMESTAMP,
	PRIMARY KEY(id),
	UNIQUE(mail)
);

CREATE TABLE usuario (
	userid INTEGER NOT NULL,
	nombre VARCHAR(30) NOT NULL,
	apellido VARCHAR(30) NOT NULL,
	nacimiento DATE NOT NULL,
	dirid INTEGER NOT NULL,
	pregid INTEGER,
	respuesta VARCHAR(100),
	lastconnection TIMESTAMP,
	blocked BOOLEAN,
	PRIMARY KEY(userid),
	FOREIGN KEY(userid) REFERENCES credencial(id) ON DELETE CASCADE,
	FOREIGN KEY(dirid) REFERENCES direccion(id) ON DELETE CASCADE,	
	FOREIGN KEY(pregid) REFERENCES preguntas(id) ON DELETE CASCADE, 
);

CREATE TABLE restaurante (
	id SERIAL NOT NUL,
	dirid INTEGER NOT NULL,
	nombre VARCHAR(30) NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	montomin FLOAT NOT NULL,
	regis TIMESTAMP NOT NULL SET DEFAULT CURRENT_TIMESTAMP,
	costoenvio FLOAT NOT NULL,	
	desde VARCHAR(5) NOT NULL,
	hasta VARCHAR(5) NOT NULL,
	deliverydesde VARCHAR(5) NOT NULL,
	deliveryhasta VARCHAR(5) NOT NULL,	
	
	PRIMARY KEY(id),
	UNIQUE(dirid), 
	FOREIGN KEY(gerid) REFERENCES TO usuario(userid) ON DELETE CASCADE, 
	FOREIGN KEY(dirid) REFERENCES TO direccion(id) ON DELETE CASCADE
);

CREATE TABLE delivery (
	restid INTEGER NOT NULL,
	barrioid INTEGER NOT NULL,
	costo FLOAT NOT NULL,
	PRIMARY KEY(restid,barrioid),
	FOREIGN KEY(barrioid) REFERENCES barrio(id) ON DELETE CASCADE,
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE
);

CREATE TABLE calificacion (
	id SERIAL NOT NULL,
	userid INTEGER NOT NULL,
	restid INTEGER NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	puntaje INTEGER NOT NULL,
	PRIMARY KEY(id),
	UNIQUE (userid,restid),
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
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE
);

CREATE TABLE periodoClausura (
	id SERIAL NOT NULL,
	restid INT NOT NULL,
	desde DATE NOT NULL,
	hasta DATE NOT NULL,
	razon VARCHAR(500) NOT NULL,
	PRIMARY KEY(id), 
	FOREIGN KEY(restid) REFERENCES restaurante(id) ON DELETE CASCADE
);