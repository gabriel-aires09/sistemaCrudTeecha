package com.example.person;

public class Person implements Comparable<Person> {
    private String name;
    private String type;

    public Person(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Nome: " + name + ", Tipo: " + type;
    }
}
