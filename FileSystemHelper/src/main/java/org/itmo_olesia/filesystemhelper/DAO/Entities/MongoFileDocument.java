package org.itmo_olesia.filesystemhelper.DAO.Entities;

import jakarta.persistence.Id;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Immutable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document // класть будем доки с битыми ссылками
public class MongoFileDocument {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    // final запрещён как противоречие принципам ORM, проверка на единственность в своём Setter-е
    @Immutable
    //@Field(name = "creation")
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}")
    private String dataOfCreation;

    //@Field(name = "last update")
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}")
    private String dataOfUpdate;

    @Field
    private String originalWay;

    @Field
    private String text;

    @Field
    private String BrokenLink;


    //дата создания фиксируется единожды
    public void setDataOfCreation(String  dataOfCreation){
        if ( this.dataOfCreation == null)
            this.dataOfCreation = dataOfCreation;
        else
            throw new IllegalArgumentException("dataOfCreation is already set");
    }
}
