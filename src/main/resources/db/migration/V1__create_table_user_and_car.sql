DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255)        NOT NULL,
    role     VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL
);

CREATE TABLE car
(
    car_id             BIGSERIAL PRIMARY KEY,
    owner_id           BIGINT       NOT NULL,
    brand              VARCHAR(255) NOT NULL,
    model              VARCHAR(255) NOT NULL,
    engine_capacity    INT          NOT NULL,
    fuel               VARCHAR(255) NOT NULL,
    year_of_production INT          NOT NULL,
    price              INT          NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users (id) ON DELETE CASCADE
)