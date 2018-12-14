/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service.impl;

import ci.spring.domain.Beneficiaire;
import ci.spring.domain.Versement;
import ci.spring.repository.VersementRepository;
import ci.spring.service.IVersementService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author willi
 */
@Service
public class VersementService implements IVersementService {

    //les proprietes
    @Autowired
    private VersementRepository versementRepository;

    @Override
    public Versement create(Versement t) {

        if (t != null) {
            t.setDateCreation(new Date());
            t.setDateUpdate(new Date());
            return versementRepository.save(t);
        }
        return null;

    }

    @Override
    public List<Versement> readAll() {

        return versementRepository.findAll();
    }

    @Override
    public Versement readOne(Integer pk) {

        if (pk > 0) {
            return versementRepository.getOne(pk);
        }
        return null;

    }

    @Override
    public Versement update(Versement t) {

        if (t != null) {
            t.setDateUpdate(new Date());
            return versementRepository.save(t);
        }
        return null;

    }

    @Override
    public Boolean delete(Integer pk) {

        if (pk > 0) {
            Versement versement = versementRepository.getOne(pk);
            if (versement != null) {
                versement.setDateUpdate(new Date());
                return versementRepository.save(versement) != null;
            }
        }
        return false;

    }

    @Override
    public Long getMontantCaisse() {

        List<Versement> maListe = readAll();
        long total = 0;

        if (!maListe.isEmpty()) {
            for (Versement versement : maListe) {
                total += versement.getMontant();
            }
        }
        return total;
    }

    @Override
    public Long getTotalVersementMoisEnCours() {

        //recuperation du premier et dernier jour du mois en cours
        String annee = new SimpleDateFormat("yyyy").format(new Date());
        String mois = new SimpleDateFormat("MM").format(new Date());

        YearMonth moisYearMonth = YearMonth.of(Integer.valueOf(annee), Integer.valueOf(mois));

        LocalDate premierJ = moisYearMonth.atDay(1);
        LocalDate dernierJ = moisYearMonth.atEndOfMonth();

        Date premierJour = java.sql.Date.valueOf(premierJ);
        Date dernierJour = java.sql.Date.valueOf(dernierJ);

        List<Versement> maListe = versementRepository.findByDateVersementBetween(premierJour, dernierJour);

        long total = 0;

        if (!maListe.isEmpty()) {
            if (!maListe.isEmpty()) {
                for (Versement versement : maListe) {
                    total += versement.getMontant();
                }
            }
        }

        return total;
    }

    @Override
    public List<Versement> get15DerniersVersements() {

//        List<Versement> maList = readAll();
//        List<Versement> autreListe = new LinkedList<>();
//        int cpt = 0;
//
//        if (!maList.isEmpty()) {
//            if (maList.size() <= 15) {
//                return maList;
//            } else {
//                cpt = maList.size() - 1;
//                for (int i = 0; i < 16; i++) {
//                    autreListe.add(maList.get(cpt));
//                    cpt--;
//                }
//                return autreListe;
//            }
//        }
        return versementRepository.findFirst15ByOrderByIdDesc().orElse(null);
    }


}
