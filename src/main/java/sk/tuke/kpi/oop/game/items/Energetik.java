package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Energetik extends AbstractActor implements Usable<Ripley> {
    private Animation energetik;

    public Energetik(){
        energetik = new Animation("sprites/money.png");
        setAnimation(energetik);
    }

    @Override
    public void useWith(Ripley actor) {
        getScene().removeActor(this);
        new ActionSequence<>(
            new Invoke<>(
                () -> actor.setSpeed(4)
            ),
            new Wait<>(10),
            new Invoke<>(
                () -> actor.setSpeed(1)
            ),
            new Wait<>(5),
            new Invoke<>(
                () -> actor.setSpeed(2)
            )
        ).scheduleFor(actor);
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
