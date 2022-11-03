package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
//import sk.tuke.kpi.oop.game.Locker;
//import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;
import sk.tuke.kpi.oop.game.walls.WallElektro;
import sk.tuke.kpi.oop.game.walls.WallHealth;

public class MissionImpossible implements SceneListener {
    private Disposable keeperControllerD;
    private Disposable movableControllerD;
    private MovableController movableControllerM;
    private KeeperController keeperControllerM;
    public static class Factory implements ActorFactory {
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
                case "locker":
                    return new Locker();
                case "ventilator":
                    return new Ventilator();
                default:
                    return null;
            }
        }
    }


    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        scene.follow(ellen);
        movableControllerM = new MovableController(ellen);
        keeperControllerM = new KeeperController(ellen);
        movableControllerD=scene.getInput().registerListener(movableControllerM);
        keeperControllerD=scene.getInput().registerListener(keeperControllerM);
        //scene.getInput().registerListener(movableControllerM);
        //scene.getInput().registerListener(keeperControllerM);
        Hammer hammer = new Hammer();
        Ammo ammo = new Ammo();
        WallElektro wallElektro = new WallElektro(WallElektro.Orientation.VERTICAL);
        WallHealth wallHealth = new WallHealth(WallElektro.Orientation.VERTICAL);
        //AccessCard accessCard = new AccessCard();
        //ellen.getBackpack().add(accessCard);
        ellen.getBackpack().add(hammer);
        scene.addActor(ammo, 150, 20);
        scene.addActor(wallElektro, 130, 20);
        scene.addActor(wallHealth, 130, 90);
        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ellen.getHealth().drain(1));
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ((Ripley) -> {
            movableControllerD.dispose();
            keeperControllerD.dispose();
        }));
        //scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ellen.decreaseEnergyOneStop().dispose());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        ellen.showRipleyState();
    }
}
