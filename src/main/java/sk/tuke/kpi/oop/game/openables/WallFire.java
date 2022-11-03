package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.oop.game.Repairable;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.FireExtinguisher;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.oop.game.walls.WallElektro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WallFire extends AbstractActor implements Openable, Usable<Ripley> , Repairable {
    private boolean isOpen;
    private Animation wallFireH;
    private Animation wallFireV;
    private FireExtinguisher fireExtinguisher;
    private List<Collectible> backpacknew = new ArrayList<>();

    public WallFire(Orientation orientation) {
        isOpen = false;
        if (orientation == Orientation.HORIZONTAL) {
            wallFireH = new Animation("sprites/laser.png", 48, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(wallFireH);
        } else {
            wallFireV = new Animation("sprites/laser.png", 16, 48, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(wallFireV);
        }
    }

    @Override
    public boolean repair() {
        open();
        isOpen = true;
        return isOpen;
    }

    @Override
    public boolean extinguish() {
        return false;
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    @Override
    public void useWith(Ripley actor) {
        if (actor == null)
            return;
        open();
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }

    @Override
    public void open() {
        if (!isOpen) {
            getAnimation().stop();
            isOpen = true;
        }
    }

    @Override
    public void close() {
        if (isOpen) {
            getAnimation().play();
            isOpen = false;
        }
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(
                this::loop
            )
        ).scheduleFor(this);
    }

    public void loop() {
        for (Actor actor : getScene().getActors()) {
            if (actor instanceof Alive && intersects(actor) && !isOpen) {
                ((Alive) actor).getHealth().drain(1);
            }
        }
    }
}


