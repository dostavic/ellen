package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
//import sk.tuke.kpi.gamelib.Scene;

public abstract class BreakableTool <T extends Actor> extends AbstractActor implements  Usable<T> {
    private int remainingUses;

    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }

    public int getRemainingUses(){
        return remainingUses;
    }

    public void setRemainingUses(int remainingUses){
        this.remainingUses = remainingUses;
    }

    public int getKolk(){
        return remainingUses;
    }

    @Override
    public void useWith(T actor){
        if(actor != null) {
            remainingUses--;
            if (remainingUses == 0) {
                Scene scene = getScene();
                scene.removeActor(this);
                //System.gc();
            }
        } else
            return;
    }
}
