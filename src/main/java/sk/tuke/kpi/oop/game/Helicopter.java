package sk.tuke.kpi.oop.game;

//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    public Helicopter(){
        Animation normalHelicopter;
        normalHelicopter = new Animation("sprites/heli.png", 64, 64, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(normalHelicopter);
    }

    private void naist(){
        Player ellen = (Player) getScene().getFirstActorByName("Player");
        if(this.getPosY() != ellen.getPosY()){
            if(ellen.getPosY() < this.getPosY())
                this.setPosition(this.getPosX(), this.getPosY() - 1);
            else if(ellen.getPosY() > getPosY())
                this.setPosition(this.getPosX(), this.getPosY() + 1);
        }
        if(this.getPosX() != ellen.getPosX()){
            if(this.getPosX() < ellen.getPosX())
                this.setPosition(this.getPosX() + 1, this.getPosY());
            else if(this.getPosX() > ellen.getPosX())
                this.setPosition(this.getPosX() - 1, this.getPosY());
        }
        if(intersects(ellen))
            ellen.setEnergy(ellen.getEnergy() - 1);
    }

    public void searchAndDestroy(){
        new Loop<>(new Invoke<>(this::naist)).scheduleFor(this);
    }
}
