package pl.coderslab.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.coderslab.entity.Address;
import pl.coderslab.entity.Client;
import pl.coderslab.entity.ContactPerson;
import pl.coderslab.service.ClientService;
import pl.coderslab.service.UserService;
@Component
public class ClientMapper {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ClientService clientService;
	
	public ClientDto toDto(Client client){
		ClientDto clientDto = new ClientDto();
		
		clientDto.setName(client.getName());
		clientDto.setStatus(client.getStatus());
		clientDto.setNip(client.getNip());
		clientDto.setContactFirstname(client.getContactPerson().getFirstname());
		clientDto.setContactLastname(client.getContactPerson().getLastname());
		clientDto.setContactEmail(client.getContactPerson().getEmail());
		clientDto.setContactPhone(client.getContactPerson().getPhone());
		clientDto.setCountry(client.getAddress().getCountry());
		clientDto.setRegion(client.getAddress().getRegion());
		clientDto.setCity(client.getAddress().getCity());
		clientDto.setStreet(client.getAddress().getStreet());
		clientDto.setUserEmail(client.getUser().getEmail());
		
        return clientDto;
		}
	
	public Client toEntity(ClientDto clientDto){
		Client client = new Client();
		
		client.setName(clientDto.getName());
		client.setStatus(clientDto.getStatus());
		client.setNip(clientDto.getNip());
		ContactPerson contactPerson = new ContactPerson();
		contactPerson.setFirstname(clientDto.getContactFirstname());
		contactPerson.setLastname(clientDto.getContactLastname());
		contactPerson.setEmail(clientDto.getContactEmail());
		contactPerson.setPhone(clientDto.getContactPhone());
		client.setContactPerson(contactPerson);
		Address address = new Address();
		address.setCountry(client.getAddress().getCountry());
		address.setRegion(client.getAddress().getRegion());
		address.setCity(client.getAddress().getCity());
		address.setStreet(client.getAddress().getStreet());
		
		client.setUser(userService.findByEmail(clientDto.getUserEmail()));
		clientService.saveClient(client);
		
        return client;
}
	

}
