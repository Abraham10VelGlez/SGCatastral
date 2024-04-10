package edomex.gob.mx.sgcatastral.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FDocumento {
    private CommonsMultipartFile documento;

    public CommonsMultipartFile getDocumento() {
        return documento;
    }

    public void setDocumento(CommonsMultipartFile documento) {
        this.documento = documento;
    }

    
    
}
