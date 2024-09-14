package org.itmo_olesia.filesystemhelper;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RequiredArgsConstructor
@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.itmo_olesia.filesystemhelper.DAO.Repositories")
public class FileSystemHelperApplication {

    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static final int NUM_THREADS = 4;

    public static void main(String[] args) throws IOException, InterruptedException {
        /*Самое место для написания коммента. Крч не варит башка уже,
         * но есть хорошая идея вытаскивать из html документов ссылки и добавлять именно их
         * в новый документ -  а практический смысл - либо прямая проверка на битые ссылки,
         *либо хотя бы возможности потом их устранять */ // топовый коммент))

        SpringApplication.run(FileSystemHelperApplication.class, args);
        /*

        long startTimeT = System.nanoTime();


        // Проверка вывода файлов с определённым расширением и ссылок в них (при html ищем по href-шаблону)
        // НЕ для html нужно написать другие способы нахождения ссылок, более поверхностные
        List<File> collectionOfFiles = new ArrayList<File>();
        String extension = ".html";
        OptionsService service = new OptionsService();
        collectionOfFiles = service.FindDocumentsByExtension(extension);
        service.getAllDocuments(collectionOfFiles);

        // Страдания над ссылками
        // ?будет ли подразумеваться рекурсивный обход поддиректорий в случае относительных
        // локальных ссылок?
        NetworkService networkService = new NetworkService();
        ParsingService parsingService = new ParsingService(networkService);
        Path startPath = Paths.get("C:\\Users\\Olesia\\Desktop\\MyHTML");
        parsingService.htmlReferenceTracker(startPath, "href=\"(.*?)\"");

        // Данные процессора
        Thread currentThread = Thread.currentThread();
        System.out.println("Current thread name: " + currentThread.getName());

        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Long freeMem = ((operatingSystemMXBean.getFreeMemorySize()) / 1024) / 1024;
        System.out.println(((operatingSystemMXBean.getFreeMemorySize()) / 1024) / 1024 + "MB");
        Long totalMem = ((operatingSystemMXBean.getTotalMemorySize()) / 1024) / 1024;
        System.out.println(totalMem - freeMem + "MB");

        service.DocumentsGenerating("C:\\Users\\Olesia\\Desktop\\Monitoring");


        // Проверка времени работы потоков в целом
        long startTime = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter("sync_output.txt")) {
            for (int i = 0; i < 10000; i++) {
                String data = "Processed data " + i + "\n";
                writer.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Synchronous write time: " + (endTime - startTime) + " ms");

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);




//        System.out.println(objectId);

//        // Запуск потоков обработки данных
//        for (int i = 0; i < 10000; i++) {
//            final int index = i;
//            executor.submit(() -> {
//                String data = "Processed data " + index + "\n";
//                try {
//                    queue.put(data);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            });
//        }
//
//        // Запуск потока записи данных
//        executor.submit(() -> {
//            try (FileWriter writer = new FileWriter("async_output.txt")) {
//                for (int i = 0; i < 10000; i++) {
//                    String data = queue.take();
//                    writer.write(data);
//                }
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        executor.shutdown();
//        executor.awaitTermination(10, java.util.concurrent.TimeUnit.MINUTES);
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("Asynchronous write time: " + (endTime - startTime) + " ms");
//
//        ParsingService service2 = new ParsingService();
//        service2.referenceTracker();
//
//    }
        // Test Network service


        //NetworkService networkService = new NetworkService();
        System.out.println("TOTAL CHECK");
        boolean info;
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt");
        System.out.println("Network service - local - " + info);
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://my.itmo.ru/");
        System.out.println("Network service - my.itmo.ru - " + info);
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://www.imperva.com/");
        System.out.println("Network service -imperva.com - " + info);
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://mvnrepository.com/artifact/com.google.cloud/google-cloud-translate/2.48.0");
        System.out.println("Network service - /mvnrepository.com - " + info);
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient");
        System.out.println("Network service - /mvnrepository.com - " + info);
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://developer.chrome.com/docs/devtools/network/reference?utm_source=devtools&hl=ru#provisional-headers");
        System.out.println("Network service - developer.chrome.com -" + info);
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "http://localhost:8080/v3/api-docs/public");
        System.out.println("Network service - localhost -" + info);
        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://www.mongodb.com/resources/products/compatibilities/docker");
        System.out.println("Network service  - www.mongodb.com -" + info);
        //info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath() , "https://www.gosuslugi.ru/");
        //System.out.println("###Network service - www.gosuslugi.ru - " + info);

        info =  networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://www.bspb.ru/retail/faq#ebs");
        System.out.println("Network service - www.bspb.ru - " + info);

        info = networkService.brokenLinks(new File(("C:\\Users\\Olesia\\Desktop\\Monitoring\\Test3.txt")).toPath(), "https://mail.google.com/mail/u/0/#settings/fwdandpop");
        System.out.println("&&Network service - mail.google.com - " + info);

        long endTimeT = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration); */

        // Проверка работоспособности почты
      //  EmailVerifierService emailVerifierService = new EmailVerifierService();
        //emailVerifierService.verifyEmail();

        // Многопоточка без спринга + телеграмм-бот + разбивка сервисов
        // ооп + шардирование по желанию + логи + отчеты
        // cron-операции - 1 - для пробега сделано, нужно к этому генерацию отчетов привязать *логирование отдельно*
        // проверь скорость с пулом соединений - ручной многопоточкой

        /*   Path directoryPath = Path.of(;
        File report = new File(directoryPath.toString() + "\\TotalReport\\report.txt");
        report.createNewFile();
        //mongoService.CreateNewDoc(Files.list(directoryPath));
        Files.walkFileTree(Paths.get(String.valueOf(startPath)), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Если это обычный файл, применяем поиск по регулярному выражению
                if (Files.isRegularFile(file) && Files.list(directoryPath).count() != 1) {
                    //searchLinkInFile(file, pattern);
                    FileOutputStream outputStream = new FileOutputStream(report);
                    byte[] buffer = (file.getFileName() + System.lineSeparator() + file.).toString().getBytes();
                    outputStream.write(buffer);
                    outputStream.close();


                }
                return FileVisitResult.CONTINUE;
            }
        });*/

    }
}
