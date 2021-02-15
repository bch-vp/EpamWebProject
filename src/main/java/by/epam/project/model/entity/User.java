package by.epam.project.model.entity;


import java.math.BigDecimal;
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
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private String email;
    private BigDecimal balance;
    private Role role;
    private boolean isActivated;

    public User() {
    }

    public User(String login, String name, String surname, String phone, String email, int role, boolean isActivated) {
        this.login = login;
        this.firstName = name;
        this.lastName = surname;
        this.telephoneNumber = phone;
        this.email = email;
        this.balance = BigDecimal.valueOf(0);
        this.role = Role.getRoleById(role);
        this.isActivated = isActivated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) {
            return false;
        }
        if (telephoneNumber != null ? !telephoneNumber.equals(user.telephoneNumber) : user.telephoneNumber != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (role != null ? !role.equals(user.role) : user.role != null) {
            return false;
        }
        if (isActivated != user.isActivated) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}