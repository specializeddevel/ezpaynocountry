TRUNCATE TABLE users;

INSERT INTO users (user_id, first_name, last_name, password, email, phone_number, dni, birth_date, gender)
VALUES
    (3, 'Juan', 'Perez', 'password123', 'juan.perez@example.com', '123-456-7890', '12345678', '1990-01-01', 'PREFIERO_NO_DECIR'),
    (4, 'Sebastian', 'Garmendia', 'password456', 'sebastian.garmendia@example.com', '234-567-8901', '23456789', '1985-02-02', 'PREFIERO_NO_DECIR'),
    (5, 'Carla', 'Camacho', 'password789', 'carla.camacho@example.com', '345-678-9012', '34567890', '1992-03-03', 'PREFIERO_NO_DECIR'),
    (6, 'Cosme', 'Fulanito', 'password101', 'cosme.fulanito@example.com', '456-789-0123', '45678901', '1980-04-04', 'PREFIERO_NO_DECIR');
