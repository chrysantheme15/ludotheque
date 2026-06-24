package fr.eni.ludotheque.service;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.exceptions.DataNotFound;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExemplaireServiceImpl implements ExemplaireService{

    @NonNull
    private final ExemplaireRepository exemplaireRepository;

    @Autowired
    public ExemplaireServiceImpl(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    @Override
    public Exemplaire trouverParCodeBarre(String codeBarre) {
        Exemplaire exemplaire = exemplaireRepository.findByCodebarre(codeBarre);

        if (exemplaire == null) {
            throw new DataNotFound("Exemplaire", codeBarre);
        }

        return exemplaire;
    }
}
