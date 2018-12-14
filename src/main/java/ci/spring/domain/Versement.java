/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author willi
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Versement implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer montant;
    
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateVersement;
    
    private String mois;
    
    private Integer annee;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date dateCreation;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Beneficiaire beneficiaire;
    
    //consructeur par sans id
    public Versement(Integer montant, Date dateVersement, Beneficiaire beneficiaire) {
        this.montant = montant;
        this.dateVersement = dateVersement;
        this.beneficiaire = beneficiaire;
    }
   
}
