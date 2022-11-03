package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

//import java.util.Objects;


public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private Animation doorAnimationV;
    private Animation doorAnimationH;
    private boolean isOpen;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public enum Orientation{
        HORIZONTAL,
        VERTICAL
    }

    public Door(Orientation orientation) {
        isOpen = false;
        //wallDoor();

        if(orientation == Orientation.HORIZONTAL) {
            doorAnimationV = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnimationV);
        } else if(orientation == Orientation.VERTICAL){
            doorAnimationH = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnimationH);
        }
        getAnimation().stop();

    }

    public Door(String name, Orientation orientation){

        super(name);
        isOpen = false;
        if(orientation == Orientation.HORIZONTAL) {
            doorAnimationV = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnimationV);
        } else {
            doorAnimationH = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnimationH);
        }
        getAnimation().stop();
        //wallDoor();
        //getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
    }

    @Override
    public void useWith(Actor actor) {
        if (isOpen())
            close();
        else if(!isOpen())
            open();
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        if(!isOpen()) {
            getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
            //getAnimation().play();
            //getAnimation().stop();
            isOpen = true;
            if(getAnimation() == doorAnimationH){
                wallDoorOpenH();
            } else {
                wallDoorOpenV();
            }
            getScene().getMessageBus().publish(DOOR_OPENED, this);
        }
    }

    @Override
    public void close() {
        if(isOpen()) {
            getAnimation().setPlayMode(Animation.PlayMode.ONCE);
            //getAnimation().play();
            //getAnimation().stop();
            isOpen = false;
            if(getAnimation() == doorAnimationH){
                wallDoorCloseH();
            } else {
                wallDoorCloseV();
            }
            getScene().getMessageBus().publish(DOOR_CLOSED, this);
        }
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if(getAnimation() == doorAnimationH){
            wallDoorCloseH();
        } else {
            wallDoorCloseV();
        }
    }

    public void wallDoorOpenH(){
        //if(isOpen()){
            Objects.requireNonNull(getScene()).getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile(getPosX() / 16 + 1, getPosY() / 16).setType(MapTile.Type.CLEAR);
        /*} else if(!isOpen()){
            Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
            //getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
        }*/
    }

    public void wallDoorCloseH(){
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
        getScene().getMap().getTile(getPosX() / 16 + 1, getPosY() / 16).setType(MapTile.Type.WALL);
    }

    public void wallDoorOpenV(){
        //if(isOpen()){
        Objects.requireNonNull(getScene()).getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
        getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 1).setType(MapTile.Type.CLEAR);
        /*} else if(!isOpen()){
            Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
            //getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
        }*/
    }

    public void wallDoorCloseV(){
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
        getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 1).setType(MapTile.Type.WALL);
    }
}
