package fr.android.griseau.finalproject;

public class Match {
    private String player1;
    private String player2;
    private int winner;

    public Match(String player1, String player2, int winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
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
