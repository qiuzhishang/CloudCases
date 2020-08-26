package com.xd.utils;

public enum UserType {
    Patient {
        public int getValue() {
            return 0;
        }
    },
    Doctor {
        public int getValue() {
            return 1;
        }
    };
    public abstract int getValue();
}

