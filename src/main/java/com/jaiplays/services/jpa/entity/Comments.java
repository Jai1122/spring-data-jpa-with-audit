package com.jaiplays.services.jpa.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "comments")
@Getter @Setter
@EntityListeners({AuditingEntityListener.class})
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String postName;

    @Column
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at", nullable = false, updatable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @Column
    @LastModifiedBy
    private String lastModifiedBy;

}
