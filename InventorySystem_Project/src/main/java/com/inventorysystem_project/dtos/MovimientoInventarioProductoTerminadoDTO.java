package com.inventorysystem_project.dtos;

import com.inventorysystem_project.entities.Almacen;
import com.inventorysystem_project.entities.ProductoTerminado;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class MovimientoInventarioProductoTerminadoDTO {

    private Long id;
    private Long almacenId;
    private Long productoTerminadoId;
    private String tipoMovimiento;
    private Date fechaMovimiento;
    private Integer cantidad;
    private String motivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(Long almacenId) {
        this.almacenId = almacenId;
    }

    public Long getProductoTerminadoId() {
        return productoTerminadoId;
    }

    public void setProductoTerminadoId(Long productoTerminadoId) {
        this.productoTerminadoId = productoTerminadoId;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
