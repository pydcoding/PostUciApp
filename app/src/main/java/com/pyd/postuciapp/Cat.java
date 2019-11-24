package com.pyd.postuciapp;

public class Cat extends Animal {
    private int numLives;

    public Cat(int legs, String name, int numLives) {
        super(legs, name);

        this.numLives = numLives;
    }

    @Override
    public void eat() {
        super.eat();

        // rebozarse();
    }
}
