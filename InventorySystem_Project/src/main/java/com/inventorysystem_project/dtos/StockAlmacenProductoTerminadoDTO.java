package com.inventorysystem_project.dtos;

public class StockAlmacenProductoTerminadoDTO {

    private Long id;
    private Long almacenId;  // ID del almacén
    private Long productoTerminadoId;  // ID del producto terminado
    private Integer cantidad;

    // Getters y setters

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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}

