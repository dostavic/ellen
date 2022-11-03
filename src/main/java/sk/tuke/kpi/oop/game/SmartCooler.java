package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.gamelib.graphics.Animation;

public class SmartCooler extends Cooler{
    private Reactor reactor;

    public SmartCooler(Reactor reactor){
        super(reactor);
        if(reactor != null)
            this.reactor = reactor;
        //super.turnOn();

        //Animation normalCoolerSmart;
        //normalCoolerSmart = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.ONCE);
    }

    private void smartCoolerReactor(){
        if(getReactor() == null){
            return;
        }
        if(reactor.getTemperature() >= 2500 && getReactor() != null) {
            super.turnOn();
            //super.coolReactor();
        }
        if (getReactor().getTemperature() <= 1500 && getReactor() != null) {
            super.turnOff();
        }
        super.coolReactor();
    }

    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::smartCoolerReactor)).scheduleFor(this);
    }
}
