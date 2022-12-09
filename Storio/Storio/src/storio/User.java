package storio;

import java.util.List;

public class User {
    
    
    private Integer id;
    private UserStatus status;
    private String login;
    private Integer phoneNumber;
    private String fullName;
    private String password;
    private String email;
    private List<SignInHistory> history;

    public User() {
        super();
    }
}
