package buteco.model.repositories;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

public class CustomizerFactory {
    //executa uma vez ao carregar a classe / static compartilha por toda a aplicacao / final nao pode ser atribuido
    private static final EntityManagerFactory emf = buildEMF();

    private static EntityManagerFactory buildEMF() { //buildEMF monta a conexao
        Dotenv dotenv = Dotenv.load(); //load le o arqivo .env na raiz do projeto

        Properties props = new Properties(); //obj cahve e valor
        // cada setProperty adiciona as credencias lidas no .env
        props.setProperty("jakarta.persistence.jdbc.url", // endereço completo do banco
                "jdbc:postgresql://" + dotenv.get("DB_HOST") +
                ":" + dotenv.get("DB_PORT") +
                "/" + dotenv.get("DB_NAME")
        );
        props.setProperty("jakarta.persistence.jdbc.user", dotenv.get("DB_USER")); //usuario e senha
        props.setProperty("jakarta.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));
        props.setProperty("jakarta.persistence.jdbc.driver", "org.postgresql.Driver"); // e o drive (tudo isso foi puxado do hibernate.xfg)

        //carrega configuracoes nao sensiveis do xml
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addProperties(props); //adiciona as info do .env por cima do xml

        SessionFactory sf = config.buildSessionFactory(); //constroi o hibernate td configurado
        // converte o SessionFactory (API Hibernate) para o EntityManagerFactory (API Padrao Jpa)
        return sf.unwrap(EntityManagerFactory.class);
    }

    //abre sessao nova com o banco
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    //fecha a sessao com o banco(igual o scan)
    public static void fechar(){
        emf.close();
    }
}
