/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.repository;

import ci.spring.domain.Beneficiaire;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sglo
 */
@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Integer>{
    
    public List<Beneficiaire> findBySoldeGreaterThanEqual(long pay);
    public Beneficiaire findByMatricule(Integer matricule);
    public Optional<List<Beneficiaire>> findBySoldeBetween(long montant1,long Montant2);
    
}
