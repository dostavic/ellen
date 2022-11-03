package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

public class KeeperController implements KeyboardListener {
    private Keeper keeper;
    //private Usable<?> usable;

    public KeeperController(Keeper keeper){
        this.keeper = keeper;
    }

    public void keyU(){
        Usable<?> usable = keeper
            .getScene()
            .getActors()
            .stream()
            .filter(Usable.class::isInstance)
            .filter(keeper::intersects)
            .map(Usable.class::cast)
            .findFirst()
            .orElse(null);
        if(usable != null)
            new Use<>(usable).scheduleForIntersectingWith(keeper);
    }

    public void keyPressed(@NotNull Input.Key key){
        switch (key){
            case ENTER:
                new Take<>().scheduleFor(keeper);
                break;
            case BACKSPACE:
                new Drop<>().scheduleFor(keeper);
                break;
            case S:
                new Shift<>().scheduleFor(this.keeper);
                break;
            case U:
                keyU();
                break;
            case B:
                if (keeper.getBackpack().peek() instanceof Usable && keeper.getBackpack().peek() instanceof Collectible) {
                    Use<?> usesItem = new Use<>((Usable<?>)keeper.getBackpack().peek());
                    if(usesItem != null) {
                        //new Use<>(usesItem).scheduleForIntersectingWith(keeper);
                        //usesItem.scheduleForIntersectingWith(keeper);
                        usesItem.scheduleForIntersectingWith(keeper);
                    }
                }
                break;
            default:
        }
        /*if(key == Input.Key.ENTER)
            new Take<>().scheduleFor(keeper);
        if(key == Input.Key.BACKSPACE)
            new Drop<>().scheduleFor(keeper);
        if(key == Input.Key.S)
            new Shift<>().scheduleFor(this.keeper);
        if(key == Input.Key.U){
            usable = keeper
                .getScene()
                .getActors()
                .stream()
                .filter(Usable.class::isInstance)
                .filter(keeper::intersects)
                .map(Usable.class::cast)
                .findFirst()
                .orElse(null);
            //usable
            //if(keeper.getBackpack().peek() instanceof Usable) {
            if(usable != null)
                new Use<>(usable).scheduleForIntersectingWith(keeper);
            //}else {
            //    return;
            //}
        }
        if(key == Input.Key.B){*/
            /*usable = keeper
                .getScene()
                .getActors()
                .stream()
                .filter(Usable.class::isInstance)
                .filter(keeper::intersects)
                .map(Usable.class::cast)
                .findFirst()
                .orElse(null);*/
            //Use<?> usesItem = new Use<>((Usable<?>)keeper.getBackpack().peek());
            /*if (keeper.getBackpack().peek() instanceof Usable && keeper.getBackpack().peek() instanceof Collectible) {
                Use<?> usesItem = new Use<>((Usable<?>)keeper.getBackpack().peek());
                if(usesItem != null) {
                    //new Use<>(usesItem).scheduleForIntersectingWith(keeper);
                    //usesItem.scheduleForIntersectingWith(keeper);
                    usesItem.scheduleForIntersectingWith(keeper);
                }
            }
        }*/
    }
}
