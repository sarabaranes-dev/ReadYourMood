-- ReadYourMood sample data (schema + data).
-- Exported from the original development database (June 2026) with pg_dump.
-- Personal data replaced with fictional users; every account uses the demo password: password
-- Load it into an EMPTY database before the first start of the backend.

--
-- PostgreSQL database dump
--




SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 25015)
-- Name: credenziali; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.credenziali (
    id bigint NOT NULL,
    password character varying(255),
    ruolo character varying(255),
    username character varying(255),
    utente_id bigint
);


--
-- TOC entry 214 (class 1259 OID 25010)
-- Name: credenziali_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.credenziali_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 220 (class 1259 OID 25022)
-- Name: libro; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.libro (
    id bigint NOT NULL,
    anno integer,
    autore character varying(255),
    condizione character varying(255),
    data_inserimento date,
    emozione character varying(255),
    foto character varying(255),
    prezzo double precision,
    stato character varying(255),
    titolo character varying(255),
    trama character varying(2000),
    valutazione integer,
    venditore_id bigint
);


--
-- TOC entry 215 (class 1259 OID 25011)
-- Name: libro_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.libro_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 221 (class 1259 OID 25029)
-- Name: ordine; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ordine (
    id bigint NOT NULL,
    data_ordine timestamp(6) without time zone,
    stato character varying(255),
    totale double precision,
    acquirente_id bigint
);


--
-- TOC entry 222 (class 1259 OID 25034)
-- Name: ordine_libri; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ordine_libri (
    ordine_id bigint NOT NULL,
    libri_id bigint NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 25012)
-- Name: ordine_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.ordine_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 223 (class 1259 OID 25037)
-- Name: recensione; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.recensione (
    id bigint NOT NULL,
    commento character varying(1000),
    data_recensione date,
    voto integer,
    acquirente_id bigint,
    ordine_id bigint,
    venditore_id bigint
);


--
-- TOC entry 217 (class 1259 OID 25013)
-- Name: recensione_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.recensione_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 224 (class 1259 OID 25044)
-- Name: utente; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.utente (
    id bigint NOT NULL,
    cognome character varying(255),
    email character varying(255),
    nome character varying(255),
    stato character varying(255)
);


--
-- TOC entry 218 (class 1259 OID 25014)
-- Name: utente_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.utente_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3753 (class 0 OID 25015)
-- Dependencies: 219
-- Data for Name: credenziali; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.credenziali VALUES (102, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'ADMIN', 'admin', 102);
INSERT INTO public.credenziali VALUES (103, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'ADMIN', 'admin2', 103);
INSERT INTO public.credenziali VALUES (104, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'USER', 'luca', 104);
INSERT INTO public.credenziali VALUES (105, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'USER', 'paolo', 105);
INSERT INTO public.credenziali VALUES (106, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'USER', 'marta', 106);
INSERT INTO public.credenziali VALUES (107, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'USER', 'elena', 107);
INSERT INTO public.credenziali VALUES (108, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'USER', 'giorgio', 108);
INSERT INTO public.credenziali VALUES (109, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'USER', 'davide', 109);
INSERT INTO public.credenziali VALUES (152, '$2a$10$.oSD2Rr..TTs/Hro1RSsnuJhs1qC45ZqAU0caM5m69rkInZDnPqwq', 'USER', 'sofia', 152);
--



