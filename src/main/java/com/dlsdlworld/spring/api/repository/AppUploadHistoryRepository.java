package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.param.AppUploadHistoryParam;
import net.minidev.json.JSONObject;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppUploadHistoryRepository {


//    List<GetPushListDto> pushList = new LinkedList<>();

    @PersistenceContext
    EntityManager em;

    public List<JSONObject> getHistory(){

        List<JSONObject> returnHistory = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

        try {
            conn = dataSource.getConnection();

            String selectSql = "select * from public.appupload_history";


            stat = conn.prepareStatement(selectSql);
//            stat.setString(1, appkind);

            rs = stat.executeQuery();

//            pushList.clear();
            int count = 0;

            while (rs.next()) {
                JSONObject object = new JSONObject();
                object.put("id",rs.getInt("id"));
                object.put("appkind",rs.getString("appkind"));
                object.put("filecode",rs.getString("filecode"));
                object.put("user_id",rs.getString("user_id"));
                object.put("insertdate",rs.getString("insertdate"));

                returnHistory.add(object);

                count++;
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("appUploadHistory Error : " + e.toString());
        }
        return returnHistory;
    }

    public String insertAppUploadHistory(AppUploadHistoryParam appUploadHistoryParam){

        String result = null;

        Connection conn = null;
        PreparedStatement stat = null;
        int rs;

        int appPlatFormId = 0;

        if(appUploadHistoryParam.getAppKind().equals("apk")){
            appPlatFormId = 2;
        } else {
            appPlatFormId = 3;
        }

        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

        try {
            conn = dataSource.getConnection();

            String insertSql = "INSERT INTO public.appupload_history \n" +
                    "(\n" +
                    "\t appkind, \n" +
                    "\t filecode, \n" +
                    "\t user_id, \n" +
                    "\t insert_date, \n" +
                    "\t update_date,\n" +
                    "\t new_app_version, \n" +
                    "\t last_app_version\n" +
                    ") \n" +
                    "VALUES(\n" +
                    "\t ?, ?, ?, now(), now(), \n" +
                    "\t ?, \n" +
                    "\t (SELECT \n" +
                    "\t version_cd FROM public.app_version WHERE app_platform_id=? limit 1) \n" +
                    ");";


            stat = conn.prepareStatement(insertSql);
            stat.setString(1, appUploadHistoryParam.getAppKind());
            stat.setString(2, appUploadHistoryParam.getFileCode());
            stat.setString(3, appUploadHistoryParam.getUserId());
            stat.setString(4, appUploadHistoryParam.getFileVersion());
            stat.setInt(5, appPlatFormId);

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

    public JSONObject getLastAppFileCode(String appkind){
        JSONObject jsonObject = new JSONObject();

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

        try {
            conn = dataSource.getConnection();

            String selectSql = "select filecode from (select * from public.appupload_history order by insert_date desc) ah where appKind = ? limit 1 offset 1";


            stat = conn.prepareStatement(selectSql);
            stat.setString(1, appkind);

            rs = stat.executeQuery();

//            pushList.clear();

            while (rs.next()) {
                jsonObject.put("filecode",rs.getString("filecode"));
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("appUploadHistory Error : " + e.toString());
        }
        return jsonObject;
    }


}
