CREATE TABLE IF NOT EXISTS utilisateur(

	identifiant_utilisateur varchar(50) PRIMARY KEY,
	sexe_utilisateur varchar(1),
	nom_utilisateur varchar(50),
	prenom_utilisateur varchar(50),
	mail_utilisateur varchar (100),
	mot_de_passe_utilisateur text NOT NULL,
	privilege_utilisateur smallint CHECK(privilege_utilisateur >= 1 AND privilege_utilisateur <= 3),
	date_inscription date DEFAULT current_date
);

CREATE TABLE IF NOT EXISTS client(

	identifiant_client serial PRIMARY KEY,
	nom_client varchar(60),
	num_tel_client varchar(30),
	adresse_client varchar(100)
);

CREATE TABLE IF NOT EXISTS produit(

	identifiant_produit serial PRIMARY KEY,
	nom_produit varchar(60),
	prix_unitaire_produit real CHECK(prix_unitaire_produit >= 0)
);

CREATE TABLE IF NOT EXISTS transaction(

	identifiant_transaction serial PRIMARY KEY,
	identifiant_client int REFERENCES client(identifiant_client),
	identifiant_produit int REFERENCES produit(identifiant_produit),
	nom_transaction varchar(255),
	etat_transaction varchar(60) DEFAULT 'en cours',
	date_transaction date DEFAULT current_date,
	sujet_transaction varchar(255),
	quantite int CHECK(quantite > 0),
	prix real
);

CREATE OR REPLACE FUNCTION proc_trig_transaction_prix() RETURNS TRIGGER AS $$
	/*declare
		prixUnit produit.prix_unitaire_produit%type;

	begin
		raise notice 'Valeur de prixUnit: %', prixUnit;

		select prix_unitaire_produit into prixUnit from transaction inner join produit using (identifiant_produit) where identifiant_produit = new.identifiant_produit;
		raise notice 'new.idProduit: %', new.identifiant_produit;

		raise notice 'Valeur de prixUnit: %', prixUnit;
		raise notice 'Valeur de quantite: %', new.quantite;
		new.prix := new.quantite * prixUnit;
		raise notice 'Valeur de prix: %', new.prix;

		update transaction set prix = new.prix where identifiant_produit = new.identifiant_produit;
		return null;
	end;
	*/
	DECLARE
		prixUnit produit.prix_unitaire_produit%TYPE;
	BEGIN
		SELECT prix_unitaire_produit INTO prixUnit FROM produit WHERE identifiant_produit = new.identifiant_produit;
		new.prix := new.quantite * prixUnit;

		RETURN NEW;
	END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER trig_transaction_prix BEFORE INSERT ON transaction FOR EACH ROW EXECUTE PROCEDURE proc_trig_transaction_prix();

/* mettre le mot clé 'default' pour le serial lors de l'insertion */
/* rajouter les checks dans les tables */
/* faire un trigger pour calculer automatiquement le prix de la transaction*/
/* prix = prixUnitaire du produit * quantite */