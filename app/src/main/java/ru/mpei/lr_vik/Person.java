package ru.mpei.lr_vik;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Person {
    public String name = "";
    public boolean isHere = false;

    public Person(String name, boolean isHere){
        this.name = name;
        this.isHere = isHere;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        assert obj != null;
        return Objects.equals(name, ((Person) obj).name);
    }
}
