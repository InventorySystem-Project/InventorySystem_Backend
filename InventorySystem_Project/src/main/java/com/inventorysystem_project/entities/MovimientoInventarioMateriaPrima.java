package com.inventorysystem_project.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movimiento_inventario_materia_prima")
public class MovimientoInventarioMateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer cantidad;

    @Column
    private Date fecha;

    @Column(length = 50)
    private String tipoMovimiento;  // Ejemplo: "Ingreso", "Salida"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_prima_id")
    private MateriaPrima materiaPrima;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }
}
