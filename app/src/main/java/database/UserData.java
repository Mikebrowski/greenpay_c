package database;

public class UserData {

    public String email;
    public String password;
    public String username;


    public String phone;
    //CALCULATE SHOULD ALWAYS BE INT AND STRING SHOULD ALWAYS BE INPUT


    public UserData() {
    }

    public UserData(String emailString, String usernamecheck) {
        this.email = emailString;
        this.username = usernamecheck;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //public void setPhone(int phone){ this.phone= phone; }
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}//end of UserProData



