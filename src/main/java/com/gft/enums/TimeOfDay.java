package com.gft.enums;

public enum TimeOfDay {

    MORNING {
        @Override
        public String asLowerCase() {
            return MORNING.toString().toLowerCase();
        }
    },
    NIGHT {
        @Override
        public String asLowerCase() {
            return NIGHT.toString().toLowerCase();
        }
    };


    public abstract String asLowerCase();

}
