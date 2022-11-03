package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<A extends Keeper> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime) {
        if(getActor() == null || getActor().getBackpack().peek() == null || getActor().getScene() == null) {
            setDone(true);
            return;
        }
        if(!isDone()){
            Collectible backBackpack = getActor().getBackpack().peek();
            getActor().getScene().addActor(backBackpack, getActor().getPosX(), getActor().getPosY());
            getActor().getBackpack().remove(backBackpack);
            setDone(true);
        }
    }
}
