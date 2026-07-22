package com.example.week2amantasksxml;

public class Database {

    private static Database instance;

    private Database() {
    }

    public static synchronized Database getInstance() {

        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }
}