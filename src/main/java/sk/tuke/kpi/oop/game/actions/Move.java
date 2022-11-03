package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<A extends Movable> implements Action<A>{
    private Direction direction;
    private float duration;
    private int time;
    private boolean isDone;
    private A actor;

    public Move(Direction direction, float duration){
       this.direction = direction;
       this.duration = duration;
       time = 0;
       isDone = false;
    }


    @Override
    public @Nullable A getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable A actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void execute(float deltaTime) {
        if(getActor() == null){
            //setActor(true);
            isDone = true;
            return;
        }
        duration -= deltaTime;
        if(time == 0 && !isDone()){
            actor.startedMoving(direction);
            time++;
        }
        if(duration > 0 && !isDone()){
            actor.setPosition(
                actor.getPosX() + direction.getDx() * actor.getSpeed(),
                actor.getPosY() + direction.getDy() * actor.getSpeed()
            );
            if(actor.getScene().getMap().intersectsWithWall(actor)){
                actor.setPosition(
                    actor.getPosX() - direction.getDx() * actor.getSpeed(),
                    actor.getPosY() - direction.getDy() * actor.getSpeed()
                );
                actor.collidedWithWall();
            }
        } else if(duration <= 0 && !isDone) {
            isDone = true;
            actor.stoppedMoving();
        }
    }

    @Override
    public void reset() {
        actor.stoppedMoving();
        duration = 0;
        time = 0;
        isDone = false;
    }

    public void stop(){
        if(getActor() == null) {
            isDone = true;
            return;
        }
        isDone = true;
        actor.stoppedMoving();
    }
}
