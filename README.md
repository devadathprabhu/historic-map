# historic-map

An extended version of a map that supports to store the snapshot of the map when ever required and also provides the feature to fetch the value of key at given snapshot

Example:

HistoricMap<String, String> map = new HistoricMapImpl<>();
map.put(“a”, “a1”);
map.snapshot(); // 0
map.put(“b”, “b1”);
map.snapshot(); // 1
map.put(“b”, “b2.1”);
map.put(“b”, “b2.1”);
map.put(“c”, “c1”);
map.snapshot(); // 2
map.get(“b”, 1); // “b1”
map.get(“b”, 2); // “b2.1”
map.get(“a”, 2); // “a1”
