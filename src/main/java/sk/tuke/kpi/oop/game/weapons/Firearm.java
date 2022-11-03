package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int minAmmo;
    private int maxAmmo;

    public Firearm(int max, int min){
        this.maxAmmo = max;
        this.minAmmo = min;
    }

    public Firearm(int min){
        this.minAmmo = min;
        this.maxAmmo = min;
    }

    public int getAmmo(){
        return minAmmo;
    }

    public void reload(int newAmmo){
        if(this.minAmmo == this.maxAmmo)
            return;
        if(this.minAmmo < this.maxAmmo) {
            this.minAmmo = this.minAmmo + newAmmo;
        }
        if(this.minAmmo >= this.maxAmmo)
            this.minAmmo = this.maxAmmo;
    }

    public Fireable fire(){
        if(this.minAmmo > 0) {
            this.minAmmo--;
            return createBullet();
        }
        return null;
    }

    public abstract Fireable createBullet();
}
