/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.controller;

import ci.spring.domain.Beneficiaire;
import ci.spring.domain.Promo;
import ci.spring.domain.Versement;
import ci.spring.service.IBeneficiaireService;
import ci.spring.service.IPromoService;
import ci.spring.service.IVersementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sglo
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private IBeneficiaireService beneficiaireService;
    @Autowired
    private IPromoService promoService;
    @Autowired
    private IVersementService versementService;

    private List<Promo> listPromo;
    private List<Beneficiaire> listBeneficiaire;
    private List<Versement> listVersements;

//    @PreAuthorize("hasAnyRole('USER','ADMIN') ")
    @GetMapping(value = "/")
    public String index(Model model) {
//        System.out.println("************** passage dans index");
//        model.addAttribute("listBeneficiaire", this.beneficiaireService.getByPay(80000));
        listBeneficiaire = beneficiaireService.readAll();
        listPromo = promoService.readAll();
        listVersements = versementService.readAll();

        if (!listBeneficiaire.isEmpty()) {
            model.addAttribute("totalBenef", listBeneficiaire.size());
        } else {
            model.addAttribute("totalBenef", 0);
        }

        if (!listPromo.isEmpty()) {
            model.addAttribute("totalPromo", listPromo.size());
        } else {
            model.addAttribute("totalPromo", 0);
        }
        
        //model.addAttribute("totalRetarddeuxMois", beneficiaireService.readAllRetard2Mois(bene))
        model.addAttribute("derniersVers", versementService.get15DerniersVersements());
        model.addAttribute("totalCaisse", versementService.getMontantCaisse());
        model.addAttribute("totalMontantMois", versementService.getTotalVersementMoisEnCours());

        return "index/index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "index/login";
    }

    @GetMapping(value = "/enregistrement")
    public String afficherEnregistrement() {
        return "index/register";
    }

}
