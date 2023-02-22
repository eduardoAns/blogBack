package com.example.blogapi.utils;

import com.example.blogapi.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ValidateUtil {

    @Autowired
    private JWTUtil jwtUtil;

    public void validateUserSession(String userToken, Integer userId) {
        if(userToken == null) {
            throw new BadRequestException("usuario sin permisos, inicie sesion", "p-401");
        }

        try {
            jwtUtil.getKey(userToken);
        }catch(Exception e){
            throw new BadRequestException("token no valido", "p-401");
        }

        String idToken = jwtUtil.getKey(userToken);
        if(!Objects.equals(idToken, userId.toString())) {
            System.out.println("entro al if");
            throw new BadRequestException("el usuario no corresponde", "p-401");
        }

    }

    public void validateJson (List<String> propertiesList, BindingResult result) {
        List<String> messageList = propertiesList.stream().map(propertie -> validatePropertie(propertie, result) ).collect(Collectors.toList());
        List<String> messageFilterList = messageList.stream().filter(message -> message != "").collect(Collectors.toList());

        if(messageFilterList.size() == 1) {
            throw new BadRequestException(messageFilterList.toString() + " es requeridos", "P-400");
        }
        if(!messageFilterList.isEmpty()) {
            throw new BadRequestException(messageFilterList.toString() + ", son requeridos", "P-400");
        }
    }

    private String validatePropertie (String propertie, BindingResult result) {
        if( result.hasFieldErrors(propertie)){
            return propertie;
        }
        return "";
    }
}
