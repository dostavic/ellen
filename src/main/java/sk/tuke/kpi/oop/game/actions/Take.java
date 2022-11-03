package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
//import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.ArrayList;
//import java.util.List;
import java.util.Objects;

public class Take<A extends Keeper> extends AbstractAction<A>{
    @Override
    public void execute(float deltaTime) {
        if(getActor() == null){
            setDone(true);
            return;
        }
        if (!isDone()) {
            ArrayList<Actor> backpack = (ArrayList<Actor>) Objects.requireNonNull(getActor().getScene()).getActors();
            for (Actor items : backpack) {
                if (items instanceof Collectible && items.intersects(getActor())) {
                    try {
                        getActor().getBackpack().add(((Collectible) items));
                        getActor().getScene().removeActor(items);
                    } catch (IllegalStateException exception) {
                        getActor().getScene().getOverlay().drawText(exception.getMessage(), 0, 0).showFor(2);
                    }
                } /*else {
                    break;
                }*/
            }
            setDone(true);
        } /*else {
            setDone(false);
        }*/
    }
}
