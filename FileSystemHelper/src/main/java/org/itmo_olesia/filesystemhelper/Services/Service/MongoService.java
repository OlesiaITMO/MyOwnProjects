package org.itmo_olesia.filesystemhelper.Services.Service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.itmo_olesia.filesystemhelper.DAO.Entities.MongoFileDocument;
import org.itmo_olesia.filesystemhelper.DAO.Repositories.IMongoDocumentRepository;
import org.itmo_olesia.filesystemhelper.Services.DTO.MongoDocumentDTO;
import org.itmo_olesia.filesystemhelper.Services.Mappers.MongoDocumentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MongoService {
    private final IMongoDocumentRepository repository;
    private final MongoDocumentMapper mapper = new MongoDocumentMapper();


    @Transactional
    public MongoDocumentDTO CreateNewDoc(MongoDocumentDTO documentDTO) {
        MongoFileDocument document = new MongoFileDocument();
//        document.setId(UUID.randomUUID());
//        document.setId();
        document.setName(documentDTO.getName());
        document.setDataOfCreation(documentDTO.getDataOfCreation());
        document.setDataOfUpdate(documentDTO.getDataOfUpdate());
        repository.save(document);
        return mapper.ConvertToDTO(document);
    }


    @Transactional
    public MongoDocumentDTO findDocById(ObjectId id) {
        try {
            mapper.ConvertToDTO(repository.findById(id).orElseThrow());
        }
        catch (Exception e) {
            return null;
        }
        return mapper.ConvertToDTO(repository.findById(id).orElseThrow());
    }

//    @Transactional
//    public MongoDocumentDTO updateDoc(ObjectId id, int sentenceIndex) {
//        MongoDocument document;
//        try {
//            document = repository.findById(id).orElseThrow();
//        }
//        catch (Exception e) {
//            return null;
//        }
//        // логика + автоматическая смена даты
//
//    }


    // допиши потом удаление с вспылвающим окном и вариативностью
    // пока хватит и простого удаления
    @Transactional
    public boolean deleteDoc(ObjectId id) {
        if (this.findDocById(id) != null) {
            repository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
