package algorithms.knapsack;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KnapsackTest {

    @Test
    void should_pack_one_goods_to_knapsack_for_capacity_is_1_and_3_goods(){
        final var knapsack = new Knapsack(1);

        final var computer = new Goods("电脑", 3, 2000);
        final var sound = new Goods("音箱", 4, 3000);
        final var guitar = new Goods("吉他", 1, 1500);

        List<Goods> goods = knapsack.packByDynamicPlanning(List.of(computer, sound, guitar));
        assertEquals(1, goods.size());
        assertTrue(goods.contains(guitar));
    }

    @Test
    void should_pack_two_goods_to_knapsack_for_capacity_is_4_and_3_goods(){
        final var knapsack = new Knapsack(4);

        final var computer = new Goods("电脑", 3, 2000);
        final var sound = new Goods("音箱", 4, 3000);
        final var guitar = new Goods("吉他", 1, 1500);

        List<Goods> goods = knapsack.packByDynamicPlanning(List.of(computer, sound, guitar));
        assertEquals(2, goods.size());
        assertTrue(goods.contains(computer));
        assertTrue(goods.contains(guitar));
    }

    @Test
    void should_pack_two_goods_to_knapsack_for_capacity_is_4_and_3_goods_222(){
        final var knapsack = new Knapsack(8);

        final var computer = new Goods("电脑", 3, 2000);
        final var sound = new Goods("音箱", 4, 3000);
        final var guitar = new Goods("吉他", 1, 1500);
        final var chuang = new Goods("床", 5, 3500);

        List<Goods> goods = knapsack.packByRecursiveBacktracking(List.of(chuang, computer, sound, guitar));
        assertEquals(3, goods.size());
        assertTrue(goods.contains(computer));
        assertTrue(goods.contains(guitar));
        assertTrue(goods.contains(sound));
    }

    @Test
    void should_pack_two_goods_to_knapsack_for_capacity_is_5_and_3_goods(){
        final var knapsack = new Knapsack(5);

        final var computer = new Goods("电脑", 3, 2000);
        final var sound = new Goods("音箱", 4, 3000);
        final var guitar = new Goods("吉他", 1, 1500);

        List<Goods> goods = knapsack.packByDynamicPlanning(List.of(computer, sound, guitar));
        assertEquals(2, goods.size());
        assertTrue(goods.contains(sound));
        assertTrue(goods.contains(guitar));
    }

    @Test
    void should_pack_two_goods_to_knapsack_for_capacity_is_7_and_3_goods(){
        final var knapsack = new Knapsack(7);

        final var computer = new Goods("电脑", 3, 2000);
        final var sound = new Goods("音箱", 4, 3000);
        final var guitar = new Goods("吉他", 1, 1500);

        List<Goods> goods = knapsack.packByDynamicPlanning(List.of(computer, sound, guitar));
        assertEquals(2, goods.size());
        assertTrue(goods.contains(computer));
        assertTrue(goods.contains(sound));
    }

    @Test
    void should_pack_three_goods_to_knapsack_for_capacity_is_10_and_3_goods(){
        final var knapsack = new Knapsack(10);

        final var computer = new Goods("电脑", 3, 2000);
        final var sound = new Goods("音箱", 4, 3000);
        final var guitar = new Goods("吉他", 1, 1500);

        List<Goods> goods = knapsack.packByDynamicPlanning(List.of(computer, sound, guitar));
        assertEquals(3, goods.size());
        assertTrue(goods.contains(computer));
        assertTrue(goods.contains(sound));
        assertTrue(goods.contains(guitar));
    }

}
