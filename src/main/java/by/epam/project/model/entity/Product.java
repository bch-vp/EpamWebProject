package by.epam.project.model.entity;

import java.math.BigDecimal;

public class Product {
    public enum Status {
        ACTIVE,
        INACTIVE,
        BLOCKED
    }

    private String name;
    private String info;
    private Status status;
    private BigDecimal price;

    public Product(String name, String info, Status status, BigDecimal price) {
        this.name = name;
        this.info = info;
        this.status = status;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product user = (Product) o;

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (info != null ? !info.equals(user.info) : user.info != null) {
            return false;
        }
        if (status != null ? !status.equals(user.status) : user.status != null) {
            return false;
        }
        if (price != null ? !price.equals(user.price) : user.price != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("name='").append(name).append('\'');
        sb.append(", info='").append(info).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
