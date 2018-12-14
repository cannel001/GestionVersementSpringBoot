/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service.impl;

import ci.spring.domain.Promo;
import ci.spring.repository.PromoRepository;
import ci.spring.service.IPromoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author willi
 */
@Service
public class PromoService implements IPromoService {

    //les proprietes
    @Autowired
    private PromoRepository promoRepository;

    @Override
    public Promo create(Promo t) {

        if (t != null) {
            t.setDateCreation(new Date());
            t.setDateUpdate(new Date());
            return promoRepository.save(t);
        }
        return null;

    }

    @Override
    public List<Promo> readAll() {

        return promoRepository.findAll();

    }

    @Override
    public Promo readOne(Integer pk) {

        if (pk > 0) {
            return promoRepository.getOne(pk);
        }
        return null;

    }

    @Override
    public Promo update(Promo t) {

        if (t != null) {
            t.setDateUpdate(new Date());
            return promoRepository.save(t);
        }
        return null;

    }

    @Override
    public Boolean delete(Integer pk) {

        if (pk > 0) {
            Promo promo = promoRepository.getOne(pk);
            if (promo != null) {
                promo.setDateUpdate(new Date());
                return promoRepository.save(promo) != null;
            }
        }
        return false;
    }

}
