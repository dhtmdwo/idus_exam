package com.example.assignment.orders;

import com.example.assignment.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String orderIdx;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;
}
