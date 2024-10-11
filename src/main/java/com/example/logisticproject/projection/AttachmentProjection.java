package com.example.logisticproject.projection;

/**
 * Projection for {@link com.example.logisticproject.entity.Attachment}
 */
public interface AttachmentProjection {
    String getId();

    String getExtension();

    String getPath();

    boolean isActive();
}