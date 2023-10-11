package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.param.UserAccessHistoryParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Transactional
@Repository
public class UserAccessHistoryRepository {

    List<UserAccessHistoryParam> getUserAccessHistoryListResult = new LinkedList<>();

    String insertUserAccessHistoryResult;

    @PersistenceContext
    private EntityManager em;

//    @PreAuthorize("@security.hasPermission({'API_READ'})")
    public List<UserAccessHistoryParam> getUserAccessHistoryList(UserAccessHistoryParam userAccessHistoryParam){

        this.getUserAccessHistoryListResult.clear();

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;


        log.info("em : {}",em);
        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);



        List<String> queryList = new ArrayList<>();

        String selectSql = "SELECT * FROM public.user_access_history";

        String addAccessDescQuery;

        String addAccessTargetQuery;

        String addAccessTypeQuery;

        String addUserIdQuery;

        String addHospitalCd;

        String addMyCi;

        String addOrderByQuery = "";

        String finalQuery = "";



        // AND ZONE

        if(userAccessHistoryParam.getAccessDesc() == null){
            addAccessDescQuery = "";
        } else {
            addAccessDescQuery = " WHERE access_desc = '"+ userAccessHistoryParam.getAccessDesc()+"' ";
            queryList.add(addAccessDescQuery);
        }

        if(userAccessHistoryParam.getAccessTarget() == null){
            addAccessTargetQuery = "";
        } else {
            if(addAccessDescQuery == ""){
                addAccessTargetQuery = " WHERE access_target = '"+ userAccessHistoryParam.getAccessTarget()+"' ";
                queryList.add(addAccessTargetQuery);
            } else {
                addAccessTargetQuery =" AND access_target = '"+ userAccessHistoryParam.getAccessTarget()+"' ";
                queryList.add(addAccessTargetQuery);
            }
        }

        if(userAccessHistoryParam.getAccessType() == null){
            addAccessTypeQuery = "";
        } else {
            if(addAccessDescQuery == "" && addAccessTargetQuery == ""){
                addAccessTypeQuery = " WHERE access_type = '"+ userAccessHistoryParam.getAccessType()+"' ";
                queryList.add(addAccessTypeQuery);
            } else {
                addAccessTypeQuery = " AND access_type = '"+ userAccessHistoryParam.getAccessType()+"' ";
                queryList.add(addAccessTypeQuery);
            }
        }

        if(userAccessHistoryParam.getUserId() == null){
            addUserIdQuery = "";
        } else {
            if(addAccessDescQuery == "" && addAccessTargetQuery == "" && addAccessTypeQuery == ""){
                addUserIdQuery = " WHERE user_id = "+ userAccessHistoryParam.getUserId()+" ";
                queryList.add(addUserIdQuery);
            } else {
                addUserIdQuery = " AND user_id = "+ userAccessHistoryParam.getUserId()+" ";
                queryList.add(addUserIdQuery);
            }
        }

        if(userAccessHistoryParam.getHospitalCd() == null){
            addHospitalCd = "";
        } else {
            if(addAccessDescQuery == "" && addAccessTargetQuery == "" && addAccessTypeQuery == "" && addUserIdQuery == ""){
                addHospitalCd = " WHERE hospital_cd = '"+ userAccessHistoryParam.getHospitalCd()+"' ";
                queryList.add(addHospitalCd);
            } else {
                addHospitalCd = " AND hospital_cd = '"+ userAccessHistoryParam.getHospitalCd()+"' ";
                queryList.add(addHospitalCd);
            }
        }

        if(userAccessHistoryParam.getMyCi() == null){
            addMyCi = "";
        } else {
            if(addAccessDescQuery == "" && addAccessTargetQuery == "" && addAccessTypeQuery == "" && addUserIdQuery == "" && addHospitalCd == ""){
                addMyCi = " WHERE my_ci= '"+ userAccessHistoryParam.getMyCi()+"' ";
                queryList.add(addMyCi);
            } else {
                addMyCi = " AND my_ci = '"+ userAccessHistoryParam.getMyCi()+"' ";
                queryList.add(addMyCi);
            }
        }


