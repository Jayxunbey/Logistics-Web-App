package com.example.logisticproject.projection;

import java.util.UUID;

/**
 * Projection for {@link com.example.logisticproject.entity.Region}
 */
public interface RegionProjection {
    UUID getId();

    String getNameEn();
}