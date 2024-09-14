package org.itmo_olesia.filesystemhelper.ControllersAndPush.Controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;
import org.itmo_olesia.filesystemhelper.Services.DTO.MongoDocumentDTO;
import org.itmo_olesia.filesystemhelper.Services.Service.MongoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongo")
@RequiredArgsConstructor
public class MongoController {
    private final MongoService mongoService;

    @Operation(summary = "Создание сущности отчета",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")))
    @ApiResponse(responseCode = "201", description = "Успешный ответ",
           // content = @Content(schema = @Schema(implementation = MongoDocumentDTO.class)),
            content = { @Content(mediaType = "application/json"), @Content(schema = @Schema(implementation = MongoDocumentDTO.class))} )
    @PostMapping( "/postDoc")
    public ResponseEntity<MongoDocumentDTO> postDoc(@RequestBody @Valid MongoDocumentDTO mongoDocumentDTO) {
        mongoService.CreateNewDoc(mongoDocumentDTO);
        return new ResponseEntity<>(mongoDocumentDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Поиск отчёта",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "string")),
                parameters = { @io.swagger.v3.oas.annotations.Parameter(
                name = "id",
                description = "Идентификатор в формате строки",
                required = true,
                in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
                schema = @Schema(type = "string"),
                examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "66ceb7f69fbfa93b28355ad7")
        )})
    @ApiResponse(responseCode = "201", description = "Успешный ответ",
            // content = @Content(schema = @Schema(implementation = MongoDocumentDTO.class)),
            content = { @Content(mediaType = "application/json"), @Content(schema = @Schema(implementation = MongoDocumentDTO.class))} )
    @GetMapping("/getById/{id}")
    public ResponseEntity<MongoDocumentDTO> getById(@PathVariable ObjectId id) {
        MongoDocumentDTO MongoDocumentDTO = mongoService.findDocById(id);
        if (MongoDocumentDTO != null) {
            return ResponseEntity.ok().body(MongoDocumentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Обновление отчёта",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")))
    @ApiResponse(responseCode = "201", description = "Успешный ответ",
            // content = @Content(schema = @Schema(implementation = MongoDocumentDTO.class)),
            content = { @Content(mediaType = "application/json"), @Content(schema = @Schema(implementation = MongoDocumentDTO.class))} )
    @PatchMapping("/updateDoc/{id}")
    public ResponseEntity<MongoDocumentDTO> updateDoc(@PathVariable ObjectId id) {
        // добавь логику обновления
        MongoDocumentDTO MongoDocumentDTO = mongoService.findDocById(id);
        if (MongoDocumentDTO != null) {
            //логика

            return ResponseEntity.ok().body(MongoDocumentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Перевод отчёта",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")))
    @ApiResponse(responseCode = "201", description = "Успешный ответ",
            // content = @Content(schema = @Schema(implementation = MongoDocumentDTO.class)),
            content = { @Content(mediaType = "application/json"), @Content(schema = @Schema(implementation = MongoDocumentDTO.class))} )
    @PatchMapping("/translateDoc/{id}")
    public ResponseEntity<MongoDocumentDTO> translateDoc(@PathVariable ObjectId id) {
        // добавь логику обновления
        MongoDocumentDTO mongoDocumentDTO = mongoService.findDocById(id);
        if (mongoDocumentDTO != null) {
            //логика
//            translateDoc(mongoDocumentDTO);
            return ResponseEntity.ok().body(mongoDocumentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    @Operation(summary = "Удаление отчёта (in progress)")
//    @ApiResponse
    @DeleteMapping("/deleteDocById/{id}")
    public ResponseEntity<String> deleteDocById(@PathVariable ObjectId id) {
        boolean flag = mongoService.deleteDoc(id);
        if (flag)
            return ResponseEntity.ok("Doc successfully deleted");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doc with id " + id + "not found");
    }
}