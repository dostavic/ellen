package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
//import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
//import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    private Animation riplayAnim = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private int spedd;
    //private int energy;
    private int ammo;
    private Backpack backpack = new Backpack("Ripley's backback", 10);
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("Ripley died", Ripley.class);
    private Animation riplayDiedAnim = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
    //private Disposable disposable = null;
    private Health health;
    private Firearm firearm;
    //private Shield shield;

    public Ripley() {
        super("Ellen");
        setAnimation(riplayAnim);
        spedd = 2;
        riplayAnim.stop();
        //energy = 100;
        //drawText();
        ammo = 0;
        //backpack.add();
        //health = new Health(100, 100);
        health = new Health(100);
        firearm = new Gun(20, 0);
        //shield = new Shield(100);
        //if(health.getValue() <= 0){
            //health.onExhaustion(this::ripleyDied);
        //    getScene().getMessageBus().publish(RIPLEY_DIED, this);
        //}
    }

    @Override
    public int getSpeed() {
        this.spedd = spedd;
        return spedd;
    }

    @Override
    public void startedMoving(Direction direction) {
        //Movable.super.startedMoving(direction);
        riplayAnim.setRotation(direction.getAngle());
        riplayAnim.play();
    }

    @Override
    public void stoppedMoving() {
        //Movable.super.stoppedMoving();
        riplayAnim.stop();
    }

    public void setSpeed(int speed){
        this.spedd = speed;
    }

    /*public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }*/

    /*public void drawText(){
        int windowHeight = getScene().getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        getScene().getGame().getOverlay().drawText("Energy " +getEnergy(), 120, yTextPos);
    }*/

    public int getAmmo() {
        return this.ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public Backpack getBackpack() {
        return this.backpack;
    }

    public void showRipleyState() {
        int windowHeight = getScene().getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        getScene().getGame().getOverlay().drawText("| Energy " + getHealth().getValue(), 100, yTextPos);
        getScene().getGame().getOverlay().drawText("| Ammo " + getFirearm().getAmmo(), 250, yTextPos);
        //getScene().getGame().getOverlay().drawText("| Shield " + getShield().getMinShield(), 350, yTextPos);
        getScene().getGame().pushActorContainer(backpack);
    }

    /*public void decreaseEnergy() {
        if (health.getValue() > 0) {
            getHealth().drain(1);
            //new Wait<>(10);
            //new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
        } else if(health.getValue() <= 0) {
            getHealth().refill();
            setAnimation(riplayDiedAnim);
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
        } else {
            return;
        }
    }

    public void decreaseEnergyOne(){
        //new Loop<>(new Invoke<>(this::decreaseEnergy)).scheduleFor(this);
        //new ActionSequence<>(new Wait<>(this.time), new Invoke<>(this::eBomb), new Invoke<>(this::removdFromScene)).scheduleFor(this);
        disposable = new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::decreaseEnergy), new Wait<>(0.5f)
            )
        ).scheduleOn(this.getScene());
    }

    public Disposable decreaseEnergyOneStop(){
        //disposable = null;
        return disposable;
    }*/

    @Override
    public Health getHealth(){
        /*if(getHealth().getValue() <= 0){
            //if(this.health <= 0){
            setAnimation(riplayDiedAnim);
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
            //return 0;
            //}
        }*/
        return health;
    }

    @Override
    public Firearm getFirearm() {
        return firearm;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        firearm = weapon;
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        /*if(getHealth().getValue() <= 0){
            //if(this.health <= 0){
                setAnimation(riplayDiedAnim);
                getScene().getMessageBus().publish(RIPLEY_DIED, this);
                //return 0;
            //}
        }*/
        //health.onExhaustion(this::getHealth);
        //if(this.health.getValue() <= 0)
        //    ripleyDied();
        this.health.onExhaustion(this::ripleyDied);
        //getScene().getMessageBus().publish(RIPLEY_DIED, this);
    }

    public void ripleyDied(){
        //if(getHealth().getValue() <= 0){
            //if(this.health <= 0){
            setAnimation(riplayDiedAnim);
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
            //getScene().cancelActions(this);
            //return 0;
            //}
        //}
    }

    /*@Override
    public Shield getShield() {
        return shield;
    }*/
}
