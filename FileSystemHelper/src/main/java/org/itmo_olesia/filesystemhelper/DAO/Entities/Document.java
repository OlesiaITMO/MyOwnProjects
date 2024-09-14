package org.itmo_olesia.filesystemhelper.DAO.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity


public class Document {
    @Id
    Long id;

    @Column
    String name;

    // final запрещён как противоречие принципам ORM, проверка на единственность в своём Setter-е
    @Column
    String dataOfCreation;

    @Column
    String dataOfUpdate;


    //дата создания фиксируется единожды
    public void setDataOfCreation(String dataOfCreation){
        if ( this.dataOfCreation == null)
            this.dataOfCreation = dataOfCreation;
        else
            throw new IllegalArgumentException("dataOfCreation is already set");
    }
}
