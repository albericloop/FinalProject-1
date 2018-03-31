package fr.android.griseau.finalproject;

public class Match {
    private String player1;
    private String player2;
    private int winner;
    private int score1;
    private int score2;


    public Match(String player1, String player2, int winner, int score1, int score2) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
        this.score1 = score1;
        this.score2 = score2;
    }
    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
