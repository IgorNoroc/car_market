package ru.job4j.market.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String price;
    private Date publish;
    private String created;
    private String color;
    private String mileage;
    private String type;
    @Column(name = "body_car")
    private String bodyCar;
    private String engine;
    private String photo;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Car() {

    }

    public Car(String name, String price, Date publish, String created,
               String color, String mileage, String type,
               String bodyCar, String engine, String photo, User user, boolean status) {
        this.name = name;
        this.price = price;
        this.publish = publish;
        this.created = created;
        this.color = color;
        this.mileage = mileage;
        this.type = type;
        this.bodyCar = bodyCar;
        this.engine = engine;
        this.photo = photo;
        this.user = user;
        this.status = status;
    }

    public Car(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        this.publish = publish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBodyCar() {
        return bodyCar;
    }

    public void setBodyCar(String bodyCar) {
        this.bodyCar = bodyCar;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", publish=" + publish +
                ", created='" + created + '\'' +
                ", color='" + color + '\'' +
                ", mileage='" + mileage + '\'' +
                ", type='" + type + '\'' +
                ", bodyCar='" + bodyCar + '\'' +
                ", engine='" + engine + '\'' +
                ", photo='" + photo + '\'' +
                ", user=" + user +
                '}';
    }
}
