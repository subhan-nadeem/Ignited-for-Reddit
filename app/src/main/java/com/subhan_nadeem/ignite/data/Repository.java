package com.subhan_nadeem.ignite.data;

import java.util.List;

/**
 * Created by Subhan Nadeem on 2017-09-09.
 *
 * Base interface required to implement repositories
 */

public interface Repository <T> {
    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

    void remove(Specification specification);

    List<T> query(Specification specification);
}
