package domain;



/**
 * Created by donnyolijslager on 01-05-16.
 */
public class CommisieLid {


        private String voornaam;
        private String achternaam;


        public CommisieLid(String voornaam, String achternaam) {
            this.voornaam = voornaam;
            this.achternaam = achternaam;
        }

        public String getVoornaam() {
            return voornaam;
        }

        public void setVoornaam(String voornaam) {
            this.voornaam = voornaam;
        }

        public String getAchternaam() {
            return achternaam;
        }

        public void setAchternaam(String achternaam) {
            this.achternaam = achternaam;
        }



}
