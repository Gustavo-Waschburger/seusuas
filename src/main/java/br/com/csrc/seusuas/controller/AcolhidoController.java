package br.com.csrc.seusuas.controller;

import br.com.csrc.seusuas.model.Acolhido;
import br.com.csrc.seusuas.service.AcolhidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acolhidos")
public class AcolhidoController {

    private  AcolhidoService acolhidoService;

    @GetMapping
    public List<Acolhido> listarTodos() {
        return acolhidoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Acolhido> criarAcolhido(@RequestBody Acolhido acolhido) {
        Acolhido novoAcolhido = acolhidoService.salvar(acolhido);
        return ResponseEntity.ok(novoAcolhido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acolhido> buscarPorId(@PathVariable String id) {
        Acolhido acolhido = acolhidoService.buscarPorId(id);
        return acolhido != null ? ResponseEntity.ok(acolhido) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcolhido(@PathVariable String id) {
        acolhidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
