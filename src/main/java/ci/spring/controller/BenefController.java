/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.controller;

import ci.spring.domain.Beneficiaire;
import ci.spring.domain.Promo;
import ci.spring.service.IBeneficiaireService;
import ci.spring.service.IPromoService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sglo
 */
@Controller
@RequestMapping(value = "/benef")
public class BenefController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BenefController.class);

    @Autowired
    private IBeneficiaireService beneficiaireService;
    @Autowired
    private IPromoService promoService;

    private Beneficiaire beneficiaire;
    private Promo promo;
    private List<Beneficiaire> listBeneficiaire;

////    @PreAuthorize("hasRole('ADMIN') ")
//    @PostMapping(value = "/update")
//    public String updateBenef(Beneficiaire b, Model model) {
//        //model.addAttribute("beneficiaire", this.beneficiaireService.getById(b.getCodeB()));
//        return "ajoutform";
//    }
//
////    @PreAuthorize("hasAnyRole('USER','ADMIN') ")
//    @GetMapping(value = "/afficher/{id}")
//    public String afficherUnBenef(@PathVariable Integer id, Model model) {
//        // model.addAttribute("benefAffiche", this.beneficiaireService.getById(id));
//        return "afficher-un";
//    }
//
//    @GetMapping(value = "/liste-non-a-jour")
//    public String listNonAJour(Model model) {
//        // model.addAttribute("listBeneficiaire", this.beneficiaireService.getByPay(80000));
//        return "index";
//    }
    @GetMapping(value = "/formajout")
    public String afficherFormAjout(Model model) {
        model.addAttribute("promos", promoService.readAll());
        model.addAttribute("beneficiaire", new Beneficiaire());
        return "beneficiaire/profilNouveauBeneficiaire";
    }

    @PostMapping(value = "/ajouter")
    public String ajouter(Beneficiaire beneficiaire, @RequestParam("promo_id") Integer id, Model model) {
        //recuperation du beneficaire
        this.beneficiaire = beneficiaire;
        //recuperation de la promo
        this.promo = promoService.readOne(id);
        //affectation de la promo au beneficiaire
        this.beneficiaire.setPromo(this.promo);
        //affectation du matricule
        this.beneficiaire.setMatricule(beneficiaireService.genererMatricule());
        //mise a jour du solde
        this.beneficiaire.setSolde(beneficiaireService.getSoldeBeneficiaire(this.beneficiaire));
        //enregistrement
        beneficiaireService.create(this.beneficiaire);
        //redirection
        return "redirect:/benef/afficherlister";
    }

    @GetMapping(value = "/afficherlister")
    public String afficherLister(Model model) {

        listBeneficiaire = beneficiaireService.readAll();

        if (!listBeneficiaire.isEmpty()) {
            model.addAttribute("totalBenef", listBeneficiaire.size());
        } else {
            model.addAttribute("totalBenef", 0);
        }

        model.addAttribute("beneficiaires", beneficiaireService.readAll());
        return "beneficiaire/listebeneficiaire";
    }

    @GetMapping(value = "/afficherformconsult/{id}")
    public String afficherFormConsult(@PathVariable Integer id, Model model) {
        if (id > 0) {
            //Beneficiaire beneficiaire1=beneficiaireService.readOne(id);
            model.addAttribute("beneficiaire", beneficiaireService.readOne(id));
            //model.addAttribute("montantVerse", beneficiaireService.getMontantVerse(beneficiaire1));
            //model.addAttribute("montantRestant", beneficiaireService.getMontantRestant(beneficiaire1));
            model.addAttribute("promos", promoService.readAll());
        }
        return "beneficiaire/profilBeneficiaire";
    }

}
