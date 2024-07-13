import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        PriorityQueue p = new PriorityQueue<>(Collections.reverseOrder());;

        for(int i = 0; i < priorities.length; i++){
            p.add(priorities[i]);
        }

        while(!p.isEmpty()){
            for(int i = 0; i < priorities.length; i++){
                if (priorities[i] == (int) p.peek()){
                    if (i == location){
                        return answer;
                    }
                    p.poll();
                    answer++;
                }
            }
        }

        return answer;
    }
}

// 원래 시도한 방법
// Queue를 활용하고, 단순히 지문에 나온 걸 그대로 구현
// 현재 큐에서 하나 꺼낸 후, 남은 큐에 그보다 우선순위가 높은 요소가 있는지 확인
// 더 우선순위가 높은게 있다면, 꺼낸 큐를 다시 큐에 그 요소를 넣고, 없다면 answer를 1 증가시키고, 현재의 index와 location 값 비교
import java.util.*;

class Process {
    int priority;
    int index;
    
    public Process(int priority, int index) {
        this.priority = priority;
        this.index = index;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> q = new LinkedList<>();
        
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new Process(priorities[i], i));
        }
        
        while (!q.isEmpty()) {
            var current = q.poll();
            boolean hasHighPriority = false;
            
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Process p = q.poll();
                if (p.priority > current.priority) {
                    hasHighPriority = true;
                }
                q.offer(p);
            }
            
            if (hasHighPriority) {
                q.offer(current);
            } else {
                answer++;
                if (current.index == location) {
                    return answer;
                }
            }
        }
        
        return answer;
    }
}