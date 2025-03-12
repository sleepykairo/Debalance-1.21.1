package net.sleepykairo.debalance.util.interfaces;

public interface PlayerAttackAccess {
    void debalance$setPreviousAttackCooldown(float prevAttackCooldown);

    float debalance$getPreviousAttackCooldown();

    void debalance$setAttackDamage(float attackDmg);

    float debalance$getAttackDamage();

    void debalance$setBonusDamage(float bonusDmg);

    float debalance$getBonusDamage();
}
