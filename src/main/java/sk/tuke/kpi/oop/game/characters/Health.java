package sk.tuke.kpi.oop.game.characters;

import java.util.HashSet;
import java.util.Set;

public class Health {
    private int minHealth;
    private int maxHealth;
    private Set<Health.ExhaustionEffect> exhaustionEffects = new HashSet<>();

    @FunctionalInterface
    public interface ExhaustionEffect{
        void apply();
    }

    public Health(int min, int max){
        this.minHealth = min;
        this.maxHealth = max;
    }

    public Health(int min){
        this.minHealth = min;
        this.maxHealth = min;
    }

    public int getValue(){
        return this.minHealth;
    }

    public void refill(int amount){
        if((this.minHealth + amount) <= maxHealth)
            minHealth = minHealth + amount;
        else
            restore();
    }

    public void restore(){
        if(this.minHealth == this.maxHealth)
            return;
        this.minHealth = this.maxHealth;
    }

    public void drain(int amount){
        if(amount <= 0)
            return;
        if(this.minHealth > 0) {
            this.minHealth = this.minHealth - amount;
        }
        if(this.minHealth < 0)
            this.minHealth = 0;
        //this.minHealth = amount < 0 ? 0 : amount > maxHealth ? maxHealth : amount;

        if(minHealth == 0){
//            for (Health.ExhaustionEffect effect : exhaustionEffects) {
//                effect.apply();
//            }
//            exhaustionEffects.forEach(effect -> effect.apply());
//            exhaustionEffects.clear();
            ifFead();
        }
    }

    public void ifFead(){
        for (Health.ExhaustionEffect effect : exhaustionEffects) {
            effect.apply();
        }
        exhaustionEffects.forEach(effect -> effect.apply());
        exhaustionEffects.clear();
    }

    public void exhaust(){
        this.minHealth = 1;
        drain(1);
        for(int i = 0; i <= 4; i++) {
            continue;
        }
    }

    public void onExhaustion(Health.ExhaustionEffect effect){
        exhaustionEffects.add(effect);
    }
}
