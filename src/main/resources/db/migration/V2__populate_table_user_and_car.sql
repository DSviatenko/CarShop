INSERT INTO users(name, role, email, password)
VALUES ('John', 'USER', 'john1@gmail.com', 'johnpass'),
       ('Bob', 'USER', 'hunter_s@gmail.com', 'bobishunter1'),
       ('Tom', 'USER', 'spider@gmail.com', '12345678'),
       ('Mike', 'USER', 'mk_32@gmail.com', 'secretpass'),
       ('Anthony', 'USER', 'anderson@gmail.com', '232356');

INSERT INTO car(owner_id, brand, model, engine_capacity, fuel, year_of_production, price)
VALUES (1, 'Opel', 'Vectra B', '1598', 'GASOLINE', 1996, 4000),
       (1, 'Mercedes-Benz', 'E-class', '3200', 'DIESEL', 2001, 5000),
       (1, 'BMW', '5 series', '2498', 'GASOLINE', 1993, 3700),
       (2, 'Tesla', 'Model 3', '0', 'ELECTRIC', 2020, 27000),
       (3, 'Audi', 'A6', '3000', 'DIESEL', 2015, 19000)
