/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service;

import ci.spring.domain.Promo;
import java.util.List;

/**
 *
 * @author willi
 */
public interface IPromoService {
    
    public Promo create(Promo t);

    public List<Promo> readAll();

    public Promo readOne(Integer pk);

    public Promo update(Promo t);

    public Boolean delete(Integer pk);
    
}
