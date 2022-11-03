package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
//import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Movable actor;
    private Move<Movable> move/* = null*/;
    //private Disposable disposable;
    private Set<Input.Key> keyboard;
    private Input.Key key_1 = null;
    private Input.Key key_2 = null;
    //private Direction refreshSet/* = null*/;

    public MovableController(Movable actor){
        this.actor = actor;
        keyboard = new HashSet<>();
    }

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );

    private void stopMoving(){
        if(move != null) {
            move.stop();
            //disposable.dispose();
            //move = null;
        }
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        Direction refreshSet = null;
        if(key_1==null && keyDirectionMap.containsKey(key)) {
            keyboard.add(key);
            key_1 = key;
        } else if(key_2 == null && keyDirectionMap.containsKey(key)){
            keyboard.add(key);
            key_2 = key;
        }
        int check_key = 0;
        for(Input.Key key_key:keyboard) {
            if(check_key == 0)
                refreshSet = keyDirectionMap.get(key_key);
            if(check_key == 1)
                refreshSet = refreshSet.combine(keyDirectionMap.get(key_key));
            check_key++;
        }
        stopMoving();
        if(refreshSet != null){
            move = new Move<>(refreshSet, Float.MAX_VALUE);
            /*Disposable disposable = */move.scheduleFor(actor);
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        Direction refreshSet = null;
        if(keyDirectionMap.containsKey(key) && (key == key_1 || key == key_2)){
            keyboard.remove(key);
            if(key_1 == key){
                key_1 = null;
            } else {
                key_2 = null;
            }
        }
        /*if (key == key_1 && keyDirectionMap.containsKey(key)) {
            keyboard.remove(key);
            key_1 = null;
        }
        if (key == key_2 && keyDirectionMap.containsKey(key)) {
            keyboard.remove(key);
            key_2 = null;
        }*/
        /*for(Input.Key key_key:keyboard) {
            refreshSet = keyDirectionMap.get(key_key);
        }*/
        int check_key = 0;
        for(Input.Key key_key:keyboard) {
            if(check_key == 0)
                refreshSet = keyDirectionMap.get(key_key);
            if(check_key == 1)
                refreshSet = refreshSet.combine(keyDirectionMap.get(key_key));
            check_key++;
        }
        stopMoving();
        if(refreshSet != null){
            move = new Move<>(refreshSet, Float.MAX_VALUE);
            /*Disposable disposable = */move.scheduleFor(actor);
        }
    }
}
