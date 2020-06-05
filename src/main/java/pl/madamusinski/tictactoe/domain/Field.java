package pl.madamusinski.tictactoe.domain;

public class Field {
    private String coordinate;
    private char signDisplay;

    public Field(String coordinate, char signDisplay){
        this.coordinate = coordinate;
        this.signDisplay = signDisplay;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public char getSignDisplay() {
        return signDisplay;
    }

    public void setSignDisplay(char signDisplay) {
        this.signDisplay = signDisplay;
    }
}
