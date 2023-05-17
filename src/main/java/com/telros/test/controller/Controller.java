package com.telros.test.controller;

import com.telros.test.db.DBRepository;
import com.telros.test.model.IdReq;
import com.telros.test.model.RequestAnswer;
import com.telros.test.model.UpdateUser;
import com.telros.test.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final DBRepository dbRepository;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user) {

        dbRepository.save(user);
        return new ResponseEntity<>(new RequestAnswer("Succeseful save user", HttpStatus.OK.value()), HttpStatus.OK);

    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody IdReq id) {

        dbRepository.delete(id.id());
        return new ResponseEntity<>(new RequestAnswer("Succeseful delete user", HttpStatus.OK.value()), HttpStatus.OK);

    }

    @GetMapping("/all")
    public Object getAll() {
        return dbRepository.selectAll();
    }

    @GetMapping("/getWithCondition")
    public Object getWithCondition(@RequestBody User user) {
        return dbRepository.select(user);
    }
    @PostMapping("/updateRaw")
    public ResponseEntity<?> updateRaw(@RequestBody UpdateUser user) {
        dbRepository.update(user);
        return new ResponseEntity<>(new RequestAnswer("Succeseful update user", HttpStatus.OK.value()), HttpStatus.OK);

    }
}
