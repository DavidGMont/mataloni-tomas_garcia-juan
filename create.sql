DROP TABLE IF EXISTS DOMICILIOS;

CREATE TABLE DOMICILIOS(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CALLE VARCHAR(100) NOT NULL,
    NUMERO INT NOT NULL,
    LOCALIDAD VARCHAR(100) NOT NULL,
    PROVINCIA VARCHAR(100) NOT NULL
);

INSERT INTO DOMICILIOS(CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES
    ('Av Siempre Viva', 135, 'Springfield', 'Massachusetts'),
    ('Calle Wallaby', 42, 'Sydney', 'Nueva Gales del Sur');

DROP TABLE IF EXISTS PACIENTES;

CREATE TABLE PACIENTES(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(100) NOT NULL,
    APELLIDO VARCHAR(100) NOT NULL,
    DNI VARCHAR(100) NOT NULL,
    FECHA DATE NOT NULL,
    DOMICILIO_ID INT
);

INSERT INTO PACIENTES(NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID) VALUES
    ('Tomás', 'Mataloni', '95424745', '2023-06-01', 1);

DROP TABLE IF EXISTS ODONTOLOGOS;

CREATE TABLE ODONTOLOGOS(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NUMERO_MATRICULA NUMERIC NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL,
    APELLIDO VARCHAR(100) NOT NULL
);

INSERT INTO ODONTOLOGOS(NUMERO_MATRICULA, NOMBRE, APELLIDO)VALUES
    (94673, 'Juanito', 'López'),
    (67842, 'Soledad', 'Ramírez'),
    (87795, 'Martín', 'Acosta');

DROP TABLE IF EXISTS TURNOS;

CREATE TABLE TURNOS(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    FECHA DATETIME NOT NULL,
    ODONTOLOGO_ID INT
);

INSERT INTO TURNOS(FECHA, ODONTOLOGO_ID) VALUES
    ('2023-06-05 10:55:00', 2);