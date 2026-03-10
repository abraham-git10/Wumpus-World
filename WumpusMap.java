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
        for(int p = 0;p < 10;p++) {
            for(int y = 0;y < 10;y++) {
                grid[p][y] = new WumpusSquare();
            }
        }
        for(int i = 0;i < 10;i++) {
            int r = (int) (Math.random() * 10);
            int c = (int) (Math.random() * 10);
            if (grid[r][c].toString().equals("*")) {
                grid[r][c].setPit(true);
                if (r == 0 && c == 0) {
                    grid[r + 1][c].setBreeze(true);
                    grid[r][c + 1].setBreeze(true);
                }
                else if(r == 9 && c == 9) {
                    grid[r - 1][c].setBreeze(true);
                    grid[r][c - 1].setBreeze(true);
                }
                else if(r == 0 && c == 9) {
                    grid[r + 1][c].setBreeze(true);
                    grid[r][c - 1].setBreeze(true);
                }
                else if(r == 9 && c == 0) {
                    grid[r - 1][c].setBreeze(true);
                    grid[r][c + 1].setBreeze(true);
                }
                else if (r == 0) {
                    grid[r + 1][c].setBreeze(true);
                    grid[r][c + 1].setBreeze(true);
                    grid[r][c - 1].setBreeze(true);
                } else if (c == 0) {
                    grid[r + 1][c].setBreeze(true);
                    grid[r - 1][c].setBreeze(true);
                    grid[r][c + 1].setBreeze(true);
                }
                else if (r == 9) {
                    grid[r - 1][c].setBreeze(true);
                    grid[r][c + 1].setBreeze(true);
                    grid[r][c - 1].setBreeze(true);
                } else if (c == 9) {
                    grid[r + 1][c].setBreeze(true);
                    grid[r - 1][c].setBreeze(true);
                    grid[r][c - 1].setBreeze(true);
                } else {
                    grid[r + 1][c].setBreeze(true);
                    grid[r - 1][c].setBreeze(true);
                    grid[r][c + 1].setBreeze(true);
                    grid[r][c - 1].setBreeze(true);
                }
            } else {
                i--;
            }
        }
        boolean a = true;
        while (a) {
            int r = (int) (Math.random() * 10);
            int c = (int) (Math.random() * 10);
            if (!grid[r][c].getPit()) {
                grid[r][c].setGold(true);
                a = false;
            }
        }
        boolean b = true;
        while (b) {
            int r = (int) (Math.random() * 10);
            int c = (int) (Math.random() * 10);
            if (!grid[r][c].getPit()) {
                grid[r][c].setWumpus(true);
                if (r == 0 && c == 0) {
                    grid[r + 1][c].setStench(true);
                    grid[r][c + 1].setStench(true);
                }
                else if(r == 9 && c == 9) {
                    grid[r - 1][c].setStench(true);
                    grid[r][c - 1].setStench(true);
                }
                else if(r == 0 && c == 9) {
                    grid[r + 1][c].setStench(true);
                    grid[r][c - 1].setStench(true);
                }
                else if(r == 9 && c == 0) {
                    grid[r - 1][c].setStench(true);
                    grid[r][c + 1].setStench(true);
                }
                else if (r == 0) {
                    grid[r + 1][c].setStench(true);
                    grid[r][c + 1].setStench(true);
                    grid[r][c - 1].setStench(true);
                } else if (c == 0) {
                    grid[r + 1][c].setStench(true);
                    grid[r - 1][c].setStench(true);
                    grid[r][c + 1].setStench(true);
                }
                else if (r == 9) {
                    grid[r - 1][c].setStench(true);
                    grid[r][c + 1].setStench(true);
                    grid[r][c - 1].setStench(true);
                } else if (c == 9) {
                    grid[r + 1][c].setStench(true);
                    grid[r - 1][c].setStench(true);
                    grid[r][c - 1].setStench(true);
                } else {
                    grid[r + 1][c].setStench(true);
                    grid[r - 1][c].setStench(true);
                    grid[r][c + 1].setStench(true);
                    grid[r][c - 1].setStench(true);
                }
                b = false;
            }
        }
        boolean d = true;
        while (d) {
            int r = (int) (Math.random() * 10);
            int c = (int) (Math.random() * 10);
            if (!grid[r][c].getPit() && !grid[r][c].getGold() && !grid[r][c].getWumpus()) {
                grid[r][c].setLadder(true);
                ladderR = r;
                ladderC = c;
                d = false;
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