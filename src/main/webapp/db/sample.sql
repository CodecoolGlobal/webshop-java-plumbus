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
    product_category_id integer,
    supplier_id integer,
    price integer,
    currency integer
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

