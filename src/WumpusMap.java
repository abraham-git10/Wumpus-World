public class WumpusMap {
    public static int NUM_ROWS = 10;
    public static int NUM_COLUMNS = 10;
    public static int NUM_PITS = 10;
    private WumpusSquare[][] grid;
    private int ladderC;
    private int ladderR;
    public WumpusMap() {
        createMap();
    }
    public void createMap() {
        grid = new WumpusSquare[NUM_ROWS][NUM_COLUMNS];
        for(int row = 0; row < 10; row++) {
            for(int column = 0; column < 10; column++) {
                grid[row][column] = new WumpusSquare();
            }
        }
        for(int placedPits = 0; placedPits < 10; placedPits++) {
            int row = (int) (Math.random() * 10);
            int column = (int) (Math.random() * 10);
            if (grid[row][column].toString().equals("*")) {
                grid[row][column].setPit(true);
                if (row == 0 && column == 0) {
                    grid[row + 1][column].setBreeze(true);
                    grid[row][column + 1].setBreeze(true);
                }
                else if(row == 9 && column == 9) {
                    grid[row - 1][column].setBreeze(true);
                    grid[row][column - 1].setBreeze(true);
                }
                else if(row == 0 && column == 9) {
                    grid[row + 1][column].setBreeze(true);
                    grid[row][column - 1].setBreeze(true);
                }
                else if(row == 9 && column == 0) {
                    grid[row - 1][column].setBreeze(true);
                    grid[row][column + 1].setBreeze(true);
                }
                else if (row == 0) {
                    grid[row + 1][column].setBreeze(true);
                    grid[row][column + 1].setBreeze(true);
                    grid[row][column - 1].setBreeze(true);
                } else if (column == 0) {
                    grid[row + 1][column].setBreeze(true);
                    grid[row - 1][column].setBreeze(true);
                    grid[row][column + 1].setBreeze(true);
                }
                else if (row == 9) {
                    grid[row - 1][column].setBreeze(true);
                    grid[row][column + 1].setBreeze(true);
                    grid[row][column - 1].setBreeze(true);
                } else if (column == 9) {
                    grid[row + 1][column].setBreeze(true);
                    grid[row - 1][column].setBreeze(true);
                    grid[row][column - 1].setBreeze(true);
                } else {
                    grid[row + 1][column].setBreeze(true);
                    grid[row - 1][column].setBreeze(true);
                    grid[row][column + 1].setBreeze(true);
                    grid[row][column - 1].setBreeze(true);
                }
            } else {
                placedPits--;
            }
        }
        boolean goldPlaced = false;
        while (!goldPlaced) {
            int row = (int) (Math.random() * 10);
            int column = (int) (Math.random() * 10);
            if (!grid[row][column].getPit()) {
                grid[row][column].setGold(true);
                goldPlaced = true;
            }
        }
        boolean wumpusPlaced = true;
        while (wumpusPlaced) {
            int row = (int) (Math.random() * 10);
            int column = (int) (Math.random() * 10);
            if (!grid[row][column].getPit()) {
                grid[row][column].setWumpus(true);
                if (row == 0 && column == 0) {
                    grid[row + 1][column].setStench(true);
                    grid[row][column + 1].setStench(true);
                }
                else if(row == 9 && column == 9) {
                    grid[row - 1][column].setStench(true);
                    grid[row][column - 1].setStench(true);
                }
                else if(row == 0 && column == 9) {
                    grid[row + 1][column].setStench(true);
                    grid[row][column - 1].setStench(true);
                }
                else if(row == 9 && column == 0) {
                    grid[row - 1][column].setStench(true);
                    grid[row][column + 1].setStench(true);
                }
                else if (row == 0) {
                    grid[row + 1][column].setStench(true);
                    grid[row][column + 1].setStench(true);
                    grid[row][column - 1].setStench(true);
                } else if (column == 0) {
                    grid[row + 1][column].setStench(true);
                    grid[row - 1][column].setStench(true);
                    grid[row][column + 1].setStench(true);
                }
                else if (row == 9) {
                    grid[row - 1][column].setStench(true);
                    grid[row][column + 1].setStench(true);
                    grid[row][column - 1].setStench(true);
                } else if (column == 9) {
                    grid[row + 1][column].setStench(true);
                    grid[row - 1][column].setStench(true);
                    grid[row][column - 1].setStench(true);
                } else {
                    grid[row + 1][column].setStench(true);
                    grid[row - 1][column].setStench(true);
                    grid[row][column + 1].setStench(true);
                    grid[row][column - 1].setStench(true);
                }
                wumpusPlaced = false;
            }
        }
        boolean ladderPlaced = false;
        while (!ladderPlaced) {
            int row = (int) (Math.random() * 10);
            int column = (int) (Math.random() * 10);
            if (!grid[row][column].getPit() && !grid[row][column].getGold() && !grid[row][column].getWumpus()) {
                grid[row][column].setLadder(true);
                ladderR = row;
                ladderC = column;
                ladderPlaced = true;
            }
        }
    }
    public int getLadderCol() {
        return ladderC;
    }
    public int getLadderRow() {
        return ladderR;
    }
    public WumpusSquare getSquare(int col, int row) {
        if(col > 9 || row > 9 || col < 0 || row < 0) {
            return null;
        }
        return grid[row][col];
    }
    public String toString() {
        String a = "";
        for(int r = 0;r < 10;r++) {
            for(int c = 0;c < 10;c++) {
                a += grid[r][c];
            }
            a += "\n";
        }
        return a;
    }
}