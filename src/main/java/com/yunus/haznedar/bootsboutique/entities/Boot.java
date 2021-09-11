package com.yunus.haznedar.bootsboutique.entities;

import com.yunus.haznedar.bootsboutique.enums.BootType;
import javax.persistence.*;


@Entity
@Table(name="BOOTS")
public class Boot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private BootType type;

    @Column(name = "SIZE")
    private Float size;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "MATERIAL")
    private String material;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BootType getType() {
        return type;
    }

    public void setType(BootType type) {
        this.type = type;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Boot{" +
                "id=" + id +
                ", type=" + type +
                ", size=" + size +
                ", quantity=" + quantity +
                ", material='" + material + '\'' +
                '}';
    }
}