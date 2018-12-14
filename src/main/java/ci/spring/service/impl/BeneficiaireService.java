/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service.impl;

import ci.spring.domain.Beneficiaire;
import ci.spring.domain.Versement;
import ci.spring.repository.BeneficiaireRepository;
import ci.spring.service.IBeneficiaireService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sglo
 */
@Service
public class BeneficiaireService implements IBeneficiaireService {

    //les proprietes
    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    @Autowired
    private VersementService versementService;

    @Override
    public Beneficiaire readOneByMatricule(Integer matricule) {

        System.out.println("************ AVANT D'eNTREE********************");

        if (!"".equals(matricule)) {
            System.out.println("************ MATRICULE EST ********************" + matricule);
            return beneficiaireRepository.findByMatricule(matricule);
        }
        return null;
    }

    @Override
    public Beneficiaire create(Beneficiaire t) {

        if (t != null) {
            t.setDateCreation(new Date());
            t.setDateUpdate(new Date());
            return beneficiaireRepository.save(t);
        }
        return null;

    }

    @Override
    public List<Beneficiaire> readAll() {
        return beneficiaireRepository.findAll();
    }

    @Override
    public Beneficiaire readOne(Integer pk) {

        if (pk > 0) {
            return beneficiaireRepository.getOne(pk);
        }
        return null;

    }

    @Override
    public Beneficiaire update(Beneficiaire t) {

        if (t != null) {
            t.setDateUpdate(new Date());
            return beneficiaireRepository.save(t);
        }
        return null;

    }

    @Override
    public Boolean delete(Integer pk) {

        if (pk > 0) {
            Beneficiaire beneficiaire = beneficiaireRepository.getOne(pk);
            if (beneficiaire != null) {
                beneficiaire.setDateUpdate(new Date());
                return beneficiaireRepository.save(beneficiaire) != null;
            }
        }
        return false;

    }

    @Override
    public Integer genererMatricule() {

        Integer code = genererCode();
        while (verifExistanceMatricule(code)) {
            code = genererCode();
        }
        return code;

    }

    @Override
    public boolean verifExistanceMatricule(Integer matricule) {

        if (readOneByMatricule(matricule) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Integer genererCode() {

        Random aleatoire = new Random();
        String code = "0123456789";
        String codeGenere = "";
        int index;

        for (int i = 0; i < 8; i++) {
            index = aleatoire.nextInt(code.length());
            codeGenere += code.charAt(index);
        }
        return Integer.parseInt(codeGenere);

    }

    @Override
    public Long getSoldeBeneficiaire(Beneficiaire b) {

        //recuperation des infos concernant la promo
        int nbMois = b.getPromo().getNbMois();
        int montant = b.getPromo().getMensualite();
        long montantTt = nbMois * montant;
        long montantVersement = b.getMontantVerse();

        long resteAPayer = 0;

        if (montantVersement > 0) {
            resteAPayer = montantTt - montantVersement;
        } else {
            resteAPayer = montantTt;
        }

        return resteAPayer;
    }

    @Override
    public List<Beneficiaire> readAllRetard2Mois(long mensualite) {

        if (mensualite > 0) {
            return beneficiaireRepository.findBySoldeBetween(mensualite * 2, mensualite * 3).orElse(null);
        }
        return null;
    }

    @Override
    public List<Beneficiaire> readAllRetard3Mois(long mensualite) {

        if (mensualite > 0) {
            return beneficiaireRepository.findBySoldeBetween(mensualite * 3, mensualite * 4).orElse(null);
        }
        return null;

    }

    @Override
    public List<Beneficiaire> readAllRetard4Mois(long mensualite) {

        if (mensualite > 0) {
            return beneficiaireRepository.findBySoldeGreaterThanEqual(mensualite * 4);
        }
        return null;
    }

}
