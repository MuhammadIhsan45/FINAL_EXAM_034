/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FINAL_EXAM;

import com.example.FINAL_EXAM.exceptions.NonexistentEntityException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author hp
 */
//Nama : Muhammad Ihsan
//NIM : 20200140034

@RestController
@CrossOrigin
@ResponseBody
public class FinalexamController {
    
//deklarasi dari entity class
    Finalexam data = new Finalexam();
    //deklarasi dari jpa class
    FinalexamJpaController control = new FinalexamJpaController();
    
    //get mapping semua data / get all
    @GetMapping(value="/GET", produces = APPLICATION_JSON_VALUE)
    public List<Finalexam> getData(){
        List<Finalexam> buffer = new ArrayList<>();
        //mengambil semua data dari database
        buffer = control.findFinalexamEntities();
        //return objek dari buffer
        return buffer;
        
    }
    
    //post mapping / megirimkan data
    @PostMapping(value = "/POST", consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> datasend) throws JsonProcessingException{
        String feedback = "Do Nothing";
            //membuat objek baru
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(datasend.getBody(), Finalexam.class);
        try {
            //memasukkan data
            control.create(data);
            feedback = "Data" + " " + data.getJudul() + " " +"Tersimpan";
        //catch
        } catch (Exception error) {
            //memberikan output bahwa id sudah ada atau terpakai
            feedback = "Id Sudah Ada / Terpakai"; 
        }
            //return feedback
            return feedback; 
        
    }
    
    //put mapping
    @PutMapping(value = "/PUT", consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> datasend) throws JsonProcessingException{
        String feedback = "Do Nothing";
            //membuat objek baru
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(datasend.getBody(), Finalexam.class);
        try {
            //mencoba mengedit isi data / update isi data
            control.edit(data);
            //pesan berhasil edit / update data
            feedback = data.getJudul() + " " + "Telah DI Edit"; 
        } catch (Exception error) {
            //pesan gagal
            feedback ="Id Tidak Di Temukan";
        }
            //return feedback
            return feedback;
        
    }
    
    //delete mapping 
    @DeleteMapping(value = "/DELETE", consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> datasend) throws JsonProcessingException{
        String feedback = "Do Nothing";
            //membuat objek baru
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(datasend.getBody(), Finalexam.class);
        try {
            //mencoba mendelete data / menghapus data
            control.destroy(data.getId());
            //pesan berhasil mendelete / menghapus
            feedback ="Data" + " " +data.getJudul() + " " + "Ter Hapus" ;
        } catch (NonexistentEntityException error) {
            //pesan gagal / kesalahan
            feedback = error.getMessage();
        }
            return feedback;
        
    }
    
}
