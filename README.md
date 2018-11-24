# ng-spring

## cassandra
``` bash
cassandra
```

## create and populate products table
``` shell
cqlsh

CREATE TABLE coffeeshop.products (product_id UUID PRIMARY KEY, name text, size int, price decimal);

INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Black Coffee (Small)', 2, 2.00) IF NOT EXISTS;
INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Black Coffee (Medium)', 4, 4.50) IF NOT EXISTS;
INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Black Coffee (Large)', 8, 7.00) IF NOT EXISTS;

INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Espresso (Small)', 2, 3.00) IF NOT EXISTS;
INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Espresso (Medium)', 4, 4.75) IF NOT EXISTS;
INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Espresso (Large)', 6, 6.25) IF NOT EXISTS;

INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Caramel Macchiato (Small)', 3, 5.00) IF NOT EXISTS;
INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Caramel Macchiato (Medium)', 6, 7.25) IF NOT EXISTS;
INSERT INTO coffeeshop.products (product_id, name, size, price) values (now(), 'Caramel Macchiato (Large)', 9, 9.00) IF NOT EXISTS;

```

## backend:
``` bash
cd backend
# install dependencies
mvn clean install
# start server
mvn spring-boot:run
```
