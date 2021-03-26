package application.service;

import application.entity.Client;
import application.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findClientById(int id){
        return clientRepository.findClientByIdentifiant_client(id);
    }

}
