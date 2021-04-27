package DAOs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Client;
import Model.Article;
import Model.Achat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClientDAO  {

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GI4");
        EntityManager em = emf.createEntityManager();
        return em;
    }
 
    public List<Article> selectAll() {
        EntityManager em = getEntityManager();
        List<Article> maListe = em.createNativeQuery("select e from Client e", Article.class)
                .getResultList();
        return maListe;
    }
    public List<Achat> selectAllbyClient(int id) {
        EntityManager em = getEntityManager();
        List<Achat> maListe =  em.createNativeQuery("SELECT e.CodeArticle,e.Designation,e.Prix,l.qteCde,c.dateCommande" +
                " from Lignescommande l,Article e,Commande c WHERE e.CodeArticle=l.code_article AND l.numCommande=c.numCommande AND c.id="+id, Client.class)
                .getResultList();
        return maListe;

    }

    public void create( Client obj )  {
        EntityManager em = getEntityManager();
        em.persist( obj );
        em.flush();
    }

    public void delete( Client client) {
        EntityManager em = getEntityManager();
        em.remove(  client );
        em.flush();

    }

    public Client update(Client obj) {
        EntityManager em = getEntityManager();
        em.persist(obj);
        em.flush();
        return obj;
    }

    public Client find(int id){
        EntityManager em = getEntityManager();
        return em.find( Client.class, id );
    }

    public Client find(String email){
        EntityManager em = getEntityManager();
        return em.find( Client.class, email );
    }

}