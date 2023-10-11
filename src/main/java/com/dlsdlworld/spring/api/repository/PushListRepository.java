package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.dto.GetPushListDto;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PushListRepository {


    List<GetPushListDto> pushList = new LinkedList<>();

    @PersistenceContext
    EntityManager em;

    public List<GetPushListDto> getPushListByUserId(int userId){

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        DataSource dataSource = (DataSource)
                em.getEntityManagerFactory().getProperties()
                        .get(AvailableSettings.JPA_NON_JTA_DATASOURCE);

        try {
            conn = dataSource.getConnection();

            String selectSql = "select push_message.contents, push_log.push_request_id, push_log.read_on, push_log.sended_on, user_mst.id user_id \n" +
                    "from push_request left join push_log on push_request.id = push_log.push_request_id \n" +
                    "    left join push_message on push_request.push_message_id = push_message.id \n" +
                    "                left join user_mst on user_mst.id=push_request.user_id \n" +
                    "               where user_id=? order by sended_on desc";


            stat = conn.prepareStatement(selectSql);
            stat.setInt(1, userId);

            rs = stat.executeQuery();

            pushList.clear();

            while (rs.next()) {
                GetPushListDto getPushListDto = new GetPushListDto();
                String contents = rs.getString("contents");
                getPushListDto.setContents(contents.substring(0,10)+"...");
                getPushListDto.setPushRequestId(rs.getInt("push_request_id"));
                getPushListDto.setReadOn(rs.getDate("read_on"));
                getPushListDto.setSendedOn(rs.getDate("sended_on"));
                getPushListDto.setUserId(rs.getInt("user_id"));
                this.pushList.add(getPushListDto);
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("getPushList" + e.toString());
        }
        return this.pushList;
    }
}
