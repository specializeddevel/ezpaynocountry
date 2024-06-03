TRUNCATE TABLE users CASCADE;

INSERT INTO users (user_id, first_name, last_name, password, email, phone_number, dni, birth_date, gender, created_at, role, user_enable)
VALUES
    (100, 'Juan', 'Perez', '$2a$10$4IsPdVRZbBq6I09nO5cPd.jiMErDv//Pe3vLFMROhwvEqupIDec0G', 'juan.perez@example.com', '123-456-7890', '12345678', '1990-01-01', 'PREFIERO_NO_DECIR', '2024-05-23', 'USER', TRUE),
    (101, 'Sebastian', 'Garmendia', '$2a$10$4IsPdVRZbBq6I09nO5cPd.jiMErDv//Pe3vLFMROhwvEqupIDec0G', 'sebastian.garmendia@example.com', '234-567-8901', '23456789', '1985-02-02', 'PREFIERO_NO_DECIR', '2024-05-23', 'USER', TRUE),
    (102, 'Carla', 'Camacho', '$2a$10$4IsPdVRZbBq6I09nO5cPd.jiMErDv//Pe3vLFMROhwvEqupIDec0G', 'carla.camacho@example.com', '345-678-9012', '34567890', '1992-03-03', 'PREFIERO_NO_DECIR', '2024-05-23', 'USER', TRUE),
    (103, 'Cosme', 'Fulanito', '$2a$10$4IsPdVRZbBq6I09nO5cPd.jiMErDv//Pe3vLFMROhwvEqupIDec0G', 'cosme.fulanito@example.com', '456-789-0123', '45678901', '1980-04-04', 'PREFIERO_NO_DECIR', '2024-05-23', 'USER', TRUE);