package org.itmo_olesia.filesystemhelper.DAO.Repositories;

import org.itmo_olesia.filesystemhelper.DAO.Entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDocumentRepository extends JpaRepository<Document, Long> {

}
