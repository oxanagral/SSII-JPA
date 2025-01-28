-- Initialisation des tables
-- Ce fichier ne peut pas être vide
SELECT 0 as INUTILE;

INSERT INTO personne (matricule, nom, prenom, poste) VALUES (1, 'Simpsom', 'Samantha', 'Ingénieure en e-santé');
INSERT INTO personne (matricule, nom, prenom, poste) VALUES (2, 'Ewing', 'Clover', 'Cheffe de projet');
INSERT INTO personne (matricule, nom, prenom, poste) VALUES (3, 'Vasquez', 'Alexandra', 'Développeuse');

INSERT INTO projet (code, nom, debut, fin) VALUES (302, 'Projet A', '2023-03-03', '2023-12-03');
INSERT INTO projet (code, nom, debut, fin) VALUES (303, 'Projet B', '2024-01-01', '2024-12-12');
INSERT INTO projet (code, nom, debut, fin) VALUES (304, 'Projet C', '2024-09-25', NULL);

INSERT INTO participation (id, contributeur_id, projet_id, role, pourcentage) VALUES (1, 1, 302, 'Ingénieure en e-santé', 50.0);
INSERT INTO participation (id, contributeur_id, projet_id, role, pourcentage) VALUES (2, 2, 303, 'Cheffe de projet', 100.0);
INSERT INTO participation (id, contributeur_id, projet_id, role, pourcentage) VALUES (3, 3, 304, 'Développeuse', 75.0);
