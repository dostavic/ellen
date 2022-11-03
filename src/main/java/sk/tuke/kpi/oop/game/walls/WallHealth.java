package sk.tuke.kpi.oop.game.walls;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Health;

import java.util.Objects;

public class WallHealth extends WallElektro implements Alive , Enemy {
    private Health health;

    public WallHealth(Orientation orientation) {
        super(orientation);
        getAnimation().setFrameDuration(0.2f);
        getAnimation().stop();
        health = new Health(40);
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        this.health.onExhaustion(this::wallCrash);
    }

    public void wallCrash(){
        /*for (float i = 0f; i <= 1f; i = i + 0.1f){
            getAnimation().setAlpha(i);
        }*/
        getScene().removeActor(this);
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
        getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 2).setType(MapTile.Type.CLEAR);
    }
}
