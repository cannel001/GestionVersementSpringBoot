/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.controller;

import ci.spring.domain.Promo;
import ci.spring.repository.PromoRepository;
import ci.spring.service.impl.PromoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author willi
 */
@Controller
@RequestMapping(value = "/promo")
public class PromoController {

    //les proprietes
    @Autowired
    private PromoService promoService;

    private List<Promo> listPromo;

    @GetMapping(value = "/formajout")
    public String afficherFormAjout(Model model) {
        model.addAttribute("promo", new Promo());
        return "promo/nouvellepromo";
    }

    @PostMapping(value = "/ajouter")
    public String save(Promo promo, Model model) {
        promoService.create(promo);
        return "redirect:/promo/afficherlister";
    }

    @GetMapping(value = "/afficherlister")
    public String afficherLister(Model model) {

        listPromo = promoService.readAll();

        if (!listPromo.isEmpty()) {
            model.addAttribute("totalPromo", listPromo.size());
        } else {
            model.addAttribute("totalPromo", 0);
        }

        model.addAttribute("promos", promoService.readAll());
        return "promo/listepromotion";
    }

    @GetMapping(value = "/afficherformconsult/{id}")
    public String afficherFormConsult(@PathVariable Integer id, Model model) {
        if (id > 0) {
            Promo promo = promoService.readOne(id);
            model.addAttribute("promo", promo);
        }
        return "promo/consulpromo";
    }

}
