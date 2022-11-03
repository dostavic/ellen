package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable{
    private boolean isOn;
    private Reactor reactor;
    private Animation onCoolerAnimation;
    private Animation offCoolerAnimation;

    public Cooler(Reactor reactor){
        if(reactor != null) {
            this.reactor = reactor;
        }
        isOn = false;
        offCoolerAnimation = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.ONCE);
        onCoolerAnimation = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(offCoolerAnimation);
    }

    public Reactor getReactor(){
        return reactor;
    }

    public void setReactor(Reactor reactor){
        this.reactor = reactor;
    }

    @Override
    public boolean isOn(){
        return isOn;
    }

    @Override
    public void turnOn(){
        if(reactor != null) {
            isOn = true;
            setAnimation(onCoolerAnimation);
        }
    }

    @Override
    public void turnOff(){
        isOn = false;
        setAnimation(offCoolerAnimation);
    }

    public void coolReactor(){
        if(reactor != null){
            if(isOn() && this.reactor.getTemperature() < 6000){
                this.reactor.decreaseTemperature(1);
                if(getAnimation().getPlayMode().equals(offCoolerAnimation.getPlayMode())){
                    setAnimation(onCoolerAnimation);
                }
            } else {
                if(getAnimation().getPlayMode().equals(onCoolerAnimation.getPlayMode())){
                    setAnimation(offCoolerAnimation);
                }
            }
        }
    }

    @Override
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
