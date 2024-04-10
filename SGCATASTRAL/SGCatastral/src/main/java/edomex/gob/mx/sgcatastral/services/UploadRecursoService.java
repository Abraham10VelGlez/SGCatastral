/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.services;

/**
 *
 * @author UAEM
 */

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadRecursoService {
        
    private CommonsMultipartFile imgRecurso1;
    private CommonsMultipartFile imgRecurso2;
    private CommonsMultipartFile imgRecurso3;
    private CommonsMultipartFile imgRecurso4;
    private CommonsMultipartFile imgRecurso5;
    private CommonsMultipartFile imgRecurso;
    private CommonsMultipartFile cv;

    public CommonsMultipartFile getImgRecurso1() {
        return imgRecurso1;
    }

    public void setImgRecurso1(CommonsMultipartFile imgRecurso1) {
        this.imgRecurso1 = imgRecurso1;
    }

    public CommonsMultipartFile getImgRecurso2() {
        return imgRecurso2;
    }

    public void setImgRecurso2(CommonsMultipartFile imgRecurso2) {
        this.imgRecurso2 = imgRecurso2;
    }

    public CommonsMultipartFile getImgRecurso3() {
        return imgRecurso3;
    }

    public void setImgRecurso3(CommonsMultipartFile imgRecurso3) {
        this.imgRecurso3 = imgRecurso3;
    }

    public CommonsMultipartFile getImgRecurso4() {
        return imgRecurso4;
    }

    public void setImgRecurso4(CommonsMultipartFile imgRecurso4) {
        this.imgRecurso4 = imgRecurso4;
    }

    public CommonsMultipartFile getImgRecurso5() {
        return imgRecurso5;
    }

    public void setImgRecurso5(CommonsMultipartFile imgRecurso5) {
        this.imgRecurso5 = imgRecurso5;
    }

    public CommonsMultipartFile getImgRecurso() {
        return imgRecurso;
    }

    public void setImgRecurso(CommonsMultipartFile imgRecurso) {
        this.imgRecurso = imgRecurso;
    }

    public CommonsMultipartFile getCv() {
        return cv;
    }

    public void setCv(CommonsMultipartFile cv) {
        this.cv = cv;
    }

    public void setImgRecurso1(String imgRecurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
