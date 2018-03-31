package fr.android.griseau.finalproject;

import static java.lang.Integer.valueOf;

/**
 * Created by alberic on 31/03/2018.
 */

public class BDDMatch {
        private long id;
        private String comment;
        private String DBID;
        private String team1;
        private String score1;
        private String team2;
        private String score2;
        private String ID;
        private String date;
        private String winner;
        private String location;



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

        public String getPlayer1() {
        return team1;
    }

        public void setPlayer1(String player1) {
        this.team1 = player1;
    }

        public String getPlayer2() {
        return team2;
    }

        public void setPlayer2(String player2) {
        this.team2 = player2;
    }





        public int getScore1() {
            return Integer.valueOf(score1);
        }

        public int getScore2() {
            return Integer.valueOf(score2);
        }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

        public int getWinner() {
            if(valueOf(score1)>valueOf(score2)){
                return 1;
            }else{
                return 2;

            }


        }


        public void setWinner(String winner) {
            this.winner = winner;
        }

        }