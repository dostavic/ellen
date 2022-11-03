package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
//import sk.tuke.kpi.oop.game.Repairable;

public class Wrench extends BreakableTool<DefectiveLight> implements Collectible{
    public Wrench(){
        super(2);
        setAnimation(new Animation("sprites/wrench.png"));
    }

    public Wrench(int remUs){
        super(remUs);
    }

    @Override
    public Class<DefectiveLight> getUsingActorClass() {
        return DefectiveLight.class;
    }

    @Override
    public void useWith(DefectiveLight actor){
        if(actor == null)
            return;
        if(actor.repair() && actor != null)
            super.useWith(actor);
    }
}
