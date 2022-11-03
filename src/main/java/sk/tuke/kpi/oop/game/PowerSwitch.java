package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class PowerSwitch extends AbstractActor {
    private Reactor reactor;
    private Switchable switchable;

    public PowerSwitch(Switchable switchable){
        Animation normalControllerAnimotion;
        normalControllerAnimotion = new Animation("sprites/switch.png");
        setAnimation(normalControllerAnimotion);
        this.switchable = switchable;
    }

    public void toggle(){
        if(reactor.isOn() == true)
            reactor.turnOff();
        else if(reactor.isOn() == false)
            reactor.turnOn();
    }

    public Switchable getDevice(){
        return switchable;
    }

    public void switchOn(){
        if(switchable == null)
            return;
        switchable.turnOn();
    }

    public void switchOff(){
        if(switchable == null)
            return;
        switchable.turnOff();
    }
}
