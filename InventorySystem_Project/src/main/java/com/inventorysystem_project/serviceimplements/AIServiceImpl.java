package com.inventorysystem_project.serviceimplements;

import com.inventorysystem_project.dtos.AIRequestDTO;
import com.inventorysystem_project.dtos.AIResponseDTO;
import com.inventorysystem_project.serviceinterfaces.AIService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class AIServiceImpl implements AIService {

    @Override
    public AIResponseDTO processPrompt(AIRequestDTO request) {
        String prompt = request.getPrompt().toLowerCase(Locale.ROOT);
        
        if (prompt.contains("inventario") || prompt.contains("producto") || prompt.contains("stock")) {
            return new AIResponseDTO(
                "Entendido. Puedo llevarte al panel de inventario para que revises el stock actual.",
                Arrays.asList("Ver Productos", "Registrar Nuevo Producto", "Ver Movimientos"),
                "NAVIGATE",
                "/productos"
            );
        } else if (prompt.contains("orden") || prompt.contains("compra") || prompt.contains("pedido")) {
            return new AIResponseDTO(
                "Claro, vamos a la sección de Órdenes de Compra.",
                Arrays.asList("Crear Orden de Compra", "Ver Historial de Órdenes"),
                "NAVIGATE",
                "/orden-compra"
            );
        } else if (prompt.contains("proveedor")) {
            return new AIResponseDTO(
                "Accediendo a la gestión de proveedores.",
                Arrays.asList("Lista de Proveedores", "Agregar Proveedor"),
                "NAVIGATE",
                "/proveedores"
            );
        } else if (prompt.contains("usuario") || prompt.contains("perfil")) {
            return new AIResponseDTO(
                "Te llevo a la configuración de usuarios.",
                Arrays.asList("Ver mi perfil", "Gestionar Roles"),
                "NAVIGATE",
                "/usuarios"
            );
        } else if (prompt.contains("dashboard") || prompt.contains("inicio") || prompt.contains("home")) {
            return new AIResponseDTO(
                "Volviendo al inicio.",
                Arrays.asList("Resumen de hoy", "Alertas de stock"),
                "NAVIGATE",
                "/dashboard"
            );
        }

        // Default response
        return new AIResponseDTO(
            "Interesante. ¿Podrías ser más específico? Puedo ayudarte a navegar por el inventario, órdenes o reportes.",
            Arrays.asList("Ver Inventario", "Crear Orden", "Ver Dashboard"),
            "NONE",
            null
        );
    }
}