--
-- TOC entry 3754 (class 0 OID 25022)
-- Dependencies: 220
-- Data for Name: libro; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.libro VALUES (1, 2007, 'Khaled Hosseini', 'COME_NUOVO', '2026-06-05', 'RIFLESSIVO', 'd59f3fb6-7095-47b7-89a2-249f1f8c920c_mille.jpeg', 8, 'DISPONIBILE', 'Mille splendidi soli', 'Due donne afghane legate da un destino comune affrontano guerra, violenza e resilienza.', 5, 102);
INSERT INTO public.libro VALUES (4, 2001, 'Carlos Ruiz Zafón', 'COME_NUOVO', '2026-06-05', 'RIFLESSIVO', 'dd5b6ac1-d016-4654-8ae8-be8f008d1baf_ombra.jpeg', 8, 'DISPONIBILE', 'L''ombra del vento', 'Un ragazzo scopre un libro maledetto nella Barcellona del dopoguerra.', 4, 103);
INSERT INTO public.libro VALUES (5, 1987, 'Haruki Murakami', 'COME_NUOVO', '2026-06-05', 'TRISTE', '98677566-8e43-4e11-81c9-62240b9b33d0_norvegian.jpeg', 10, 'DISPONIBILE', 'Norwegian Wood', 'Amore, perdita e crescita nella Tokyo degli anni ’60.', 5, 103);
INSERT INTO public.libro VALUES (7, 2008, 'Paolo Giordano', 'BUONO', '2026-06-05', 'TRISTE', 'b5d4323d-5ed3-498a-bade-14a65f3d7ed6_solitudine.jpeg', 8, 'DISPONIBILE', 'La solitudine dei numeri primi', 'Due anime fragili incapaci di avvicinarsi davvero, come numeri primi gemelli.', 3, 104);
INSERT INTO public.libro VALUES (8, 2018, 'Tara Westover', 'COME_NUOVO', '2026-06-05', 'RIFLESSIVO', '0b2a3e55-dec3-496f-ac62-1ee62c07d275_educazione.jpeg', 9, 'DISPONIBILE', 'Educazione', 'Una ragazza cresce senza istruzione formale e riesce a costruirsi un futuro.', 5, 104);
INSERT INTO public.libro VALUES (10, 1813, 'Jane Austen', 'COME_NUOVO', '2026-06-05', 'INNAMORATO', '113ab95a-90f5-4d11-a4f1-fd21b36fda9a_orgoglio.webp', 10, 'DISPONIBILE', 'Orgoglio e pregiudizio', 'Una storia d’amore fatta di orgoglio, incomprensioni e crescita emotiva.', 4, 105);
INSERT INTO public.libro VALUES (11, 1949, 'George Orwell', 'COME_NUOVO', '2026-06-05', 'ARRABBIATO', '29735463-d382-42ca-8525-18876c85edb7_1984.jpeg', 10, 'DISPONIBILE', '1984', 'Rabbia e ribellione contro un sistema oppressivo e totalitario', 4, 106);
INSERT INTO public.libro VALUES (9, 1943, 'Antoine de Saint-Exupéry', 'COME_NUOVO', '2026-06-05', 'FELICE', 'dfb18ff1-2d27-4f8d-b90f-0ef7e42cbf53_principe.jpeg', 8, 'VENDUTO', 'Il piccolo principe', 'Una favola poetica che celebra amicizia, amore e semplicità della vita.', 3, 105);
INSERT INTO public.libro VALUES (14, 2016, 'Paolo Cognetti', 'NUOVO', '2026-06-05', 'FELICE', '923cfdec-0170-4a50-acff-9a3660729815_8montagne.jpeg', 13, 'VENDUTO', 'Le otto montagne', 'Felicità semplice tra natura, amicizia e ricerca di equilibrio.', 4, 109);
INSERT INTO public.libro VALUES (2, 2003, 'Khaled Hosseini', 'DISCRETO', '2026-06-05', 'TRISTE', '09bbfbad-607d-4ae9-a335-0e8ec8b2ebc7_cacciatore.jpeg', 5, 'VENDUTO', 'Il cacciatore di aquiloni', 'Amicizia e senso di colpa nella Kabul sconvolta dai cambiamenti politici.', 5, 102);
INSERT INTO public.libro VALUES (3, 2015, 'Paula Hawkins', 'BUONO', '2026-06-05', 'ANSIOSO', 'f062df7b-d3b0-455e-ac4c-6c0c9638d24a_ragazza.jpeg', 9, 'DISPONIBILE', 'La ragazza del treno', 'Una donna ossessionata da una coppia perfetta si ritrova coinvolta in una scomparsa misteriosa.', 3, 102);
INSERT INTO public.libro VALUES (12, 1986, 'Stephen King', 'DISCRETO', '2026-06-05', 'SPAVENTATO', '4dca32e8-7a39-4629-bc4e-d8cc1c29b16d_it.jpeg', 5, 'VENDUTO', 'It', 'Un gruppo di amici affronta una presenza maligna che incarna le loro paure.', 3, 107);
INSERT INTO public.libro VALUES (13, 1963, 'Sylvia Plath', 'ROVINATO', '2026-06-05', 'ANSIOSO', 'd94f2d8c-ecc6-40bd-afe9-1e283712f3d2_la-campana-di-vetro.jpeg', 3, 'VENDUTO', 'La campana di vetro', 'Discesa nell’ansia e nella fragilità mentale di una giovane donna.', 4, 107);
INSERT INTO public.libro VALUES (6, 1953, 'J. D. Salinger', 'COME_NUOVO', '2026-06-05', 'RIFLESSIVO', 'a8ad6497-bdf1-4909-b04f-8bbac666413e_giovane.jpeg', 7.5, 'DISPONIBILE', 'Il giovane Holden', 'Il disagio adolescenziale raccontato attraverso il viaggio di Holden.', 4, 104);
INSERT INTO public.libro VALUES (52, 1970, 'Patrick McGrath', 'COME_NUOVO', '2026-07-02', 'RIFLESSIVO', '03161cd3-87b1-4145-bb9a-95a1ec0c259c_follia.jpeg', 10, 'DISPONIBILE', 'Follia', 'Storia d''amore intrecciata. psicologico. ', 4, 104);
INSERT INTO public.libro VALUES (252, 2006, 'Daniel Glattauer', 'COME_NUOVO', '2026-07-08', 'INNAMORATO', 'ba2cb21a-e6f7-49d8-8fda-a48613187ad3_vento.jpeg', 10, 'DISPONIBILE', 'Le ho mai raccontato del vento del Nord', 'Romanzo d’amore epistolare dell’era Internet, Le ho mai raccontato del vento del Nord descrive la nascita di un legame intenso, di una relazione che coppia non è, ma lo diventata virtualmente. Un rapporto di questo tipo potrà mai sopravvivere a un vero incontro?', 5, 152);


