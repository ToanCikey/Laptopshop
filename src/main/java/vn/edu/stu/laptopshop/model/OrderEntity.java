package vn.edu.stu.laptopshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.edu.stu.laptopshop.common.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "receiverName", length = 255)
    private String receiverName;

    @Column(name = "receiverPhone", length = 255)
    private String receiverPhone;

    @Column(name = "receiverAddress", length = 255)
    private String receiverAddress;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 255)
    private OrderStatus status;

    @Column(name = "reason", length = 255)
    private String reason;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;
}
