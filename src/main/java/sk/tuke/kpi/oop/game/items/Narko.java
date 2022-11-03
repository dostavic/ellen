package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Game;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Narko extends AbstractActor implements Usable<Ripley> {
    public Narko(){
        setAnimation(new Animation("sprites/life.png"));
    }

    @Override
    public void useWith(Ripley actor) {
        getScene().removeActor(this);
        //int prewAmmo = actor.getAmmo();
        //int prewHealth = actor.getHealth().getValue();
        new ActionSequence<>(
            new Invoke<>(
                () -> {
                    actor.getHealth().restore();
                    actor.setAmmo(1000);
                    getScene().centerOn(actor.getPosX(), actor.getPosY() + 20);
                }
            ), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() + 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY());
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX(), actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY());
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY() + 20);
            }), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX(), actor.getPosY() + 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() + 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY());
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX(), actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY());
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY() + 20);
            }), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX(), actor.getPosY() + 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() + 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY());
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX(), actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY());
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY() + 20);
            }), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX(), actor.getPosY() + 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() + 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY());
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() - 20, actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX(), actor.getPosY() - 20);
            }), new Wait<>(0.5f), new Invoke<>(() -> {
                actor.getHealth().restore();
                actor.setAmmo(1000);
                getScene().centerOn(actor.getPosX() + 20, actor.getPosY() - 20);
            }), new Invoke<>(
                () -> {
                    //actor.getHealth().drain(99);
                    //actor.setAmmo(prewAmmo);
                    actor.setSpeed(1);
                    Ripley ellen = getScene().getFirstActorByType(Ripley.class);
                    getScene().follow(ellen);
                }
            ), new Wait<>(10), new Invoke<>(
                () -> {
                    actor.setSpeed(2);
                    //getScene().centerOn(getPosX(), getPosY());
                }
            )).scheduleFor(actor);
        //getScene().centerOn(getPosX(), getPosY());
}
    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
