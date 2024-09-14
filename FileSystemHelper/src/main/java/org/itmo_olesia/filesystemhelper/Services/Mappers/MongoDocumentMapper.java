package org.itmo_olesia.filesystemhelper.Services.Mappers;

import org.itmo_olesia.filesystemhelper.DAO.Entities.MongoFileDocument;
import org.itmo_olesia.filesystemhelper.Services.DTO.MongoDocumentDTO;

public class MongoDocumentMapper {
    public MongoDocumentDTO ConvertToDTO(MongoFileDocument document) {
        MongoDocumentDTO dto = new MongoDocumentDTO();
        dto.setId(document.getId());
        dto.setName(document.getName());
        dto.setDataOfCreation(document.getDataOfCreation());
        dto.setDataOfCreation(document.getDataOfCreation());
        return dto;
    }
}
