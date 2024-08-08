import com.filkond.upgrades.configuration.buff.DoubleBuff;
import com.filkond.upgrades.configuration.buff.IntegerBuff;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.db.MySQLCredentials;

import java.io.*;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        final DatabaseCredentials credentials = new MySQLCredentials("upgrades", "localhost", "filkond", "1230");
        final TestController controller = new TestController(credentials);
        Entity e1 = new Entity(UUID.randomUUID());
        Entity e2 = new Entity(UUID.randomUUID());
        Entity e3 = new Entity(UUID.randomUUID());
        controller.getHolderOrDefault(e1).setLevel("test2", 2);
        controller.getHolderOrDefault(e1).setLevel("test3", 2);
        controller.getHolderOrDefault(e1).setLevel("test", 2);
        controller.getHolderOrDefault(e2).setLevel("test2", 1);
        controller.getHolderOrDefault(e2).setLevel("test3", 3);
        controller.getHolderOrDefault(e2).setLevel("test", 2);
        controller.getHolderOrDefault(e3).setLevel("test", 1);
        controller.getHolderOrDefault(e3).setLevel("test3", 3);
        controller.getHolderOrDefault(e3).setLevel("test2", 2);
        long sum = 0;
        long st = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            long start = System.currentTimeMillis();
            var buffs = controller.getAllBuffs(e1, "test", IntegerBuff.class);
            for (UpgradeBuff<?> buff : buffs) {
                System.out.println(buff.getId() + " " + buff.getValue());
            }
            controller.getAllBuffs(e2, "test2", DoubleBuff.class);
            controller.getAllBuffs(e3, "test3", IntegerBuff.class);
            long stop = System.currentTimeMillis();
            System.out.println("Time: " + (stop - start));
            sum += stop - start;
        }
        System.out.println("Total: " + " " + (System.currentTimeMillis() - st));
        System.out.println(sum / 2000);
    }
}
