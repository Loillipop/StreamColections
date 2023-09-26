
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    // Tasks for check knowledge of streams:
//    1) Find set of race
//    2) show all gold on the map
//    3) find count of object on coordinate  x = 2
//    4) Find third by counting gold on the map
//    5) Count all gold  grouping by race
//    6) Find the second by counting gold on each cell
    public static void main(String[] args) {
        Map<Coordinate, List<GameObject>> gameMap = GameWorld.game;
        System.out.println(getRaceSet(gameMap));
        System.out.println(getAllGold(gameMap));
        System.out.println(getObjectCountX(2, gameMap));
        System.out.println(getThirdObjectByGold(gameMap));
        System.out.println(getGoldByRace(gameMap));
        System.out.println(getSecondObjectByGoldOnEachCell(gameMap));
    }

    // 1) Find set of race

    public static Set<String> getRaceSet(Map<Coordinate, List<GameObject>> gameMap) {
        return gameMap.values()
                .stream()
                .flatMap(Collection::stream)
                .map(GameObject::getRace)
                .collect(Collectors.toSet());
    }

    //    2) show all gold on the map

    public static Integer getAllGold(Map<Coordinate, List<GameObject>> gameMap) {
        return gameMap.values()
                .stream()
                .flatMap(Collection::stream)
                .map(GameObject::getGold)
                .mapToInt(Integer::intValue)
                .sum();
    }

    //    3) find count of object on coordinate  x = 2

    public static Long getObjectCountX(int coordinate, Map<Coordinate, List<GameObject>> gameMap) {
        return gameMap.entrySet()
                .stream()
                .filter(x -> x.getKey().getX() == coordinate)
                .flatMap(value -> value.getValue().stream())
                .count();
    }

    //    4) Find third by counting gold on the map

    public static GameObject getThirdObjectByGold(Map<Coordinate, List<GameObject>> gameMap) {
        Integer thirdOfGold = gameMap.values()
                .stream()
                .flatMap(Collection::stream)
                .map(GameObject::getGold)
                .sorted((e, e1) -> e1 - e)
                .skip(2)
                .findFirst().get();
        return gameMap.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(value -> value.getGold().equals(thirdOfGold))
                .toList().get(0);
    }

    //    5) Count all gold  grouping by race

    public static Map<String, Integer> getGoldByRace(Map<Coordinate, List<GameObject>> gameMap) {
        return gameMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(groupingBy(GameObject::getRace,
                        mapping(GameObject::getGold, summingInt(Integer::intValue))));
    }

    //    6) Find the second by counting gold on each cell

    public static List<GameObject> getSecondObjectByGoldOnEachCell(Map<Coordinate, List<GameObject>> gameMap) {
        return gameMap.values()
                .stream()
                .map(e -> e.stream().min(Comparator.comparingInt(GameObject::getGold))
                .get())
                .toList();
    }
}
