package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;
import sk.tuke.kpi.oop.game.scenarios.FirstSteps;
import sk.tuke.kpi.oop.game.scenarios.MapRoom;
import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;
//import sk.tuke.kpi.oop.game.

public class Main {
    public static void main(String[] args) {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);
        Game game = new GameApplication(windowSetup, new LwjglBackend());
        //Scene missionImpossible = new World("World", "maps/mission-impossible.tmx", new MissionImpossible.Factory());
        Scene escapeRoom = new World("World", "maps/map.tmx", new MapRoom.Factory());
        //game.addScene(missionImpossible);
        //escapeRoom.centerOn(0, 0);
        game.addScene(escapeRoom);
        //game

        //new
        //FirstSteps firstSteps = new FirstSteps();
        //missionImpossible.addListener(new MissionImpossible());
        escapeRoom.addListener(new MapRoom());
        //missionImpossible.addListener(new MissionImpossible());
        //scene.addListener(new MissionImpossible());

        game.start();
        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
    }
}
