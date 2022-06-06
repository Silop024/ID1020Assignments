package Lab4.Extra1;

public class IndexMinPQ<Key extends Comparable<Key>>
{
    private int N;
    private int maxN;
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    
    public IndexMinPQ(int max)
    {
        this.maxN = max;
        this.N = 0;
        this.keys = (Key[]) new Comparable[maxN + 1];
        this.pq = new int[maxN + 1];
        this.qp = new int[maxN + 1];

        for(int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public void change(int k, Key key)
    {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean isEmpty() { return N == 0; }

    public boolean contains(int k) { return qp[k] != -1; }

    public void insert(int k, Key key)
    {
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    public int delMin()
    {
        int indexOfMin = pq[1];
        exchange(1, N--);
        sink(1);
        keys[pq[N +1 ]] = null;
        qp[pq[N + 1]] = -1;

        return indexOfMin;
    }

    private boolean greater(int i, int j) { return keys[pq[i]].compareTo(keys[pq[j]]) > 0; }


    private void exchange(int i, int j)
    {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k)
    {
        while(k > 1 && greater(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k)
    {
        while(2 * k <= N) {
            int j = 2 * k;
            if(j < N && greater(j, j + 1)) {
                j++;
            }
            if(!greater(k, j)) {
                break;
            }
            exchange(k, j);
            k = j;
        }
    }
}
