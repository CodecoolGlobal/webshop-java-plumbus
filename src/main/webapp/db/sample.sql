ALTER TABLE IF EXISTS ONLY public.users DROP CONSTRAINT IF EXISTS pk_users_id CASCADE;

DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS public.users_id_seq;
CREATE TABLE users (
    id serial NOT NULL,
    name text,
    password text
);

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_users_id PRIMARY KEY (id);

INSERT INTO public.users (id, name, password) VALUES (1, 'admin', '$2b$12$olSVRWsB/1U/zexb3Oemced1Dn14OGH.ISfXMHWQFXpmCTF910DlO');
SELECT pg_catalog.setval('users_id_seq', 1, true);