package fr.eni.ludotheque.DAL;

import fr.eni.ludotheque.BO.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
