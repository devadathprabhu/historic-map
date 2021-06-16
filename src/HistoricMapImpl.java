import java.util.*;

import static java.util.stream.Collectors.toList;

public class HistoricMapImpl<K, V> implements HistoricMap<K, V> {
    Map<K, Set<ValueAtSnapshot>> map = new HashMap<>();
    private int snapshot = 0;

    @Override
    public void put(K key, V val) {
        Set<ValueAtSnapshot> list = new LinkedHashSet<>();
        if (map.containsKey(key)) {
            list = map.get(key);
        }
        List<ValueAtSnapshot> valueAtSnapshots = list.stream().filter(valueAtSnapshot -> (valueAtSnapshot.getSnapshot() == snapshot)).collect(toList());
        if(valueAtSnapshots.size() > 0){
            list.remove(valueAtSnapshots.get(0));
        }
        list.add(new ValueAtSnapshot(snapshot, val));
        map.put(key, list);
    }

    @Override
    public V get(K key) {
        V value = null;
        if (map.containsKey(key)) {
            Set<ValueAtSnapshot> val = map.get(key);
            if (val != null) {
                List<ValueAtSnapshot> lastInsertion = val.stream()
                        .filter(valueAtSnapshot -> (valueAtSnapshot.getSnapshot() == snapshot))
                        .collect(toList());
                if (lastInsertion.size() > 0) {
                    ValueAtSnapshot valueAtSnapshot = lastInsertion.get(0);
                    value = (V) valueAtSnapshot.getValue();
                }
            }
        }
        return value;
    }

    @Override
    public int snapshot() {
        return snapshot++;
    }

    @Override
    public V getAt(K key, int snapshotId) {
        V value = null;
        ValueAtSnapshot prevVal = null;
        if (map.containsKey(key)) {
            Set<ValueAtSnapshot> valList = map.get(key);
            if (valList != null && valList.size() > 0) {
                List<ValueAtSnapshot> lastInsertion = valList.stream()
                        .filter(valueAtSnapshot -> (valueAtSnapshot.getSnapshot() == snapshotId))
                        .collect(toList());
                if (lastInsertion.size() > 0) {
                    ValueAtSnapshot valueAtSnapshot = lastInsertion.get(0);
                    value = (V) valueAtSnapshot.getValue();
                } else {
                    lastInsertion = valList.stream().skip(valList.size()-1).collect(toList());
                    if(lastInsertion.size() > 0){
                        ValueAtSnapshot valueAtSnapshot = lastInsertion.get(0);
                        if (valueAtSnapshot.getSnapshot() < snapshotId ){
                            value = (V) valueAtSnapshot.getValue();
                        }
                    }
                }
            }
        }
        return value;
    }
}
