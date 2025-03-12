package net.sleepykairo.debalance.util.interfaces;

public interface EntityVoltageAccess {
    void debalance$setVoltageAmount(float voltageAmount);

    float debalance$getVoltageAmount();

    void debalance$incrementVoltageAmount(float amount);

    void debalance$decrementVoltageAmount();
}
