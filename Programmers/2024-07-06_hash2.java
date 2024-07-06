import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> clothesMap = new HashMap<String, Integer>();

        for (int i = 0; i < clothes.length; i++) {
            String[] arr = clothes[i];;
            clothesMap.put(arr[1], clothesMap.getOrDefault(arr[1], 0) + 1);
        }

        for (Integer value: clothesMap.values()) {
            answer *= (value + 1);
        }

        return answer - 1;
    }
}