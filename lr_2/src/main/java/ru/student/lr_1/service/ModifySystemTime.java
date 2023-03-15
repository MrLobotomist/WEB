package ru.student.lr_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.student.lr_1.model.Response;

@Service
@RequiredArgsConstructor
@Qualifier("ModifySystemTime")
public class ModifySystemTime implements MyModifyService{

    @Override
    public Response modify(Response response) {

        response.setSystemTime("");
        return response;
    }
}
