package tn.ims.todolist.jwt;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginForm {
    private String username;
    private String password;
}
