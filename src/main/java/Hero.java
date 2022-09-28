import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {

    public Hero(int x, int y) {
        position.setX_(x);
        position.setY_(y);
    }

    private Position position;

    public void setPosition(Position position_){
        position.setX_(position_.getX_());
        position.setY_(position_.getY_());
    }

    public void draw(Screen screen) {
        screen.setCharacter(position.getX_(), position.getY_(), TextCharacter.fromCharacter('X')[0]);
    }


    public Position moveUp() { return new Position(position.getX_(), position.getY_() - 1 ); }
    public Position moveDown() { return new Position(position.getX_(), position.getY_() + 1 );  }
    public Position moveLeft() { return new Position(position.getX_() - 1 , position.getY_());  }
    public Position moveRight() { return new Position(position.getX_() + 1 , position.getY_());  }


}
