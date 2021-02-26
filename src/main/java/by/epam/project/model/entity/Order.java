package by.epam.project.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    public enum Status {
        CONFIRMED,
        NOT_CONFIRMED
    }

    private long id;
    private String comment;
    private String address;
    private Date dateCreatedAt;
    private BigDecimal totalPrice;
    private Status status;

    public Order(long id, String comment, String address, Date createdAt, BigDecimal totalPrice) {
        this.id = id;
        this.comment = comment;
        this.address = address;
        this.dateCreatedAt = createdAt;
        this.totalPrice = totalPrice;
        this.status = Status.NOT_CONFIRMED;
    }

    public Order(String comment, String address, Date createdAt, BigDecimal totalPrice) {
        this.comment = comment;
        this.address = address;
        this.dateCreatedAt = createdAt;
        this.totalPrice = totalPrice;
        this.status = Status.NOT_CONFIRMED;
    }

    public Order(String comment, String address, Date createdAt, BigDecimal totalPrice, Status status) {
        this.comment = comment;
        this.address = address;
        this.dateCreatedAt = createdAt;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateCreatedAt() {
        return dateCreatedAt;
    }

    public void setDateCreatedAt(Date dateCreatedAt) {
        this.dateCreatedAt = dateCreatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order user = (Order) o;

        if (id != user.id) {
            return false;
        }

        if (comment != null ? !comment.equals(user.comment) : user.comment != null) {
            return false;
        }
        if (address != null ? !address.equals(user.address) : user.address != null) {
            return false;
        }
        if (dateCreatedAt != null ? !dateCreatedAt.equals(user.dateCreatedAt) : user.dateCreatedAt != null) {
            return false;
        }
        if (totalPrice != null ? !totalPrice.equals(user.totalPrice) : user.totalPrice != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateCreatedAt != null ? dateCreatedAt.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id='").append(id).append('\'');
        sb.append("comment='").append(comment).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", createdAt='").append(dateCreatedAt).append('\'');
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
}
