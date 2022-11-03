package sk.tuke.kpi.oop.game;

//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private float time;
    private boolean isActivated;
    private Animation onBombAnimation;
    private boolean isE;
    private Animation eBombAnimation;
    private Animation offBombAnimation;

    public TimeBomb(float time){
        this.time = time;
        isActivated = false;
        isE = false;
        //Animation onBombAnimation;
        //Animation offBombAnimation;
        //Animation eBombAnimation;
        onBombAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f);
        offBombAnimation = new Animation("sprites/bomb.png");
        eBombAnimation = new Animation("sprites/small_explosion.png", 64, 32, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(offBombAnimation);
    }

    public boolean isActivated(){
        return isActivated;
    }

    public void activate(){
        isActivated = true;
        update();
        new ActionSequence<>(new Wait<>(this.time), new Invoke<>(this::eBomb), new Invoke<>(this::removdFromScene)).scheduleFor(this);
    }

    public boolean isE(){
        //isE = true;
        //setAnimation(eBombAnimation);
        return isE;
    }

    public void removdFromScene(){
        getScene().removeActor(this);
    }

    public float getTime(){
        return time;
    }

    public void eBomb(){
        isE = true;
        //update();
        setAnimation(eBombAnimation);
    }

    public void update(){
        if(isE)
            setAnimation(eBombAnimation);
        if(isActivated)
            setAnimation(onBombAnimation);
        if(!isE && !isActivated)
            setAnimation(offBombAnimation);
    }
}
