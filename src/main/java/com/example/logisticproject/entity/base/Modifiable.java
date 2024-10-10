package com.example.logisticproject.entity.base;

public interface Modifiable {
    void setDeleted(boolean deleted);

    boolean isDeleted();
}
