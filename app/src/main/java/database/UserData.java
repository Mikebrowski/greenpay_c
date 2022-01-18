package database;

public class UserData {

    private String email;
    private String username;
    private String profilePicture;

    public UserData() {
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public UserData(String emailString, String usernamecheck, String profilePicture) {
        this.email = emailString;
        this.username = usernamecheck;
        this.profilePicture= profilePicture;
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

}//end of UserProData



