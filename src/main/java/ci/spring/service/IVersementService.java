/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service;

import ci.spring.domain.Beneficiaire;
import ci.spring.domain.Versement;
import java.util.List;

/**
 *
 * @author willi
 */
public interface IVersementService {
    
    public Long getMontantCaisse();
    
    public Long getTotalVersementMoisEnCours();
    
    public List<Versement> get15DerniersVersements();
    
    public Versement create(Versement t);

    public List<Versement> readAll();

    public Versement readOne(Integer pk);

    public Versement update(Versement t);

    public Boolean delete(Integer pk);
            
}
