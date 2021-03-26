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
    };



    public abstract String getTitle();

    public abstract String getFxmlFile();

}
