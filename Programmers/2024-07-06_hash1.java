import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>(Arrays.asList(phone_book));

        for (String phone : phone_book) {
            for (int i = 1; i < phone.length(); i++) {
                if (set.contains(phone.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
