package com.filkond.upgrades.holder;

import com.filkond.upgrades.holder.dao.UpgradeHolderDAOImpl;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "upgrade_holders", daoClass = UpgradeHolderDAOImpl.class)
public abstract class SimpleUpgradeHolder implements UpgradeHolder {
    public SimpleUpgradeHolder(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = "uuid", unique = true, canBeNull = false)
    protected UUID uniqueId;

    @DatabaseField(columnName = "progress", dataType = DataType.SERIALIZABLE, canBeNull = false)
    protected final HashMap<String, Integer> progresses = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleUpgradeHolder that = (SimpleUpgradeHolder) o;
        return id == that.id && Objects.equals(uniqueId, that.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueId);
    }
}
