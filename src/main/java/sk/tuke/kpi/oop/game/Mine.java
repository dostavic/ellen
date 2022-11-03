package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

public class Mine extends AbstractActor implements Collectible, Usable<Ripley> {
    private Animation itemMineAnimation;
    private Animation activateMineAnimation;
    private Animation boomMineAnimation;
    private boolean isActivate;
    private float time;
    private boolean isBoom;

    public Mine(){
        itemMineAnimation = new Animation("sprites/mine.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        activateMineAnimation = new Animation("sprites/mine.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        //activateMineAnimation.play();
        setAnimation(itemMineAnimation);
        getAnimation().stop();
        boomMineAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
        isActivate = false;
        this.time = 10f;
        isBoom = false;
    }

    public boolean isActivate(){
        return isActivate;
    }

    public void activate(){
        isActivate = true;
        update();
        new ActionSequence<>(
            new Wait<>(this.time),
            new Invoke<>(this::boomActivate),
            new Wait<>(.1f),
            new Invoke<>(
                () -> getScene().removeActor(this)
            )
        ).scheduleFor(this);
    }

    public void update(){
        if(isActivate && !isBoom) {
            setAnimation(activateMineAnimation);
            getAnimation().play();
        }
        if(isBoom) {
            setAnimation(boomMineAnimation);
            getAnimation().play();
        }
    }

    private float getTime(){
        return this.time;
    }

    @Override
    public void useWith(Ripley actor) {
        getScene().addActor(this, actor.getPosX() + 20, actor.getPosY() + 20);
        actor.getBackpack().remove(this);
        activate();
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }

    public void boomActivate(){
        isBoom = true;
        update();
        for(Actor actor : getScene().getActors()){
            if(actor instanceof Alive && intersects(actor) && !(actor instanceof  Ripley)){
                ((Alive) actor).getHealth().drain(50);
            }
        }
    }
}
