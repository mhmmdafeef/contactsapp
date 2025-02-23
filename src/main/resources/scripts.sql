CREATE TABLE contacts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    username VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);



INSERT INTO users(
	username, password, enabled)
	VALUES (?, ?, ?);

INSERT INTO public.contacts(
    id, name, contact_number, username)
    VALUES (?, ?, ?, ?);

