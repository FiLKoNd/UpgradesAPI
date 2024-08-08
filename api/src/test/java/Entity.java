import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder
@AllArgsConstructor
public class Entity {
    private final UUID uniqueId;
}
