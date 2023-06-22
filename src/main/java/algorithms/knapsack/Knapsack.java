package algorithms.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private final int capacity;

    public Knapsack(int capacity) {
        this.capacity = capacity;
    }

    public List<Goods> packByDynamicPlanning(List<Goods> goods) {
        int[][] table = new int[goods.size() + 1][capacity + 1];

        setMaxValue(goods, table);

        return getSelectedGoods(goods, table);
    }

    public List<Goods> packByRecursiveBacktracking(List<Goods> goodsList) {
        List<Goods> selectedGoods = new ArrayList<>();
        List<Goods> currentCombination = new ArrayList<>();
        backtrack(goodsList, 0, currentCombination, selectedGoods, 0, 0);
        return selectedGoods;
    }

    private void backtrack(List<Goods> goodsList, int index, List<Goods> currentCombination, List<Goods> selectedGoods,
                           int currentWeight, int currentValue) {
        int maxValue = selectedGoods.stream().mapToInt(Goods::getValue).sum();
        if (currentWeight <= capacity && currentValue > maxValue) {
            selectedGoods.clear();
            selectedGoods.addAll(currentCombination);
        }

        if (currentWeight > capacity || index >= goodsList.size()) {
            return;
        }

        Goods currentGoods = goodsList.get(index);

        currentCombination.add(currentGoods);
        backtrack(goodsList, index + 1, currentCombination, selectedGoods, currentWeight + currentGoods.getWeight(),
                currentValue + currentGoods.getValue());
        currentCombination.remove(currentCombination.size() - 1);
        backtrack(goodsList, index + 1, currentCombination, selectedGoods, currentWeight, currentValue);
    }

    private List<Goods> getSelectedGoods(List<Goods> goods, int[][] table) {
        List<Goods> selectedGoods = new ArrayList<>();
        int goodIndex = goods.size();
        int cap = capacity;
        while (goodIndex > 0 && cap > 0) {
            if (table[goodIndex][cap] != table[goodIndex - 1][cap]) {
                Goods good = goods.get(goodIndex - 1);
                selectedGoods.add(good);
                cap -= good.getWeight();
            }
            goodIndex--;
        }

        return selectedGoods;
    }

    private void setMaxValue(List<Goods> goods, int[][] table) {
        for (int goodIndex = 1; goodIndex <= goods.size(); goodIndex++) {
            Goods good = goods.get(goodIndex - 1);
            for (int cap = 1; cap <= capacity; cap++) {
                if (good.getWeight() > cap) {
                    table[goodIndex][cap] = table[goodIndex - 1][cap];
                } else {
                    table[goodIndex][cap] = Math.max(
                            table[goodIndex - 1][cap], table[goodIndex - 1][cap - good.getWeight()] + good.getValue());
                }
            }
        }
    }
}
