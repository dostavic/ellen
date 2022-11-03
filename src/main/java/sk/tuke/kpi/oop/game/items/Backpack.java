package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private List<Collectible> backpacknew = new ArrayList<>();

    public Backpack(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        //return backpack;
        return List.copyOf(backpacknew);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return backpacknew.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if(getSize() < getCapacity())
            backpacknew.add(actor);
        else
            throw new IllegalStateException("<backpack> is full");
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        backpacknew.remove(actor);
    }

    @Override
    public @Nullable Collectible peek() {
        if(getSize() <= 0)
            return null;
        return backpacknew.get(backpacknew.size() - 1);
    }

    @Override
    public void shift() {
        //backpack.add(0, backpack.remove(backpack.size() - 1));
        Collections.rotate(backpacknew, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return backpacknew.iterator();
    }
}
