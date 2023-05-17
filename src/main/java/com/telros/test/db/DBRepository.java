package com.telros.test.db;

import com.telros.test.model.UpdateUser;
import com.telros.test.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
@Service
public class DBRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;


    public void save(User user) {
        try {
            String sql = """
                    INSERT INTO users(firstname, lastname, surname, birthday, telephone, email) values(:firstname, :lastname, :surname, :birthday, :telephone, :email);
                    """;
            jdbcTemplate.update(sql, Map.of("lastname", user.lastname(), "firstname", user.firstname(), "surname", user.surname(), "birthday", user.birthday(), "telephone", user.telephone(), "email", user.email()));
        } catch (Exception e) {
            log.error("Save user error", e);
            throw new RuntimeException();
        }
    }

    public void delete(Long id) {
        try {
            String sql = """
                    DELETE FROM users WHERE id = :id;
                    """;
            jdbcTemplate.update(sql, Map.of("id", id));
        } catch (Exception e) {
            log.error("Delete user error", e);
            throw new RuntimeException();
        }
    }


    public Object select(User user) {
        try {
            String sql = """
                    SELECT * 
                    FROM users 
                    WHERE (:firstnameIsEmpty or firstname = :firstname) and 
                    (:lastnameIsEmpty or lastname = :lastname) and 
                    (:surnameIsEmpty or surname = :surname) and
                    (:birthdayIsEmpty or birthday = :birthday) and 
                    (:telephoneIsEmpty or telephone = :telephone) and 
                    (:emailIsEmpty or email = :email);
                    """;
            Map<String, Object> parameters = new java.util.HashMap<>(Map.of("lastname", user.lastname() == null ? "" : user.lastname(), "firstname", user.firstname() == null ? "" : user.firstname(), "surname", user.surname() == null ? "" : user.surname(), "birthday", user.birthday() == null ? "" : user.birthday(), "telephone", user.telephone() == null ? "" : user.telephone(), "email", user.email() == null ? "" : user.email()));
            Map<String, Object> IsEmpty = Map.of("lastnameIsEmpty", user.lastname() == null, "firstnameIsEmpty", user.firstname() == null, "surnameIsEmpty", user.surname() == null, "birthdayIsEmpty", user.birthday() == null, "telephoneIsEmpty", user.telephone() == null, "emailIsEmpty", user.email() == null);
            parameters.putAll(IsEmpty);
            return jdbcTemplate.queryForList(sql, parameters);
        } catch (Exception e) {
            log.error("Select user error", e);
            throw new RuntimeException();
        }
    }

    public Object selectAll() {
        try {
            String sql = """
                    SELECT * FROM users;
                    """;
            return jdbcTemplate.queryForList(sql, Collections.emptyMap());
        } catch (Exception e) {
            log.error("Select all user error", e);
            throw new RuntimeException();
        }
    }

    public void update(UpdateUser user) {
        try {
            String sql = """
                    UPDATE users 
                    SET firstname = :firstname, 
                    lastname = :lastname, 
                    surname = :surname, 
                    birthday = :birthday, 
                    telephone = :telephone, 
                    email = :email
                    WHERE id = :id;
                    """;
            jdbcTemplate.update(sql, Map.of("lastname", user.lastname(), "firstname", user.firstname(), "surname", user.surname(), "birthday", user.birthday(), "telephone", user.telephone(), "email", user.email(), "id", user.id()));
        } catch (Exception e) {
            log.error("Update user error", e);
            throw new RuntimeException();
        }
    }
}
