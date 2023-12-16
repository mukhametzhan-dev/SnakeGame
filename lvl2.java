public class lvl2 {
    private int x;
    private int y;
    private int deltaX; 
    private int deltaY; 

    public lvl2(int x, int y, int deltaX, int deltaY) {
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {

        x += deltaX;
        y += deltaY;
    }
}