package justin.reference.softReference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SoftReferenceCache<K, V> implements Cache<K, V> {

	private Map<K, ExtraInfoReference<V>> cache = new ConcurrentHashMap<>();
    private ReferenceQueue<V> referenceQueue = new ReferenceQueue<>();

    @Override
    public boolean set(K key, V value) {
        ExtraInfoReference<V> extraInfoReference = new ExtraInfoReference<>(key, value, referenceQueue);
        cache.put(key, extraInfoReference);
        return true;
    }

    @Override
    public V get(K key) {
        V value = null;
        if (cache.containsKey(key)) {
            ExtraInfoReference<V> sr = cache.get(key);
            value = sr.get();
        }

        if (value == null) {
            clear();
            value = getValueFromDB(key);
            ExtraInfoReference<V> extraInfoReference = new ExtraInfoReference<V>(key, value, referenceQueue);
            cache.put(key, extraInfoReference);
        }
        return value;
    }

    protected abstract V getValueFromDB(K key);

    private void clear() {
        ExtraInfoReference<V> reference = null;
        while ((reference = (ExtraInfoReference<V>) referenceQueue.poll()) != null) {
            Object key = reference.getKey();
            cache.remove(key);
            System.out.println("删除key: " + key);
        }
    }

    private static class ExtraInfoReference<T> extends SoftReference<T> {
        private Object key;

        public ExtraInfoReference(Object key, T value, ReferenceQueue<T> referenceQueue) {
            super(value, referenceQueue);
            this.key = key;
        }

        public Object getKey() {
            return key;
        }
    }

}
