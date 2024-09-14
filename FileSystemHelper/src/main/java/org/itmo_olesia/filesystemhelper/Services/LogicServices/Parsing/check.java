//package org.itmo_olesia.multithreading.Services.LogicServices.Parsing;
//
//import org.itmo_olesia.multithreading.Services.HardCodeParameters.Regexes;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class check {
//    // Метод для поиска по регулярному выражению в конкретном файле, формат любой, поиск по паттерну
//    protected void searchLinkInFile(Path file, Pattern pattern, String extension) throws IOException {
//        System.out.println("Searching in file: " + file);
//        Pattern patternCheck = Pattern.compile(Regexes.HTMLRegex.getRegex());
//        Pattern patternCheck2 = Pattern.compile(Regexes.TotalRegex.getRegex());
////
////        String fileName = file.getFileName().toString().toLowerCase().trim();
////        if (extension == Extensions.DOCX.toString() && fileName.endsWith("docx")) {
////            System.out.println("WORD search " + fileName);
////            FileInputStream fis = new FileInputStream(file.toFile());
////            XWPFDocument doc = new XWPFDocument(fis); // кидает IOEx
////
////            for (XWPFParagraph paragraph : doc.getParagraphs()) {
////                System.out.println(paragraph.getText());
////                Matcher matcherW = pattern.matcher(paragraph.getText());
////                System.out.println(paragraph.getText());
////                while (matcherW.find()) {
////                    System.out.println("W start");
////                    if ((matcherW.group(1) != "" && matcherW.groupCount() != 0)) {
////                        System.out.println("Found link: " + matcherW.group() + " in file " + file + " active = " + networkService.brokenLinks(file, matcherW.group()));
////                    }
////                    System.out.println();
////                }
////            }
////        }
//
//        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
//            String line;
//            // Чтение файла построчно
//            while ((line = br.readLine()) != null) {
//                // Применение регулярного выражения к каждой строке
//                Matcher matcher = pattern.matcher(line);
//                //System.out.println(line);
//                // Поиск совпадений
//                while (matcher.find()) {
//
//                    if ((matcher.group(1) != "" && matcher.groupCount() != 0)) {
//                        // а тут ты должна записывать битые ссылки в файлы директории
//                        // советую вынести логику обхода, больно частую, в отдельный сервис
//                        // + логику оформления отчёта по возможности тоже.
//                        // если делаешь разово отчёт, логика может быть инкапсулирована в один метод класса
//                        // иначе тоже можно, но будет разбиение методов на части файла (+ необходимо определение старта и конца)
//
//                        // изначально кладём в бд, потом данные из бд пишем в отчет по истечению тайм-аута (можно в push тоже прописать)
//                        // замененные ссылки уберем из бд только после отчета (в процессе)
//                        // с тебя сервис репортов и запись в монгу
//
//
//                        // Запись битых ссылок в файлы директории
//                        // Предполагается, что есть сервис для записи отчетов или логирования
//                        if (pattern.pattern().equals(patternCheck.pattern())) {
//                            System.out.println("Found link: " + matcher.group(1) + " in file " + file + " active = " + networkService.brokenLinks(file, matcher.group(1)));
//                            System.out.println("#1 "+ matcher.group(1));
//                            System.out.println("#2 instead  "+ matcher.group());
//                        } else if (pattern.pattern().equals(patternCheck2.pattern())) {
//                            System.out.println("Found link: " + matcher.group() + " in file " + file + " active = " + networkService.brokenLinks(file, matcher.group()));
//                            System.out.println("#2 "+ matcher.group());
//                            System.out.println("#3 instead  "+ matcher.group(1));
//                        }
//                        //System.out.println("Found link: " + matcher.group(1) + " in file " + file + " active = " + networkService.brokenLinks(file, matcher.group(1)));
//                        // Здесь добавьте логику для записи в базу данных или отчет
//                    }
//                    // Обработка ошибок при проверке ссылки
//                    //ex.printStackTrace();
//                    else {
//                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                    }
//                    //System.out.println("Found href: " + matcher.group(1) + " in file " + file + " active =" + networkService.brokenLinks(file, matcher.group(1)));
//                }
//            }
//        }
//    }
//}
