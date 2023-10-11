package com.dlsdlworld.spring.api.repository;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AppFileCodeRepository {


//    List<GetPushListDto> pushList = new LinkedList<>();

    @PersistenceContext
    EntityManager em;

    @PreAuthorize("@security.hasPermission({'APP_READ'})")
    public String getFileCodeByAppKind(String appkind){

        String returnFileCode = null;

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

        try {
            conn = dataSource.getConnection();

            String selectSql = "select filecode from public.app_filecode where appkind = ?";


            stat = conn.prepareStatement(selectSql);
            stat.setString(1, appkind);

            rs = stat.executeQuery();

//            pushList.clear();

            while (rs.next()) {
                returnFileCode = rs.getString("filecode");
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("getPushList" + e.toString());
        }
        return returnFileCode;
    }
    @PreAuthorize("@security.hasPermission({'APP_WRITE'})")
    public String updateFileCodeByAppKind(String appkind, String filecode){

        String result = null;

        Connection conn = null;
        PreparedStatement stat = null;
        int rs;

        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

        try {
            conn = dataSource.getConnection();

            String selectSql = "UPDATE public.app_filecode SET filecode=? where appkind=?";


            stat = conn.prepareStatement(selectSql);
            stat.setString(1, filecode);
            stat.setString(2, appkind);

            rs = stat.executeUpdate();

            if(rs > 0) {
                result = "수정 성공";
            } else {
                result = "수정 실패";
            }


            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("getPushList" + e.toString());
        }
        return result;
    }


}
