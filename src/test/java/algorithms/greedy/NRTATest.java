package algorithms.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NRTATest {

    @Test
    void should_get_min_radio_station_by_areas() {
        RadioStation rs1 = new RadioStation("K1", Set.of("北京", "上海", "天津"));
        RadioStation rs2 = new RadioStation("K2", Set.of("广州", "北京", "深圳"));
        RadioStation rs3 = new RadioStation("K3", Set.of("成都", "上海", "杭州"));
        RadioStation rs4 = new RadioStation("K4", Set.of("上海", "天津"));
        RadioStation rs5 = new RadioStation("K5", Set.of("杭州", "大连"));

        final var nrta = new NRTA(List.of(rs1, rs2, rs3, rs4, rs5));

        Set<String> areas = new HashSet<>(Arrays.asList("北京", "上海", "天津", "广州", "深圳", "成都", "杭州", "大连"));
        final var minRadioStation = nrta.getMinRadioStation(areas);
        assertEquals(List.of(rs1, rs2, rs3, rs5), minRadioStation);
    }
}
