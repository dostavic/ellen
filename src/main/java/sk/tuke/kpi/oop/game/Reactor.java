package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
//import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
//import sk.tuke.kpi.oop.game.tools.Hammer;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
//import sk.tuke.kpi.oop.game.tools.Usable;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable{
    private int temperature;
    private int damage;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offReactorAnimation;
    private Animation fireReactorExtinguisher;
    private boolean True;
    private Light light;
    //private FireExtinguisher fIreExtinguisher;
    private Set<EnergyConsumer> dev;

    public Reactor(){
        temperature = 0;
        damage = 0;
        True = false;
        dev = new HashSet<>();
        // create animation object
        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        // set actor's animation to just created Animation object
        setAnimation(offReactorAnimation);
        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        offReactorAnimation = new Animation("sprites/reactor.png");
        fireReactorExtinguisher = new Animation("sprites/reactor_extinguished.png");
        updateAnimation();
    }

    public int getTemperature(){
        return temperature;
    }

    public int getDamage(){
        return damage;
    }

    public void increaseTemperature(int increment){
        if(!True || increment < 0)
            return;
        if(damage >= 33 && damage <= 66)
            temperature = (int) (temperature + 1.5 * increment);
        else if(damage > 66)
            temperature = temperature + 2 * increment;
        else
            temperature = temperature + increment;

        if(temperature >= 2000){
            this.damage = Math.round((100 * (temperature - 2000)) / 4000) + 1;
        }
        if(damage >= 100){
            damage = 100;
            True = false;
        }
        updateAnimation();
    }


    public void decreaseTemperature(int decrement){
        if(True && decrement > 0){
            if(damage > 50 && damage < 100){
                temperature = (int) (temperature - decrement * 0.5);
                updateAnimation();
            } else if(damage <= 50){
                temperature = temperature - decrement;
                updateAnimation();
            } else if(damage >= 100){
                updateAnimation();
            }
        } else{
            return;
        }
    }

    private void updateAnimation(){
        if(True == false){
            if(damage < 100)
                setAnimation(offReactorAnimation);
            else if(damage >= 100)
                setAnimation(brokenAnimation);
        } else if(temperature < 0)
            return;
        else if(temperature <= 4000)
            setAnimation(normalAnimation);
        else if(temperature < 6000)
            setAnimation(hotAnimation);
        else
            setAnimation(brokenAnimation);
    }

    /*public void repairWith(Hammer hammer){
        if(hammer != null && damage > 0 && damage < 100) {
            int new_damage = damage - 50;
            if (new_damage <= 0){
                temperature = 2000;
                damage = 0;
            } else if(new_damage > 0){
                temperature = (int) Math.round(new_damage / 0.025 + 2000);
                damage = new_damage;
            }
            getTemperature();
            getDamage();
            updateAnimation();

        }
    }*/

    @Override
    public void turnOn(){
        if(this.getDamage() >= 100)
            this.True = false;
        if(this.getDamage() < 100)
            this.True = true;
        updateAnimation();
    }

    @Override
    public void turnOff(){
        if(temperature >= 6000){
            setAnimation(brokenAnimation);
            temperature = 6000;
        } else {
            setAnimation(offReactorAnimation);
            temperature = 6000;
        }
        this.True = false;
        updateAnimation();
    }

    @Override
    public boolean isOn() {
        return this.True;
    }

    /*public boolean isRunning(){
        if(True == true)
            return true;
        if(True == false)
            return  false;
        else
            return false;
    }*/

    public void addLight(Light light){
        this.light = light;
        if(True == true && damage == 0)
            this.light.setElectricityFlow(true);
        else
            this.light.setElectricityFlow(false);
    }

    public void removeLight(Light light){
        this.light.setElectricityFlow(false);
        if(True == true && damage == 0)
            this.light.setElectricityFlow(true);
        else
            this.light.setElectricityFlow(false);
    }

    /*public void extinguisherWith(FireExtinguisher fIreExtinguisher){
        if(damage == 100){
            fIreExtinguisher.use();
            temperature = 4000;
            setAnimation(fireReactorExtinguisher);
        } else
            return;
    }*/

    @Override
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        //scene.scheduleAction(new PerpetualReactorHeating(1), this);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

    public void addDevice(EnergyConsumer energyConsumer){
        dev.add(energyConsumer);
        if(isOn()){
            energyConsumer.setPowered(true);
        } else if(!isOn()){
            energyConsumer.setPowered(false);
            energyConsumer.turnOff();
        }
    }

    public void removeDevice(EnergyConsumer energyConsumer){
        dev.remove(energyConsumer);
        energyConsumer.setPowered(false);
        energyConsumer.turnOff();
    }

    @Override
    public boolean repair() {
        if(damage > 0 && damage < 100){
            if(damage > 50) {
                damage = damage - 50;
            } else {
                damage = 0;
            }
            temperature = damage*40+2000;
            updateAnimation();
            return true;
        }
        return false;
    }

    @Override
    public boolean extinguish() {
        if(damage == 100){
            setAnimation(fireReactorExtinguisher);
            temperature = 4000;
            return true;
        }
        return false;
    }
}
