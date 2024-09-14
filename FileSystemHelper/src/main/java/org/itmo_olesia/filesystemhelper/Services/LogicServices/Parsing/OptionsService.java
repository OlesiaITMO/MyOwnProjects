//package org.itmo_olesia.filesystemhelper.Services.LogicServices.Parsing;
//
//import lombok.RequiredArgsConstructor;
//import org.itmo_olesia.filesystemhelper.Services.Service.MongoService;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Predicate;
//import java.util.stream.Stream;
//
//@RequiredArgsConstructor
//@Service
//public class OptionsService {
//    private  final MongoService mongoService;
//
//
//    // \\JavaSearchHTML
//    Path startPath = Paths.get("C:\\Users\\Olesia\\Desktop\\JavaSearchHTML"); // Начальная директория
//    //String extension = ".html";
////    public List<File> collectionOfFiles;
//
//    // имеет смысл сделать асинхронным (спринговый sheduling выплюнь)
//    // И не надейтесь на void + ссылки, это вам не плюсы
//    //@Async
//    public List<File> FindDocumentsByExtension(String extension){
//        List<File> collectionOfFiles = new ArrayList<>();
//        try (Stream<Path> paths = Files.walk(startPath)) {
//            paths.filter(Files::isRegularFile)
//                    .filter(hasExtension(extension))
//                    .forEach(file ->collectionOfFiles.add(file.toFile()));
//        } catch (Exception e) {
//           // а мне плевать (пока) // потом тоже
//        }
//        return collectionOfFiles;
//    }
//
//    private static Predicate<Path> hasExtension(String extension) {
//        return path -> path.toString().endsWith(extension);
//    }
//
//    public void getAllDocuments(List<File> collectionOfFiles) {
//        for(File doc : collectionOfFiles){
//            System.out.println(doc.getName());
//        }
//    }
//
////    public void rewrite(){
////
////    }
//
//    // так, пока приостановись и смотри - формирование отчетов МОНГИ мы делаем уже после формирования отчётов битых ссылок и тп
//    // иначе говоря - сначала консольный вывод начни красиво в файл записывать
//    // напиши условия по созданию и обработке этих файлов, интервалы, время...
//    // Мы и без того делаем итоговый отчёт, итоговый критический (timeout) - в монгу.
//    // его логику ты можешь и потом дописать, иначе с ботом и логированием не успеешь (переводом тоже)
//     public void DocumentsGenerating(String pathMonitoring) throws IOException {
//        Path directoryPath = Path.of(pathMonitoring);
//         if (Files.notExists(directoryPath)) {
//             Files.createDirectories(directoryPath);
//             System.out.println("Директория создана: " + directoryPath);
//         } else {
//             System.out.println("Директория уже существует: " + directoryPath);
//         }
//
//         /*
//         if (Files.list(directoryPath).count() >= 10){
//             File report = new File(directoryPath.toString() + "\\TotalReport\\report.txt");
//             report.createNewFile();
//            //mongoService.CreateNewDoc(Files.list(directoryPath));
//             Files.walkFileTree(Paths.get(String.valueOf(startPath)), new SimpleFileVisitor<Path>() {
//                 @Override
//                 public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                     // Если это обычный файл, применяем поиск по регулярному выражению
//                     if (Files.isRegularFile(file) && Files.list(directoryPath).count() != 1) {
//                         //searchLinkInFile(file, pattern);
//                         FileOutputStream outputStream = new FileOutputStream(report);
//                         byte[] buffer = (file.getFileName() + System.lineSeparator() + ).toString().getBytes();
//                         outputStream.write(buffer);
//                         outputStream.close();
//
//
//                     }
//                     return FileVisitResult.CONTINUE;
//                 }
//             });
//         }*/
//         /*
//         String fileName = "Test.txt";
//         Path toFilePath = Path.of(directoryPath + "\\" + fileName);
//         if (Files.notExists(toFilePath)) {
//             Files.createFile(toFilePath);
//             System.out.println("Файл создан: " + directoryPath);
//         } else {
//             System.out.println("Файл уже существует: " + directoryPath);
//         } */
//     }
//}
