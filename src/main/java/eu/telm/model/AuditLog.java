package eu.telm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by kasia on 15.01.17.
 */
@Entity
@Table(name = "auditlog")
public class AuditLog implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "AUDIT_LOG_ID", unique = true)
    private Long auditLogId;

    @Column(name = "ACTION")
    private String action;
    @Column(name = "DETAIL")
    private String detail;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "ENTITY_ID")
    private long entityId;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "ENTITY_NAME")
    private String entityName;

    public AuditLog(String action, String detail, Date createdDate, String createdBy, long entityId, String entityName) {
        this.action = action;
        this.detail = detail;
        this.entityId = entityId;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.entityName = entityName;
    }

    public Long getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Long auditLogId) {
        this.auditLogId = auditLogId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
