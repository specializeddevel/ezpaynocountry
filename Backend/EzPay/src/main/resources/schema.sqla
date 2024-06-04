CREATE TABLE IF NOT EXISTS users (
     user_id INTEGER PRIMARY KEY,
     first_name VARCHAR(30) NOT NULL,
     last_name VARCHAR(30) NOT NULL,
     password VARCHAR(100) NOT NULL,
     email VARCHAR(50) NOT NULL UNIQUE,
     phone_number VARCHAR(20) NOT NULL,
     dni VARCHAR(20) NOT NULL UNIQUE,
     birth_date DATE,
     gender VARCHAR(17),
     created_At DATE,
     role VARCHAR(5),
     user_enable BOOLEAN DEFAULT TRUE
                                );