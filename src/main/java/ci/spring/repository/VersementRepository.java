/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.repository;

import ci.spring.domain.Versement;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author willi
 */
public interface VersementRepository extends JpaRepository<Versement, Integer> {

    public List<Versement> findByDateVersementBetween(Date dateDebut, Date dateFin);
    public Optional<List<Versement>> findFirst15ByOrderByIdDesc();

}
