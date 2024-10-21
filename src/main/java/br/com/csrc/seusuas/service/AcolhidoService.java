package br.com.csrc.seusuas.service;

import br.com.csrc.seusuas.config.MongoClientConnection;
import br.com.csrc.seusuas.model.Acolhido;
import br.com.csrc.seusuas.repository.AcolhidoRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class AcolhidoService {
    private final static MongoClientConnection mongoClientConnection= new MongoClientConnection();
    private final MongoCollection<Document> collection;
    private final AcolhidoRepository acolhidoRepository;

    @Autowired//(required = true)
    public AcolhidoService(MongoDatabase mongoDatabase, AcolhidoRepository acolhidoRepository){//MongoClient mongoClient, @Value("${dbName}") String dbName,@Value("${colleection.acolhido}") String collectionName) {
        MongoClient mongoClient = null;

        MongoDatabase database = mongoClientConnection.getDatabase(mongoClient);
        this.acolhidoRepository = acolhidoRepository; // database = mongoClient.getDatabase(dbName);
        this.collection = mongoDatabase.getCollection("acolhido");//.getCollection(collectionName);
    }

    public void createAcolhido(String nome, String dataNascimento, String cpf, Document endereco, Document contato, String dataEntrada, String status) {

        Document acolhido = new Document("nome", nome)
                .append("dataNascimento", dataNascimento)
                .append("cpf", cpf)
                .append("endereco", endereco)
                .append("contato", contato)
                .append("dataEntrada", dataEntrada)
                .append("status", status);

        collection.insertOne(acolhido);

        System.out.println("Acolhido inserido: " + acolhido.toJson());
    }

    public Document readAcolhido(String nome) {
        return collection.find(eq("nome", nome)).first();
    }

    public void updateAcolhido(String nome, String novoStatus) {
        collection.updateOne(eq("nome", nome), new Document("$set", new Document("status", novoStatus)));
        System.out.println("Acolhido atualizado: status mudado para " + novoStatus);
    }

    public void deleteAcolhido(String nome) {
        collection.deleteOne(eq("nome", nome));
        System.out.println("Acolhido deletado: " + nome);
    }

    public List<Acolhido> listarTodos() {
        return acolhidoRepository.findAll();
    }

    public Acolhido salvar(Acolhido acolhido) {
        return acolhidoRepository.save(acolhido);
    }

    public Acolhido buscarPorId(String id) {
        return acolhidoRepository.findById(id).orElse(null);
    }

    public void deletar(String id) {
        acolhidoRepository.deleteById(id);
    }
}
