import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int time = 0;
        int currentWeight = 0;
        int truckIndex = 0;

        // 다리의 길이만큼 0으로 초기화 <- 일종의 padding
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while (truckIndex < truck_weights.length || !bridge.isEmpty()) {
            time++;
            
            // 다리에서 나가는 트럭의 무게를 뺌
            currentWeight -= bridge.poll();

            if (truckIndex < truck_weights.length) {
                // 새 트럭이 다리에 올라갈 수 있는 경우
                if (currentWeight + truck_weights[truckIndex] <= weight) {
                    bridge.offer(truck_weights[truckIndex]);
                    currentWeight += truck_weights[truckIndex];
                    truckIndex++;
                } else {
                    // 새 트럭이 올라갈 수 없으면 0을 추가
                    bridge.offer(0);
                }
            }
        }

        return time;
    }
}