INSERT INTO produit VALUES(DEFAULT, 'je_suis_un_nom', 19.5);
INSERT INTO produit VALUES(DEFAULT, 'Produit2', 18.5);
INSERT INTO client VALUES (DEFAULT, 'Haris', '06 51 79 46 88', 'je suis une adresse');
INSERT INTO transaction (identifiant_transaction, identifiant_client, identifiant_produit, nom_transaction, etat_transaction, date_transaction, sujet_transaction, quantite) VALUES (DEFAULT, 1, 1, 'Transaction Test', DEFAULT, DEFAULT, 'sujet de transaction', 5);
INSERT INTO transaction (identifiant_transaction, identifiant_client, identifiant_produit, nom_transaction, etat_transaction, date_transaction, sujet_transaction, quantite) VALUES (DEFAULT, 1, 2, 'Transaction Test 2', DEFAULT, DEFAULT, 'sujet de transaction 2', 3);
INSERT INTO utilisateur VALUES ('knunes', 'h', 'Nunes', 'Kenzo', 'mail', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 3, DEFAULT);
INSERT INTO utilisateur VALUES ('hamarouche', 'h', 'Amarouche', 'Haris', 'mail', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 3, DEFAULT);
INSERT INTO utilisateur VALUES ('testFemme', 'f', 'Test', 'Femme', 'mail', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1, DEFAULT);
INSERT INTO utilisateur VALUES ('testFemme2', 'f', 'Test', 'Femme2', 'mail', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 2, DEFAULT);