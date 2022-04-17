package com.hit.narration.domain.enumeration;

public enum SpeakingSpeedEnum {

    slow(0.61),
    average(0.462),
    fast(0.380);

    public final double value;

    private SpeakingSpeedEnum(double value) {
        this.value = value;
    }
}

