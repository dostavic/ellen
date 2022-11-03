package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable{
    private Animation ventilatorAnimation;
    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repair", Ventilator.class);

    public Ventilator(){
        ventilatorAnimation = new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(ventilatorAnimation);
        getAnimation().stop();
    }

    @Override
    public boolean repair() {
        getAnimation().play();
        getScene().getMessageBus().publish(VENTILATOR_REPAIRED, this);
        return true;
    }

    @Override
    public boolean extinguish() {
        return false;
    }
}
