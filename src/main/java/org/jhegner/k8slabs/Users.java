package org.jhegner.k8slabs;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Users {

    private String createdAt;
    private String name;
    private String avatar;
    private String id;

    public Users(String createdAt, String name, String avatar, String id) {
        this.createdAt = createdAt;
        this.name = name;
        this.avatar = avatar;
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
