create table if not exists utilisateur(
	
	idUser serial primary key,
	login varchar(50) unique not null,
	nomUtilisateur varchar(50),
	prenomUtilisateur varchar(50),
	mail varchar (100),
	password text not null,
	rights int check(rights >= 1 and rights <= 3)
);

create table if not exists client(

	idClient serial primary key,
	nomClient varchar(60),
	numTel varchar(30)
);

create table if not exists produit(
	
	idProduit serial primary key,
	nom varchar(60),
	prixUnitaire real check(prixUnitaire >= 0)
);

create table if not exists transaction(

	idTransac serial primary key,
	idClient int references client(idClient),
	idProduit int references produit(idProduit),
	nomTransac varchar(255),
	etatTransac varchar(60) default 'en cours',
	dateTransaction date default current_date,
	sujetTransac varchar(255),
	quantite int check(quantite > 0),
	prix real
);

create or replace function proc_trig_transaction_prix() returns trigger as $$
	declare
		prixUnit produit.prixUnitaire%type;

	begin
		select into prixUnit prixUnitaire from transaction inner join produit using (idProduit) where idProduit = new.idProduit;
		new.prix = new.quantite * prixUnit;
		raise notice 'Valeur de prix: %', new.prix;
		return new;
	end;
$$ language 'plpgsql';

create trigger trig_transaction_prix before insert on transaction for each row execute procedure proc_trig_transaction_prix();

/* mettre le mot clé 'default' pour le serial lors de l'insertion */
/* rajouter les checks dans les tables */
/* faire un trigger pour calculer automatiquement le prix de la transaction*/
/* prix = prixUnitaire du produit * quantite */