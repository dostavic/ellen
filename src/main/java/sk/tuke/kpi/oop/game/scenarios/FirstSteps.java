package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.walls.WallElektro;

public class FirstSteps implements SceneListener {
    private Ripley ripley;
    private Energy energy;
    private Ammo ammo;
    private Backpack backpack;
    private Hammer hammer;
    private Wrench wrench;
    private FireExtinguisher fireExtinguisher;
    private WallElektro wallElektro;
    /*private Direction direction(int x, int y) {
        this.direction(this.x, this.y);
        return null;
    }

    private int x;
    private int y;*/

    //private Move move = new Move(direction, 2);
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = new Ripley();
        energy = new Energy();
        ammo = new Ammo();
        hammer = new Hammer();
        wrench = new Wrench();
        fireExtinguisher = new FireExtinguisher();
        backpack = new Backpack("Ripley's backback", 10);
        wallElektro = new WallElektro(WallElektro.Orientation.HORIZONTAL);
        //backpack.add(hammer);
        backpack.add(wrench);
        backpack.add(fireExtinguisher);
        //backpack.shift();
        scene.addActor(ripley, 0, 0);
        scene.addActor(energy, -100, 50);
        scene.addActor(ammo, 100, 50);
        scene.addActor(hammer, 150, 50);
        scene.addActor(wallElektro, 150, 100);
        new When<>(() -> ripley.intersects(energy), new Invoke<Actor>(() -> energy.useWith(ripley))).scheduleFor(ripley);
        new When<>(() -> ripley.intersects(ammo), new Invoke<Actor>(() -> ammo.useWith(ripley))).scheduleFor(ripley);
        //Move move = new Move(direction(25, 25), 2f);
        //move.execute(1);

        //Move move = new Move(direction(this.x, this.y), 2f);
        MovableController movableController = new MovableController(ripley);
        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);
        scene.getInput().registerListener(movableController);
    }

    @Override
    public void sceneUpdating(Scene scene){
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("| Energy " +ripley.getHealth().getValue(), 100, yTextPos);
        scene.getGame().getOverlay().drawText("| Ammo " + ripley.getAmmo(), 250, yTextPos);
        scene.getGame().pushActorContainer(backpack);
    }
}