--
-- TOC entry 3755 (class 0 OID 25029)
-- Dependencies: 221
-- Data for Name: ordine; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.ordine VALUES (1, '2026-06-05 15:12:04.922121', 'COMPLETATO', 8, 109);
INSERT INTO public.ordine VALUES (2, '2026-06-05 15:13:12.465723', 'COMPLETATO', 13, 102);
INSERT INTO public.ordine VALUES (52, '2026-06-12 17:29:16.058529', 'COMPLETATO', 5, 105);
INSERT INTO public.ordine VALUES (102, '2026-06-16 13:34:45.736388', 'COMPLETATO', 8, 102);


--
-- TOC entry 3756 (class 0 OID 25034)
-- Dependencies: 222
-- Data for Name: ordine_libri; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.ordine_libri VALUES (1, 9);
INSERT INTO public.ordine_libri VALUES (2, 14);
INSERT INTO public.ordine_libri VALUES (52, 2);
INSERT INTO public.ordine_libri VALUES (102, 12);
INSERT INTO public.ordine_libri VALUES (102, 13);


--
-- TOC entry 3757 (class 0 OID 25037)
-- Dependencies: 223
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.recensione VALUES (1, 'Libro arrivato subito', '2026-06-05', 5, 109, 1, 105);
INSERT INTO public.recensione VALUES (2, 'Tutto perfetto', '2026-06-05', 5, 102, 2, 109);
INSERT INTO public.recensione VALUES (52, 'ordine perfetto ', '2026-06-12', 5, 105, 52, 102);
INSERT INTO public.recensione VALUES (102, 'Ordine arrivato troppo in ritardo e i libri erano completamente rovinati ', '2026-06-16', 1, 102, 102, 107);


--
-- TOC entry 3758 (class 0 OID 25044)
-- Dependencies: 224
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.utente VALUES (102, 'Neri', 'anna@example.com', 'Anna', 'ATTIVO');
INSERT INTO public.utente VALUES (103, 'Conti', 'laura@example.com', 'Laura', 'ATTIVO');
INSERT INTO public.utente VALUES (104, 'Russo', 'luca@example.com', 'Luca', 'ATTIVO');
INSERT INTO public.utente VALUES (105, 'Greco', 'paolo@example.com', 'Paolo', 'BLOCCATO');
INSERT INTO public.utente VALUES (106, 'Galli', 'marta@example.com', 'Marta', 'ATTIVO');
INSERT INTO public.utente VALUES (107, 'Costa', 'elena@example.com', 'Elena', 'ATTIVO');
INSERT INTO public.utente VALUES (108, 'Fontana', 'giorgio@example.com', 'Giorgio', 'ATTIVO');
INSERT INTO public.utente VALUES (109, 'Moretti', 'davide@example.com', 'Davide', 'ATTIVO');
INSERT INTO public.utente VALUES (152, 'Marino', 'sofia@example.com', 'Sofia', 'BLOCCATO');
--



