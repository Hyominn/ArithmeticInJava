package workbook.ch08;

/**
 * @Author: NZY
 * @Date: 2020-05-31 13:03:05
 * */
public class DisjSets {
    private int[] set;

    public DisjSets(int numElements) {
        set = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            set[i] = -1;
        }
    }

    public void union(int root1, int root2) {
        if (set[root2] < set[root1]) {
            set[root1] = root2;
        } else {
            if (set[root1] == set[root2])
                set[root1]--;
            set[root2] = root1;
        }
    }

    public int find(int x) {
        if (set[x] < 0) {
            return x;
        } else {
            return set[x] = find(set[x]);
        }
    }
}
