package com.example.greendao.model;

public enum Role {
    NORMAL(2),
    GUEST(1),
    ADMIN(0);


    private int i;

    Role(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
