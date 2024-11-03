--changeset strushkov:1

CREATE TABLE authors
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL
);

CREATE TABLE books
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    published_date date NOT NULL,
    author_id INTEGER REFERENCES authors(id)
);