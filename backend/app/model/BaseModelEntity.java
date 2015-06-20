package app.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Jacobo on 20/06/2015.
 */
public class BaseModelEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Transient
    private UUID uuid;

    @Column(name = "UUID")
    private String uuidStr;

    @PrePersist
    protected void prePersist() {
        syncUuidString();
    }

    protected void syncUuidString() {
        if (null == uuidStr) {
            // initial method call fills the uuid
            getUuid();
        }
    }

    public UUID getUuid() {
        if (uuidStr == null) {
            if (uuid == null) {
                uuid = UUID.randomUUID();
            }
            uuidStr = uuid.toString();
        }
        if (uuid == null && uuidStr != null) {
            uuid = UUID.fromString(uuidStr);
        }
        return uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseModelEntity that = (BaseModelEntity) o;

        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getUuid() != null ? getUuid().hashCode() : 0;
    }

    public Long getVersion() {
        return version;
    }

    public String getUuidStr() {
        return uuidStr;
    }
}
