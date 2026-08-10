package com.example.week2amantasksxml;

import java.util.ArrayList;
import java.util.List;

public class Counter {

    private int value;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setValue(int value) {
        this.value = value;

        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.onValueChanged(value);
        }
    }
}
