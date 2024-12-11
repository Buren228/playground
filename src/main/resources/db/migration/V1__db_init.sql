CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255),
                       enabled BOOLEAN DEFAULT true,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       role_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
                            user_id INT REFERENCES users(id),
                            role_id INT REFERENCES roles(id),
                            PRIMARY KEY (user_id, role_id)
);