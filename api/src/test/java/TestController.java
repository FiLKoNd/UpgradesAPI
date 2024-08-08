import com.filkond.upgrades.configuration.UpgradeLevel;
import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.DoubleBuff;
import com.filkond.upgrades.configuration.buff.IntegerBuff;
import com.filkond.upgrades.controller.AdvancedController;
import com.filkond.upgrades.db.DatabaseCredentials;

import java.util.Set;
import java.util.UUID;

public class TestController extends AdvancedController<Entity> {
    Set<UpgradeType> types = Set.of(UpgradeType.builder()
                    .id("test")
                    .levels(Set.of(
                            UpgradeLevel.builder()
                                    .level(1)
                                    .buffs(Set.of(new IntegerBuff("test", 5), new IntegerBuff("test", 5))).build(),
                            UpgradeLevel.builder()
                                    .level(2)
                                    .buffs(Set.of(new IntegerBuff("test2", 5), new IntegerBuff("test3", 5))).build(),
                            UpgradeLevel.builder()
                                    .level(3)
                                    .buffs(Set.of(new IntegerBuff("test3", 5), new IntegerBuff("test2", 5))).build()
                    )).build(),
            UpgradeType.builder()
                    .id("test2")
                    .levels(Set.of(
                            UpgradeLevel.builder()
                                    .level(1)
                                    .buffs(Set.of(new IntegerBuff("test2", 5), new IntegerBuff("test", 5))).build(),
                            UpgradeLevel.builder()
                                    .level(2)
                                    .buffs(Set.of(new DoubleBuff("test2", 5D), new IntegerBuff("test", 5))).build(),
                            UpgradeLevel.builder()
                                    .level(3)
                                    .buffs(Set.of(new IntegerBuff("test2", 5), new IntegerBuff("test", 5))).build()
                    )).build(),
            UpgradeType.builder()
                    .id("test3")
                    .levels(Set.of(
                            UpgradeLevel.builder()
                                    .level(1)
                                    .buffs(Set.of(new IntegerBuff("test3", 5), new IntegerBuff("test", 5))).build(),
                            UpgradeLevel.builder()
                                    .level(2)
                                    .buffs(Set.of(new IntegerBuff("test3", 5), new IntegerBuff("test", 5))).build(),
                            UpgradeLevel.builder()
                                    .level(3)
                                    .buffs(Set.of(new IntegerBuff("test3", 5), new IntegerBuff("test", 5))).build()
                    )).build());
    public TestController(DatabaseCredentials credentials) {
        super(credentials);
    }

    @Override
    public UUID getUniqueId(Entity entity) {
        return UUID.randomUUID();
    }

    @Override
    public Set<UpgradeType> getUpgradeTypes() {
        // return test filled set of upgradetype with levels and buffs, 5 elements for single set
        return types;
    }
}
