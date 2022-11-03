package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class Locker extends AbstractActor implements Usable<Ripley> {
    private Animation lockerAnimation;
    private boolean isUses;
    private Hammer hammer;

    public Locker(){
        isUses = false;
        lockerAnimation = new Animation("sprites/locker.png");
        setAnimation(lockerAnimation);
    }

    @Override
    public void useWith(Ripley actor) {
        if(!isUses){
            hammer = new Hammer();
            getScene().addActor(hammer, getPosX(), getPosY());
            isUses = true;
        } else{
            isUses = false;
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
