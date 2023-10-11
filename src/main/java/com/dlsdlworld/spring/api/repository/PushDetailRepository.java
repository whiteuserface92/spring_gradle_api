package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.dto.GetPushDetailDto;
import com.dlsdlworld.spring.api.param.GetPushDetailParam;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


@Repository
public class PushDetailRepository {



    @PersistenceContext
    EntityManager em;



    public GetPushDetailDto getPushDetail(GetPushDetailParam getPushDetailParam){


        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        GetPushDetailDto getPushDetailDto =  new GetPushDetailDto();

        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

//        JdbcUtils jdbcUtils = new JdbcUtils();

        try {


            conn = dataSource.getConnection();

            String selectSql = "select \n" +
                    "(select distinct user_mst.my_ci from user_mst where user_mst.id=push_request.user_id) receive_id,\n" +
                    "(select push_log.sended_on from push_log where push_log.push_request_id=push_request.id)  ,\n" +
                    "(select push_log.read_on  from push_log where push_log.push_request_id=push_request.id)  ,\n" +
                    "push_request.id push_request_id ,\n" +
                    "push_message.contents, \n" +
                    "(select distinct user_mst.my_ci from user_mst where user_mst.id=push_message.created_by) send_id ,\n" +
                    "push_request.user_id\n" +
                    "from push_message left join push_request on push_request.push_message_id =push_message.id \n" +
                    "where push_request.user_id= ? and push_request.id= ? \n" +
                    "order by (select push_log.sended_on from push_log where push_log.push_request_id=push_request.id) desc";

            stat = conn.prepareStatement(selectSql);
            stat.setInt(1, getPushDetailParam.getUserId());
            stat.setInt(2, getPushDetailParam.getPushRequestId());

            rs = stat.executeQuery();



            while (rs.next()) {

                Map<String, String> sample = new HashMap<>();

                String receiveId = rs.getString("receive_id");
                Date sendedOn = rs.getDate("sended_on");
                Date readOn = rs.getDate("read_on");
                int pushRequestId = rs.getInt("push_request_id");
                String contents = rs.getString("contents");
                String sendId = rs.getString("send_id");
                int userId = rs.getInt("user_id");

                getPushDetailDto.setReceiveId(receiveId);
                getPushDetailDto.setSendedOn(sendedOn);
                getPushDetailDto.setReadOn(readOn);
                getPushDetailDto.setPushRequestId(pushRequestId);
                getPushDetailDto.setContents(contents);
                getPushDetailDto.setSendId(sendId);
                getPushDetailDto.setUserId(userId);
                getPushDetailDto.setStatus("");
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("getPushDetail" + e.toString());
        }
        return getPushDetailDto;
    }
}
