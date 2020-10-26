package br.com.thyagoribeiro.ecommerce.http;

import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadFile {

    public static List<URI> uploadFiles(List<MultipartFile> files) {
        List<URI> paths = new ArrayList<>();
        files.forEach(file -> {
            try {
                paths.add(new URI(URLEncoder.encode("https://bucket.com/ecommerce/resources?path=" + UUID.randomUUID() + "-" +  file.getOriginalFilename(), "utf-8")));
            } catch (URISyntaxException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return paths;
    }

}
