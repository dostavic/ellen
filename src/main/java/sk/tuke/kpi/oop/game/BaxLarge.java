package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Health;
import sk.tuke.kpi.oop.game.items.*;

import java.util.Objects;

public class BaxLarge extends AbstractActor implements Alive, Enemy {
    private Hammer hammer;
    private Health health;
    private Animation boxLargeAnimation;

    public BaxLarge(){
        boxLargeAnimation = new Animation("sprites/box_large.png");
        setAnimation(boxLargeAnimation);
        health = new Health(1);
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
        this.health.onExhaustion(this::boxCrach);
    }

    public @Nullable Actor boxCrach(){
        getScene().removeActor(this);
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
        double check = (int)(Math.random() * 5);
        System.out.println(check);
        switch ((int) check){
            case 0:
                getScene().addActor(new Hammer(), getPosX(), getPosY());
                break;
            case 1:
//                addedToScene((Scene) new Energy());
                getScene().addActor(new Energy(), getPosX(), getPosY());
                break;
            case 2:
                getScene().addActor(new Energetik(), getPosX(), getPosY());
                break;
            case 3:
                getScene().addActor(new Ammo(), getPosX(), getPosY());
                break;
            case 4:
                getScene().addActor(new AccessCard(), getPosX(), getPosY());
                break;
            default:
                getScene().addActor(new Narko(), getPosX(), getPosY());
        }
        return null;
    }
}
