package by.epam.project.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private long id;
    private String comment;
    private String address;
    private Date createdAt;
    private BigDecimal totalPrice;
}
