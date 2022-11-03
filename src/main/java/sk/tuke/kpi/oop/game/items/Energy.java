package sk.tuke.kpi.oop.game.items;

//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
//import sk.tuke.kpi.oop.game.characters.Ripley;

public class Energy extends AbstractActor implements Usable<Alive> {
    public Energy() {
        Animation medicineChestAnimation = new Animation("sprites/energy.png");
        setAnimation(medicineChestAnimation);
    }

    /*@Override
    public void useWith(Ripley actor) {
        if(actor == null)
            return;
        else if(actor.getHealth().getValue() < 100){
            actor.getHealth().refill(50);
            getScene().removeActor(this);
        }
    }*/

    @Override
    public void useWith(Alive actor) {
        if(actor == null)
            return;
        else if(actor.getHealth().getValue() < 100){
            actor.getHealth().refill(50);
            actor.getScene().removeActor(this);
        }
    }

    /*@Override
    public Class<Ripley> getUseClass() {
        return Ripley.class;
    }*/

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
