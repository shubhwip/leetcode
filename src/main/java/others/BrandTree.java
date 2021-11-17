package others;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

// Booking
public class BrandTree {

    static class BrandDto {
        Integer brandId;
        Integer parentBrandId;
        Integer hotelCount;

        public BrandDto(Integer i, Integer i1, Integer i2) {
            this.brandId = i;
            this.parentBrandId = i1;
            this.hotelCount = i2;
        }
    }

    static int calculate(List<BrandDto> brandDto) {
        Map<Integer, List<Pair<Integer, Integer>>> map = new HashMap<>();
        for (BrandDto bd : brandDto) {
            if (bd.parentBrandId == null) {
                if (map.get(bd.brandId) == null) {
                    List<Pair<Integer, Integer>> l = new ArrayList<>();
                    l.add(new Pair<>(bd.brandId, bd.hotelCount));
                    map.put(bd.brandId, l);
                } else
                    map.get(bd.brandId).add(new Pair<>(bd.brandId, bd.hotelCount));
            } else if (map.get(bd.parentBrandId) == null) {
                List<Pair<Integer, Integer>> l = new ArrayList<>();
                l.add(new Pair<>(bd.brandId, bd.hotelCount));
                map.put(bd.parentBrandId, l);
            } else
                map.get(bd.parentBrandId).add(new Pair<>(bd.brandId, bd.hotelCount));
        }
        Map<Integer, Integer> sortedMap = new HashMap<>();
        List<Integer> sum = new ArrayList<>();
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : map.entrySet()) {
            sortedMap.put(entry.getKey(), recur(entry.getKey(), Integer.MIN_VALUE, entry.getValue(), map));
        }
        Map<Integer, Integer> finalMap = sortedMap.entrySet().stream().sorted(Map.Entry
                .<Integer, Integer>comparingByValue().reversed()).collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,      // merge function
                LinkedHashMap::new));
        System.out.println(finalMap);
        return 0;
    }

    private static Integer recur(Integer key, Integer listPairKey, List<Pair<Integer, Integer>> value, Map<Integer, List<Pair<Integer, Integer>>> map) {
        int sum = 0;
        if (key == listPairKey) {
            return 0;
        }
        for (Pair<Integer, Integer> v : value) {
            sum += v.getValue();
            if (map.get(v.getKey()) != null)
                sum += recur(key, v.getKey(), map.get(v.getKey()), map);
        }
        return sum;
    }


    public static void main(String[] args) {
        BrandDto brandto1 = new BrandDto(3, 0, 11);
        BrandDto brandto2 = new BrandDto(6, null, 12);
        BrandDto brandto3 = new BrandDto(1, 0, 43);
        BrandDto brandto4 = new BrandDto(5, 3, 10);
        BrandDto brandto5 = new BrandDto(0, null, 10);
        BrandDto brandto6 = new BrandDto(2, 0, 17);
        BrandDto brandto7 = new BrandDto(4, 3, 11);
        BrandDto brandto8 = new BrandDto(7, 6, 34);
        List<BrandDto> bd = Arrays.asList(brandto1, brandto2, brandto3, brandto4, brandto5, brandto6, brandto7, brandto8);
        System.out.println(calculate(bd));
    }
}
