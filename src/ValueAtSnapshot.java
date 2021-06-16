public class ValueAtSnapshot<V> {
    int snapshot;
    V value;

    public ValueAtSnapshot(int snapshot, V value){
        this.snapshot = snapshot;
        this.value = value;
    }

    public int getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(int snapshot) {
        this.snapshot = snapshot;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        ValueAtSnapshot valueAtSnapshot = (ValueAtSnapshot) obj;

        if(valueAtSnapshot.getSnapshot() == this.snapshot){
            return true;
        }

        return false;
    }

    @Override
    public int hashCode(){
        return snapshot;
    }
}
