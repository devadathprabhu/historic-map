public interface HistoricMap<K, V> {
    /** Puts the value for key */
    public void put(K key, V val);

    /** Returns the most recent value for key */
    public V get(K key);

    /** Takes a snapshot and returns the ID of that snapshot */
    public int snapshot();

    /** Returns the value for K at the point in time represented by snapshotId. */
    public V getAt(K key, int snapshotId);
}
