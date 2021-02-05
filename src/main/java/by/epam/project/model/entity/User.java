package by.epam.project.model.entity;


import java.util.HashMap;
import java.util.Map;

public class User extends Entity {
    public enum Role {
        GUEST(0),
        CLIENT(1),
        ADMIN(2);

        private final int roleId;

        Role(int roleId) {
            this.roleId = roleId;
        }

        public int getRoleId() {
            return roleId;
        }


        private static final Map<Integer, Role> LOOKUP_MAP = new HashMap<>();

        static {
            for (Role element : values()) {
                LOOKUP_MAP.put(element.getRoleId(), element);
            }
        }

        public static Role getRoleById(int roleId) {
            return LOOKUP_MAP.get(roleId);
        }
    }

    private String login;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Role role;
    private boolean isActivated;

    public User() {
    }

    public User(String login, String name, String surname, String phone, String email, int role, boolean isActivated) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.role = Role.getRoleById(role);
        this.isActivated = isActivated;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) {
            return false;
        }
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}