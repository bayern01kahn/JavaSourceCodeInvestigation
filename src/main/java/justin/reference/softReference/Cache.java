package justin.reference.softReference;

public interface Cache<K, V> {
	boolean set(K key, V value);
    V get(K key);
}
