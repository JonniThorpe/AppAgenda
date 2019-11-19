/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appagenda;

import entidades.Provincia;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
public class ConsultaProvincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppAgendaPU");
           EntityManager em = emf.createEntityManager();
           
           
        Query queryProvincias = em.createNamedQuery("Provincia.findAll");
        List<Provincia> listProvincias = queryProvincias.getResultList();
        
        Query queryProvinciaCadiz = 
                em.createNamedQuery("Provincia.findByNombre"); 
        queryProvinciaCadiz.setParameter("nombre", "Cádiz");
        List<Provincia> listProvinciasCadiz =
                queryProvinciaCadiz.getResultList();
        em.getTransaction().begin();
        
        for(Provincia ProvinciaCadiz:listProvinciasCadiz){
            System.out.println(ProvinciaCadiz.getId()+":"); 
            System.out.println(ProvinciaCadiz.getNombre()); 
        }
        
        //modificacion de objetos 
        for(Provincia provinciaCadiz : listProvinciasCadiz){
            provinciaCadiz.setCodigo("CA");
            em.merge(provinciaCadiz); 
        } 
        
        //eliminar un deter,omado objeto
        Provincia provinciaId15 = em.find(Provincia.class, 15);
                em.getTransaction().begin();
                if (provinciaId15 != null){
                    em.remove(provinciaId15);
                }else{ 
                    System.out.println("No hay ninguna provincia con ID=15");
                } 
                em.getTransaction().commit();
        
        em.getTransaction().commit();
        
       
    }
    
}
