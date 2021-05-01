package com.barribob.intothemaelstrom.mana;

public interface IMana {
    boolean isRecentlyConsumed();

    void setRecentlyConsumed(boolean consumed);

    void consume(float amount);

    void replenish(float amount);

    void set(float amount);

    float getMana();

    boolean isLocked();

    void setLocked(boolean locked);
}
