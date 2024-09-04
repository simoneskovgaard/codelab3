package dk.cph;


import dk.cph.config.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        HibernateConfig.getEntityManagerFactory(false);
    }
}