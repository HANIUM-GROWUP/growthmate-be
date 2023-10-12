package com.growup.growthmate.batch;

import java.util.List;

public interface BatchRepository<T> {

    void insertAll(List<T> entities);

    void updateAll(List<T> entities);
}
