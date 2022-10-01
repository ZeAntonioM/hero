import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {

    protected Position position = new Position(0,0);

    public void setPosition(Position position_){
        position.setX_(position_.getX_());
        position.setY_(position_.getY_());
    }

    public Position getPosition() {
        return position;
    }

    public Element(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics graphics);


}
