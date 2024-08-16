package com.filkond.upgrades.holder;

import com.filkond.upgrades.configuration.UpgradeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AdvancedHolder<E> extends UpgradeHolder {
    default int getLevel(@NotNull String key) {
        return getProgresses().getOrDefault(key, 0);
    }

    default void setLevel(@NotNull String key, int level) {
        getProgresses().put(key, level);
    }

    default void resetProgress(@NotNull String key) {
        getProgresses().remove(key);
    }

    default void resetProgresses() {
        getProgresses().clear();
    }

    default void resetProgress(@NotNull UpgradeType type) {
        resetProgress(type.getId());
    }

    default int getLevel(@NotNull UpgradeType type) {
        return getLevel(type.getId());
    }

    default void setLevel(@NotNull UpgradeType type, int level) {
        setLevel(type.getId(), level);
    }

    @Nullable E getEntity();
}
