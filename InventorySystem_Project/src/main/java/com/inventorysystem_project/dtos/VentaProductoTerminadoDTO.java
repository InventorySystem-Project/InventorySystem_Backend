package com.inventorysystem_project.dtos;

import java.util.Date;

public class VentaProductoTerminadoDTO {

    private Long id;
    private Long almacenId;
    private Date fechaMovimiento;
    private String tipoMovimiento;
    private Integer cantidad;
    private Integer unidad;
    private String estadoEntrega;
    private Long productoTerminadoId;

    // Getters and Setters

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

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public Long getProductoTerminadoId() {
        return productoTerminadoId;
    }

    public void setProductoTerminadoId(Long productoTerminadoId) {
        this.productoTerminadoId = productoTerminadoId;
    }
}
