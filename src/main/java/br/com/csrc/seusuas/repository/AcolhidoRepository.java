package br.com.csrc.seusuas.repository;

import br.com.csrc.seusuas.model.Acolhido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcolhidoRepository extends MongoRepository<Acolhido, String> {

    /**
     Podemos definir consultas específicas, se necessário.
     O MongoRepository ja providencia os seguintes métodos
     S save(S entity):
     Salva a entidade dada no banco de dados. Se a entidade já existe, ela será atualizada; se não existe, será criada.
     Optional<T> findById(ID id):
     Encontra um documento pelo seu id. Retorna um Optional que pode conter o documento encontrado ou vazio se não existir.
     boolean existsById(ID id):
     Verifica se um documento com o id fornecido existe no banco.
     List<T> findAll():
     Retorna uma lista com todos os documentos da coleção.
     List<T> findAllById(Iterable<ID> ids):
     Retorna uma lista com todos os documentos cujos ids estão presentes no iterable fornecido.
     long count():
     Retorna o número total de documentos na coleção.

     S save(S entity):
     Atualiza a entidade se já existir, ou cria uma nova se não existir. Mesma função mencionada no "Create".
     void deleteById(ID id):
     Remove o documento correspondente ao id fornecido.
     void delete(T entity):
     Remove o documento correspondente à entidade fornecida.
     void deleteAll(Iterable<? extends T> entities):
     Remove todos os documentos correspondentes à lista de entidades fornecida.
     void deleteAll():
     Remove todos os documentos da coleção.

     List<Acolhido> findByNome(String nome):
     Retorna uma lista de acolhidos cujo nome corresponde ao valor passado.
     List<Acolhido> findByCpf(String cpf):
     Retorna acolhidos pelo CPF.
     List<Acolhido> findByStatus(String status):
     Retorna todos os acolhidos com determinado status.

     Outros Métodos Úteis
     T insert(T entity):
     Insere um novo documento (somente se ele ainda não existe).
     List<S> saveAll(Iterable<S> entities):
     Salva ou atualiza múltiplas entidades de uma só vez.
     */

}
