package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.BaxLarge;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Mine;
import sk.tuke.kpi.oop.game.Ventilator;
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

public class MapRoom implements SceneListener {
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
                case "ammo":
                    return new Ammo();
                case "exit door":
                    return new LockedDoor();
                case "energetik":
                    return new Energetik();
                case "wallelektro":
                    return new WallElektro(name, WallElektro.Orientation.VERTICAL);
                case "narko":
                    return new Narko();
                case "mine":
                    return new Mine();
                case "wallcrash":
                    return new WallHealth(WallElektro.Orientation.VERTICAL);
                case "box":
                    return new BaxLarge();
                case "access card":
                    return new AccessCard();
                case "wallfire":
                    return new WallFire(WallFire.Orientation.VERTICAL);
                default:
                    return null;
            }
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene){
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        scene.follow(ellen);
        movableControllerM = new MovableController(ellen);
        keeperControllerM = new KeeperController(ellen);
        shooterControllerM = new ShooterController(ellen);
        movableControllerD=scene.getInput().registerListener(movableControllerM);
        keeperControllerD=scene.getInput().registerListener(keeperControllerM);
        shooterControllerD = scene.getInput().registerListener(shooterControllerM);
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ((Ripley) -> {
            movableControllerD.dispose();
            keeperControllerD.dispose();
            shooterControllerD.dispose();
        }));
        scene.getMessageBus().subscribe(Door.DOOR_OPENED, door -> {
            System.out.println(door.getName());
            if (door.getName().equals("Locked door")) {
                scene.cancelActions(ellen);
                movableControllerD.dispose();
                keeperControllerD.dispose();
                shooterControllerD.dispose();
                onEndGame(scene);
            }
        });
    }

    private void onEndGame(Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int windowWidth = scene.getGame().getWindowSetup().getWidth();
        scene.getGame().getOverlay().drawText("Well done!", windowWidth / 2 - 50, windowHeight / 2).showFor(10);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene){
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        ellen.showRipleyState();
    }
}
