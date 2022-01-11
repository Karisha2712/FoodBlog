package edu.radyuk.foodblog.entity;

public class AbstractEntity {
    private long entityId;

    protected AbstractEntity() {
    }

    protected AbstractEntity(long entityId) {
        this.entityId = entityId;
    }

    public long getEntityId() {
        return entityId;
    }

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
