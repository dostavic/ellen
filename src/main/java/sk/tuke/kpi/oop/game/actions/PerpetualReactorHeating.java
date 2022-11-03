package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.oop.game.Reactor;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private int incrementReactor;

    public PerpetualReactorHeating(int incrementReactor){
        this.incrementReactor = incrementReactor;
    }

    @Override
    public void execute(float deltaTime){
        if(getActor() != null)
            getActor().increaseTemperature(incrementReactor);
    }
}
