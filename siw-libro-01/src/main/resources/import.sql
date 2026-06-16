-- =============================================
-- UTENTI
-- (ID generati: 1, 51, 101, 151, 201)
-- =============================================
INSERT INTO utente (id, nome, cognome, email, stato) VALUES (nextval('utente_seq'), 'Marco', 'Rossi', 'marco@readyourmood.it', 'ATTIVO');
INSERT INTO utente (id, nome, cognome, email, stato) VALUES (nextval('utente_seq'), 'Giulia', 'Ferrari', 'giulia@readyourmood.it', 'ATTIVO');
INSERT INTO utente (id, nome, cognome, email, stato) VALUES (nextval('utente_seq'), 'Admin', 'RYM', 'admin@readyourmood.it', 'ATTIVO');

-- =============================================
-- CREDENZIALI
-- Collegati agli utenti tramite utente_id (1, 51, 101, 151, 201)
-- =============================================
INSERT INTO credenziali (id, username, password, ruolo, utente_id) VALUES (nextval('credenziali_seq'), 'marco', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER', 101);
INSERT INTO credenziali (id, username, password, ruolo, utente_id) VALUES (nextval('credenziali_seq'), 'giulia', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER', 151);
INSERT INTO credenziali (id, username, password, ruolo, utente_id) VALUES (nextval('credenziali_seq'), 'admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', 201);
