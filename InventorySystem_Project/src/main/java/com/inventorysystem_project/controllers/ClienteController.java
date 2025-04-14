package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.ClienteDTO;
import com.inventorysystem_project.entities.Cliente;
import com.inventorysystem_project.entities.Empresa;
import com.inventorysystem_project.serviceinterfaces.IClienteService;
import com.inventorysystem_project.serviceinterfaces.IEmpresaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IEmpresaService empresaService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registrar(@RequestBody ClienteDTO dto) {
        ModelMapper m = new ModelMapper();
        Cliente cliente = m.map(dto, Cliente.class);

        // Establecer la empresa manualmente ya que el DTO solo tiene el ID
        if (dto.getEmpresaId() != null) {
            Empresa empresa = empresaService.listId(dto.getEmpresaId());
            if (empresa != null) {
                cliente.setEmpresa(empresa);
            }
        }

        clienteService.insert(cliente);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<ClienteDTO> listar() {
        return clienteService.list().stream().map(cliente -> {
            ClienteDTO dto = new ClienteDTO();
            dto.setId(cliente.getId());
            if (cliente.getEmpresa() != null) {
                dto.setEmpresaId(cliente.getEmpresa().getId());
            }
            dto.setCorreo(cliente.getCorreo());
            dto.setDireccion(cliente.getDireccion());
            dto.setTelefono(cliente.getTelefono());
            dto.setRuc(cliente.getRuc());
            dto.setPais(cliente.getPais());
            dto.setTipoCliente(cliente.getTipoCliente());
            dto.setEnabled(cliente.getEnabled());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClienteDTO listarPorId(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.listId(id);
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        if (cliente.getEmpresa() != null) {
            dto.setEmpresaId(cliente.getEmpresa().getId());
        }
        dto.setCorreo(cliente.getCorreo());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setRuc(cliente.getRuc());
        dto.setPais(cliente.getPais());
        dto.setTipoCliente(cliente.getTipoCliente());
        dto.setEnabled(cliente.getEnabled());
        return dto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") Long id) {
        clienteService.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void modificar(@RequestBody ClienteDTO dto) {
        ModelMapper m = new ModelMapper();
        Cliente cliente = m.map(dto, Cliente.class);

        // Establecer la empresa manualmente ya que el DTO solo tiene el ID
        if (dto.getEmpresaId() != null) {
            Empresa empresa = empresaService.listId(dto.getEmpresaId());
            if (empresa != null) {
                cliente.setEmpresa(empresa);
            }
        }

        clienteService.insert(cliente);
    }
}
