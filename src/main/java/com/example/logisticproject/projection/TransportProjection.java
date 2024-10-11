package com.example.logisticproject.projection;

import com.example.logisticproject.entity.Transport;

/**
 * Projection for {@link Transport}
 */
public interface TransportProjection {
    Integer getId();

    String getName();

    Integer getTypeId();

    String getPhotoAttachmentId();

    Integer getMaxCapacity();

    Double getLength();

    Double getHeight();

    Double getWidth();

    boolean isActive();

    boolean isCanBeFully();

    boolean isCanBePartially();

}