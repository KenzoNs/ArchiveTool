package application.view;

public enum ErrorMessages {

    MISS_FIELD_ERROR {
        @Override
        public String getText() {
            return "Tous les champs ne sont pas remplis";
        }
    },

    INCORRECT_CREDENTIALS{
        @Override
        public String getText() {
            return "L'identifiant ou le mot de passe est incorrect";
        }
    },

    INCORRECT_FLOAT_TYPE{
        @Override
        public String getText() {
            return "Le format n'est pas un nombre flottant";
        }
    },

    INCORRECT_PHONE_NUMBER_FORMAT_TYPE {
        @Override
        public String getText() {
            return "Le format du numéro de téléphone est incorrect. Format valide: 00 00 00 00 00";
        }
    };

    public abstract String getText();

}
