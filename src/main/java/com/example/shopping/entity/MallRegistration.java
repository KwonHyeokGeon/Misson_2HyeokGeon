package com.example.shopping.entity;

import com.example.shopping.entity.enumeration.MallStatus;
import com.example.shopping.entity.enumeration.MallType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MallRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String introduce;
    @Enumerated(EnumType.STRING)
    @Setter
    private MallType mallType;
    @Enumerated(EnumType.STRING)
    @Setter
    private MallStatus mallStatus;

    @OneToOne
    private User user;
}
