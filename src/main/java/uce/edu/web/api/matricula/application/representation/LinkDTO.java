package uce.edu.web.api.matricula.application.representation;

public class LinkDTO {
    public String href;
    public String rel;

    public LinkDTO() {
        
    }

    public LinkDTO(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }
}
