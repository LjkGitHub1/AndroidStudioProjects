package com.example.keepaccounts;

/**
 * @name: UserManager
 * @date: 2020-05-18 10:31
 * @comment: 全局用户信息
 */
public enum UserManager {
    instance;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
