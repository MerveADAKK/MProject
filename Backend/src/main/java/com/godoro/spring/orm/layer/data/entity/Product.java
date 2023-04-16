package com.godoro.spring.orm.layer.data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private double salesPrice;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
