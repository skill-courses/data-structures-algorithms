package algorithms.greedy;

import java.util.HashSet;
import java.util.Set;

public class RadioStation {
    private final String name;
    private final Set<String> areas;

    public RadioStation(String name, Set<String> areas) {
        this.name = name;
        this.areas = areas;
    }

    public String getName() {
        return name;
    }

    public Set<String> getAreas() {
        return areas;
    }

    public int getAreasSize(Set<String> areas) {
        Set<String> intersection = new HashSet<>(areas);
        intersection.retainAll(this.areas);
        return intersection.size();
    }
}
