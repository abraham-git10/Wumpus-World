public class WumpusPlayer {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    private int direction;
    private boolean arrow;
    private boolean gold;
    private int colPosition;
    private int rowPosition;
    public WumpusPlayer() {
        direction = NORTH;
        gold = false;
        arrow = true;
    }
    public int getDirection() {
        return direction;
    }
    public boolean getArrow() {
        return arrow;
    }
    public boolean getGold() {
        return gold;
    }
    public int getColPosition() {
        return colPosition;
    }
    public int getRowPosition() {
        return rowPosition;
    }
    public void setDirection(int d) {
        direction = d;
    }
    public void setArrow(boolean b) {
        arrow = b;
    }
    public void setGold(boolean g) {
        gold = g;
    }
    public void setColPosition(int i) {
        colPosition = i;
    }
    public void setRowPosition(int i) {
        rowPosition = i;
    }
}
