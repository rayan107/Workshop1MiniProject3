package desctopapp.workshop1_miniproject3;

import javafx.beans.property.*;

public class student {
    private final StringProperty name;
    private final IntegerProperty age;
    private final StringProperty id;
    private final IntegerProperty password;

    public student(String name, int age, String id, Integer password) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.id = new SimpleStringProperty(id);
        this.password = new SimpleIntegerProperty(password);
    }

    public student(StringProperty name, IntegerProperty age, StringProperty id, IntegerProperty password) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.password=password;
    }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public int getAge() { return age.get(); }
    public void setAge(int value) { age.set(value); }
    public IntegerProperty ageProperty() { return age; }

    public String getId() { return id.get(); }
    public void setId(String value) { id.set(value); }
    public StringProperty idProperty() { return id; }

    public int getpassword() { return password.get(); }
    public void setPassword(int value) { password.set(value); }
    public IntegerProperty passwordProperty() { return password; }
}
