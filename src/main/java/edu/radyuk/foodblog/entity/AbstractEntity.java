package edu.radyuk.foodblog.entity;

/**
 * The type Abstract entity.
 */
public abstract class AbstractEntity {
    private long entityId;

    /**
     * Instantiates a new Abstract entity.
     */
    protected AbstractEntity() {
    }

    /**
     * Instantiates a new Abstract entity.
     *
     * @param entityId the entity id
     */
    protected AbstractEntity(long entityId) {
        this.entityId = entityId;
    }

    /**
     * Gets entity id.
     *
     * @return the entity id
     */
    public long getEntityId() {
        return entityId;
    }

    /**
     * Sets entity id.
     *
     * @param entityId the entity id
     */
    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return entityId == that.entityId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(entityId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEntity{");
        sb.append("entityId=").append(entityId);
        sb.append('}');
        return sb.toString();
    }
}
