package eu.telm.util;

import eu.telm.dataBase.HibernateUtil;
import eu.telm.model.AuditLog;
import eu.telm.model.IAuditLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.sql.Connection;

/**
 * Created by kasia on 15.01.17.
 */
public class AuditLogUtil{

    public static void LogIt(String action,
                             IAuditLog entity ){

        Session tempSession = HibernateUtil.getSessionFactory().openSession();

        try {
            AuditLog auditRecord = new AuditLog(action,entity.getLogDeatil()
                    , new Date(),entity.getCreatedBy(), entity.getIdToLog(), entity.getClass().toString());
            tempSession.save(auditRecord);
            tempSession.flush();

        } finally {
            tempSession.close();

        }

    }
}
