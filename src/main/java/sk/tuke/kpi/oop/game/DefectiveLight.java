package sk.tuke.kpi.oop.game;

//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable{
    private boolean zalom;
    private Disposable disposable;

    public DefectiveLight(){
        super();
        zalom = false;
    }

    public void zalomDefectiveLight(){
        if(((int) (Math.random() * (20 - 1 + 1)) + 1) == 1)
            super.toggle();
    }

    public void setDisposable(){
        disposable = new Loop<>(new Invoke<>(this::zalomDefectiveLight)).scheduleFor(this);
    }

    @Override
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        setDisposable();
    }

    @Override
    public boolean repair(){
        if(!zalom && disposable != null){
            disposable.dispose();
            zalom = true;
            new ActionSequence<>(new Wait<>(10), new Invoke<>(this::setDisposable), new Invoke<>(this::zamenZalom)).scheduleFor(this);
            return true;
        } else
            return false;
    }

    @Override
    public boolean extinguish() {
        return false;
    }

    private void zamenZalom(){
        if(this.zalom == true)
            zalom = false;
        else
            zalom = true;
    }
}
