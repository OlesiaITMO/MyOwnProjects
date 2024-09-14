package org.itmo_olesia.filesystemhelper.DAO.Repositories;

import org.bson.types.ObjectId;
import org.itmo_olesia.filesystemhelper.DAO.Entities.MongoFileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMongoDocumentRepository extends MongoRepository<MongoFileDocument, ObjectId>{
    MongoFileDocument findByName(String name);
}
