/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FINAL_EXAM;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "finalexam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finalexam.findAll", query = "SELECT f FROM Finalexam f"),
    @NamedQuery(name = "Finalexam.findById", query = "SELECT f FROM Finalexam f WHERE f.id = :id"),
    @NamedQuery(name = "Finalexam.findByNoSurat", query = "SELECT f FROM Finalexam f WHERE f.noSurat = :noSurat"),
    @NamedQuery(name = "Finalexam.findByJudul", query = "SELECT f FROM Finalexam f WHERE f.judul = :judul"),
    @NamedQuery(name = "Finalexam.findByTembusan", query = "SELECT f FROM Finalexam f WHERE f.tembusan = :tembusan"),
    @NamedQuery(name = "Finalexam.findByTimestamp", query = "SELECT f FROM Finalexam f WHERE f.timestamp = :timestamp")})
public class Finalexam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "no_surat")
    private String noSurat;
    @Column(name = "judul")
    private String judul;
    @Column(name = "tembusan")
    private String tembusan;
    @Lob
    @Column(name = "file")
    private byte[] file;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIME)
    private Date timestamp;

    public Finalexam() {
    }

    public Finalexam(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTembusan() {
        return tembusan;
    }

    public void setTembusan(String tembusan) {
        this.tembusan = tembusan;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Finalexam)) {
            return false;
        }
        Finalexam other = (Finalexam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.FINAL_EXAM.Finalexam[ id=" + id + " ]";
    }
    
}
