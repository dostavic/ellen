package sk.tuke.kpi.oop.game.walls;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;

import java.util.Objects;

public class WallElektro extends AbstractActor {
    private Animation wallElektroV;
    private Animation wallElektroH;

    public enum Orientation{
        HORIZONTAL,
        VERTICAL
    }

    public WallElektro(Orientation orientation){
        if(orientation == Orientation.HORIZONTAL){
            wallElektroH = new Animation("sprites/electricity.png", 48, 64, 0.1f, Animation.PlayMode.LOOP);
            setAnimation(wallElektroH);
        } else {
            wallElektroV = new Animation("sprites/electricity.png", 16, 48, 0.1f, Animation.PlayMode.LOOP);
            setAnimation(wallElektroV);
        }
    }

    public WallElektro(String name , Orientation orientation){
        super(name);
        if(orientation == Orientation.HORIZONTAL){
            wallElektroH = new Animation("sprites/electricity.png", 48, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(wallElektroH);
        } else {
            wallElektroV = new Animation("sprites/electricity.png", 16, 48, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(wallElektroV);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
        getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 2).setType(MapTile.Type.WALL);
        scene.getActors().forEach(
            actor -> {
                if(actor instanceof Alive && !(actor instanceof Enemy) && (getAnimation().getFrameDuration() == 0.1f)/* && intersects(actor)*/) {
                    Alive aliveActor = (Alive) actor;
                    /*disposable = */new Loop<>(
                        new When<>(
                            () -> actor.intersects(this),
                            //this.health.getValue()>0),
                            new Invoke<>(
                                () -> aliveActor.getHealth().drain(1)
                            )
                        )
                    ).scheduleOn(this.getScene());
                }
            }
        );
    }
}
