package com.example.shopping.domain.mall.entity;

import com.example.shopping.domain.mall.entity.enumeration.MallType;
import com.example.shopping.domain.user.entity.User;
import com.example.shopping.domain.mall.entity.enumeration.MallStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mall {
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
    @Setter
    private User user;
}
