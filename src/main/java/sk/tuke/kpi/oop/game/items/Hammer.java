package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;


public class Hammer extends BreakableTool<Repairable> implements Collectible {
    //private int kolk;

    public Hammer(){
        /*super(1);
        //Uses(1);
        Animation normalHammerAnimation = new Animation("sprites/hammer.png");
        setAnimation(normalHammerAnimation);*/
        this(1);
    }


    public Hammer(int reinUs){
        super(reinUs);
        //Uses(1);
        Animation normalHammerAnimation = new Animation("sprites/hammer.png");
        setAnimation(normalHammerAnimation);
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }

    @Override
    public void useWith(Repairable repairable){
        if(repairable == null)
            return;
        if(repairable.repair() && repairable != null)
            super.useWith(repairable);
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
