package sk.tuke.kpi.oop.game.behaviours;

//import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving implements Behaviour<Movable> {
    //private Movable movable;
    //private Move<Movable> move;
    //private Disposable disposable;

    @Override
    public void setUp(Movable actor) {
        /*private*/ //Movable movable = null;
        /*private*/ //Move<Movable> move = null;
        /*private*/ //Disposable disposable;

        if(actor == null)
            return;
        //Movable movable = actor;
        //int nex;
        //int ney;
        /*Disposable disposable = */new Loop<Movable>(
            new ActionSequence<>(
                new Invoke<>(() -> {
                    Direction direction = null;
                    int nex = (int) (Math.random() * (3)) - 1;
                    int ney = (int) (Math.random() * (3)) - 1;
                    for(Direction xy : Direction.values()){
                        if(nex == xy.getDx() && ney == xy.getDy())
                            direction = xy;
                    }
                    actor.getAnimation().setRotation(direction.setAngaleA());
                    /*move = */new Move<>(direction, 2).scheduleFor(actor);
                }),
                new Wait<>(1)
            )
        ).scheduleFor(actor);
    }
}
