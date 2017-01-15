package eu.telm.model;

/**
 * Created by kasia on 15.01.17.
 */
public interface IAuditLog {

    public Long getIdToLog();
    public String getLogDeatil();
    public String getCreatedBy();
}
