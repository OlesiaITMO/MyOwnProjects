package org.itmo_olesia.filesystemhelper.Services.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Immutable;

@Data
@Schema(description = "DTO для представления отчета")
public class MongoDocumentDTO {

    private ObjectId id;

    private String name;

    @Immutable
    @Schema(description = "Дата создания документа, имутабельна", example = "27.08.2024 00:13")
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}")
    private String dataOfCreation;

    @Schema(description = "Дата обновления документа", example = "27.08.2024 00:13")
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}")
    private String dataOfUpdate;

    @Schema(description = "Путь к файлу")
    private String originalWay;

    @Schema(description = "Основной документ")
    private  String text;
}
