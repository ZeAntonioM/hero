public class Position {


    public Position(int x, int y) {x_=x; y_=y;}
    private int x_ , y_ ;

    public int getX_() {
        return x_;
    }

    public void setX_(int x_) {
        this.x_ = x_;
    }

    public int getY_() {
        return y_;
    }

    public void setY_(int y_) {
        this.y_ = y_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return x_ == p.getX_() && y_ == p.getY_();
    }
}
