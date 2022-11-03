package sk.tuke.kpi.oop.game.weapons;

public class Lazer extends Firearm{

    public Lazer(int min) {
        super(min);
    }

    @Override
    public Fireable createBullet() {
        return new LazerBullet();
    }
}
