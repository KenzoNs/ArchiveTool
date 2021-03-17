DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS utilisateur;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS produit;
DROP FUNCTION IF EXISTS proc_trig_transaction_prix;
DROP TRIGGER IF EXISTS trig_transaction_prix ON transaction;
