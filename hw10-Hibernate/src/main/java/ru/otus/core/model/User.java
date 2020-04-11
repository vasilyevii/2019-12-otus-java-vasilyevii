package ru.otus.core.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @ru.otus.jdbc.mapper.ID
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(targetEntity = AddressDataSet.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AddressDataSet address;

    @OneToMany(targetEntity = PhoneDataSet.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<PhoneDataSet> phone;

    public User() {
    }

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = null;
        this.phone = null;
    }

    public User(long id, String name, int age, AddressDataSet address, List<PhoneDataSet> phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public List<PhoneDataSet> getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phone=" + phone +
                '}';
    }
}
