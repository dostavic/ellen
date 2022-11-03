package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Mjolnir extends Hammer{

    public Mjolnir(){
        //Uses(4);
        super.setRemainingUses(4);
        Animation normalMjolnirAnimation = new Animation("sprites/hammer.png");
        setAnimation(normalMjolnirAnimation);
    }
}
