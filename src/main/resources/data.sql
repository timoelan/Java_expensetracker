-- ==============================
-- CATEGORY
-- ==============================
INSERT INTO category (id, title) VALUES (1, 'Arbeit');
INSERT INTO category (id, title) VALUES (2, 'Pause');
INSERT INTO category (id, title) VALUES (3, 'Projekt Meeting');
INSERT INTO category (id, title) VALUES (4, 'Home Office');
INSERT INTO category (id, title) VALUES (5, 'Kundentermin');
INSERT INTO category (id, title) VALUES (6, 'Training / Weiterbildung');
INSERT INTO category (id, title) VALUES (7, 'Support');
INSERT INTO category (id, title) VALUES (8, 'Entwicklung');
INSERT INTO category (id, title) VALUES (9, 'Planung');

-- ==============================
-- TAGS
-- ==============================
INSERT INTO tag (id, title) VALUES (1, 'Priorität Hoch');
INSERT INTO tag (id, title) VALUES (2, 'Extern');
INSERT INTO tag (id, title) VALUES (3, 'Intern');
INSERT INTO tag (id, title) VALUES (4, 'Remote');
INSERT INTO tag (id, title) VALUES (5, 'Vor Ort');
INSERT INTO tag (id, title) VALUES (6, 'Bugfixing');
INSERT INTO tag (id, title) VALUES (7, 'Dokumentation');
INSERT INTO tag (id, title) VALUES (8, 'Kunde A');
INSERT INTO tag (id, title) VALUES (9, 'Kunde B');
INSERT INTO tag (id, title) VALUES (10, 'Testing');
INSERT INTO tag (id, title) VALUES (11, 'Meeting');
INSERT INTO tag (id, title) VALUES (12, 'Neues Feature');

-- ==============================
-- ENTRIES
-- ==============================
INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (1, '2025-03-10T08:00:00', '2025-03-10T12:00:00', 1);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (2, '2025-03-10T13:00:00', '2025-03-10T17:30:00', 8);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (3, '2025-03-11T09:00:00', '2025-03-11T11:00:00', 3);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (4, '2025-03-11T11:00:00', '2025-03-11T11:30:00', 2);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (5, '2025-03-11T12:00:00', '2025-03-11T16:45:00', 8);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (6, '2025-03-12T08:15:00', '2025-03-12T10:45:00', 6);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (7, '2025-03-12T11:00:00', '2025-03-12T12:00:00', 7);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (8, '2025-03-12T13:00:00', '2025-03-12T17:00:00', 9);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (9, '2025-03-13T07:55:00', '2025-03-13T12:10:00', 1);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (10, '2025-03-13T13:00:00', '2025-03-13T16:00:00', 5);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (11, '2025-03-14T09:00:00', '2025-03-14T11:30:00', 4);

INSERT INTO entry (id, checkIn, checkOut, category_id)
VALUES (12, '2025-03-14T12:00:00', '2025-03-14T17:00:00', 8);

-- ==============================
-- ENTRY TAGS (Many-to-Many)
-- ==============================
-- Entry 1: Arbeit
INSERT INTO entry_tags (entry_id, tag_id) VALUES (1, 1);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (1, 3);

-- Entry 2: Entwicklung
INSERT INTO entry_tags (entry_id, tag_id) VALUES (2, 6);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (2, 12);

-- Entry 3: Meeting
INSERT INTO entry_tags (entry_id, tag_id) VALUES (3, 2);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (3, 11);

-- Entry 4: Pause
INSERT INTO entry_tags (entry_id, tag_id) VALUES (4, 3);

-- Entry 5: Feature Entwicklung
INSERT INTO entry_tags (entry_id, tag_id) VALUES (5, 12);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (5, 10);

-- Entry 6: Training
INSERT INTO entry_tags (entry_id, tag_id) VALUES (6, 7);

-- Entry 7: Support
INSERT INTO entry_tags (entry_id, tag_id) VALUES (7, 6);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (7, 8);

-- Entry 8: Planung
INSERT INTO entry_tags (entry_id, tag_id) VALUES (8, 7);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (8, 11);

-- Entry 9: Arbeit (Kunde B)
INSERT INTO entry_tags (entry_id, tag_id) VALUES (9, 9);

-- Entry 10: Kundentermin
INSERT INTO entry_tags (entry_id, tag_id) VALUES (10, 5);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (10, 2);

-- Entry 11: Home Office
INSERT INTO entry_tags (entry_id, tag_id) VALUES (11, 4);

-- Entry 12: Entwicklung / Testing
INSERT INTO entry_tags (entry_id, tag_id) VALUES (12, 10);
INSERT INTO entry_tags (entry_id, tag_id) VALUES (12, 12);
