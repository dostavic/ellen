package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;
//import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo extends AbstractActor implements Usable<Armed>, Collectible {
    public Ammo(){
//        Animation ammoAnimation
        setAnimation(new Animation("sprites/ammo.png"));
    }

    /*@Override
    public void useWith(Ripley actor) {
        if(actor == null)
            return;
        else if(actor.getAmmo() == 500)
            return;
        else{
            actor.setAmmo(actor.getAmmo() + 50);
            if(actor.getAmmo() > 500)
                actor.setAmmo(500);
            getScene().removeActor(this);
        }
    }*/

    @Override
    public void useWith(Armed actor) {
        if(actor == null)
            return;
        else if(actor.getFirearm().getAmmo() == 500)
            return;
        else{
            actor.getFirearm().reload(50);
            if(actor.getFirearm().getAmmo() > 500){
                while (actor.getFirearm().getAmmo() != 500){
                    actor.getFirearm().reload(1);
                }
            }
            getScene().removeActor(this);
        }
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