--
-- TOC entry 3764 (class 0 OID 0)
-- Dependencies: 214
-- Name: credenziali_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.credenziali_seq', 201, true);


--
-- TOC entry 3765 (class 0 OID 0)
-- Dependencies: 215
-- Name: libro_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.libro_seq', 501, true);


--
-- TOC entry 3766 (class 0 OID 0)
-- Dependencies: 216
-- Name: ordine_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.ordine_seq', 151, true);


--
-- TOC entry 3767 (class 0 OID 0)
-- Dependencies: 217
-- Name: recensione_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.recensione_seq', 151, true);


--
-- TOC entry 3768 (class 0 OID 0)
-- Dependencies: 218
-- Name: utente_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.utente_seq', 201, true);


--
-- TOC entry 3587 (class 2606 OID 25021)
-- Name: credenziali credenziali_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.credenziali
    ADD CONSTRAINT credenziali_pkey PRIMARY KEY (id);


--
-- TOC entry 3591 (class 2606 OID 25028)
-- Name: libro libro_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (id);


--
-- TOC entry 3593 (class 2606 OID 25033)
-- Name: ordine ordine_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordine
    ADD CONSTRAINT ordine_pkey PRIMARY KEY (id);


--
-- TOC entry 3595 (class 2606 OID 25043)
-- Name: recensione recensione_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_pkey PRIMARY KEY (id);


--
-- TOC entry 3589 (class 2606 OID 25052)
-- Name: credenziali uk_momch2qmcwhvr1f1dvk1kah4w; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.credenziali
    ADD CONSTRAINT uk_momch2qmcwhvr1f1dvk1kah4w UNIQUE (username);


--
-- TOC entry 3597 (class 2606 OID 25050)
-- Name: utente utente_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (id);


--
-- TOC entry 3603 (class 2606 OID 25088)
-- Name: recensione fkjr1vx63155kl5fwedkuf9sjnc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT fkjr1vx63155kl5fwedkuf9sjnc FOREIGN KEY (venditore_id) REFERENCES public.utente(id);


--
-- TOC entry 3598 (class 2606 OID 25053)
-- Name: credenziali fkkacq4taa3oam0bacomgcubkv5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.credenziali
    ADD CONSTRAINT fkkacq4taa3oam0bacomgcubkv5 FOREIGN KEY (utente_id) REFERENCES public.utente(id);


--
-- TOC entry 3601 (class 2606 OID 25068)
-- Name: ordine_libri fkl6gr0uhg8qpa6n5orysq6fnm8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordine_libri
    ADD CONSTRAINT fkl6gr0uhg8qpa6n5orysq6fnm8 FOREIGN KEY (libri_id) REFERENCES public.libro(id);


--
-- TOC entry 3600 (class 2606 OID 25063)
-- Name: ordine fkma48fr363rpk97gclv4q58dcc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordine
    ADD CONSTRAINT fkma48fr363rpk97gclv4q58dcc FOREIGN KEY (acquirente_id) REFERENCES public.utente(id);


--
-- TOC entry 3602 (class 2606 OID 25073)
-- Name: ordine_libri fknxhe73k2oe88bmh1e2lmrtx3r; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordine_libri
    ADD CONSTRAINT fknxhe73k2oe88bmh1e2lmrtx3r FOREIGN KEY (ordine_id) REFERENCES public.ordine(id);


--
-- TOC entry 3604 (class 2606 OID 25078)
-- Name: recensione fkos87glv5rbakyug2kmanwf529; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT fkos87glv5rbakyug2kmanwf529 FOREIGN KEY (acquirente_id) REFERENCES public.utente(id);


--
-- TOC entry 3599 (class 2606 OID 25058)
-- Name: libro fkp9gr4rtden1l849fqc2xj7idu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fkp9gr4rtden1l849fqc2xj7idu FOREIGN KEY (venditore_id) REFERENCES public.utente(id);


--
-- TOC entry 3605 (class 2606 OID 25083)
-- Name: recensione fkq8a3s4bfjt0sge5k5i3u7gkfp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT fkq8a3s4bfjt0sge5k5i3u7gkfp FOREIGN KEY (ordine_id) REFERENCES public.ordine(id);



--
-- PostgreSQL database dump complete
--
