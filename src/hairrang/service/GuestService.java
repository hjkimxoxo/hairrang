package hairrang.service;

import hairrang.dao.GuestDao;
import hairrang.dao.impl.GuestDaoImpl;
import hairrang.dto.Guest;

public class GuestService {
	
	private GuestDao dao = GuestDaoImpl.getInstance();
	
	public void addGuest(Guest guest) {
		dao.insertGuest(guest);
	}
	
	public void updateGuest(Guest guest) {
		dao.updateGuest(guest);
	}
	
	public void deleteGuest(Guest guest) {
		dao.deleteGuest(guest);
	}

}
