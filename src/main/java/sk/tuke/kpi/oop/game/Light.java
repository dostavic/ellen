package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer{
    private Animation offLightAnimation;
    private Animation onLightAnimation;
    private boolean isOn;
    private boolean isPowered;
    //private boolean On;

    public Light(){
        isOn = false;
        isPowered = false;
        onLightAnimation = new Animation("sprites/light_on.png");
        offLightAnimation = new Animation("sprites/light_off.png");
        setAnimation(offLightAnimation);
    }

    public void setElectricityFlow(boolean check){
        isPowered = check;
        if(isOn == true && isPowered == true)
            setAnimation(onLightAnimation);
        else
            setAnimation(offLightAnimation);
    }

    public void toggle(){
        isOn = !isOn;
        if(this.isOn && this.isPowered)
            setAnimation(onLightAnimation);
        else if(this.isOn && !this.isPowered)
            setAnimation(offLightAnimation);
        else
            setAnimation(offLightAnimation);
    }

    @Override
    public void turnOn() {
        this.isOn = true;
        if (this.isOn && this.isPowered)
            setAnimation(onLightAnimation);
        else if(this.isOn && !this.isPowered)
            setAnimation(offLightAnimation);

        //On = true;
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        setAnimation(offLightAnimation);
        //On = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void setPowered(boolean computerPower){
        this.isPowered = computerPower;
        if(isOn() && isPowered)
            setAnimation(onLightAnimation);
        else
            setAnimation(offLightAnimation);
    }
}
