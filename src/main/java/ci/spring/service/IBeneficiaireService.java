/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service;

import ci.spring.domain.Beneficiaire;
import java.util.List;

/**
 *
 * @author sglo
 */
public interface IBeneficiaireService {

    public Beneficiaire readOneByMatricule(Integer matricule);

    public Integer genererMatricule();

    public boolean verifExistanceMatricule(Integer matricule);

    public Integer genererCode();

    public Beneficiaire create(Beneficiaire t);

    public List<Beneficiaire> readAll();

    public Beneficiaire readOne(Integer pk);

    public Beneficiaire update(Beneficiaire t);

    public Boolean delete(Integer pk);

    public Long getSoldeBeneficiaire(Beneficiaire b);

    public List<Beneficiaire> readAllRetard2Mois(long mensualite);

    public List<Beneficiaire> readAllRetard3Mois(long mensualite);

    public List<Beneficiaire> readAllRetard4Mois(long mensualite);

}
