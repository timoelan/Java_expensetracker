-- ==============================
-- USERS
-- ==============================
INSERT INTO users (id, name, email, createdAt) VALUES (1, 'Max Mustermann', 'max@example.com', '2025-01-01T10:00:00');
INSERT INTO users (id, name, email, createdAt) VALUES (2, 'Anna Schmidt', 'anna@example.com', '2025-01-02T10:00:00');
INSERT INTO users (id, name, email, createdAt) VALUES (3, 'Peter Müller', 'peter@example.com', '2025-01-03T10:00:00');

-- ==============================
-- CATEGORY
-- ==============================
INSERT INTO category (id, name, description, user_id) VALUES (1, 'Lebensmittel', 'Essen und Trinken', 1);
INSERT INTO category (id, name, description, user_id) VALUES (2, 'Transport', 'Auto, Bahn, Bus', 1);
INSERT INTO category (id, name, description, user_id) VALUES (3, 'Freizeit', 'Hobbies und Entertainment', 1);
INSERT INTO category (id, name, description, user_id) VALUES (4, 'Wohnen', 'Miete und Nebenkosten', 1);
INSERT INTO category (id, name, description, user_id) VALUES (5, 'Gesundheit', 'Arzt und Medikamente', 2);
INSERT INTO category (id, name, description, user_id) VALUES (6, 'Shopping', 'Kleidung und Elektronik', 2);
INSERT INTO category (id, name, description, user_id) VALUES (7, 'Bildung', 'Kurse und Bücher', 3);

-- ==============================
-- BUDGETS
-- ==============================
INSERT INTO budget (id, amount, month, year, user_id, category_id) VALUES (1, 500, 1, 2025, 1, 1);
INSERT INTO budget (id, amount, month, year, user_id, category_id) VALUES (2, 200, 1, 2025, 1, 2);
INSERT INTO budget (id, amount, month, year, user_id, category_id) VALUES (3, 150, 1, 2025, 1, 3);
INSERT INTO budget (id, amount, month, year, user_id, category_id) VALUES (4, 1200, 1, 2025, 1, 4);
INSERT INTO budget (id, amount, month, year, user_id, category_id) VALUES (5, 300, 1, 2025, 2, 5);
INSERT INTO budget (id, amount, month, year, user_id, category_id) VALUES (6, 400, 1, 2025, 2, 6);

-- ==============================
-- TRANSACTIONS
-- ==============================
INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (1, 45, 'Supermarkt Einkauf', '2025-01-15T14:30:00', '2025-01-15T14:30:00', 1, 1);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (2, 80, 'Tankstelle', '2025-01-16T08:15:00', '2025-01-16T08:15:00', 1, 2);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (3, 25, 'Kino', '2025-01-17T19:00:00', '2025-01-17T19:00:00', 1, 3);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (4, 1200, 'Miete Januar', '2025-01-01T00:00:00', '2025-01-01T00:00:00', 1, 4);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (5, 60, 'Apotheke', '2025-01-18T10:30:00', '2025-01-18T10:30:00', 2, 5);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (6, 120, 'Neue Schuhe', '2025-01-19T15:45:00', '2025-01-19T15:45:00', 2, 6);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (7, 35, 'Fachbuch', '2025-01-20T12:00:00', '2025-01-20T12:00:00', 3, 7);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (8, 65, 'Restaurant', '2025-01-21T18:30:00', '2025-01-21T18:30:00', 1, 1);

INSERT INTO transaction (id, amount, description, date, createdAt, user_id, category_id) 
VALUES (9, 40, 'Zugticket', '2025-01-22T07:00:00', '2025-01-22T07:00:00', 1, 2);
