package br.com.thyagoribeiro.ecommerce.rest.contracts;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class NovasImagensRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagem;

    public List<MultipartFile> getImagem() {
        return imagem;
    }

    public void setImagem(List<MultipartFile> imagem) {
        this.imagem = imagem;
    }
}
