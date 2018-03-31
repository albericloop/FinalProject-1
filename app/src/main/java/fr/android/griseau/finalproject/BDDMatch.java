package fr.android.griseau.finalproject;

/**
 * Created by alberic on 31/03/2018.
 */

public class BDDMatch {
        private long id;
        private String comment;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getBDDMatch() {
            return comment;
        }

        public void setBDDMatch(String comment) {
            this.comment = comment;
        }

        // Sera utilisée par ArrayAdapter dans la ListView
        @Override
        public String toString() {
            return comment;
        }
    }