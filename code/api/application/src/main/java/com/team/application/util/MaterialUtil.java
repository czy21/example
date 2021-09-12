package com.team.application.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MaterialUtil {

    public static final String LINUX_PATH_SEPARATOR = "/";
    public static final String WINDOWS_PATH_SEPARATOR = "\\";

    public static final String URL_REGEX = "[a-zA-z]+://[^\\s]*";

    public static File getFile(String fileName, String... dir) throws IOException {
        List<String> dirs = Arrays.asList(dir);
        List<String> subDirs = new ArrayList<>();
        subDirs.add(fileName);
        if (dirs.size() > 1) {
            subDirs.addAll(0, dirs.subList(1, dirs.size()));
        }
        File file = Path.of(dir[0], subDirs.toArray(new String[]{})).toFile().getAbsoluteFile();
        FileUtils.forceMkdirParent(file);
        return file;
    }


    private static String transToUrlPath(Path path) {
        return ObjectUtils.isNotEmpty(path.toString())
                ? StringUtils.replace(StringUtils.replace(path.toFile().getPath(), WINDOWS_PATH_SEPARATOR, LINUX_PATH_SEPARATOR), "//", LINUX_PATH_SEPARATOR)
                : "";
    }

    public static String ofPath(String first, String... more) {
        List<String> other = Arrays.asList(more);
        try {
            URIBuilder uriBuilder = new URIBuilder(first);
            if (verifyUrl(first)) {
                return uriBuilder.setPath(transToUrlPath(Path.of(other.get(0), other.subList(1, other.size()).toArray(new String[]{})))).build().toString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return transToUrlPath(Path.of(first, more));
    }

    private static boolean verifyUrl(String url) {
        return Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE).matcher(url).matches();
    }


}
