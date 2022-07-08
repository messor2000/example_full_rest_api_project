package crud.example.restapiproject.entity.order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "CUSTOMER_ORDER")
public class Order {

    private @Id
    @GeneratedValue
    Long id;

    private String description;
    private Status status;

    public Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}

