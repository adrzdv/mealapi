CREATE SCHEMA IF NOT EXISTS PUBLIC;

CREATE TABLE IF NOT EXISTS users(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(254) NOT NULL,
    email VARCHAR(320) NOT NULL,
    age SMALLINT NOT NULL CHECK (age > 0),
    weight SMALLINT NOT NULL CHECK (weight > 0),
    height SMALLINT NOT NULL CHECK (height > 0),
    target VARCHAR(254) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT uq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS dish(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(254) NOT NULL,
    kkal INTEGER NOT NULL CHECK (kkal > 0),
    proteins DOUBLE PRECISION NOT NULL CHECK (proteins > 0),
    fats DOUBLE PRECISION NOT NULL CHECK (fats > 0),
    carbohydrates DOUBLE PRECISION NOT NULL CHECK (carbohydrates > 0),
    CONSTRAINT pk_dish PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS meals (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_day SMALLINT NOT NULL,
    id_user BIGINT NOT NULL,
    CONSTRAINT pk_meals PRIMARY KEY (id),
    CONSTRAINT fk_meals_user FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS meal_dishes (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_meal BIGINT NOT NULL,
    id_dish BIGINT NOT NULL,
    portion_size DECIMAL(6,2) NOT NULL CHECK (portion_size > 0),
    CONSTRAINT pk_meal_dishes PRIMARY KEY (id),
    CONSTRAINT fk_meal_dishes_meal FOREIGN KEY (id_meal) REFERENCES meals(id) ON DELETE CASCADE,
    CONSTRAINT fk_meal_dishes_dish FOREIGN KEY (id_dish) REFERENCES dish(id) ON DELETE CASCADE
);
