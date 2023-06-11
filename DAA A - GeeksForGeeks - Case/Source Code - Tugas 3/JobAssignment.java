import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
    Node parent;
    int pathCost;
    int cost;
    int workerID;
    int jobID;
    boolean[] assigned;

    Node(int x, int y, boolean[] assigned, Node parent) {
        this.workerID = x;
        this.jobID = y;
        this.assigned = assigned.clone();
        this.assigned[y] = true;
        this.parent = parent;
    }
}

class JobAssignment {
    private static final int N = 4;

    private static Node newNode(int x, int y, boolean[] assigned, Node parent) {
        return new Node(x, y, assigned, parent);
    }

    private static int calculateCost(int[][] costMatrix, int x, int y, boolean[] assigned) {
        int cost = 0;
        boolean[] available = new boolean[N];

        for (int j = 0; j < N; j++) {
            available[j] = true;
        }

        for (int i = x + 1; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < N; j++) {
                if (!assigned[j] && available[j] && costMatrix[i][j] < min) {
                    minIndex = j;
                    min = costMatrix[i][j];
                }
            }

            cost += min;
            available[minIndex] = false;
        }

        return cost;
    }

    private static class NodeComparator implements Comparator<Node> {
        public int compare(Node lhs, Node rhs) {
            return lhs.cost - rhs.cost;
        }
    }

    private static void printAssignments(Node min) {
        if (min.parent == null) {
            return;
        }

        printAssignments(min.parent);
        System.out.println("Assign Worker " + (char) (min.workerID + 'A') + " to Job " + min.jobID);
    }

    private static int findMinCost(int[][] costMatrix) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        boolean[] assigned = new boolean[N];
        Node root = newNode(0, -1, assigned, null); // Mengubah indeks dummy node menjadi 0
        root.pathCost = root.cost = 0;
        root.workerID = 0; // Mengubah indeks dummy node menjadi 0
        pq.add(root);

        while (!pq.isEmpty()) {
            Node min = pq.poll();
            int i = min.workerID + 1;

            if (i == N) {
                printAssignments(min);
                return min.cost;
            }

            for (int j = 0; j < N; j++) {
                if (!min.assigned[j]) {
                    Node child = newNode(i, j, min.assigned, min);
                    child.pathCost = min.pathCost + costMatrix[i][j];
                    child.cost = child.pathCost + calculateCost(costMatrix, i, j, child.assigned);
                    pq.add(child);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] costMatrix = {
                {9, 2, 7, 8},
                {6, 4, 3, 7},
                {5, 8, 1, 8},
                {7, 6, 9, 4}
        };

        System.out.println("\nOptimal Cost is " + findMinCost(costMatrix));
    }
}
