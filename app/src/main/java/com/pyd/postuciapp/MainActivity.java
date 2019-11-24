package com.pyd.postuciapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Nuestras cosas
        setContentView(R.layout.login_activity);

        Dog dog = new Dog(4, "Nymeria", 2);
        Cat cat = new Cat(4, "Sugus", 7);

        dog.eat();
        cat.eat();
    }
}
