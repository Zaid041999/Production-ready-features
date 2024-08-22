package com.codingshuttle.zaid.prod_ready_features.prod_ready_features.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts")
@Audited
public class PostEntity  extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;
}
