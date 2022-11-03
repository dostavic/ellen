package sk.tuke.kpi.oop.game;

//import java.security.PublicKey;
//import java.sql.Driver;

//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;

public enum Direction {
    NORTH(0,1),
    EAST(1,0),
    SOUTH(0,-1),
    WEST(-1,0),
    NORTHEAST(1,1),
    NORTHWEST(-1,1),
    SOUTHEAST(1,-1),
    SOUTHWEST(-1,-1),
    NONE(0, 0);

    private final int dx;
    private final int dy;
    private float angale;

    Direction(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }

    /*public static Map<Direction, Float> newAngale = new HashMap<>(Map.ofEntries(
        Map.entry(NORTH, 0.f),
        Map.entry(EAST, 270.f),
        Map.entry(WEST, 90.f),
        Map.entry(SOUTH, 180.f),
        Map.entry(NORTHEAST, 315.f),
        Map.entry(NORTHWEST, 45.f),
        Map.entry(SOUTHEAST, 225.f),
        Map.entry(SOUTHWEST, 135.f)
    ));*/

    static {
        NORTH.angale = 0;
        WEST.angale = 90;
        SOUTH.angale = 180;
        EAST.angale = 270;
        NORTHEAST.angale = 315;
        NORTHWEST.angale = 45;
        SOUTHEAST.angale = 225;
        SOUTHWEST.angale = 135;
    }

    public float getAngle(){
        /*if(setAngaleA() == 0)
            this.angale = 0.f;
        if(setAngaleA() == 90)
            this.angale = 90.f;
        if(setAngaleA() == 180)
            this.angale = 180.f;
        if(setAngaleA() == 270)
            this.angale = 270.f;
        if(setAngaleA() == 45)
            this.angale = 45.f;
        if(setAngaleA() == 135)
            this.angale = 135.f;
        if(setAngaleA() == 225)
            this.angale = 225.f;
        if(setAngaleA() == 315)
            this.angale = 315.f;*/
        /*Map<Direction, Float> newAngale = new HashSet<>(Map.ofEntries(
            Map.entry(NORTH, 0.f),
            Map.entry(EAST, 270.f),
            Map.entry(WEST, 90.f),
            Map.entry(SOUTH, 180.f),
            Map.entry(NORTHEAST, 315.f),
            Map.entry(NORTHWEST, 45.f),
            Map.entry(SOUTHEAST, 225.f),
            Map.entry(SOUTHWEST, 135.f)
        ));*/
        //return newAngale.getOrDefault(this, 0.f);
        return angale;
        /*if(this.dx == 0 && this.dy == 1)
            return 0;
        if(this.dx == -1 && this.dy == 1)
            return 45;
        if(this.dx == -1 && this.dy == 0)
            return 90;
        if(this.dx == -1 && this.dy == -1)
            return 135;
        if(this.dx == 0 && this.dy == -1)
            return 180;
        if(this.dx == 1 && this.dy == -1)
            return 225;
        if(this.dx == 1 && this.dy == 0)
            return 270;
        if(this.dx == 1 && this.dy == 1)
            return 315;
        return 0;*/
    }

    public Direction combine(Direction other){
        Direction direction = NONE;
        for(Direction value: Direction.values()){
            int dx_n;
            int dy_n;
            if(dx + other.dx < -1)
                dx_n = -1;
            else if(dx + other.dx > 1)
                dx_n = 1;
            else
                dx_n = dx + other.dx;
            if(dy + other.dy < -1)
                dy_n = -1;
            else if(dy + other.dy > 1)
                dy_n = 1;
            else
                dy_n = dy + other.dy;
            if(value.dx == dx_n && value.dy == dy_n) {
                direction = value;
                return direction;
            }
        }
        return direction;
    }

    public float setAngaleA(){
        /*if(this.dx == 0 && this.dy == 1)
            return 0;
        if(this.dx == -1 && this.dy == 1)
            return 45;
        if(this.dx == -1 && this.dy == 0)
            return 90;
        if(this.dx == -1 && this.dy == -1)
            return 135;*/
        /*if(this.dx == 0 && this.dy == -1)
            return 180;
        if(this.dx == 1 && this.dy == -1)
            return 225;
        if(this.dx == 1 && this.dy == 0)
            return 270;
        if(this.dx == 1 && this.dy == 1)
            return 315;
        return 360;*/
        return setAngaleB();
    }

    public float setAngaleB(){
        if(this.dx == 0 && this.dy == 1){
            return 0;
        } else if(this.dx == 1 && this.dy == 0){
            return 270;
        } else if(this.dx == -1 && this.dy == 0){
            return 90;
        } else if(this.dx == 0 && this.dy == -1){
            return 180;
        } else {
            return setAngaleC();
        }
    }

    public float setAngaleC(){
        if(this.dx == -1 && this.dy == -1){
            return 135;
        } else if(this.dx == 1 && this.dy == -1){
            return 225;
        } else if(this.dx == -1 && this.dy == 1){
            return 45;
        } else if(this.dx == 1 && this.dy == 1){
            return 315;
        } else {
            return 0;
        }
    }

    public static Direction fromAngle(float angle) {
        if(angle == 0)
            return Direction.NORTH;
        if(angle == 90)
            return Direction.WEST;
        if(angle == 180)
            return Direction.SOUTH;
        if(angle == 270)
            return Direction.EAST;
        if(angle == 45)
            return Direction.NORTHWEST;
        if(angle == 315)
            return Direction.NORTHEAST;
        if(angle == 135)
            return Direction.SOUTHWEST;
        if(angle == 225)
            return Direction.SOUTHEAST;
        return NONE;
    }
}

