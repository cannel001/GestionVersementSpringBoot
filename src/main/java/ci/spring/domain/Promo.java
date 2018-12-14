/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author willi
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Promo implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "Ce champs ne doit pas etre vide")
    @Column(unique = true)
    private String libelle;
    
    @NotNull(message = "Ce champs ne doit pas etre vide")
    private Integer mensualite;
    
    @NotNull(message = "Ce champs ne doit pas etre vide")
    private Integer nbMois;
        
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation; 
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "promo",orphanRemoval = true)
    private List<Beneficiaire> beneficiaires; 
    
    //constructeur sans id
    public Promo(String libelle, Integer mensualite, Integer nbMois) {
        this.libelle = libelle;
        this.mensualite = mensualite;
        this.nbMois = nbMois;
    }
    
    
}
