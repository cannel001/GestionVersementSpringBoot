/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.controller;

import ci.spring.domain.Beneficiaire;
import ci.spring.domain.Versement;
import ci.spring.service.IBeneficiaireService;
import ci.spring.service.IVersementService;
import java.util.List;
import javax.websocket.server.PathParam;
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
 * @author willi
 */
@Controller
@RequestMapping(value = "/versement")
public class VersementController {

    //les proprietes
    @Autowired
    private IVersementService versementService;
    @Autowired
    private IBeneficiaireService beneficiaireService;

    private List<Versement> listVersement;

    @GetMapping(value = "/ajoutform")
    public String formVersement(Model model) {
        model.addAttribute("versement", new Versement());
        return "ajoutformversement";
    }

//    @PostMapping(value = "/save")
//    public String saveVersement(Versement versement, Model model, HttpServletRequest request) {
//
//        if (beneficiaireService.readOneByMatricule(versement.getBeneficiaire().getMatricule()) != null) {
//            versement.setBeneficiaire(beneficiaireService.readOneByMatricule(versement.getBeneficiaire().getMatricule()));
//            versementService.create(versement);
//            return "redirect:/versement/liste";
//        }
//        return "ajoutformversement";
//    }
//
//    @GetMapping(value = "/liste")
//    public String listVersement(Model model) {
//        model.addAttribute("liste", versementService.readAll());
//        return "liste-versement";
//    }
//
//    @GetMapping(value = "/consulterversement/{id}")
//    public String consulter(@PathVariable Integer id, Model model) {
//
//        Versement versement = versementService.readOne(id);
//        model.addAttribute("versement", versementService.readOne(id));
//        return "afficher_un_versement";
//    }
//
//    @GetMapping(value = "/editerversement/{id}")
//    public String editer(@PathVariable Integer id, Model model) {
//        model.addAttribute("versement", versementService.readOne(id));
//
//        return "ajoutformversement";
//    }
    @GetMapping(value = "/formajout")
    public String afficherFormAjout(Model model) {
        model.addAttribute("versement", new Versement());
        return "versement/nouveauversement";
    }

    @PostMapping(value = "/ajouter")
    public String ajouter(Versement versement, Model model) {

        if (beneficiaireService.readOne(versement.getBeneficiaire().getMatricule()) != null) {
            Beneficiaire beneficiaire=beneficiaireService.readOneByMatricule(versement.getBeneficiaire().getMatricule());
            beneficiaire.setSolde(beneficiaire.getSolde()-versement.getMontant());
            versement.setBeneficiaire(beneficiaire);
            //enregistrement du versement
            versementService.create(versement);
            //mise a jour du beneficiaire
            beneficiaireService.create(beneficiaire);
        }

        return "redirect:/versement/afficherlister";
    }

    @GetMapping(value = "/afficherlister")
    public String afficherLister(Model model) {

        listVersement = versementService.readAll();

        if (!listVersement.isEmpty()) {
            model.addAttribute("totalVersement", listVersement.size());
        } else {
            model.addAttribute("totalVersement", 0);
        }

        model.addAttribute("versements", versementService.readAll());
        return "versement/listeversements";
    }

    @GetMapping(value = "/afficherformconsult/{id}")
    public String afficherFormConsult(@PathVariable Integer id, Model model) {
        model.addAttribute("versement", versementService.readOne(id));
        return "versement/consultversement";
    }

}
