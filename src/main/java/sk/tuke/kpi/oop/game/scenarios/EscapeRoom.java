package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.BaxLarge;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Mine;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;
import sk.tuke.kpi.oop.game.openables.WallFire;
import sk.tuke.kpi.oop.game.walls.WallElektro;
import sk.tuke.kpi.oop.game.walls.WallHealth;
import sk.tuke.kpi.oop.game.weapons.Lazer;

public class EscapeRoom implements SceneListener {
    private Disposable keeperControllerD;
    private Disposable movableControllerD;
    private Disposable shooterControllerD;
    private MovableController movableControllerM;
    private KeeperController keeperControllerM;
    private ShooterController shooterControllerM;
    public static class Factory implements ActorFactory{

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            assert name != null;
            switch (name)
            {
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "access card":
                    return new AccessCard();
                case "door":
                    return new LockedDoor();
                case "front door":
                    return new Door(name, Door.Orientation.HORIZONTAL);
                case "back door":
                    return new Door(name, Door.Orientation.VERTICAL);
                //case "exit door":
                //    return new Door(name, getDoorOrientation(type));
                case "locker":
                    return new Locker();
                case "ventilator":
                    return new Ventilator();
                case "alien":
                    return new Alien();
                case "alien mother":
                    return new Alien.AlienMother();
                default:
                    return null;
            }
        }
    }

    //private Behaviour<? super Alien> nameB()

    @Override
    public void sceneInitialized(@NotNull Scene scene){
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        scene.follow(ellen);
        //Lazer lazer = new Lazer(100);
        //scene.centerOn(0,0);
        //scene.getOverlay().drawText("NARIK", 0, 0);
        movableControllerM = new MovableController(ellen);
        keeperControllerM = new KeeperController(ellen);
        shooterControllerM = new ShooterController(ellen);
        //WallHealth wallHealth = new WallHealth(WallElektro.Orientation.VERTICAL);
        Energetik energetik = new Energetik();
        Narko narko = new Narko();
        Mine mine = new Mine();
        WallFire wallFire = new WallFire(WallFire.Orientation.VERTICAL);
        scene.addActor(new BaxLarge(), 420, 350);
        scene.addActor(wallFire, 400, 300);
        scene.addActor(energetik, 400, 360);
        scene.addActor(mine, 400, 370);
        //scene.addActor(narko, 420, 360);
        movableControllerD=scene.getInput().registerListener(movableControllerM);
        keeperControllerD=scene.getInput().registerListener(keeperControllerM);
        shooterControllerD = scene.getInput().registerListener(shooterControllerM);
        ellen.getBackpack().add(new FireExtinguisher());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ((Ripley) -> {
            movableControllerD.dispose();
            keeperControllerD.dispose();
            shooterControllerD.dispose();
            ellen.getBackpack().add(new Hammer());

        }));
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene){
        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        //ellen.getName().replace("background", "backgroundOff");
        ellen.showRipleyState();
    }
}
