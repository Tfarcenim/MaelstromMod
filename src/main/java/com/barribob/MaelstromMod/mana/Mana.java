package com.barribob.MaelstromMod.mana;

import com.barribob.MaelstromMod.config.ModConfig;

public class Mana implements IMana {
    private float mana = 0;
    private boolean locked = true;
    private boolean recentlyConsumed = false;

    @Override
    public void consume(float amount) {
        if (locked)
            return;
        this.recentlyConsumed = true;
        this.mana = Math.max(0, this.mana - amount);
    }

    @Override
    public void replenish(float amount) {
        if (locked)
            return;
        this.mana = Math.min(ModConfig.misc.mana_capacity, mana + amount);
    }

    @Override
    public void set(float amount) {
        this.mana = Math.min(Math.max(amount, 0), ModConfig.misc.mana_capacity);
    }

    @Override
    public float getMana() {
        return mana;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean isRecentlyConsumed() {
        return this.recentlyConsumed;
    }

    @Override
    public void setRecentlyConsumed(boolean consumed) {
        this.recentlyConsumed = consumed;
    }
}
