package skillbox.com.users.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "cities", schema = "users_scheme")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public CityEntity() {
    }

    public CityEntity(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
