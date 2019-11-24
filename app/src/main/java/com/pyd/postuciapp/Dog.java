package com.pyd.postuciapp;

public class Dog extends Animal {
    private int mofletitos;

    public Dog(int legs, String name, int mofletitos) {
        super(legs, name);

        this.mofletitos = mofletitos;
    }

    @Override
    public void eat() {
        super.eat();

        // sleep();
    }
}
