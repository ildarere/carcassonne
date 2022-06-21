package com.example.carcassonne.web.form.room;

import com.example.carcassonne.domain.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoomFormValidator implements Validator {
   @Autowired
    RoomService roomService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RoomForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoomForm form = (RoomForm) target;

        String name = form.getName();
        System.out.println(name);
        System.out.println(roomService.isRoomWithNameExist(name));
        if (roomService.isRoomWithNameExist(name)) {
            errors.rejectValue("name", "", "Room already exist");
        }
    }
}
