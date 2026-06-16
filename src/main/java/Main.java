import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4w3d2");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

    }
}
