package algorithms.greedy;

import java.util.*;

public class NRTA {
    private final List<RadioStation> radioStations;

    public NRTA(List<RadioStation> radioStations) {
        this.radioStations = radioStations;
    }

    public List<RadioStation> getMinRadioStation(Set<String> areas) {
        List<RadioStation> minRadioStation = new ArrayList<>();
        while (areas.isEmpty()) {
            this.radioStations.stream().max(Comparator.comparingInt(a -> a.getAreasSize(areas)))
                    .ifPresent((RadioStation max) -> {
                        areas.removeAll(max.getAreas());
                        minRadioStation.add(max);
                    });
        }
        return minRadioStation;
    }
}
