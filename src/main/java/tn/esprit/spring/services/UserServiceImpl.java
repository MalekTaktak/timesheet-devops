package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public List<User> retrieveAllUsers() { 
		List<User> users = null; 
		try {
	
			l.debug("Getting list of users.");
			users = (List<User>) userRepository.findAll();
			  
			for (User user : users) {
				l.info("User "+ user.getId()+ " : "+ user.getFirstName()+" "+ user.getLastName()+ " born: "+ user.getDateNaissance()+ " role: "+ user.getRole());  
			} 
			l.debug("Finished getting list of users.");
		}catch (Exception e) {
			l.warn(e);
		}

		return users;
	}


	@Override
	public User addUser(User u) {
		l.debug("Adding user "+u.getId());
		User u_saved = userRepository.save(u); 
		l.debug("User "+u.getId()+ " added.");
		return u_saved; 
	}

	@Override 
	public User updateUser(User u) { 
		l.debug("Updating user "+u.getId());
		User u_saved = userRepository.save(u); 
		l.debug("User "+u.getId()+ " updated.");
		return u_saved; 
	}

	@Override
	public void deleteUser(String id) {
		l.debug("Deleting user "+id);
		userRepository.deleteById(Long.parseLong(id)); 
		l.debug("User "+id+" deleted.");
	}

	@Override
	public User retrieveUser(String id) {
		l.debug("Retrieving user "+id);
		//User u =  userRepository.findById(Long.parseLong(id)).orElse(null);
		User u =  userRepository.findById(Long.parseLong(id)).get(); 
		l.debug("User "+id+ " retrieved.");
		l.info("User "+ u.getId()+ " info : "+ u.getFirstName()+" "+ u.getLastName()+ " born: "+ u.getDateNaissance()+ " role: "+ u.getRole());  
			
		return u; 
	}

}
