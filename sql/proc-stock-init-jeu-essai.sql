CREATE OR REPLACE PROCEDURE init_jeu_essai()
LANGUAGE plpgsql
AS $$
DECLARE
no_jeu INTEGER;
    no_client INTEGER;
    no_exemplaire INTEGER;
BEGIN

DELETE FROM clients;
DELETE FROM adresses;
DELETE FROM jeux_genres;
DELETE FROM exemplaires;
DELETE FROM jeux;
DELETE FROM genres;


-- Genres
INSERT INTO genres (no_genre, libelle) VALUES (1, 'Jeu de plateau');
INSERT INTO genres (no_genre, libelle) VALUES (2, 'Jeu de cartes');
INSERT INTO genres (no_genre, libelle) VALUES (3, 'Jeu de stratégie');
INSERT INTO genres (no_genre, libelle) VALUES (4, 'Coopératif');
INSERT INTO genres (no_genre, libelle) VALUES (5, 'Jeu de dé');
INSERT INTO genres (no_genre, libelle) VALUES (6, 'Jeu d''enquete');


-- Client 1
INSERT INTO adresses(rue, code_postal, ville)
VALUES ('rue des Cormorans', '79000', 'Niort')
    RETURNING no_adresse INTO no_client;

INSERT INTO clients
(nom, prenom, email, no_telephone, no_adresse)
VALUES
    ('Curie', 'Marie', 'marie.curie@example.com', '123456789', no_client);



-- Client 2
INSERT INTO adresses(rue, code_postal, ville)
VALUES ('rue des marguerites', '79500', 'Melle')
    RETURNING no_adresse INTO no_client;

INSERT INTO clients
(nom, prenom, email, no_telephone, no_adresse)
VALUES
    ('Einstein', 'Albert', 'albert.einstein@example.com', '0123456789', no_client);



-- Jeu Pandemic
INSERT INTO jeux
(titre, description, reference, duree, age_min, tarif_jour)
VALUES
    ('Pandemic', 'Descr pandemic', 'refPandemic', 30, 10, 12.5)
    RETURNING no_jeu INTO no_jeu;


INSERT INTO jeux_genres(no_jeu, no_genre)
VALUES(no_jeu, 3);

INSERT INTO jeux_genres(no_jeu, no_genre)
VALUES(no_jeu, 1);



INSERT INTO exemplaires(no_jeu, codebarre, louable)
VALUES(no_jeu, '1111111111111', true);

INSERT INTO exemplaires(no_jeu, codebarre, louable)
VALUES(no_jeu, '2222222222222', false);

INSERT INTO exemplaires(no_jeu, codebarre, louable)
VALUES(no_jeu, '3333333333333', true);



-- Jeu Welcome
INSERT INTO jeux
(titre, description, reference, duree, age_min, tarif_jour)
VALUES
    ('Welcome', 'Descr welcome', 'refWelcome', 30, 10, 9.3)
    RETURNING no_jeu INTO no_jeu;


INSERT INTO jeux_genres(no_jeu, no_genre)
VALUES(no_jeu, 2);

INSERT INTO jeux_genres(no_jeu, no_genre)
VALUES(no_jeu, 3);



INSERT INTO exemplaires(no_jeu, codebarre, louable)
VALUES(no_jeu, '4444444444444', true);

INSERT INTO exemplaires(no_jeu, codebarre, louable)
VALUES(no_jeu, '5555555555555', false);

INSERT INTO exemplaires(no_jeu, codebarre, louable)
VALUES(no_jeu, '6666666666666', true);


END;
$$;