import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {

    private int width, height;
    public int score = 0;

    private List<Wall> walls;
    private List<Coin> coins;

    private List<Monster> monsters;

    private List<Wall> createWalls() {
        List <Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;

    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }

    private Monster createMonster() {
        Random random = new Random();
        return new Monster(random.nextInt(width - 2) + 1,
                random.nextInt(height - 2) + 1);
    }
    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters  = new ArrayList<>();
        for (int i = 0; i < 1; i++)
            monsters.add(createMonster());
        return monsters;
    }

    Hero hero = new Hero(10,10);

    public Arena(int width_, int height_) {
        width = width_; height = height_;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            Random random = new Random();

            Position first_position = new Position(monster.position.getX_(), monster.position.getY_());
            Position new_position;

            switch (random.nextInt(5)){
                case 0:
                    new_position = new Position(first_position.getX_() + 1, first_position.getY_());
                    monster.setPosition(new_position);
                    if (!verifyMonsterCollisions()) { break; }

                case 1:
                    new_position = new Position(first_position.getX_() - 1, first_position.getY_());
                    monster.setPosition(new_position);
                    if (!verifyMonsterCollisions()) { break; }

                case 2:
                    new_position = new Position(first_position.getX_() , first_position.getY_() - 1 );
                    monster.setPosition(new_position);
                    if (!verifyMonsterCollisions()) {break; }

                case 3:
                    new_position = new Position(first_position.getX_(), first_position.getY_() + 1);
                    monster.setPosition(new_position);
                    if (!verifyMonsterCollisions()) { break; }
                case 4:
                    monster.setPosition(first_position);
                    break;
            }


        }
    }

    public boolean verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (hero.position.equals(monster.position)) return true;
            for (Wall wall : walls) {
                if (monster.position.equals(wall.position)) return true;
            }
        }
        return false;
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls)
            if ( wall.getPosition().equals(position) ) return false;
        return true;
    }

    public void retrieveCoins() {
        for (Coin coin : coins)
            if (hero.position.equals(coin.position) ) {
                coins.remove(coin);
                score++;
                System.out.println("Score: " + score);
                monsters.add(createMonster());
                break;
            }
    }

    public void draw(TextGraphics graphics) {
        moveMonsters();
        retrieveCoins();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        if (coins.isEmpty()) this.coins = createCoins();
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster:monsters)
            monster.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
    }

    public void processKey(KeyStroke key, Screen screen) throws IOException{
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case Character:
                if (key.getCharacter() == 'q') {
                    screen.close();
                    break;
                }

        }
    }




}
