package skillbox.com.users.dto;

public class UserDto {
    private Integer id;
    private String name;
    private String login;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private Integer cityId;

    public UserDto() {
    }

    public UserDto(Integer id, String name, String login, String gender, String email, String phone,
                   String address, Integer cityId)
    {
        this.id = id;
        this.name = name;
        this.login = login;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
