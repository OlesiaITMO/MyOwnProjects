package org.itmo_olesia.filesystemhelper.DAO.Entities;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Immutable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document
public class MongoLinkDocument {

    private ObjectId id;

    @Indexed(unique = true)
    private String sourceFile;


    @Field
    private String originalWay;

    // final запрещён как противоречие принципам ORM, проверка на единственность в своём Setter-е
    @Immutable
    //@Field(name = "creation")
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}")
    private String dataDetected;

    @Field // нужно на случай, если изменена будет лишь одна ссылка
    private String duplicatesInFileCount;

    @Field
    @Pattern(regexp = "^(\\d+):(\\d+)$")
    private String locationInFile;

    @Field // important
    private String brokenLink;

    @Field
    private int responseCode;

    @Field
    private String linkState;


    //дата создания фиксируется единожды
    public void setDataDetected(String  dataDetected){
        if ( this.dataDetected == null)
            this.dataDetected = dataDetected;
        else
            throw new IllegalArgumentException("dataDetected is already set");
    }
}
