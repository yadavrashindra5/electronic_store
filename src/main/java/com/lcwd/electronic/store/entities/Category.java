package com.lcwd.electronic.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String categoryId;
    @Column(name = "category_title",length = 60,nullable = false)
    private String title;
    @Column(name = "category_des",length = 500)
    private String description;
    private String coverImage;
}
