ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart DROP CONSTRAINT IF EXISTS pk_cart_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.users DROP CONSTRAINT IF EXISTS pk_users_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.order DROP CONSTRAINT IF EXISTS pk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS pk_product_category_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart DROP CONSTRAINT IF EXISTS fk_users_id CASCADE;



DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS public.users_id_seq;
CREATE TABLE users (
    id serial NOT NULL,
    name text,
    password text
);

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_users_id PRIMARY KEY (id);

DROP TABLE IF EXISTS public.supplier;
DROP SEQUENCE IF EXISTS public.supplier_id_seq;
CREATE TABLE supplier (
    id serial NOT NULL,
    name text,
    description text
);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);

DROP TABLE IF EXISTS public.product_category;
DROP SEQUENCE IF EXISTS public.product_category_id_seq;
CREATE TABLE product_category (
    id serial NOT NULL,
    name text,
    department text,
    description text
);

ALTER TABLE ONLY product_category
    ADD CONSTRAINT pk_product_category_id PRIMARY KEY (id);

DROP TABLE IF EXISTS public.product;
DROP SEQUENCE IF EXISTS public.product_id_seq;
CREATE TABLE product (
    id serial NOT NULL,
    name text,
    description text,
    product_category_id integer,
    supplier_id integer,
    price float,
    currency text
);

ALTER TABLE ONLY product
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES product(id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier(id);

DROP TABLE IF EXISTS public.cart;
DROP SEQUENCE IF EXISTS public.cart_seq;
CREATE TABLE cart (
    id serial NOT NULL,
    product_id integer,
    users_id integer,
    quantity integer
);

ALTER TABLE ONLY cart
    ADD CONSTRAINT pk_cart_id PRIMARY KEY (id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);

DROP TABLE IF EXISTS public.orders;
DROP SEQUENCE IF EXISTS public.orders_seq;
CREATE TABLE orders (
    id serial NOT NULL,
    product_id integer,
    users_id integer,
    quantity integer,
    date timestamp not null default CURRENT_TIMESTAMP
);

ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_orders_id PRIMARY KEY (id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);





INSERT INTO public.users (id, name, password) VALUES (1, 'admin', '$2b$12$olSVRWsB/1U/zexb3Oemced1Dn14OGH.ISfXMHWQFXpmCTF910DlO');
SELECT pg_catalog.setval('users_id_seq', 1, true);

INSERT INTO public.product_category (id, name, department, description) VALUES (1, 'Single Board Computer', 'Hardware', 'Single board computers are used for lightweight tasks, they need very little amount of power.');
INSERT INTO public.product_category (id, name, department, description) VALUES (2, 'Notebook', 'Hardware', 'Notebooks are reliable way of having a computer with you on the go.');
INSERT INTO public.product_category (id, name, department, description) VALUES (3, 'Graphics Card', 'Hardware', 'Graphics card are mainly used for gaming, it boosts the graphical processing power of the computer');
INSERT INTO public.product_category (id, name, department, description) VALUES (4, 'CPU', 'Hardware', 'Central processing units are the brain of computers');
SELECT pg_catalog.setval('product_category_id_seq', 4, true);

INSERT INTO public.supplier (id, name, description) VALUES (1, 'Amazon', 'Digital content and services');
INSERT INTO public.supplier (id, name, description) VALUES (2, 'Lenovo', 'Notebooks');
INSERT INTO public.supplier (id, name, description) VALUES (3, 'RaspberryPi', 'Single board computers');
INSERT INTO public.supplier (id, name, description) VALUES (4, 'Arduino', 'Micro controllers');
INSERT INTO public.supplier (id, name, description) VALUES (5, 'ASUS', 'Notebooks');
INSERT INTO public.supplier (id, name, description) VALUES (6, 'Apple', 'Consumer electronics');
INSERT INTO public.supplier (id, name, description) VALUES (7, 'Gigabyte', 'Consumer electronics');
INSERT INTO public.supplier (id, name, description) VALUES (8, 'AMD', 'Processing units');
SELECT pg_catalog.setval('supplier_id_seq', 8, true);

INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (1, 'Raspberry Pi 3B', 'The Raspberry Pi 3 Model B is the earliest model of the third-generation Raspberry Pi.', 1, 3, 48.85, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (2, 'Raspberry Pi 3B+', 'The Raspberry Pi 3 Model B+ is the highest performing model in this generation of SBC-s', 1, 3, 55.96, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (3, 'Raspberry Pi 4B [4GB]', 'You''ll recognise the price along with the basic shape and size, so you can simply drop your new Raspberry Pi into your old projects for an upgrade', 1, 3, 76.99, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (4, 'Arduino Uno R3', 'Great for learning the basics of how sensors and actuators work, and an essential tool for your rapid prototyping needs', 1, 4, 18, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (5, 'Lenovo IdeaPad S145', 'This notebook is designed for long time portable performance, with its i5 processor', 2, 2, 466.9, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (6, 'Lenovo Legion Y540', 'This gaming laptop has the top of the line performance for the best price on the market right now, you games definetly won''t lag.', 2, 2, 998.99, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (7, 'ASUS ROG Strix G531GT', 'Join the Republic Of Gamers with this laptop, its cooling solution will definetly last longer then your gaming session', 2, 5, 999.9, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (8, 'Apple MacBook Pro 16', 'Apple''s 16-inch MacBook Pro is basically every creative''s dream machine, with a ton of power and a vastly improved keyboard.', 2, 6, 2999.9, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (9, 'Raspberry Pi 3A+', 'The Raspberry Pi 3 Model A+ is a smaller version of the B model, with less processing power, but it''s nice if you''re in need of space', 1, 3, 24.9, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (10, 'Gigabyte GeForce GTX 1660 Ti OC 6GB', 'This unit has a Windforce cooling system, that keeps the gpu cool for long sessions.', 3, 7, 319.9, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (11, 'GIGABYTE GeForce GTX 1050 Ti OC 4GB', 'Most popular choice among low budget gamers', 3, 7, 165, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (12, 'GIGABYTE GeForce RTX 2060 SUPER AORUS 8GB', 'Nice looking Graphics Card with RGB lighting', 3, 7, 519.9, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (13, 'AMD Ryzen 5 3600 Hexa-Core 3.6GHz AM4 Processor', 'Get the best performance per dollar out of a CPU with this processor', 4, 8, 209.9, 'USD');
INSERT INTO public.product (id, name, description, product_category_id, supplier_id, price, currency) VALUES (14, 'AMD Ryzen 7 3700x Octa-Core 3.6GHz AM4', 'Get the best 8 core CPU on the market right now.', 4, 8, 399.9, 'USD');
SELECT pg_catalog.setval('product_id_seq', 14, true);
