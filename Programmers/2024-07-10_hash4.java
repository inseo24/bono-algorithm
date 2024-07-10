import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String runner : participant) {
            map.put(runner, map.getOrDefault(runner, 0) + 1);
        }
        
        for (String runner : completion) {
            int count = map.get(runner);
            if (count == 1) {
                map.remove(runner);
            } else {
                map.put(runner, count - 1);
            }
        }
        
        return map.keySet().iterator().next();
    }
}