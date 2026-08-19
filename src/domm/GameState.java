package domm;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable{
    List<String> koriscenaSlova;
    int preostaliPokusaji;
    String revealed;
    String playerName;
    boolean isOver;
    String winner;

    public GameState(List<String> koriscenaSlova, int preostaliPokusaji, String revealed) {
        this.koriscenaSlova = koriscenaSlova;
        this.preostaliPokusaji = preostaliPokusaji;
        this.revealed = revealed;
    }

    public GameState() {
    }

    public List<String> getKoriscenaSlova() {
        return koriscenaSlova;
    }

    public void setKoriscenaSlova(List<String> koriscenaSlova) {
        this.koriscenaSlova = koriscenaSlova;
    }

    public int getPreostaliPokusaji() {
        return preostaliPokusaji;
    }

    public void setPreostaliPokusaji(int preostaliPokusaji) {
        this.preostaliPokusaji = preostaliPokusaji;
    }

    public String getRevealed() {
        return revealed;
    }

    public void setRevealed(String revealed) {
        this.revealed = revealed;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isIsOver() {
        return isOver;
    }

    public void setIsOver(boolean isOver) {
        this.isOver = isOver;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
