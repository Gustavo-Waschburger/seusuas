package br.com.csrc.seusuas.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "acolhidos")
public class Acolhido {

    @Id
    private String id;
    private String nome;
    private LocalDate dataNascimento;
    private String genero;
    private Endereco endereco;
    private DadosContato dadosContato;
    private LocalDate dataAcolhimento;
    private String situacao;
    private List<Prontuario> prontuarios;
    //private Responsavel responsavel;
    //private Documentos documentos;

    // Subclasses internas para endereço, dados de contato, prontuário e responsável promover para Classes?
    @Data
    public static class Endereco {
        private String rua;
        private String numero;
        private String cidade;
        private String estado;
        private String cep;
    }

    @Data
    public static class DadosContato {
        private String telefone;
        private String email;
    }

    @Data
    public static class Prontuario {
        private LocalDate dataAtendimento;
        private Profissional profissional;
        private String anotacoes;
        private List<String> anexos;
    }

    @Data
    public static class Profissional {
        private String nome;
        private String area;
        private String registroProfissional;
    }
/*
    @Data
    public static class Responsavel {
        private String nome;
        private String parentesco;
        private String telefone;
    }*/
}
