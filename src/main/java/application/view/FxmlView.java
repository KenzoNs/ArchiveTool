package application.view;

public enum FxmlView {

    LOGIN_MENU {
        @Override
        public String getTitle() {
            return "Menu authentification";
        }

        @Override
        public String getFxmlFile() {
            return "loginMenu.fxml";
        }
    },

    MAIN_MENU {
        @Override
        public String getTitle() {
            return "Menu principal";
        }

        @Override
        public String getFxmlFile() {
            return "mainMenu.fxml";
        }
    },

    ADD_TRANSACTION_MENU {
        @Override
        public String getTitle() {
            return "Ajouter une transaction";
        }

        @Override
        public String getFxmlFile() {
            return "addTransactionMenu.fxml";
        }
    },

    ADD_CLIENT_MENU {
        @Override
        public String getTitle() {
            return "Ajouter un client";
        }

        @Override
        public String getFxmlFile() {
            return "addClientMenu.fxml";
        }
    },

    ADD_ITEM_MENU {
        @Override
        public String getTitle() {
            return "Ajouter un Produit";
        }

        @Override
        public String getFxmlFile() {
            return "addProduitMenu.fxml";
        }
    },

    ADMIN_PANEL_MENU {
        @Override
        public String getTitle() {
            return "Menu administrateur";
        }

        @Override
        public String getFxmlFile() {
            return "adminPanelMenu.fxml";
        }
    },

    USERS_LIST_MENU {
        @Override
        public String getTitle() {
            return "Liste des utilisateurs";
        }

        @Override
        public String getFxmlFile() {
            return "usersListMenu.fxml";
        }
    };



    public abstract String getTitle();

    public abstract String getFxmlFile();

}
