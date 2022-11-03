package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{
    //private Bullet bullet;
    public Gun(int max, int min) {
        super(max, min);
    }

    @Override
    public Fireable createBullet() {
        /*Bullet *///bullet = new Bullet();
        return new Bullet();
    }
}
