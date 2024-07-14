CREATE DATABASE libreria;

USE libreria;

CREATE TABLE libros (
	libro_id INT PRIMARY KEY AUTO_INCREMENT,
    libro_titulo VARCHAR(100) NOT NULL,
    libro_autor VARCHAR(100) NOT NULL,
    ISBN INT,
    fecha_publicacion DATE,
    editorial VARCHAR(100),
    libro_genero VARCHAR(50)
);

CREATE TABLE usuarios (
	usuario_id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_nombre VARCHAR(30) NOT NULL,
    usuario_password VARCHAR(30) NOT NULL,
    usuario_tipo int(11) NOT NULL
);

CREATE TABLE prestamos (
	id_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    libro_id INT NOT NULL,
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    estado_devuelto BOOLEAN,
    
	CONSTRAINT fk_prestamos 
		FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id),
        FOREIGN KEY (libro_id) REFERENCES libros(libro_id)
);

CREATE TABLE devoluciones (
	id_devolucion INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    libro_id INT NOT NULL,
    fecha_devolucion DATE NOT NULL,
    CONSTRAINT fk_devoluciones 
		FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id),
        FOREIGN KEY (libro_id) REFERENCES libros(libro_id)
    );
    
INSERT INTO usuarios (usuario_nombre, usuario_password, usuario_tipo) VALUES ("admin", "admin", 1);


 GRANT ALL ON libreria.* TO 'admin'@'localhost' IDENTIFIED BY '1234';
 
