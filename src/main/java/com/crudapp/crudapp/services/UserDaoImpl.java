package com.crudapp.crudapp.services;

import com.crudapp.crudapp.UserAuditHistoryRepo;
import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.User;
import com.crudapp.crudapp.entities.UserAuditHistory;
import com.crudapp.crudapp.json.FieldsToBeUpdated;
import com.crudapp.crudapp.json.PutRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Repository
public class UserDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserAuditHistoryRepo auditHistoryRepo;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Gson gson;
    @Autowired
    private UserRepo userRepo;


    //    public User save(User user)
//    {
//        String sql="INSERT INTO users (firstname,lastname, age, courseId) VALUES(?,?,?,?)";
//        //logQuery(sql);
//        jdbcTemplate.update(sql,user.getFirstName(),user.getLastName(),user.getAge(),user.getCourseId());
//        auditHistory("User created",sql,user,new Object[]{user.getFirstName(),user.getLastName(),user.getAge(),user.getCourseId(),user.getId()});
//        return user;
//    }
    public Optional<User> update(Long userId, PutRequestBody requestBody) throws JsonProcessingException {
        //,?> newFields= (Map<String, String>) requestBody.getFieldsToBeUpdated();
        FieldsToBeUpdated fieldsToBeUpdated = requestBody.getFieldsToBeUpdated();
        StringBuilder sql = new StringBuilder("UPDATE user SET ");
        StringBuilder remarks = new StringBuilder();
        List<String> params = new ArrayList<>();

        Map<String, String> fieldMap = objectMapper.readValue(gson.toJson(fieldsToBeUpdated), new TypeReference<Map<String, String>>() {
        });

        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            String fieldName = entry.getKey();
            String updatedValue = entry.getValue();
            switch (fieldName) {
                case "firstName":
                    sql.append("first_name=?, ");
                    params.add(updatedValue);
                    remarks.append("FirstName Changed ");
                    break;
                case "lastName":
                    sql.append("last_name=?, ");
                    params.add(updatedValue);
                    remarks.append("LastName Changed ");
                    break;
                case "age":
                    sql.append("age=?, ");
                    params.add(updatedValue);
                    remarks.append("Age Changed ");
                    break;
                case "courseId":
                    sql.append("course_id=?, ");
                    params.add(updatedValue);
                    remarks.append("CourseId Changed ");
                    break;

            }
        }

        sql.setLength(sql.length() - 2);
        remarks.setLength(remarks.length());

        sql.append(" WHERE id =?");
        params.add(String.valueOf(userId));
        jdbcTemplate.update(sql.toString(), params.toArray());
//        jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                for(int i=1;i<=params.size();i++)
//                {
//                    ps.setNString(i,params.get(i-1));
//                }
//            }
//        });

        Optional<User> optionalUser = userRepo.findById(userId);
        User user = optionalUser.get();
        auditHistory(remarks.toString(), sql.toString(), userId, params);
        return Optional.of(user);
    }

    //String name= user.getFirstName();
    //String sql="UPDATE user SET first_name=?,last_name=?, age=?, course_id=? WHERE id=?";
    //logQuery(sql);
    // jdbcTemplate.update(sql,user.getFirstName(),user.getLastName(),user.getAge(),user.getCourseId(),user.getId());
    //System.out.println(name+" " + user.getFirstName());

    //jdbcTemplate.update(sql,params.toArray());


    private void auditHistory(String action, String queryPerfomed, Long userId, List params) {
        StringBuilder metaDataBuilder = new StringBuilder(queryPerfomed);
        metaDataBuilder.append(" [ Parameters: ");
        for (Object param : params) {
            metaDataBuilder.append(param).append(", ");
        }
        metaDataBuilder.delete(metaDataBuilder.length() - 2, metaDataBuilder.length());
        metaDataBuilder.append("]");
        Optional<User> optionalUser = userRepo.findById(userId);
        LocalDateTime date = LocalDateTime.now(ZoneId.systemDefault());
        UserAuditHistory userEntry = new UserAuditHistory();
        userEntry.setUserId(userId);
        userEntry.setCreatedAt(optionalUser.get().getCreatedAt());
        userEntry.setUpdatedAt(date);
        userEntry.setMetaData(metaDataBuilder.toString());
        userEntry.setRemarks(action);
        auditHistoryRepo.save(userEntry);
    }
}
