import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;

    Hero hero = new Hero(10,10);

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    private void processKey (KeyStroke key) throws IOException {
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp()); break;
            case ArrowDown:
                moveHero(hero.moveDown()); break;
            case ArrowLeft:
                moveHero(hero.moveLeft()); break;
            case ArrowRight:
                moveHero(hero.moveRight()); break;
            case Character:
                if (key.getCharacter() == 'q') {screen.close(); break;}


        }
        
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {}

    }


    private void moveHero(Position position) {
        hero.setPosition(position);
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType()==KeyType.EOF) break;
        }
    }

}
