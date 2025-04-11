DROP TABLE IF EXISTS usuarios;
DROP INDEX IF EXISTS idx_email;
DROP TABLE IF EXISTS juegos;
DROP TABLE IF EXISTS generos;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS usuario_rol;
DROP TABLE IF EXISTS usuario_juego;
DROP TABLE IF EXISTS juego_genero;

CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    nombre TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL 
);

CREATE INDEX idx_email ON usuarios (email);

INSERT INTO usuarios (user, email, nombre, password) VALUES 
    ('dios', 'email1@example.com', 'Jonathan', 'dios123'),  
    ('nexphernandez', 'email2@example.com', 'Nicolas', 'abc456'), 
    ('Usuario-3', 'email3@example.com', 'user3', 'xyz789');  

CREATE TABLE juegos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    urlImagen TEXT,
    codigo TEXT NOT NULL
);

INSERT INTO juegos (nombre,urlImagen,codigo) VALUES
('assassin-s-creed-valhalla', null, 'sgdhbdf'),
('elden-ring', null, 'avfsfgs');

CREATE TABLE generos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL
);

INSERT INTO generos(nombre) VALUES
('Accion'),
('Aventuras'),
('Estrategia'),
('Rol'),
('Shooters'),
('puzzles'),
('lucha');

CREATE TABLE rol (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT UNIQUE NOT NULL
);

INSERT INTO rol (nombre) VALUES
    ('Administrador'),
    ('Editor'),
    ('Usuario');
    

CREATE TABLE juego_genero (
    juego_id INTEGER,
    genero_id INTEGER,
    PRIMARY KEY (juego_id, genero_id),
    FOREIGN KEY (juego_id) REFERENCES juego(id) ON DELETE CASCADE,
    FOREIGN KEY (genero_id) REFERENCES genero(id) ON DELETE CASCADE
);

INSERT INTO juego_genero (juego_id, genero_id) VALUES
    (1, 4),
    (1, 1),
    (1, 2),
    (2, 4),
    (2, 1),
    (2, 7);

CREATE TABLE usuario_juego (
    usuario_id INTEGER,
    juego_id INTEGER,
    PRIMARY KEY (usuario_id, juego_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (juego_id) REFERENCES juego(id) ON DELETE CASCADE
);

