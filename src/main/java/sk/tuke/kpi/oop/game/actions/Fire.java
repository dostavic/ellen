package sk.tuke.kpi.oop.game.actions;

//import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;
import sk.tuke.kpi.oop.game.weapons.Firearm;

public class Fire<A extends Armed> extends AbstractAction<A> {
    @Override
    public void execute(float deltaTime){
        if(getActor() == null){
            setDone(true);
            return;
        }
        if(!isDone()){
            Firearm firearm = getActor().getFirearm();
            Fireable fireable = firearm.fire();
            if(fireable != null) {
                Direction direction = Direction.fromAngle(getActor().getAnimation().getRotation());
                getActor().getScene().addActor(fireable, getActor().getPosX(), getActor().getPosY());
                new Move<Fireable>(direction, Float.MAX_VALUE).scheduleFor(fireable);
            }
        }
        setDone(true);
    }
}
