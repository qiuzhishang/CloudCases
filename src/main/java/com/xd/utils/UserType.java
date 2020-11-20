package com.xd.utils;


//医生患者类型
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

