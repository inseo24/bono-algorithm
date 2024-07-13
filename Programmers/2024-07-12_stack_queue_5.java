import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12906
public class Solution {
    public Stack<Integer> solution(int[] arr) {
        Stack<Integer> s = new Stack();
        s.push(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            if (s.peek() != arr[i]) {
                s.push(arr[i]);
            }
        }
        
        return s;
    }
}

// int[] 로 리턴, ArrayList 활용하는 답안 추가
public class Solution {
    public int[] solution(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            if (list.get(list.size() - 1) != arr[i]) {
                list.add(arr[i]);
            }
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}