package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {
    private Health health;
    //private Disposable disposable = null;
    private Behaviour<? super Alien> behaviour/* = null*/;

    public Alien(){
        /*private*/ Animation alienAnimation;
        alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(alienAnimation);
        health = new Health(100, 100);
        health.onExhaustion(() -> {
            getScene().removeActor(this);
            //getScene().cancelActions(this);
        });
    }

    public Alien(Behaviour<? super Alien> behaviour){
        super();
        this.behaviour = behaviour;
    }

    public Alien(int healthValue, Behaviour<? super Alien> behaviour){
        super();
        this.behaviour = behaviour;
        health = new Health(healthValue, 100);
        /*private*/ /*Animation alienAnimation;
        alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(alienAnimation);
        health = new Health(healthValue, 100);
        health.onExhaustion(() -> {
            getScene().removeActor(this);
            //getScene().cancelActions(this);
        });*/
    }

    public static class AlienMother extends Alien{
        private Health health;
        private Animation alienMotherAnimation;

        public AlienMother(){
            health = new Health(200, 200);
            alienMotherAnimation = new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(alienMotherAnimation);
        }

        /*public AlienMother(Behaviour<? super Alien> behaviour){
            super();
        }*/
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void startedMoving(Direction direction) {
        Movable.super.startedMoving(direction);
    }

    @Override
    public void stoppedMoving() {
        Movable.super.stoppedMoving();
    }

    @Override
    public void collidedWithWall() {
        Movable.super.collidedWithWall();
    }

    @Override
    public Health getHealth(){
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        if(behaviour != null)
            behaviour.setUp(this);
        health.onExhaustion(() -> {
            getScene().removeActor(this);
            getScene().cancelActions(this);
        });
        if (getScene() == null)
            return;
        scene.getActors().forEach(
            actor -> {
                if(this.health.getValue() > 0 && actor instanceof Alive && !(actor instanceof Enemy)/* && intersects(actor)*/) {
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
