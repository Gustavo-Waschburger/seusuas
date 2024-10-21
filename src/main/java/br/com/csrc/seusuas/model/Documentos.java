package br.com.csrc.seusuas.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "acolhidos")
public class Documentos {
    @Id
    private String id;
    private String rg;
    private String cpf;
    private String sus;
    private String cnh;
    private String nis;
    private String certidaoNascimneto;
}
