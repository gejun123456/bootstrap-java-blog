package com.generator;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/1/23
 * @Description
 */
public class ProdTemplateGenerator {
    public static final String FREEMARKER_SUFFIT = ".ftl";
    private static List<ReplacePair> replacePairs = Lists.newArrayList(
            new ReplacePair("/static/js/jquery-3.1.1.min.js", "//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"),
            new ReplacePair("/static/js/bootstrap.js", "//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"),
            new ReplacePair("/static/js/jquery.validate.min.js", "//cdn.bootcss.com/jquery-validate/1.15.1/jquery.validate.min.js"),
            new ReplacePair("/static/css/bootstrap.css", "//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"));
    private static String DEVTEMPLATEPATH = "templates";

    public static final String PRODTEMPLATEPATH = "prod-templates";

//    public static void main(String[] args) throws URISyntaxException, IOException {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        Path path
//                = Paths.get("src/main/resources/" + DEVTEMPLATEPATH);
//
//
//        Path parent = path.getParent();
//        Path prodPath = parent.resolve(PRODTEMPLATEPATH);
//
//        if (Files.exists(prodPath)) {
//            FileUtils.deleteDirectory(prodPath.toFile());
//        }
//
//        File file = path.toFile();
//        List<File> freemarkFiles = Lists.newArrayList();
//        addToFreeMarker(file, freemarkFiles);
//
//        System.out.println("the freemarker files size is:" + freemarkFiles.size());
//
//        for (File freemarkFile : freemarkFiles) {
//            System.out.println("scan with:" + freemarkFile.getName());
//            Path relativize = path.relativize(freemarkFile.toPath());
//            System.out.println("the relativize path is:" + relativize);
//            Path resolve = prodPath.resolve(relativize);
//            System.out.println("the resolved path is:" + resolve.toString());
//            String freemarkerString = IOUtils.toString(new FileInputStream(freemarkFile), Charset.defaultCharset());
//            System.out.println("read freemarker file:" + freemarkFile.getName() + " to String success");
//            String replaceString = freemarkerString;
//            for (ReplacePair replacePair : replacePairs) {
//                if (replaceString.contains(replacePair.getDevPath())) {
//                    System.out.println("find " + replacePair.getDevPath() + " from " + freemarkFile.getName() + " gonna replace it");
//                    replaceString = replaceString.replaceAll(replacePair.getDevPath(), replacePair.getCdnPath());
//                }
//            }
//            System.out.println("start write to file:" + resolve.toString());
//            File m = new File(resolve.toString());
//            m.getParentFile().mkdirs();
//            FileOutputStream output = new FileOutputStream(m);
//            IOUtils.write(replaceString, output, Charset.defaultCharset());
//            System.out.println("write to file:" + resolve.toString() + " success");
//        }
//        stopwatch.stop();
//        System.out.println("the time cost is:" + stopwatch.elapsed(TimeUnit.SECONDS));
//    }

    private static void addToFreeMarker(File file, List<File> freemarkFiles) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile() && file1.getName().endsWith(FREEMARKER_SUFFIT)) {
                    freemarkFiles.add(file1);
                } else if (file1.isDirectory()) {
                    addToFreeMarker(file1, freemarkFiles);
                }
            }
        }
    }

}
