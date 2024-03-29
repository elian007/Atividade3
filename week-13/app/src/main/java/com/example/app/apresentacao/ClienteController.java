package com.example.app.apresentacao;

import com.example.app.negocio.PaisNegocio;
import com.example.app.negocio.dominio.PaisDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CLienteController {
    
    private final ClienteNegocio CLienteNegocio;
    
    @GetMapping("/cliente")
    public String listar(Model memoria) {
        
        memoria.addAttribute("listaCliente",
                ClienteDTO.ModelsFromDTOs(clienteNegocio.listar()));
        
        return "pais-view";
    }
    
    @PostMapping("/cliente/criar")
    public String criar(ClienteModel cliente) {
        
        try {
            cliente.incluir(ClienteDTO.DTOFromModel(cliente));
            
        } catch (ObjetoJaExisteException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/pais";
    }
    
}
