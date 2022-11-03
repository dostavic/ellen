package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
//import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Bullet extends AbstractActor implements Fireable {
    private int speed;
    private Animation bulletAnimation;

    public Bullet(){
        bulletAnimation = new Animation("sprites/bullet.png");
        this.speed = 4;
        setAnimation(bulletAnimation);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        Fireable.super.startedMoving(direction);
        bulletAnimation.setRotation(direction.setAngaleA());
    }

    @Override
    public void stoppedMoving() {
        Fireable.super.stoppedMoving();
    }

    @Override
    public void collidedWithWall() {
        Fireable.super.collidedWithWall();
        getScene().removeActor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        /*for(Actor actor : getScene().getActors()){
            if(actor instanceof Alive && intersects(actor) && !(actor instanceof Ripley)){
                ((Alive) actor).getHealth().drain(10);
                scene.removeActor(this);
            }
        }
        loop();*/
        new Loop<>(
            new Invoke<>(
                this::loop
            )
        ).scheduleFor(this);
    }

    public void loop(){
        for(Actor actor : getScene().getActors()){
            if(actor instanceof Alive && intersects(actor) && !(actor instanceof Ripley)){
                ((Alive) actor).getHealth().drain(10);
                collidedWithWall();
            }
        }
    }
}
