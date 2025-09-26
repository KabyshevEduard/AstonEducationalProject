package org.example.models;

// Abstract class for objects with builder pattern
public abstract class CustomEntity {

    private int id;
    private String name;
    private String surname;
    private int age;

    public CustomEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    // Builder pattern
    public static class Builder {
        private int id;
        private String name;
        private String surname;
        private int age;

        public CustomEntity.Builder id(int id) {
            this.id = id;
            return this;
        }

        public  CustomEntity.Builder name(String name) throws IllegalArgumentException {
            if (name == null || name.length() < 3 || name.length() > 50) {
                throw new IllegalArgumentException("Невалидное имя");
            }
            this.name = name;
            return this;
        }

        public  CustomEntity.Builder surname(String surname) throws IllegalArgumentException {
            if (surname == null || surname.length() < 3 || surname.length() > 50) {
                throw new IllegalArgumentException("Невалидная фамилия");
            }
            this.surname = surname;
            return this;
        }

        public  CustomEntity.Builder age(int age) throws IllegalArgumentException {
            if (age < 0 || age > 108) {
                throw new IllegalArgumentException("Возраст должен быть настоящим");
            }
            this.age = age;
            return this;
        }

        public CustomEntity build() {
            return new CustomEntity(this) {
            };
        };
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + "]";
    }
}
