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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author sglo
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiaire implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeB;
    
    @Size(min = 3, message = "ce champs doit avoir plus de 3 caractères")
    @NotNull(message = "ce champ ne peux pas etre vide")
    private String nom;
    
    @NotNull
    private String prenom;
    
    private Long solde;
    
    private Integer matricule;
    
    @Email(message = "Veuillez entrer un email correct")
    private String email;
    
    private Integer tel;
        
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_id")
    private Promo promo;
    
    @Transient
    private Long montantVerse;
    
    @Transient
    private Long montantRestant;
    
    @OneToMany(mappedBy = "beneficiaire",fetch = FetchType.LAZY,cascade = CascadeType.MERGE,orphanRemoval = true)
    private List<Versement> versements;
    
    //constructeur sans id
    public Beneficiaire(String nom, String prenom, Integer matricule, String email, Integer tel, Promo promo) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.email = email;
        this.tel = tel;
        this.promo = promo;
    }
    
    //les getters des transients
    public Long getMontantVerse(){
        
        long montantTt=0;
        if(versements!=null && !versements.isEmpty()){
            for (Versement versement : versements) {
                montantTt+=versement.getMontant();
            }
        }
        return montantTt;
    }
    
    public Long getMontantRestant(){
        return solde-getMontantVerse();
    }
    

}
