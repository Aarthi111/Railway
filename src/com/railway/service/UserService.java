package com.railway.service;

import com.railway.dao.UserDAO;
import com.railway.models.User;
import com.railway.util.InputValidator;
import com.railway.util.PasswordUtil;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(String name, String email, String phone, String password, String address) {

        if (!InputValidator.isValidName(name)) {
            System.out.println("Invalid name! Must be 3-100 characters.");
            return false;
        }
        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return false;
        }
        if (!InputValidator.isValidPhone(phone)) {
            System.out.println("Invalid phone number! Must be 10 digits.");
            return false;
        }
        if (!PasswordUtil.isStrongPassword(password)) {
            System.out.println("Weak password! Min 8 chars with uppercase, lowercase, and digit.");
            return false;
        }

        if (userDAO.isEmailExists(email)) {
            System.out.println("Email already registered!");
            return false;
        }

        User user = new User(name, email, phone, address);
        return userDAO.registerUser(user, password);
    }

    public User loginUser(String email, String password) {
        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return null;
        }
        return userDAO.loginUser(email, password);
    }

    public boolean updateProfile(User user) {
        if (!InputValidator.isValidName(user.getName()) ||
            !InputValidator.isValidPhone(user.getPhone())) {
            System.out.println("Invalid input data!");
            return false;
        }
        return userDAO.updateProfile(user);
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        if (!PasswordUtil.isStrongPassword(newPassword)) {
            System.out.println("New password is too weak!");
            return false;
        }

        return userDAO.changePassword(userId, newPassword);
    }

    public User getUser(int userId) {
        return userDAO.getUserById(userId);
    }
}
