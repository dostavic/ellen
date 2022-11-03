package sk.tuke.kpi.oop.game.items;

//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.graphics.Animation;
//import sk.tuke.kpi.oop.game.Repairable;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible{
    //private int kolk;

    public FireExtinguisher(){
        super(1);
        //Uses(1);
        Animation normalFileExtinguisherAnimation;
        normalFileExtinguisherAnimation = new Animation("sprites/extinguisher.png");
        setAnimation(normalFileExtinguisherAnimation);
    }

    //@Override
    public void useWith(Reactor reactor) {
        if(reactor == null)
            return;
        if(reactor.extinguish() && reactor != null)
            super.useWith(reactor);
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }

    /*public void use(){
        kolk--;
        if(kolk == 0)
            getScene().removeActor(this);
    }

    public int getKolk(){
        return kolk;
    }

    public void Uses(int kol){
        kolk = kol;
    }*/
}
