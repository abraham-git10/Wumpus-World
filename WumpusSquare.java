public class WumpusSquare {
    private boolean gold;
    private boolean ladder;
    private boolean pit;
    private boolean breeze;
    private boolean wumpus;
    private boolean deadWumpus;
    private boolean stench;
    private boolean visited;
    public WumpusSquare() {
        gold = false;
        ladder = false;
        pit = false;
        breeze = false;
        wumpus = false;
        deadWumpus = false;
        stench = false;
        visited = false;
    }
    public boolean getGold() {
        return gold;
    }
    public boolean getLadder() {
        return ladder;
    }
    public boolean getPit() {
        return pit;
    }
    public boolean getBreeze() {
        return breeze;
    }
    public boolean getWumpus() {
        return wumpus;
    }
    public boolean getDeadWumpus() {
        return deadWumpus;
    }
    public boolean getStench() {
        return stench;
    }
    public boolean getVisited() {
        return visited;
    }
    public void setGold(boolean a) {
        gold = a;
    }
    public void setLadder(boolean a) {
        ladder = a;
    }
    public void setPit(boolean a) {
        pit = a;
    }
    public void setBreeze(boolean a) {
        breeze = a;
    }
    public void setWumpus(boolean a) {
        wumpus = a;
    }
    public void setDeadWumpus(boolean a) {
        deadWumpus = a;
    }
    public void setStench(boolean a) {
        stench = a;
    }
    public void setVisited(boolean a) {
        visited = a;
    }
    public String toString() {
        if(getGold() == true && getWumpus() == true) {
            return "W";
        }
        if(getWumpus() == true) {
            return "w";
        }
        if(getDeadWumpus() == true) {
            return "D";
        }
        if(getLadder() == true) {
            return "L";
        }
        if(getPit() == true) {
            return "P";
        }
        if(getGold() == true) {
            return "G";
        }
        return "*";
    }
}