        //갯수별로 ORDER BY 분기처리
        if(queryList.size() == 6){
            addOrderByQuery = " ORDER BY user_id ASC ";
        } else if (queryList.size() == 5){
            addOrderByQuery = " ORDER BY user_id ASC ";
        } else if (queryList.size() == 4){
            addOrderByQuery = " ORDER BY user_id ASC ";
        } else if(queryList.size() == 2 || queryList.size() == 3){
            if(queryList.contains("user_id")){
                addOrderByQuery = " ORDER BY id ASC";
            } else {
                addOrderByQuery = " ORDER BY user_id ASC";
            }
        } else if (queryList.size() == 1) {
            //한개만 넣은 경우
                addOrderByQuery = " ORDER BY id ASC";
        } else {
            addOrderByQuery = " ORDER BY id ASC ";
        }



        try {
            conn = dataSource.getConnection();

            finalQuery = selectSql + addAccessDescQuery + addAccessTargetQuery + addAccessTypeQuery + addUserIdQuery + addHospitalCd + addMyCi + addOrderByQuery;

            log.info("finalQuery : {}",finalQuery);

            stat = conn.prepareStatement(finalQuery);
//            stat.setInt(1, userId);

            rs = stat.executeQuery();

            queryList.clear();

            while (rs.next()) {
                UserAccessHistoryParam userAccessHistoryParam1 = new UserAccessHistoryParam();
                userAccessHistoryParam1.setId(rs.getLong("id"));
                userAccessHistoryParam1.setUserId(rs.getLong("user_id"));
                userAccessHistoryParam1.setCreatedBy(rs.getLong("created_by"));
                userAccessHistoryParam1.setHospitalCd(rs.getString("hospital_cd"));
                userAccessHistoryParam1.setAccessDesc(rs.getString("access_desc"));
                userAccessHistoryParam1.setAccessTarget(rs.getString("access_target"));
                userAccessHistoryParam1.setAccessType(rs.getString("access_type"));
                userAccessHistoryParam1.setCreatedOn(rs.getString("created_on"));
                userAccessHistoryParam1.setMyCi(rs.getString("my_ci"));
                this.getUserAccessHistoryListResult.add(userAccessHistoryParam1);
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            log.error("getUserAccessHistoryList" + e.toString());
        }
        return this.getUserAccessHistoryListResult;
    }

//    @PreAuthorize("@security.hasPermission({'API_WRITE'})")
    public String insertUserAccessHistory(UserAccessHistoryParam userAccessHistoryParam){

        log.info("UserAccessHistoryParam : {}",userAccessHistoryParam);

        Connection conn = null;
        PreparedStatement stat = null;
        int rs;

        int appPlatFormId = 0;



        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

        try {

            if(userAccessHistoryParam.getUserId() == null || userAccessHistoryParam.getUserId().equals("")){
                this.insertUserAccessHistoryResult = "USERID";
                return this.insertUserAccessHistoryResult;
            }

            if(userAccessHistoryParam.getCreatedBy() == null){
                this.insertUserAccessHistoryResult = "실패";
                return this.insertUserAccessHistoryResult;
            }

            if(userAccessHistoryParam.getHospitalCd() == null){
                this.insertUserAccessHistoryResult = "실패";
                return this.insertUserAccessHistoryResult;
            }

            conn = dataSource.getConnection();

            String insertSql = "INSERT INTO public.user_access_history\n" +
                    "(user_id, access_type, access_target, access_desc, hospital_cd, created_by, created_on, my_ci)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, NOW(), ?)";

            log.info("insertSql = {}",insertSql);
            stat = conn.prepareStatement(insertSql);
            stat.setLong(1, userAccessHistoryParam.getUserId());
            stat.setString(2, userAccessHistoryParam.getAccessType());
            stat.setString(3, userAccessHistoryParam.getAccessTarget());
            stat.setString(4, userAccessHistoryParam.getAccessDesc());
            stat.setString(5, userAccessHistoryParam.getHospitalCd());
            stat.setLong(6, userAccessHistoryParam.getCreatedBy());
            stat.setString(7,userAccessHistoryParam.getMyCi());

            rs = stat.executeUpdate();

            log.info("rs value = {}",rs);

                if(rs > 0) {
                    this.insertUserAccessHistoryResult = "성공";
                } else {
                    this.insertUserAccessHistoryResult = "실패";
                }






            stat.close();
            conn.close();
        } catch (Exception e) {
            log.error("insertUserAccessHistory" + e.getCause());
        } finally {
            return this.insertUserAccessHistoryResult;
        }
    }
}
