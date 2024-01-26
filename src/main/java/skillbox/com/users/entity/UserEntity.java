package skillbox.com.users.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "users", schema = "users_scheme")
@SQLDelete(sql = "UPDATE users_scheme.users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;
    private String login;
    @Column(name = "gender", nullable = false)
    private String gender;
    private boolean deleted = Boolean.FALSE;
    private String email;
    private String phone;
    private String address;
    @Column(name = "city_id", nullable = true)
    private Integer cityId;

    public UserEntity() {
    }

    public UserEntity(String name, String login, String gender, boolean active, String email, String phone
            , String address, Integer cityId) {
        this.name = name;
        this.login = login;
        this.gender = gender;
        this.deleted = active;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", gender='" + gender + '\'' +
                ", deleted=" + deleted +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
