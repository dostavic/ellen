package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class LazerBullet extends AbstractActor implements Fireable {
    private Animation lazerAnimation;
    private int speed;

    public LazerBullet(){
        lazerAnimation = new Animation("stprites/laser_beam.png", 16, 50);
        setAnimation(lazerAnimation);
        this.speed = 10;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction){
        Fireable.super.startedMoving(direction);
        lazerAnimation.setRotation(direction.setAngaleA());
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(
                this::loop
            )
        ).scheduleFor(this);
    }

    public void loop(){
        for(Actor actor : getScene().getActors()){
            if(actor instanceof Alive && intersects(actor) && !(actor instanceof Ripley)){
                ((Alive) actor).getHealth().drain(100);
                //collidedWithWall();
            }
        }
    }
}
